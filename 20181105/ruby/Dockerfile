FROM ruby:2.5.3

RUN apt-get update && \
    apt-get upgrade -y && \
    rm -rf /var/lib/apt/lists/*

RUN gem install rubygems-update --no-document && \
    update_rubygems && \
    gem update --system && \
    gem install bundler --no-document

WORKDIR /tmp

ADD Gemfile /tmp/Gemfile
ADD Gemfile.lock /tmp/Gemfile.lock

RUN bundle install --binstubs --without development:test

WORKDIR /app

ADD . /app

CMD bundle exec ruby server.rb

EXPOSE 32180
