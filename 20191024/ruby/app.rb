# frozen_string_literal: true

require 'sinatra'
require 'sinatra/reloader' if development?

set :port, 3000

get '/' do
  erb :index
end
