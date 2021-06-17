/**
* holds a state and the coordinates to the next link in the chain.
*
* @author William Relken
* @version 02/18/2021
*/
public class SinglyNode {
	State data;
	SinglyNode leadNode;
	
	public SinglyNode(State data) {
		this.data = data;
		leadNode = null;
	}
	
}
