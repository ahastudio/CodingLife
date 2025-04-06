# frozen_string_literal: true

require 'graphql'

require_relative './resolvers'

# Query type
class QueryType < GraphQL::Schema::Object
  description 'The query root of this schema'

  field :tickets, resolver: TicketsResolver
end
