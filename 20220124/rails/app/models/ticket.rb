class Ticket < ApplicationRecord
  belongs_to :project
  belongs_to :target, polymorphic: true, optional: true

  validates :body, presence: true
end
