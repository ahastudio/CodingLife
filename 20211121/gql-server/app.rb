# frozen_string_literal: true

require 'sinatra'
require 'sinatra/cors'
require 'sinatra/json'
require 'rack/contrib'

# App class
class App < Sinatra::Base
  use Rack::JSONBodyParser

  register Sinatra::Cors

  set :allow_origin, 'http://localhost:8080'
  set :allow_methods, 'GET,HEAD,POST'
  set :allow_headers, 'content-type,if-modified-since'
  set :expose_headers, 'location,link'

  get '/' do
    'Hello, world!'
  end

  post '/graphql' do
    varibales = params[:variables]
    varibales = JSON.parse(varibales) if varibales.is_a?(String)
    json Schema.execute(
      params[:query],
      variables: varibales,
      context: {}
    )
  end
end
