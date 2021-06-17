/**
* creates an array that functions as a priority queue data structure.
*
* @author William Relken
* @version 02/18/2021
*/
public class PriorityQ {
	public State[] stateQ;
	int amtInQ;
	public PriorityQ(int qSize) {
		stateQ = new State[qSize];
		amtInQ = 0;
	}
	
	/**
	* inserts a state at the end of the queue
	*
	* @param stateIns - State object to be inserted
	* @return void
	*/
	public void InsertState(State stateIns) {
		if(!IsFull()) {
			stateQ[amtInQ] = stateIns;
			amtInQ++;
		}
	}
	
	/**
	* finds the lowest DR among the queue, returns it and shifts all the queues contents to the left
	*
	* @return State - state with the lowest DR in the queue
	*/
	public State Remove() {
//		initialized as 0 so for loop can only include comparisons
		State removedState = stateQ[0];
		int removedPos = 0;
		int endQ = stateQ.length;
		
//		finds current end of the queue
		for(int i = (stateQ.length - 1); i >= 0; i--) {
			if (stateQ[i] == null) {
				endQ = i;
			}
		}
		
//		finds the lowest deathrate state
		for(int i = 1; i < endQ && amtInQ != 1; i++) {
			if(removedState.getDeathRate() > stateQ[i].getDeathRate()) {
				removedState = stateQ[i];
				removedPos = i;
			}
		}
		
//		removes and shifts all the states after the deleted record
		for(int i = removedPos; i < (endQ + 1); i++) {
			if(i < (endQ - 1)){
				stateQ[i] = stateQ[i + 1];
			}
		}
		stateQ[endQ - 1] = null;
		amtInQ--;
		
		return removedState;
	}
	
	/**
	* prints the stack with proper formatting
	*
	* @return void
	*/
	public void PrintQueue() {
		System.out.printf("%-15s | %-10s | %-10s | %-10s | %10s | %10s |\n","Name", "MHI","VCR", "CFR", "Case Rate", "Death Rate");
		System.out.println("---------------------------------------------------------------------------------|");
		for(int i = 0; i < stateQ.length && stateQ[i] != null; i++) {
			stateQ[i].PrintData();
		}
		System.out.println("---------------------------------------------------------------------------------|");
	}
	
	/**
	* returns a boolean based on if the stateQ array is empty or not
	*
	* @return boolean - true if empty, false if otherwise
	*/
	public boolean IsEmpty() {
		boolean empty = false;
		
		if(stateQ[0] == null) {
			empty = true;
		}
		return empty;
	}
	
	/**
	* returns a boolean based on if the stateQ array is full or not
	*
	* @return boolean - true if full, false if otherwise
	*/
	public boolean IsFull() {
		boolean filled = false;
		
		if(stateQ[(stateQ.length - 1)] != null)
			filled = true;
		
		return filled;
	}
	
}
