# frozen_string_literal: true

module Types
  class TaskType < Types::BaseObject
    description 'A task (to-do)'

    field :id, ID, null: false
    field :title, String, null: false
    field :status, String, null: false
  end
end
