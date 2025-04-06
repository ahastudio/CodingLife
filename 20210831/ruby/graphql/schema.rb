# frozen_string_literal: true

require 'graphql'

require_relative './queries'
require_relative './mutations'

# Schema
class Schema < GraphQL::Schema
  query QueryType
  mutation MutationType
end
