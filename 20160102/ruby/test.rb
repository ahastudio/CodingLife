class Array
  def sum(&block)
    map(&block).reduce(&:+)
  end
end

class Part
  attr_reader :cost, :performance

  def initialize(cost:, performance:)
    @cost = cost
    @performance = performance
  end
end

class Machine
  attr_reader :parts

  def initialize(options = nil)
    @parts = []
    init(options) if options
  end

  def init(cost:, performance:)
    @parts << Part.new(cost: cost, performance: performance)
  end

  def clone
    Machine.new << @parts
  end

  def <<(part)
    @parts.concat([part].flatten)
    self
  end

  def cost_effectiveness
    @parts.sum(&:performance) / @parts.sum(&:cost)
  end
end

class Builder
  def initialize(machine, parts)
    @machine = machine
    @parts = parts
  end

  def machine(parts)
    machine = @machine.clone
    machine << parts
  end

  def machines
    combinations = (0..@parts.size).flat_map { |i| @parts.combination(i).to_a }
    combinations.map { |parts| machine(parts) }
  end

  def max_cost_effectiveness
    machines.map(&:cost_effectiveness).max
  end
end

puts '---------------------------'

describe 'cost-effectiveness' do
  let(:machine) { Machine.new(cost: 10, performance: 150) }

  let(:parts) do
    [30, 70, 15, 40, 65].map do |performance|
      Part.new(cost: 3, performance: performance)
    end
  end

  it 'finds max effective' do
    expect(Builder.new(machine, parts).max_cost_effectiveness).to eq(17)
  end

  it 'finds max effective machine' do
    machines = Builder.new(machine, parts).machines
    parts = machines.max_by(&:cost_effectiveness).parts.drop(1)
    expect(parts.map(&:performance).sort).to match_array([65, 70])
  end
end

describe Machine do
  describe '#cost_effectiveness' do
    it 'calculates cost-effectiveness' do
      machine = Machine.new(cost: 10, performance: 150)
      expect(machine.cost_effectiveness).to eq(15)
      machine << Part.new(cost: 10, performance: 150)
      expect(machine.cost_effectiveness).to eq(15)
      machine << Part.new(cost: 0, performance: 300)
      expect(machine.cost_effectiveness).to eq(30)
    end
  end
end
