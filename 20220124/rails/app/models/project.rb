class Project < ApplicationRecord
  has_many :tasks
  has_many :jobs, through: :tasks
  has_many :tickets
end
