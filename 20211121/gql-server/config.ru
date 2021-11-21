# frozen_string_literal: true

require 'active_support'

ActiveSupport::Dependencies.autoload_paths = [
  'models/',
  'graphql/'
]

require './app'

run App
