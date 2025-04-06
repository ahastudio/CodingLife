# frozen_string_literal: true

require_relative './comment_type'

# TicketType
class TicketType < GraphQL::Schema::Object
  field :id, ID, null: false
  field :title, String, null: false
  field :description, String, null: false
  field :status, String, null: false
  field :comments, [CommentType], null: false
end
