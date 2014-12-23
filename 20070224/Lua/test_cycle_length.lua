require("luaunit")
require("cycle_length")

TestCycleLength = {}
	function TestCycleLength:test()
		assertEquals(1, CycleLength:getLength(1))
		assertEquals(2, CycleLength:getLength(2))
		assertEquals(8, CycleLength:getLength(3))
		assertEquals(3, CycleLength:getLength(4))
		assertEquals(6, CycleLength:getLength(5))
	end

	function TestCycleLength:testEvenNumber()
		for i = 2, 1000, 2 do
			assertEquals(CycleLength:getLength(i / 2) + 1, CycleLength:getLength(i))
		end
	end

	function TestCycleLength:testOddNumber()
		for i = 3, 1001, 2 do
			assertEquals(CycleLength:getLength(i * 3 + 1) + 1, CycleLength:getLength(i))
		end
	end

LuaUnit:run()
