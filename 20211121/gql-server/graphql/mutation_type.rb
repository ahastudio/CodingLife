# frozen_string_literal: true

require 'graphql'

# Mutation root
class MutationType < GraphQL::Schema::Object
  description 'The mutation root of this schema'

  field :addTask, mutation: Mutations::AddTask
end
