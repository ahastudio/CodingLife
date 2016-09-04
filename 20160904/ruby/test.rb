class String
  def mirror?
    self == reverse
  end
end

class Fixnum
  def mirror_number?
    # [2, 8, 10].map { |i| to_s(i) }.all?(&:mirror?)
    [dec, bin, oct].all?(&:mirror?)
  end

  def dec
    to_s(10)
  end

  def bin
    to_s(2)
  end

  def oct
    to_s(8)
  end
end

puts '----------------------------------------------'
puts (10..1000).find(&:mirror_number?)
puts '----------------------------------------------'

describe Fixnum do
  describe '#mirror_number?' do
    it 'knows the number is mirror number' do
      expect(0).to be_mirror_number
      expect(1).to be_mirror_number
      expect(2).not_to be_mirror_number
      expect(3).to be_mirror_number
      expect(4).not_to be_mirror_number
      expect(8).not_to be_mirror_number
      expect(9).to be_mirror_number
    end
  end

  describe '#dec' do
    it 'converts dec number string' do
      expect(0.dec).to eq('0')
      expect(1.dec).to eq('1')
      expect(10.dec).to eq('10')
    end
  end

  describe '#bin' do
    it 'converts bin number string' do
      expect(0.bin).to eq('0')
      expect(1.bin).to eq('1')
      expect(2.bin).to eq('10')
    end
  end

  describe '#oct' do
    it 'converts oct number string' do
      expect(0.oct).to eq('0')
      expect(1.oct).to eq('1')
      expect(8.oct).to eq('10')
    end
  end
end

describe String do
  describe '#mirror?' do
    it 'knows that is mirror string' do
      expect('123').not_to be_mirror
      expect('121').to be_mirror
    end
  end
end
