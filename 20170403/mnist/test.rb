require 'matrix'
require 'nmatrix'
require 'numo/narray'
require 'benchmark'

raw_x_data = IO.binread('data/t10k-images-idx3-ubyte', nil, 16)
raw_y_data = IO.binread('data/t10k-labels-idx1-ubyte', nil, 8)

rows = raw_x_data.size / (28 * 28)

puts '-------------------------'

Benchmark.bm do |x|
  x.report('unpack') do
    x_data = raw_x_data.unpack('c*')
    y_data = raw_y_data.unpack('c*')
  end

  x.report('Matrix') do
    x_data = Matrix[raw_x_data.unpack('c*')]
    y_data = Matrix[raw_y_data.unpack('c*')]
  end

  x.report('NMatrix[]') do
    x_data = NMatrix[raw_x_data.unpack('c*')]
    y_data = NMatrix[raw_y_data.unpack('c*')]
  end

  x.report('NMatrix.new') do
    x_data = NMatrix.new([rows, 28 * 28], raw_x_data)
    y_data = NMatrix.new([rows], raw_y_data)
  end

  x.report('NArray[]') do
    x_data = Numo::NArray[raw_x_data.unpack('c*')]
    y_data = Numo::NArray[raw_y_data.unpack('c*')]
  end

  x.report('NArray.from_binary') do
    x_data = Numo::Int8.from_binary(raw_x_data, [rows, 28 * 28])
    y_data = Numo::Int8.from_binary(raw_y_data)
  end
end
