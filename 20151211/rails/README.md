# API Demo

https://github.com/rails/rails

```
$ gem update --system
$ gem install bundler --no-document
$ gem install rails --no-document
$ rails new api-demo
$ cd api-demo
```

https://github.com/rspec/rspec-rails

```
$ rm -rf test
$ vi Gemfile

G
o

group :development, :test do
  gem 'rspec-rails', '~> 3.0'
end

:wq

$ bundle install --without production
$ bundle binstubs rspec-core
$ bin/rails generate rspec:install
$ bin/rspec
```

https://github.com/basecamp/pow

```
$ curl get.pow.cx | sh
```

https://github.com/Rodreegez/powder

```
$ gem install powder --no-document
$ powder link
$ powder open
```

https://github.com/guard/guard-rspec
```
$ vi Gemfile

G
o

gem 'guard-rspec', require: false, group: :development

:wq

$ bundle
$ bundle binstubs guard
$ bin/guard init rspec
$ bin/guard
```

```
$ bin/rails generate scaffold post title:string body:text
$ bin/rake db:migrate RAILS_ENV=test
```

```
$ bin/rake db:migrate
$ open http://api-demo.dev/posts
```

```
$ vi app/models/post.rb
```

```ruby
class Post < ActiveRecord::Base
  validates :title, presence: true
  validates :body, presence: true
end
```

```
$ bin/rails console

> Post.all
> Post.first
> Post.last
> post = _
> post.title
> post.title = '조...조은 글이다'
> post.save!
> post.title = ''
> post.save!
> exit
```

```
$ bin/rails generate controller api/posts --no-helper --no-assets
$ vi spec/controllers/api/posts_controller_spec.rb
```

```ruby
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
```

```
$ vi config/routes.rb
```

```ruby
  namespace :api do
    resources :posts, only: :index
  end
```

```
$ touch spec/controllers/api/posts_controller_spec.rb
$ vi app/controllers/api/posts_controller.rb
```

```ruby
class Api::PostsController < ApplicationController
  def index
    @posts = Post.all
    render json: @posts
  end
end
```

```
$ open http://api-demo.dev/api/posts.json
```
