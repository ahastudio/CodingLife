class Stack
  def initialize
    @data = []
  end

  def add(element)
    @data << element
  end

  def pop
    @data.pop
  end

  def size
    @data.size
  end
end

RSpec.shared_examples_for 'last-taker' do
  it { is_expected.to eq elements.last }
end

RSpec.describe Stack do
  subject(:stack) { Stack.new }

  describe '#pop' do
    subject { stack.pop }

    before do
      elements.each { |e| stack.add(e) }
    end

    context 'without elements' do
      let(:elements) { [].freeze }
      it { is_expected.to be_nil }
    end

    context 'with a element' do
      let(:elements) { %w(A).freeze }
      it_behaves_like 'last-taker'
    end

    context 'with N-elements' do
      let(:elements) { %w(A B C D E).freeze }
      it_behaves_like 'last-taker'
    end
  end
end

# --------------------------------

class Account
  def initialize(amount)
    @amount = amount
  end

  def balance
    @amount
  end

  def deposit(amount)
    @amount += amount
  end

  def withdraw(amount)
    @amount -= amount
  end
end

RSpec.describe Account do
  let(:account) { Account.new(base) }
  let(:base) { 10_000 }
  let(:delta) { 1_000 }

  describe '#balance' do
    subject { account.balance }
    it { is_expected.to eq base }
  end

  describe '#deposit' do
    it "increases account's balance" do
      expect { account.deposit(delta) }
        .to change { account.balance }.by(delta)
    end
  end

  describe '#withdraw' do
    it "decreases account's balance" do
      expect { account.withdraw(delta) }
        .to change { account.balance }.by(-delta)
    end
  end
end
