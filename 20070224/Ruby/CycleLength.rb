class CycleLength
	def getLength(n)
		return 1 if n == 1
		if n % 2 == 0 then
			return getLength(n / 2) + 1
		else
			return getLength(n * 3 + 1) + 1
		end
	end
end
