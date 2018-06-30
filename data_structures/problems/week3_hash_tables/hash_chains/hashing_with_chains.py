# python3

class HashChains:
	_prime = 1000000007
	_multiplier = 263
	
	def __init__(self):
		self.m = 5
		self.hashList = []
		for i in range(self.m):
			self.hashList.append([])

	def hash(self, entry):
		result = 0
		for index, value in enumerate(entry):
			result += ord(value) * self._multiplier ** index
		return (result % self._prime) % self.m

	def add(self, entry):
		index = self.hash(entry)
		sublist = self.hashList[index]
		if (self.hasEntry(sublist, entry)):
			return
		else :
			sublist.insert(0, entry)

	def delete(self, entry):
		index = self.hash(entry)
		sublist = self.hashList[index]
		if (self.hasEntry(sublist, entry)):
			sublist.remove(entry)

	def find(self, entry):
		index = self.hash(entry)
		sublist = self.hashList[index]
		if (self.hasEntry(sublist, entry)):
			return "yes"
		else:
			return "no"

	def check(self, index):
		sublist = self.hashList[index]
		output = ""
		for value in sublist:
			output += value + " "
			
		return output.strip()
	
	def hasEntry(self, sublist, entry):
		for value in sublist:
			if entry == value:
				return True
		return False

if __name__ == '__main__':
	hc = HashChains()
	hc.add("world")
	hc.add("HellO")
	print hc.check(4)
	print hc.find("World")
	print hc.find("world")
	hc.delete("world")
	print hc.check(4)
	hc.delete("HellO")
	hc.add("luck")
	hc.add("GooD")
	print hc.check(2)
	hc.delete("good")
	#bucket_count = int(input())
	#proc = QueryProcessor(bucket_count)
	#proc.process_queries()