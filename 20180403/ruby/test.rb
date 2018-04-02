# https://en.wikipedia.org/wiki/RIPEMD
# https://ruby-doc.org/stdlib-2.4.3/libdoc/digest/rdoc/Digest/RMD160.html

require 'digest'

hash = Digest::RMD160.hexdigest('')
puts hash
puts hash.size

# https://en.wikipedia.org/wiki/SHA-3
# https://github.com/johanns/sha3

require 'sha3'

hash = SHA3::Digest::SHA256.hexdigest('')
puts hash
puts hash.size
