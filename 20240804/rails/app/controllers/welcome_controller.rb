require 'httparty'

class WelcomeController < ApplicationController
  skip_before_action :verify_authenticity_token

  def index
  end

  def upload_file
    uploaded_file = params[:file]

    response = HTTParty.post(
      'http://localhost:3000/attachments',
      # Use `multipart: true` to send files. But it's just optional.
      # multipart: true,
      body: {
        attachment: {
          # Don't use `uploaded_file.tempfile` here!
          uploaded_file: uploaded_file
        }
      }
    )

    render json: {
      ok: response.ok?,
      code: response.code,
      data: response
    }
  end
end
