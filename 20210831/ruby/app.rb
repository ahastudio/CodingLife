# frozen_string_literal: true

require 'sinatra'
require 'sinatra/cors'

require_relative './models/ticket'

require_relative './graphql/schema'

Ticket.all

set :bind, '0.0.0.0'

set :allow_origin, '*'
set :allow_methods, 'GET,HEAD,POST,PUT,PATCH,DELETE,OPTIONS'
set :allow_headers, 'content-type,if-modified-since'
set :expose_headers, 'location,link'

before do
  content_type :json
end

get '/' do
  content_type :text
  'Hello, world!'
end

get '/tickets' do
  puts '▶ GET /tickets'

  if params[:delay]
    puts 'Delaying response...'
    sleep(1.2)
  end

  tickets = Ticket.all

  { tickets: tickets }.to_json
end

get '/tickets/:id' do
  puts "▶ GET /tickets/#{params[:id]}"

  ticket = Ticket.find(params[:id])

  unless ticket
    status 404
    return { error: 'Ticket not found' }.to_json
  end

  ticket.to_json
end

post '/tickets' do
  puts '▶ POST /tickets'

  data = JSON.parse(request.body.read)

  ticket = Ticket.create(title: data['title'], description: data['description'])

  status 201
  ticket.to_json
end

patch '/tickets/:id' do
  puts "▶ PATCH /tickets/#{params[:id]}"

  ticket = Ticket.find(params[:id])

  unless ticket
    status 404
    return { error: 'Ticket not found' }.to_json
  end

  data = JSON.parse(request.body.read)

  ticket.status = data['status']

  ticket.to_json
end

delete '/tickets/:id' do
  puts "▶ DELETE /tickets/#{params[:id]}"

  ticket = Ticket.find(params[:id])

  unless ticket
    status 404
    return { error: 'Ticket not found' }.to_json
  end

  ticket.destroy

  ticket.to_json
end

get '/tickets/:ticket_id/comments' do
  puts "▶ GET /tickets/#{params[:ticket_id]}/comments"

  ticket = Ticket.find(params[:ticket_id])

  unless ticket
    status 404
    return { error: 'Ticket not found' }.to_json
  end

  ticket.comments.to_json
end

post '/tickets/:ticket_id/comments' do
  puts "▶ POST /tickets/#{params[:ticket_id]}/comments"

  ticket = Ticket.find(params[:ticket_id])

  unless ticket
    status 404
    return { error: 'Ticket not found' }.to_json
  end

  data = JSON.parse(request.body.read)

  comment = ticket.add_comment(content: data['content'])

  status 201
  comment.to_json
end

delete '/tickets/:ticket_id/comments/:id' do
  puts "▶ DELETE /tickets/#{params[:ticket_id]}/comments/#{params[:id]}"

  ticket = Ticket.find(params[:ticket_id])

  unless ticket
    status 404
    return { error: 'Ticket not found' }.to_json
  end

  comment = ticket.remove_comment(id: params[:id])

  comment.to_json
end

post '/graphql' do
  puts '▶ POST /graphql (GraphQL)'

  data = JSON.parse(request.body.read)

  result = Schema.execute(
    data['query'],
    variables: data['variables'],
    context: {}
  )

  result.to_json
end
