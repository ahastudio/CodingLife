module Api
  class PostsController < ApplicationController
    rescue_from ActiveRecord::RecordNotFound, with: :not_found

    respond_to :json

    def index
      @posts = Post.all
      respond_with(@posts)
    end

    def show
      @post = Post.find(params[:id])
      respond_with(@post)
    end

    private

    def not_found
      respond_with({}, status: :not_found)
    end
  end
end
