# frozen_string_literal: true

# Task model
class Task
  attr_accessor :id, :title, :status

  @tasks = []

  def initialize(id:, title:, status:)
    @id = id
    @title = title
    @status = status
  end

  def self.all
    @tasks
  end

  def self.add(task)
    @tasks << task
  end
end
