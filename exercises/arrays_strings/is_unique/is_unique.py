

def hasDuplicates(str):
  foundCharacters = set()

  for c in str :
    if c in foundCharacters:
      return True
    else :
      foundCharacters.add(c)
	
  return False

if __name__ == "__main__":
    str = "abc"
    print(hasDuplicates(str))
