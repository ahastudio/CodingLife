require 'sinatra'
require 'sinatra/json'
require 'sinatra/cross_origin'
require 'json'

scene_nodes = [
  { id: 1, body: 'First', edges: [{ label: '', ids: [2, 3] }] },
  { id: 2, body: 'Second', edges: [{ label: '', ids: [] }] },
  { id: 3, body: 'Third', edges: [{ label: '', ids: [] }] }
]

configure do
  enable :cross_origin
end

get '/scene' do
  json scene_nodes: scene_nodes
end

post '/scene' do
  data = JSON.parse(request.body.read)
  scene_nodes = data['scene_nodes']
  ''
end

options '/scene' do
  ''
end
