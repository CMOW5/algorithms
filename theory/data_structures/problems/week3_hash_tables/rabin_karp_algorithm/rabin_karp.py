import random

class rabinKarp:

	def __init__(self):
		self.prime = 1000000007
		self.multiplier = random.randint(1, self.prime)


	def getOccurrences(self, text, pattern):
		ocurrences = []

		phash = self.polyhash(pattern)

		hashes = self.precomputedHashes(text, pattern)

		for i in range(len(text) - len(pattern) + 1):
			if (phash != hashes[i]):
				continue

			if(pattern == text[i:i+len(pattern)]):
				ocurrences.append(i)

		return ocurrences
	
	def precomputedHashes(self, text, pattern):
		textLength = len(text)
		patternLength = len(pattern)
		hashesLength = textLength - patternLength + 1

		# H[ |T| - |P| + 1 ]
		H = [None] * hashesLength
		# s <- T[ |T|-|P| .. |T|-1 ]
		s = text[textLength - patternLength:]

		H[hashesLength-1] = self.polyhash(s)

		y = 1
		for i in range(patternLength):
			y = (y * self.multiplier) % self.prime

		for i in reversed(range(textLength - patternLength)):
			prehash = self.multiplier * H[i+1] + ord(text[i]) - y * ord(text[i+patternLength])
			H[i] = ((prehash % self.prime) + self.prime) % self.prime

		return H

	def polyhash(self, s):
		hash = 0
		for character in reversed(s):
			hash = (hash * self.multiplier + ord(character)) % self.prime
		return hash


if __name__ == '__main__':
		rK = rabinKarp()
		print rK.getOccurrences('abacaba','aba')
		print rK.getOccurrences('baaaaaaa','aaaaa')