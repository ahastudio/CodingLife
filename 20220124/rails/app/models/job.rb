class Job < ApplicationRecord
  belongs_to :task
  has_many :frames
  has_many :tickets, as: :target
end
