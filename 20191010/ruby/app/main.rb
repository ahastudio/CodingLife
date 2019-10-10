require 'opal'

if `location.pathname`[1..-1].empty?
  require 'main-basic'
else
  require 'main-objects'
end
