# frozen_string_literal: true

require 'ulid'

# Comment model
class Comment
  attr_reader :id, :ticket_id, :content, :created_at, :updated_at

  def initialize(ticket_id:, content:, id: nil)
    @id = id || ULID.generate
    @ticket_id = ticket_id
    @content = content
    @created_at = Time.now
    @updated_at = Time.now
  end

  def to_json(*_args)
    {
      id: @id,
      ticket_id: @ticket_id,
      content: @content
    }.to_json
  end
end
