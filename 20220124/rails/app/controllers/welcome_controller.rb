class WelcomeController < ApplicationController
  def index
    @project = Project.first
    @jobs = @project.jobs
    @tickets = @project.tickets
  end
end
