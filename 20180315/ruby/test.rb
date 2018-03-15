class Item
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def pack
    [@text.size].pack('C') + @text
  end
end

class Block
  attr_reader :items

  def initialize
    @items = []
  end

  def read_file(filename)
    path = File.expand_path(filename, data_path)
    return unless File.exist?(path)
    data = open(path).read
    items_count = data[0...4].unpack('L<').first
    (0...items_count).reduce(4) do |offset, _|
      read_item(data, offset)
    end
  end

  def read_item(data, offset)
    size = data[offset...offset + 1].unpack('C').first
    text = data[offset + 1...offset + 1 + size]
    @items << Item.new(text)
    offset + 1 + size
  end

  def write_file(filename)
    path = File.expand_path(filename, data_path)
    open(path, 'wb') do |f|
      f.write [@items.size].pack('L<')
      @items.each do |item|
        f.write item.pack
      end
    end
  end

  private

  def data_path
    File.expand_path('data', __dir__)
  end
end

class App
  def main
    make_directory
    block = Block.new
    block.read_file('0001')
    display_block(block)
    block.items << create_item
    block.write_file('0001')
  end

  def make_directory
    path = File.expand_path('data', __dir__)
    return if File.exist?(path)
    Dir.mkdir(path)
  end

  def display_block(block)
    puts block.items.size
    block.items.each { |i| puts i.text }
  end

  def create_item
    text = Time.now.strftime('%Y-%m-%d %H:%M:%S')
    Item.new(text)
  end
end

App.new.main
