/**
* creates an array that functions as a priority queue data structure.
*
* @author William Relken
* @version 02/18/2021
*/
public class PriorityQ {
	DoublyNode first;
	DoublyNode last;

	
	public PriorityQ() {
		first = null;
		last = null;
	}
	
	/**
	* inserts a node containing the state in the queue based on state death rate.
	*
	* @param stateIns - State object to be inserted
	* @return void
	*/
	public void InsertState(State stateIns) {
		DoublyNode temp = new DoublyNode(stateIns);
		boolean inserted = false;
		
		if(IsEmpty()) {
			first = temp;
			last = first;
		}
		else if(first == last){
			first.tailNode = temp;
			temp.leadNode = first;
			
			last = first;
			first = temp;
		}
		else {
	//		scan through the nodes to find correct pos and inserts
			DoublyNode currentNode = first;
			
			do{
				if(currentNode.data.getDeathRate() < stateIns.getDeathRate()) {
					if(currentNode.tailNode == null) {
						temp.leadNode = first;
						first = temp;
						first.leadNode.tailNode = first;
					}
					else {
						
						temp.tailNode = currentNode.tailNode;
						currentNode.tailNode = temp;
						temp.leadNode = currentNode;
						temp.tailNode.leadNode = temp;				

					}
					inserted = true;
					
					break;
				}
	
				currentNode = currentNode.leadNode;

			} while(currentNode != null);
			
			if(!inserted) {
				last.leadNode = temp;
				temp.tailNode = last;
				
				last = temp;
			}
		
		}

	}
	
	/**
	* returns and removes the lowest link from the queue.
	*
	* @return State - state with the lowest DR in the queue
	*/
	public State Remove() {
		State removedState = last.data;
		
		if(!IsEmpty()) {
			if(first != last) {
				last = last.tailNode;
				last.leadNode = null;
			}
			else {
				first = null; 
				last = null;
			}
		}
		return removedState;
	}
	
	/**
	* moves through linked list deleting any values within the range.
	*
	* @return boolean - if any nodes were deleted in the range
	*/
	public boolean IntervalDelete(float startRange, float endRange) {
		DoublyNode currentNode = last;
		boolean nodesDeleted = false;
		if(IsEmpty())
			return nodesDeleted;
		
		do {
			if((Math.round(currentNode.data.getDeathRate() * 100)/ 100) >= startRange && (Math.round(currentNode.data.getDeathRate() * 100)/ 100) <= endRange) {
				if(first == last) {
					first = null;
					last = null;
				}
				else if(currentNode == last) {
					last = currentNode.tailNode;
					last.leadNode = null;
					nodesDeleted = true;
				}
				else if(currentNode == first) {
					first = first.leadNode;
					first.tailNode = null;
					nodesDeleted = true;
				}
				else {
					currentNode.leadNode.tailNode = currentNode.tailNode;
					currentNode.tailNode.leadNode = currentNode.leadNode;
					nodesDeleted = true;
				}
			}
			
			currentNode = currentNode.tailNode;
		} while(currentNode != null);
		
		return nodesDeleted;
	}
	
	/**
	* prints the priority queue from bottom up with proper formatting
	*
	* @return void
	*/
	public void PrintStack() {
		System.out.println("\nPriorityQ contents: ");
		System.out.printf("%-15s | %-10s | %-10s | %-10s | %10s | %10s |\n","Name", "MHI","VCR", "CFR", "Case Rate", "Death Rate");
		System.out.println("---------------------------------------------------------------------------------|");
		
		if(first != null) {
			DataPrint(last);
		}
		
		System.out.println("---------------------------------------------------------------------------------|");
	}
	
	/**
	* sub-method of PrintStack to recursively print the data.
	*
	* @return void
	*/
	public static void DataPrint(DoublyNode node) {
		if(node.tailNode != null) {
			node.data.PrintData();
			DataPrint(node.tailNode);
		}
		else
			node.data.PrintData();
	}
	
	/**
	* returns a boolean based on if the linked list does not have any nodes
	*
	* @return boolean - true if empty, false if otherwise
	*/
	public boolean IsEmpty() {
		boolean empty = false;
		
		if(first == null) {
			empty = true;
		}
		return empty;
	}
	
}
