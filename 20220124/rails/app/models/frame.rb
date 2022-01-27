class Frame < ApplicationRecord
  belongs_to :task
  belongs_to :job, optional: true
  has_many :tickets, as: :target
end
