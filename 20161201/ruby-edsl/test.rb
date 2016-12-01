class HttpApp
  def request(method, path, params = {})
    @params = params.clone
    instance_eval(&router[[method, path]])
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

puts '-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-'

app = App.new
puts app.request(:get, '/')
puts app.request(:get, '/say')
puts app.request(:post, '/say', message: 'New Message')
puts app.request(:get, '/say')
