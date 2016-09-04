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
    context 'with mirror-number' do
      subject { [0, 1, 3, 9] }
      it { is_expected.to be_all(&:mirror_number?) }
    end

    context 'with non-mirror-number' do
      subject { [2, 4, 8] }
      it { is_expected.not_to be_all(&:mirror_number?) }
    end
  end

  describe '#dec' do
    it 'converts dec number string' do
      expect([0, 1, 10].map(&:dec)).to match_array(%w(0 1 10))
    end
  end

  describe '#bin' do
    it 'converts bin number string' do
      expect([0, 1, 2].map(&:bin)).to match_array(%w(0 1 10))
    end
  end

  describe '#oct' do
    it 'converts oct number string' do
      expect([0, 1, 8].map(&:oct)).to match_array(%w(0 1 10))
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
