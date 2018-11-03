require 'rails_helper'

RSpec.describe Api::PostsController, type: :controller do
  describe 'GET #index' do
    before(:each) do
      Post.create!(title: '제목', body: '조...조은 글이다')
    end

    it 'renders all posts' do
      get :index, format: :json
      expect(response).to be_success
      expect(response.body).to match(/제목/)
      expect(response.body).to be_include('"body":"조...조은 글이다"')
    end
  end

  describe 'GET #index' do
    context 'with existed post' do
      let(:post_id) { @post.id.to_s }

      before(:each) do
        @post = Post.create!(title: '제목', body: '조...조은 글이다')
      end

      it 'renders the posts' do
        get :show, id: post_id, format: :json
        expect(response).to have_http_status(:success)
        expect(response.body).to match(/제목/)
        expect(response.body).to be_include('"body":"조...조은 글이다"')
      end
    end

    context 'with not existed post' do
      let(:post_id) { '37' }

      before(:each) do
        allow(Post).to receive(:find).with(post_id)
                                     .and_raise(ActiveRecord::RecordNotFound)
      end

      it 'responses 404' do
        get :show, id: post_id, format: :json
        expect(response).to have_http_status(:not_found)
      end
    end
  end
end
