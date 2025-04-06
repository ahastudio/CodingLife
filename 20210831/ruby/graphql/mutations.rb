# frozen_string_literal: true

require 'graphql'

require_relative './types/ticket_type'

# createTicket mutation
class CreateTicketMutation < GraphQL::Schema::Mutation
  null false

  argument :title, String, required: true
  argument :description, String, required: true

  type TicketType

  def resolve(title:, description:)
    sleep(0.5)
    Ticket.create(title: title, description: description)
  end
end

# Mutation type
class MutationType < GraphQL::Schema::Object
  field :createTicket, mutation: CreateTicketMutation
end
