# frozen_string_literal: true

require 'graphql'

module Mutations
  # mutation addTask
  class AddTask < Mutations::BaseMutation
    description 'addTask'

    argument :title, String, required: true

    field :id, ID, null: false
    field :title, String, null: false
    field :status, String, null: false

    def resolve(title:)
      id = Task.all.map(&:id).max.to_i + 1
      task = Task.new(id: id, title: title, status: 'todo')
      Task.add(task)
      puts "* add a new task - #{task.inspect}"
      {
        id: task.id,
        title: task.title,
        status: task.status
      }
    end
  end
end
