require 'rbnacl'
require 'digest'
require 'active_support/core_ext/hash/slice'

class Service
  NAMES = %i[echo send_transaction].freeze

  def call(name, params)
    return { error: 'method_not_found' } unless NAMES.include?(name)
    args = params.each_with_object({}) { |i, o| o[i[0].to_sym] = i[1] }
    send(name, args)
  end

  def echo(message:)
    { result: message }
  end

  def send_transaction(data)
    puts
    puts '*** sendTransaction ***'
    message = raw_tx_data(data.slice(:timestamp, :from, :to, :amount))
    signature = [data[:signature]].pack('H*')
    public_key = [data[:publicKey]].pack('H*')
    address = address_from_pubkey(public_key)
    return { error: 'sign error' } if address != data[:from]
    _send_transaction(message, signature, public_key)
  end

  private

  def _send_transaction(message, signature, public_key)
    puts "Raw - #{to_hex(message)}"
    verify(message, signature, public_key)
    hash = Digest::SHA256.hexdigest(message).upcase
    puts "Tx - 0x#{hash}"
    { result: { hash: hash } }
  rescue RbNaCl::BadSignatureError
    puts RbNaCl::BadSignatureError
    { error: 'bad signature' }
  end

  def address_from_pubkey(public_key)
    Digest::RMD160.hexdigest(public_key).upcase
  end

  def raw_tx_data(timestamp:, from:, to:, amount:)
    from_int(timestamp, 4) + from_hex(from, 20) + from_hex(to, 20) +
      from_int(amount, 32)
  end

  def from_int(value, bytes)
    hex = ('0' * bytes * 2) + value.to_i.to_s(16)
    from_hex(hex[-bytes * 2..-1], bytes).reverse
  end

  def from_hex(value, bytes)
    data = [value].pack('H*')
    data + [0].pack('C') * (bytes - data.size)
  end

  def to_hex(data)
    data.unpack('H*').join('').upcase
  end

  def verify(message, signature, public_key)
    verify_key = RbNaCl::VerifyKey.new(public_key)
    verify_key.verify(signature, message)
  end
end
