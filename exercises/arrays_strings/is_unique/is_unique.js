
let str = "abca";

console.log(hasDuplicates(str));

function hasDuplicates(str) {
  let foundCharacters = new Set();
  [...str].forEach((character) => {
    if (foundCharacters.has(character)) {
      return true;
    } else {
      foundCharacters.add(character);
    }
  });
  return false;
}