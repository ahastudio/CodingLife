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

group :development, :test do
  gem 'rspec-rails', '~> 3.0'
end

:wq

$ bundle install --without production
$ bundle binstubs rspec-core
$ bin/rails generate rspec:install
$ bin/rspec
```


