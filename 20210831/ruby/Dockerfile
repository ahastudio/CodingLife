FROM ruby:3.4.2

WORKDIR /app

RUN gem update --system && \
    gem install bundler --no-document

COPY Gemfile Gemfile.lock /app/

RUN bundle config set --local without 'development test' && \
    bundle install

COPY . /app/

ENV APP_ENV=development

CMD ["ruby", "app.rb"]
