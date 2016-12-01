require 'rack'

class Parameters
  def initialize(hash = {})
    @hash = hash
  end

  def [](key)
    @hash[key.to_s] || @hash[key.to_sym]
  end

  def inspect
    @hash.inspect
  end
end

class HttpApp
  def call(env)
    request = Rack::Request.new(env)
    method = request.request_method.downcase.to_sym
    block = router[[method, request.path]]
    return [404, {}, []] if block.nil?
    @params = Parameters.new(request.params)
    body = instance_eval(&block)
    [200, { 'Content-Type' => 'text/html' }, [body.to_s]]
  end

  protected

  def self.get(path, &block)
    router[[:get, path]] = block
  end

  def self.post(path, &block)
    router[[:post, path]] = block
  end

  def self.router
    @@router ||= {}
  end

  def router
    @@router ||= {}
  end

  def params
    @params ||= {}
  end
end

class App < HttpApp
  def initialize
    @message = 'Test'
  end

  get '/' do
    'Hello, world!'
  end

  get '/say' do
    @message
  end

  post '/say' do
    @message = params[:message]
    :created
  end
end

app = App.new
Rack::Handler::WEBrick.run app
