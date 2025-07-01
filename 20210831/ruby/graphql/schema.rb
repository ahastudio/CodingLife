# frozen_string_literal: true

require 'graphql'

require_relative './queries'
require_relative './mutations'
require_relative './subscriptions'

# Schema
class Schema < GraphQL::Schema
  query QueryType
  mutation MutationType
  subscription SubscriptionType
end
