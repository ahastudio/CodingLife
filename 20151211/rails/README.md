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
