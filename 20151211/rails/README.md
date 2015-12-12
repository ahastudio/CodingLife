# API Demo

## Rails 프로젝트 만들기

https://github.com/rails/rails

```
$ gem update --system
$ gem install bundler --no-document
$ gem install rails --no-document
$ rails new api-demo
$ cd api-demo
```

## RSpec 사용하기

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

## Pow 서버 사용하기

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

## Guard 사용하기

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

## RESTful MVC 찍어내기

https://en.wikipedia.org/wiki/Representational_state_transfer

https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller

```
$ bin/rails generate scaffold post title:string body:text
$ bin/rake db:migrate RAILS_ENV=test
```

```
$ bin/rake db:migrate
$ open http://api-demo.dev/posts
```

## Validation

```
$ vi app/models/post.rb
```

```ruby
class Post < ActiveRecord::Base
  validates :title, presence: true
  validates :body, presence: true
end
```

## Console

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

## API 만들기

```
$ bin/rails generate controller api/posts --no-helper --no-assets
$ vi spec/controllers/api/posts_controller_spec.rb
```

### Test-first

```ruby
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
end
```

### Implements

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
module Api
  class PostsController < ApplicationController
    def index
      @posts = Post.all
      render json: @posts
    end
  end
end
```

### HTTP 확인

```
$ open http://api-demo.dev/api/posts.json
$ curl http://api-demo.dev/api/posts.json
```

https://github.com/jkbrzt/httpie

```
$ brew install httpie
$ http GET api-demo.dev/api/posts.json
```

## Coding Standard

http://www.extremeprogramming.org/rules/standards.html

https://github.com/dalzony/ruby-style-guide/blob/master/README-koKR.md

### RuboCop

https://github.com/yujinakayama/guard-rubocop

```
$ vi Gemfile

G
o

gem 'guard-rubocop', group: :development

:wq

$ bundle
$ bin/guard init rubocop
```

엄청난 고통!

## 빠져나갈 구멍 만들기

https://github.com/bbatsov/rubocop/wiki/Automatic-Corrections

https://github.com/bbatsov/rubocop/blob/master/config/default.yml

https://github.com/bbatsov/rubocop/blob/master/config/enabled.yml

```
$ vi .rubocop.yml
```

```yaml
Documentation:
  Enabled: false

AllCops:
  Exclude:
    - 'Gemfile'
    - 'Rakefile'
    - 'Guardfile'
    - 'bin/**/*'
    - 'db/**/*'
    - 'config/**/*'
    - 'spec/**/*'
```

### Refactoring

http://refactoring.com/

https://github.com/bbatsov/rubocop/issues/494

https://github.com/plataformatec/responders

```
$ vi Gemfile

G
o

gem 'responders', '~> 2.0'

:wq

$ bundle
$ bin/rails generate responders:install
$ vi app/controllers/application_controller.rb

:%s/"/'/g
:wq

$ vi app/controllers/posts_controller.rb
```

```ruby
  respond_to :html, :json
```

```ruby
  # POST /posts
  # POST /posts.json
  def create
    @post = Post.new(post_params)
    @post.save
    respond_with(@post)
  end

  # PATCH/PUT /posts/1
  # PATCH/PUT /posts/1.json
  def update
    @post.update(post_params)
    respond_with(@post)
  end

  # DELETE /posts/1
  # DELETE /posts/1.json
  def destroy
    @post.destroy
    respond_with(@post)
  end
```

## Quality
### Best Practices

http://rails-bestpractices.com/

https://github.com/railsbp/rails_best_practices

```
$ vi Gemfile

group :development do
  gem 'guard-rspec', require: false
  gem 'guard-rubocop'
  gem 'rails_best_practices'
end

$ bundle
$ bundle binstubs rails_best_practices
$ bin/rails_best_practices
```

우리에게 이게 필요한가요?

http://no-smok.net/nsmk/DoWeNeedThisPage

http://c2.com/cgi/wiki?YouArentGonnaNeedIt

http://api.rubyonrails.org/classes/ActionView/Helpers/TextHelper.html#method-i-simple_format

```
$ vi spec/helpers/posts_helper_spec.rb
$ vi app/helpers/posts_helper.rb
```

자나 깨나 Refactoring!

http://c2.com/cgi/wiki?CategoryRefactoring

http://rails-bestpractices.com/posts/2010/07/24/replace-instance-variable-with-local-variable/

```
$ vi app/views/posts/_form.html.erb

:%s/@post/post/g

$ vi app/views/posts/new.html.erb

<%= render 'form', post: @post %>

$ vi app/views/post/edit.html.erb
```

### 정적 분석

https://www.codacy.com/

## 지속적인 통합

https://shippable.com/

```
$ vi shippable.yml
```

## DevOps

https://en.wikipedia.org/wiki/DevOps

http://continuousdelivery.com/

https://www.docker.com/

## AND...

https://twitter.com/ahastudio/status/674934955915390981

## END...

https://ahastudio.com/
