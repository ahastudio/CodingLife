class Api::PostsController < ApplicationController
  def index
    @posts = Post.all
    render json: @posts
  end
end
