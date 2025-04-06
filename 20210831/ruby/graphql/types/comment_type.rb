# frozen_string_literal: true

# Comments type
class CommentType < GraphQL::Schema::Object
  field :id, ID, null: false
  field :content, String, null: false
end
