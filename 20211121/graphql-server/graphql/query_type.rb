# frozen_string_literal: true

require 'graphql'

# Query root
class QueryType < GraphQL::Schema::Object
  description 'The query root of this schema'

  field :tasks, [Types::TaskType], null: true do
    description 'Find tasks'
    argument :search, String, required: false
  end

  def tasks(search: nil)
    return [] if search.present?

    Task.all
  end
end
