/**
* creates linked list that functions as a stack data structure.
*
* @author William Relken
* @version 02/18/2021
*/
public class Stack {
	SinglyNode first;
	SinglyNode last;
	
	public Stack() {
		first = null;
		last = null;
	}
	
	/**
	* adds a state object to the top of a stack increasing the chain of linked list
	*
	* @param pushedState - The state object that is to be added to the stack.
	* @return void
	*/
	public void Push(State pushedState) {
		SinglyNode temp = new SinglyNode(pushedState);
		if(IsEmpty()) {
			first = temp;
			last = first;
		}
		else {
			temp.leadNode = first;
			first = temp;
		}
	}
	
	/**
	* removes the state that is at the top of the stack and returns it, redirects first node to its leading node.
	*
	* @return State - state at top of stack
	*/
	public State Pop() {
		State returnState = null;
		
		if(!IsEmpty()) {
			returnState = new State();
			returnState = first.data;
			if(first.leadNode != null)
				first = first.leadNode;
			else {
				first = null;
				last = null;
			}
		}
		
		return returnState;
	}
	
	/**
	* prints the stack from top down with proper formatting
	*
	* @return void
	*/
	public void PrintStack() {
		System.out.println("\nStack contents: ");
		System.out.printf("%-15s | %-10s | %-10s | %-10s | %10s | %10s |\n","Name", "MHI","VCR", "CFR", "Case Rate", "Death Rate");
		System.out.println("---------------------------------------------------------------------------------|");
		
		if(first != null) {
			DataPrint(first);
		}
		
		System.out.println("---------------------------------------------------------------------------------|");
	}
	
	/**
	* sub-method of PrintStack to recursively print the data.
	*
	* @return void
	*/
	public static void DataPrint(SinglyNode node) {
		if(node.leadNode != null) {
			node.data.PrintData();
			DataPrint(node.leadNode);
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
