class Test
  attr_reader :error

  def initialize(testcase, name)
    @testcase = testcase
    @name = name
    @error = nil
  end

  def run
    @testcase.send(@name)
  rescue => e
    @error = e
  end

  def failed?
    !@error.nil?
  end
end

class TestCase
  attr_reader :messages

  def initialize
    @messages = ['-------------------------']
  end

  def log(message)
    @messages << message
  end

  def assert_equal(expected, actual)
    return if expected == actual
    fail "Not Equal - expected: [#{expected}], but actual: [#{actual}]"
  end

  def run
    tests = methods
      .select { |name| name =~ /^test\_/ }
      .map { |name| Test.new(self, name) }
    tests.each do |test|
      setup
      test.run
      teardown
    end
    tests.select(&:failed?).map(&:error).each { |e| log("#{e}\n") }
    log("#{tests.reject(&:failed?).size} passed," +
        " #{tests.select(&:failed?).size} failed")
    self
  end

  def setup
  end

  def teardown
  end
end

class FlowTest < TestCase
  attr_reader :flow

  def setup
    (@flow ||= '') << '-S'
  end

  def teardown
    @flow << '-E'
  end

  def test_simple1
    @flow << '-1'
    assert_equal 1, 1
  end

  def test_simple2
    @flow << '-2'
    assert_equal 2, 2
  end
end

class MyTest < TestCase
  def test_simple
    assert_equal 1, 1
  end

  def test_add_success
    assert_equal 2, 1 + 1
  end

  def test_add_fail
    assert_equal 1, 1 + 1
  end
end

class TestCaseTest < TestCase
  def test_result
    test = MyTest.new.run
    assert_equal '2 passed, 1 failed', test.messages.last
  end

  def test_flow
    test = FlowTest.new.run
    assert_equal '-S-1-E-S-2-E', test.flow
  end
end

test = TestCaseTest.new.run
puts test.messages.join("\n")
