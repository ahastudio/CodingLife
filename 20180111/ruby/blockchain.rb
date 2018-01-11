require 'digest'

class Block
  MAX_NONCE = 2 ** 32

  attr_reader :hash
  attr_accessor :previous_hash, :data, :nonce

  def initialize(data)
    @hash = ''
    @previous_hash = ''
    @timestamp = Time.now.to_i
    @difficulty = 10
    @nonce = nil
    @data = data
  end

  def to_s
    "Hash: #{@hash}\nPrevious: #{@previous_hash}\nNonce: #{@nonce}\n#{@data}"
  end

  def compute_hash(nonce)
    Digest::SHA256.hexdigest(@data.to_s + nonce.to_s)
  end

  def body
    [@previous_hash, @timestamp, @difficulty, @data].map(&:to_s).join('')
  end

  def proof!
    puts 'Proof start!'
    target = 2 ** (256 - @difficulty)
    (0..MAX_NONCE).each do |nonce|
      # puts nonce
      hash = compute_hash(nonce)
      if hash.to_i(16) < target
        @hash = hash
        @nonce = nonce
        break
      end
    end
    puts 'Proof complete!'
    puts
  end

  def proof?
    compute_hash(@nonce) == @hash
  end
end

class BlockChain
  def initialize
    @blocks = []
    @transactions = []
  end

  def init!
    genesis_block = Block.new(['God of Coding'])
    genesis_block.proof!
    add_block(genesis_block)
  end

  def add_block(block)
    block.previous_hash = @blocks.last.hash
    block.proof!
    @blocks << block
  end

  def to_s
    @blocks.map(&:to_s).join("\n\n")
  end
end

class App
  def main
    blockchain = BlockChain.new
    blockchain.init!
    blockchain.add_block(Block.new(['Ashal aka JOKER']))
    blockchain.add_block(Block.new(['https://www.youtube.com/ahastudio']))
    block1 = Block.new(['Take my money!', 'https://youtu.be/pVrIXWDiBDw'])
    blockchain.add_block(block1)
    block2 = Block.new(['Can you feel?', 'https://youtu.be/6BDW1V_P5sA'])
    blockchain.add_block(block2)
    puts '-' * 80
    puts blockchain.to_s
    puts '-' * 80
    puts '* normal'
    puts block1.proof?
    puts block2.proof?
    puts '* hack data'
    block1.data = 'Hack!'
    puts block1.proof?
    puts '* hack nonce'
    block2.nonce = 0
    puts block2.proof?
  end
end

print "=" * 80
print %x{clear}
App.new.main
