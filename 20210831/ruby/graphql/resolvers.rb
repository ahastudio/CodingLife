# frozen_string_literal: true

require 'graphql'

require_relative './types/ticket_type'

# tickets query resolver
class TicketsResolver < GraphQL::Schema::Resolver
  type [TicketType], null: false

  def resolve
    ::Ticket.all
  end
end
