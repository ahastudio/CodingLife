require 'rails_helper'

RSpec.describe Api::PostsController, type: :controller do
  describe 'GET #index' do
    before(:each) do
      Post.create!(title: '제목', body: '조...조은 글이다')
    end

    it 'renders all posts' do
      get :index, foramt: :json
      expect(response).to be_success
      expect(response.body).to match(/제목/)
      expect(response.body).to be_include('"body":"조...조은 글이다"')
    end
  end
end
