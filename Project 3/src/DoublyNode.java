/**
* holds a state and coordinates to both the next link and its previous link.
*
* @author William Relken
* @version 02/18/2021
*/
public class DoublyNode {
	DoublyNode tailNode;
	State data;
	DoublyNode leadNode;
	
	public DoublyNode(State data) {
	tailNode = null;
	this.data = data;
	leadNode = null;
	}
	
}
