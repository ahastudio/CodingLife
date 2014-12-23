CycleLength = {}
	function CycleLength:getLength(n)
		if n == 1 then
			return 1
		elseif self:isEvenNumber(n) then
			return self:getLength(n / 2) + 1
		else
			return self:getLength(n * 3 + 1) + 1
		end
	end

	function CycleLength:isEvenNumber(n)
		return (n / 2) == math.floor(n / 2)
	end
