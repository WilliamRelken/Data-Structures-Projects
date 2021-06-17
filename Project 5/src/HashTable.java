/**
* A hash table using separate chaining and double ended linked lists
*
* @author William Relken
* @version 04/15/2021
*/
public class HashTable {
	/**
	* Node class containing data and a link to another Node
	*
	* @author William Relken
	* @version 04/15/2021
	*/
	private class Node {
		String name;
		long population;
		long deaths;
		Node nextNode;
		public Node(String name, long population, long deaths) {
			this.name = name;
			this.population = population;
			this.deaths = deaths;
		}
		public void printNode() {
			System.out.printf("%-30s %-20.2f\n", name, (double)deaths/population*100000);
		}
	}
	
	/**
	* Simple container for the first and last nodes
	*
	* @author William Relken
	* @version 04/15/2021
	*/
	public class LinkList {
		
		public Node first = null;
		public Node last = null;
		
	}
	
	public LinkList[] hashTab;
	
	public HashTable() {
		hashTab = new LinkList[101];
	}

	/**
	* Description of the purpose of the method, the meaning of the
	* input parameters (if any) and the meaning of the return values * (if any).
	*
	* @param String State - The name of the state to insert
	* @param long population - population of state to insert
	* @param long deaths - amount of deaths of state to insert
	*/
	public void insert(String state, long population, long deaths) {
		int hashIndex = 0;
		
		for(int i = 0; i < state.length(); i++) {
			hashIndex += state.codePointAt(i);
		}
		hashIndex += (population + deaths);
		
		hashIndex %= 101;
		
		if(hashTab[hashIndex] == null) {
			hashTab[hashIndex] = new LinkList();
		}
		
		if(hashTab[hashIndex].first == null) {
			hashTab[hashIndex].first = new Node(state, population, deaths);
			hashTab[hashIndex].last = hashTab[hashIndex].first;
		}
		else {
			hashTab[hashIndex].last.nextNode = new Node(state, population, deaths);
			hashTab[hashIndex].last = hashTab[hashIndex].last.nextNode;
		}
		
	}
	
	/**
	* finds the state inside the hash table and returns the index where it was found
	* or -1 if it was not found.
	*
	* @param String State - The name of the state to find
	* @param long population - population of state to find
	* @param long deaths - amount of deaths of state to find
	* @return Integer corresponding with the index of the state or -1 if not found
	*/
	public int find(String state, long population, long deaths) {
		int hashIndex = 0;
		
		for(int i = 0; i < state.length(); i++) {
			hashIndex += state.codePointAt(i);
		}
		hashIndex += (population + deaths);
		
		hashIndex %= 101;
		
		if(hashTab[hashIndex] != null) {
			Node current = hashTab[hashIndex].first;
			while(current != null) {
				if(current.name.equalsIgnoreCase(state)) {
					
					System.out.printf("\n %s is found at %d with DR of %.2f\n",state, hashIndex, (double)current.deaths/current.population*100000);
					
					return hashIndex;
				}
				else {
					current = current.nextNode;
				}
			}
			
		}
		
		return -1;
	}
	
	/**
	* Description of the purpose of the method, the meaning of the
	* input parameters (if any) and the meaning of the return values * (if any).
	*
	* @param String State - The name of the state to delete
	* @param long population - population of state to delete
	* @param long deaths - amount of deaths of state to delete
	*/
	public void delete(String state, long population, long deaths) {
		int hashIndex = 0;
		
		for(int i = 0; i < state.length(); i++) {
			hashIndex += state.codePointAt(i);
		}
		hashIndex += (population + deaths);
		
		hashIndex %= 101;
		
		if(hashTab[hashIndex] != null) {
			Node previous = null;
			Node current = hashTab[hashIndex].first;
			while(current != null) {
				if(current.name.equalsIgnoreCase(state)) {
					if(current == hashTab[hashIndex].first) {
						if(hashTab[hashIndex].first.nextNode == null) {
							hashTab[hashIndex] = null;
						}
						else {
							hashTab[hashIndex].first = hashTab[hashIndex].first.nextNode;
						}
						
						break;
					}
					else if(current == hashTab[hashIndex].last) {
						previous.nextNode = null;
						hashTab[hashIndex].last = previous;
						break;
					}
					else {
						previous.nextNode = current.nextNode;
						break;
					}
				}
				else {
					previous = current;
					current = current.nextNode;
				}
			}
			
		}
		
		System.out.printf("\n %s is deleted from hash table\n", state);
	}
	
	/**
	* Prints a index-numbered and formatted view of the contents inside the hash
	* table with chained data appearing in order below index number
	*/
	public void display() {
		for(int i = 0; i < hashTab.length; i++) {
			
			
			System.out.printf("%3d.   ", i);
			if(hashTab[i] != null) {
				Node current = hashTab[i].first;
				
				if(current == hashTab[i].first) {
					current.printNode();
					current = current.nextNode;
				}
				
				while(current != null) {
					System.out.print("       ");
					current.printNode();
					current = current.nextNode;
				}
			}
			else {
				System.out.println("Empty");
			}
		}
	}
	
	/**
	* counts the amount of null values and collisions in the Hash Table 
	*/
	public void printEmptyAndCollisions() {
		int emptyCount = 0;
		int collisions = 0;
		
		for(int i = 0; i < hashTab.length; i++) {
			if(hashTab[i] == null) {
				emptyCount++;
			}
			else {
				if(hashTab[i].first.nextNode != null) {
					collisions++;
				}
			}
		}
		
		System.out.printf("\n There are %d empty cells and %d collisions in the hash table\n", emptyCount, collisions);
	}
	
}
