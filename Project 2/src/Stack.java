/**
* creates an array that functions as a stack data structure.
*
* @author William Relken
* @version 02/18/2021
*/
public class Stack {
	State[] stateStack;
	int amtInStack;
	
	public Stack(int stackSize) {
		stateStack = new State[stackSize];
		amtInStack = 0;
	}
	
	/**
	* adds a state object to the top of a stack increasing the amount currently in the array
	*
	* @param pushedState - The state object that is to be added to the stack.
	* @return void
	*/
	public void Push(State pushedState) {
		if(!IsFull()) {
			stateStack[amtInStack] = pushedState;
			amtInStack++;
		}
	}
	
	/**
	* removes the state that is at the top of the stack and returns it
	*
	* @return State - state at top of stack
	*/
	public State Pop() {
		State returnState = new State();
		
		if(!IsEmpty()) {
			returnState = stateStack[amtInStack - 1];
			stateStack[amtInStack - 1] = null;
			amtInStack--;
		}
		
		return returnState;
	}
	
	/**
	* prints the stack with proper formatting
	*
	* @return void
	*/
	public void PrintStack() {
		System.out.println("\nStack contents: ");
		System.out.printf("%-15s | %-10s | %-10s | %-10s | %10s | %10s |\n","Name", "MHI","VCR", "CFR", "Case Rate", "Death Rate");
		System.out.println("---------------------------------------------------------------------------------|");
		for(int i = amtInStack - 1; i >= 0 && !IsEmpty(); i--) {
			stateStack[i].PrintData();
		}
		System.out.println("---------------------------------------------------------------------------------|");
	}
	
	/**
	* returns a boolean based on if the stack array is empty or not
	*
	* @return boolean - true if empty, false if otherwise
	*/
	public boolean IsEmpty() {
		boolean empty = false;
		
		if(stateStack[0] == null) {
			empty = true;
		}
		return empty;
	}
	
	/**
	* returns a boolean based on if the stack array is full or not
	*
	* @return boolean - true if full, false if otherwise
	*/
	public boolean IsFull() {
		boolean filled = false;
		
		if(stateStack[(stateStack.length - 1)] != null)
			filled = true;
		
		return filled;
	}
	
}
