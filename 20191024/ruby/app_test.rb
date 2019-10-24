# frozen_string_literal: true

require_relative 'app'

require 'minitest/autorun'
require 'rack/test'

class AppTet < Minitest::Test
  include Rack::Test::Methods

  def app
    Sinatra::Application
  end

  def test_home
    get '/'
    assert last_response.ok?
    assert_includes last_response.body, '서울특별시 인기 공동구매'
  end
end
