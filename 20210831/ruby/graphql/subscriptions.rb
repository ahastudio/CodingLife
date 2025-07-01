# frozen_string_literal: true

require 'graphql'

# commentCreated subscription
class CommentCreatedSubscription < GraphQL::Schema::Subscription
  field :ticketId, ID, null: false

  def subscribe
    {
      ticketId: ticket_id
    }
  end
end

# Subscription type
class SubscriptionType < GraphQL::Schema::Object
  field :commentCreated, subscription: CommentCreatedSubscription
end
