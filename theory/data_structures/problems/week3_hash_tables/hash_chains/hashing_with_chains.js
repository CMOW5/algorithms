
class HashChains {
	constructor() {
		this._prime = 1000000007;
		this._multiplier = 263;
		this.m = 5;
		this._list = [this.m];
		for (let i = 0; i < this.m; i++) {
			this._list[i] = [];
		} 
	}

	hash(entry) {
		let result = 0
		for (let i = 0; i < entry.length; i++) {
			const value = entry.charCodeAt(i);
			result += value * this._multiplier ** i;
		}
		return (result % this._prime) % this.m
	}

	add(entry) {
		let index = this.hash(entry);
		const sublist = this._list[index]
		if (this.hasEntry(sublist, entry)) return
		else sublist.unshift(entry) // add the new entry in the begining of the list
	}

	delete(entry) {
		let index = this.hash(entry);
		const sublist = this._list[index]
		if (this.hasEntry(sublist, entry)) sublist.shift();
	}

	find(entry) {
		let index = this.hash(entry);
		const sublist = this._list[index]
		if (this.hasEntry(sublist, entry)) return "yes";
		else return "no";
	}

	check(index) {
		const sublist = this._list[index];
		let output = "";
		for (let i = 0; i < sublist.length; i++) {
			output += value + " ";
		}
		return output.trim();
	}

	check(index) {
		const sublist = this._list[index];
		let output = "";
		for (let i = 0; i < sublist.length; i++) {
			const value = sublist[i];
			output += value + " ";
		}
		return output.trim();
	}


	hasEntry(sublist, entry) {
		for (let i = 0; i < sublist.length; i++) {
			const value = sublist[i];
			if (entry === value) {
				return true;
			}
		}
		return false;
	}
}

const hc = new HashChains();
hc.add("world")
hc.add("HellO")
console.log(hc.check(4))
console.log(hc.find("World"))
console.log(hc.find("world"))
hc.delete("world")
console.log(hc.check(4))
hc.delete("HellO")
hc.add("luck")
hc.add("GooD")
console.log(hc.check(2))
hc.delete("good")
