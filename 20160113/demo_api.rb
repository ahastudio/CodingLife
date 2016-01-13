require 'grape'

module Demo
  class API < Grape::API
    version 'v1', using: :header, vendor: 'demo'
    format :json
    prefix :api

    resource :posts do
      desc 'Return all posts.'
      get do
        []
      end
    end
  end
end
