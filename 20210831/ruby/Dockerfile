FROM ruby:3.4.2

WORKDIR /app

RUN gem update --system && \
    gem install bundler --no-document

COPY Gemfile Gemfile.lock /app/

RUN bundle config set --local without 'development test' && \
    bundle install

COPY . /app/

ENV RACK_ENV=production

EXPOSE 4567

CMD ["rackup", "-p", "4567"]
