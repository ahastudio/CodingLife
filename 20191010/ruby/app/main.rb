require 'opal'

if `location.search`.empty?
  require 'main-basic'
else
  require 'main-objects'
end
