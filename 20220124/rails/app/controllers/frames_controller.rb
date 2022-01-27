class FramesController < ApplicationController
  def show
    @job = Job.find(params[:job_id])
    @frame = @job.frames.find(params[:id])
  end
end
