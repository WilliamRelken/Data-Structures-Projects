/**
* Holds data and a connection to a left and right node.
*
* @author William Relken
* @version 03/26/2021
*/
public class Node {
	Node left;
	Node right;
	String stateName;
	double deathRate;
	
	public Node() {
		left = null;
		right = null;
	}
	
	public void PrintNode() {
		System.out.printf("%-14s    %6.2f \n", stateName, deathRate);
	}
}
