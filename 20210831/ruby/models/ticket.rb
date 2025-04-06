# frozen_string_literal: true

require 'ulid'

require_relative './comment'

# Ticket model
class Ticket
  attr_reader :id, :title, :description, :status, :comments,
              :created_at, :updated_at

  def initialize(title:, description:, id: nil, status: nil)
    @id = id || ULID.generate
    @title = title
    @description = description
    @status = status || 'open'
    @comments = []
    @created_at = Time.now
    @updated_at = Time.now
  end

  def status=(value)
    raise ArgumentError, 'Invalid status' unless %w[open closed].include?(value)

    @status = value
    @updated_at = Time.now
  end

  def destroy
    self.class.all.delete(self)
  end

  def add_comment(content:)
    comment = Comment.new(ticket_id: @id, content: content)
    @comments << comment
    comment
  end

  def remove_comment(id:)
    comment = @comments.find { |i| i.id == id }
    return unless comment

    @comments.delete(comment)

    comment
  end

  def to_json(*_args)
    {
      id: @id,
      title: @title,
      description: @description,
      status: @status,
      comments: @comments
    }.to_json
  end

  def self.all
    @all ||= []
  end

  def self.create(title:, description:)
    ticket = Ticket.new(title: title, description: description)
    @all << ticket
    ticket
  end

  def self.find(id)
    @all.find { |ticket| ticket.id == id }
  end
end
