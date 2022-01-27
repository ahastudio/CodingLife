class Task < ApplicationRecord
  belongs_to :project
  has_many :frames
  has_many :jobs
end
