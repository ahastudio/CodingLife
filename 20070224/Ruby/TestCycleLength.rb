require 'test/unit'
require 'CycleLength'

class TestCycleLength < Test::Unit::TestCase
	def setup
		@cyclelength = CycleLength.new
	end

	def test1
		assert_equal(1, @cyclelength.getLength(1))
		assert_equal(2, @cyclelength.getLength(2))
		assert_equal(8, @cyclelength.getLength(3))
		assert_equal(3, @cyclelength.getLength(4))
	end

	def test2
		for i in (2..100)
			if i % 2 == 1 then
				assert_equal(@cyclelength.getLength(i * 3 + 1) + 1,
					@cyclelength.getLength(i))
			else
				assert_equal(@cyclelength.getLength(i / 2) + 1,
					@cyclelength.getLength(i))
			end
		end
	end
end
