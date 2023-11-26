@SuppressWarnings("unchecked")
public class Hashtable<K, V> {
	private Pair[] table;
	private int n;// the number of key-value pairs in the table
	private int m;// the size of the table
	private double alphaHigh = 0.5;// resize if n/m exceeds this (1/2)
	private double alphaLow = 0.125;// resize if n/m goes below this (1/8)

// constructor--default table size is 11
	public Hashtable() {
		table = new Pair[11];
		n = 0;
		m = 11;
	}

// constructor
	public Hashtable(int m) {
		table = new Pair[m];
		n = 0;
		this.m = m;
	}

// returns the value associated with key <key>
// return null if key is not in table
// do not forget that you will have to cast the result to (V)
	public V get(K key) {
		int index = hash(key);
		while (table[index] != null) {
			if (key == table[index].getKey()) {
				if (table[index].isDeleted()) {
					return null;
				} else {
					return (V) table[index].getValue();
				}
			}
			index++;
		}
		return null;
	}

// puts (key, val) into the table or updates value
// if the key is already in the table
// resize to getNextNum(2*m) if (double)n/m exceeds alphaHigh after the insert
	public void put(K key, V val) {
		int index = hash(key);
		if (table[index] == null) {
			table[index] = new Pair(key, val);
			n++;
		} else {
			boolean copyFound = false;
			while (table[index] != null) {
				if (table[index].getKey() == key) {
					table[index] = new Pair(key, val);
					copyFound = true;
					break;
				}
				index++;
				if (index == m) {
					index = 0;
				}
			}
			if (!copyFound) {
				table[index] = new Pair(key, val);
				n++;
			}
		}
// resizing if necessary
		if ((double) n / m > alphaHigh)
			resize(getNextNum(2 * m));
	}

// removes the (key, value) pair associated with <key>
// returns the deleted value or null if the element was not in the table
// resize to getNextNum(m/2) if m/2 >= 11 AND (double)n/m < alphaLow after the
// delete
	public V delete(K key) {
		int index = hash(key);
		V ret = null;
		if (table[index] == null) {
			return null;
		} else {
			if (table[index].getKey().equals(key)) {
				ret = (V) table[index].getValue();
				table[index].delete();
				n--;
			} else {
				while (table[index] != null && index < m) {
					if (table[index].getKey().equals(key)) {
						ret = (V) table[index].getValue();
						table[index].delete();
						n--;
						break;
					}
					index++;
				}
			}
		}
// resizing if necessary
		if (m / 2 >= 11 && (double) n / m < alphaLow)
			resize(getNextNum(m / 2));

		return ret;
	}

// return true if table is empty
	public boolean isEmpty() {
		return n == 0;
	}

// return the number of (key,value) pairs in the table
	public int size() {
		return n;
	}

// This method is used for testing only. Do not use this method yourself for any
// reason
// other than debugging. Do not change this method.
	public Pair[] getTable() {
		return table;
	}

// PRIVATE

// gets the next multiple of 6 plus or minus 1,
// which has a decent probability of being prime.
// Use this method when resizing the table.
	private int getNextNum(int num) {
		if (num == 2 || num == 3)
			return num;
		int rem = num % 6;
		switch (rem) {
		case 0:
			num++;
			break;
		case 2:
			num += 3;
			break;
		case 3:
			num += 2;
			break;
		case 4:
			num++;
			break;
		}
		return num;
	}
	
	/**
	 * 
	 * @param max, the new max size of the hashtable
	 * this method resized the table if the ratio of entries
	 * to pairs goes above or below alpha high or alpha low respectively
	 */
	private void resize(int max) {
		Pair[] resized = new Pair[max];
		this.m = max;
		this.n = 0;
		int index;
		for (Pair p : table) {
			if (p == null) {
				continue;
			}
			if (!p.isDeleted()) {
				index = hash((K) p.getKey());
				while (resized[index] != null) {
					index++;
				}
				resized[index] = p;
				this.n++;
			}
		}
		table = resized;
	}
		
// get the hash of a key. i.e. the index that a key value pair
// in the hashtable. used for getting and setting
	private int hash(K key) {
		int retVal = (key.hashCode() % this.m);
		if (retVal < 0) {
			retVal += this.m;
		}
		return retVal;
	}

	public Pair<String, String>[] entryArray() {
		Pair<String, String>[] retVal = new Pair[n];
		int i = 0;
		for (Pair p : table) {
			if (p == null) { continue; }
			if (p.isDeleted() == false) {
				retVal[i] = p;
				i++;
			}
		}
		
		return retVal;
		
	}
}
