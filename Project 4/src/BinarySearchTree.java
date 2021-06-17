/**
* A Binary search tree using a Node class. It contains several
* functions useful to the tree:
* 	Insert
* 	Delete
* 	Print inorder
* 	Print preorder
* 	Print postorder
* 	Print path
* 	Print bottom states
* 	Print top states
*
* @author William Relken
* @version 03/26/2021
*/
public class BinarySearchTree {
	public Node root;
	public BinarySearchTree() {
		root = null;
	}
	
	/**
	 * inserts a new node in the proper location with
	 * the state name and death rate.
	 * 
	 * @param name - Name of the node to find
	 * @param DR - Death rate of state
	 */
	public void insert(String name, double DR) {
		boolean inserted = false;
		Node traverse = root;
		if(root == null) {
			root = new Node();
			root.stateName = name;
			root.deathRate = DR;
		}
		else {
			while(!inserted) {
				if(traverse.stateName.compareToIgnoreCase(name) > 0) {
					if(traverse.left != null) {
						traverse = traverse.left;
					}
					else {
						traverse.left = new Node();
						traverse.left.stateName = name;
						traverse.left.deathRate = DR;
						inserted = true;
					}
				}
				else if(traverse.stateName.compareToIgnoreCase(name) < 0) {
					if(traverse.right != null) {
						traverse = traverse.right;
					}
					else {
						traverse.right = new Node();
						traverse.right.stateName = name;
						traverse.right.deathRate = DR;
						inserted = true;
					}
				}
			}
		}
	}
	
	/**
	 * finds and prints the death rate corresponding to the state parameter
	 * 
	 * @param name - Name of the node to find
	 * @return DR - The death rate corresponding to the
	 * name of the state found.
	 */
	public double find(String name) {
		double foundDR = -1;
		Node traverse = root;
		
		while(!traverse.stateName.equalsIgnoreCase(name)) {
			if(traverse.stateName.compareToIgnoreCase(name) > 0) {
				traverse = traverse.left;
			}
			else if(traverse.stateName.compareToIgnoreCase(name) < 0) {
				traverse = traverse.right;
			}
			if(traverse == null)
				break;
		}
		
		if(traverse != null)
			foundDR = traverse.deathRate;
		
		return foundDR;
	}
	
	/**
	 * locates the position of the node to be deleted and runs the
	 * proper deletion scenario based where Node is located in tree.
	 * 
	 * @param name - Name of the node to delete
	 */
	public void delete(String name) {
		Node prevNode = root;
		Node currentNode = root;
		boolean leftChild = true;
		
		while(!currentNode.stateName.equalsIgnoreCase(name)) {
			prevNode = currentNode;
			
			if(currentNode.stateName.compareToIgnoreCase(name) > 0) {
				
				currentNode = currentNode.left;
				leftChild = true;
			}
			else {
				
				currentNode = currentNode.right;
				leftChild = false;
			}
			
			if(currentNode == null)
				break;
		}		
		
		if(currentNode != null) {
			
			if(currentNode.left == null && currentNode.right == null) {
				if(currentNode == root) {
					root = null;
				}
				else if(leftChild) {
					prevNode.left = null;
				}
				else {
					prevNode.right = null;
				}
			}
			
			else if(currentNode.right == null) {
				if(currentNode == root) {
					root = currentNode.left;
				}
				else if(leftChild) {
					prevNode.left = currentNode.left;
				}
				else {
					prevNode.right = currentNode.right;
				}
			}
			else if(currentNode.left == null) {
				if(currentNode == root) {
					root = currentNode.right;
				}
				else if(leftChild) {
					prevNode.left = currentNode.right;
				}
				else {
					prevNode.right = currentNode.right;
				}
			}
			else {
				Node successor = GetSuccess(currentNode);
				
				if(currentNode == root) {
					root = successor;
				}
				else if(leftChild) {
					prevNode.left = successor;
				}
				else {
					prevNode.right = successor;
				}
				
				successor.left = currentNode.left;
			}
		}
	}
	
	/**
	 * finds the next largest value on the right side of the StartNode
	 * 
	 * @param StartNode - The Node to find the successor of.
	 * @return successor - Successor to StartNode.
	 */
	public Node GetSuccess(Node StartNode) {
		Node prevSuccessor = StartNode;
		Node successor = StartNode;
		Node current = StartNode.right;
		
		while(current != null) {
			prevSuccessor = successor;
			successor = current;
			current = current.left;
		}
		
		if(successor != StartNode.right) {
			prevSuccessor.left = successor.right;
			successor.right = StartNode.right;
		}
		return successor;
	}
	
	/**
	 * Calls a recursive function using the root class.
	 */
	public void printInorder() {
		System.out.println("Inorder traversal: ");
		System.out.println("Name              Death Rate");
		System.out.println("----------------------------");
		LNR(root);
	}
	
	/**
	 * recursively goes through tree printing node according to LNR 
	 * principal: Go left -> Print node -> Go right
	 * 
	 * @param tempNode - node to be recursively printed
	 */
	public void LNR(Node tempNode) {
		if(tempNode != null) {
			LNR(tempNode.left);

			tempNode.PrintNode();
			
			LNR(tempNode.right);
		}
	}
	
	/**
	 * Calls a recursive function using the root class.
	 */
	public void printPreorder() {
		System.out.println("Preorder traversal: ");
		System.out.println("Name              Death Rate");
		System.out.println("----------------------------");
		NLR(root);
	}
	
	/**
	 * recursively goes through tree printing node according to NLR 
	 * principal: Print node -> Go left -> Go right
	 * 
	 * @param tempNode - node to be recursively printed
	 */
	public void NLR(Node tempNode) {
		if(tempNode != null) {
			tempNode.PrintNode();
			
			NLR(tempNode.left);
			
			NLR(tempNode.right);
		}
	}
	
	/**
	 * Calls a recursive function using the root class.
	 */
	public void printPostorder() {
		System.out.println("Postorder traversal: ");
		System.out.println("Name              Death Rate");
		System.out.println("----------------------------");
		LRN(root);
	}
	
	/**
	 * recursively goes through tree printing node according to x 
	 * principal: Go left -> Go right -> Print node
	 * 
	 * @param tempNode - node to be recursively printed
	 */
	public void LRN(Node tempNode) {
		if(tempNode != null) {
			
			LRN(tempNode.left);
			
			LRN(tempNode.right);
			
			tempNode.PrintNode();
		}
	}

	/**
	 * follows path from root to state, printing the node as it travels
	 * down the tree.
	 * 
	 * @param name - name of the state to find and print path to
	 */
	public void printPathTo(String name) {
		Node traverse = root;
		
		//confirms that state exists in tree first
		if(find(name) != -1) {
			
			//prints path to reach that tree
			while(!traverse.stateName.equalsIgnoreCase(name)) {
				System.out.print(traverse.stateName + " -> ");
				
				if(traverse.stateName.compareToIgnoreCase(name) > 0) {
					traverse = traverse.left;
					
				}
				else if(traverse.stateName.compareToIgnoreCase(name) < 0) {
					traverse = traverse.right;
				}
				if(traverse == null)
					break;
			}

			System.out.print(traverse.stateName);
		}
	}
	
	/**
	 * uses Morris traversal to read states and insert their data into 
	 * the array, the array is sorted on input with states closer to the top
	 * being pushed to the bottom(of the array) until removed. Array is
	 * then printed.
	 * 
	 * @param c - How many states you want to show
	 */
	public void printBottomStates(int c) {
		
		//return if empty or invalid
		if (root == null || c < 1)
			return;
		
		Node[] lowList = new Node[c];
		boolean inList = false;
		
		Node tempNode;
		Node currentNode = root;
		
		//fills start array with -1 to avoid null pointers
		for(int i = 0; i < lowList.length; i++) {
			lowList[i] = new Node();
			lowList[i].stateName = "nullVar";
			lowList[i].deathRate = -1;
		}
 
        currentNode = root;
        while (currentNode != null) {
        	
            if (currentNode.left == null) {
            	
            	inList = false;
            	//checks and inputs state into array
            	for(int i = 0; i < lowList.length; i++) {
                	
            		for(int k = 0; k < lowList.length; k++) {
            			if(lowList[k] == currentNode) {
            				inList = true;
            			}
            		}
            		
                	if(!inList && lowList[i].deathRate < currentNode.deathRate) {
                		
                		for(int j = (lowList.length - 1); j > i; j--) {
                			
                			lowList[j] = lowList[j - 1];
                			
                		}
                		lowList[i] = currentNode;
                		
                	}
                }
                
                currentNode = currentNode.right;
            }
            else {
                //find rightmost node on left of current
                tempNode = currentNode.left;
                while (tempNode.right != null && tempNode.right != currentNode) {
                	tempNode = tempNode.right;
                }
 
                //links currentNode to the right of tempNode placing that branch on left side
                if (tempNode.right == null) {
                	tempNode.right = currentNode;
                    currentNode = currentNode.left;
                }
 
                //fixes the modifications to the tree
                else
                {
                    tempNode.right = null;
                	
                	inList = false;
                	//checks and inputs state into array
                	for(int i = 0; i < lowList.length; i++) {
                    	
                		for(int k = 0; k < lowList.length; k++) {
                			if(lowList[k] == currentNode) {
                				inList = true;
                			}
                		}
                		
                    	if(!inList && lowList[i].deathRate < currentNode.deathRate) {
                    		
                    		for(int j = (lowList.length - 1); j > i; j--) {
                    			
                    			lowList[j] = lowList[j - 1];
                    			
                    		}
                    		lowList[i] = currentNode;
                    		
                    	}
                    	
                    }
                    
                    currentNode = currentNode.right;
                }
            }
        }
        System.out.println("Bottom " + c + " states regarding DR: ");
		System.out.println("Name              Death Rate");
		System.out.println("----------------------------");
        
        for(int i = 0; i < lowList.length; i++) {
        	
        	if(lowList[i].deathRate != -1) {
        		lowList[i].PrintNode();
        	}
        	
        }
        
	}
	
	/**
	 * uses Morris traversal to read states and insert their data into 
	 * the array, the array is sorted on input with states closer to the bottom
	 * being pushed to the bottom(of the array) until removed. Array is
	 * then printed.
	 * 
	 * @param c - How many states you want to show
	 */
	public void printTopStates(int c) {
		
		//return if empty or invalid
		if (root == null || c < 1)
			return;
		
		Node[] lowList = new Node[c];
		boolean inList = false;
		
		Node tempNode;
		Node currentNode = root;
		
		//fills start array with -1 to avoid null pointers
		for(int i = 0; i < lowList.length; i++) {
			lowList[i] = new Node();
			lowList[i].stateName = "nullVar";
			lowList[i].deathRate = -1;
		}
		
 
        currentNode = root;
        while (currentNode != null) {
        	
            if (currentNode.left == null) {
            	
            	inList = false;
            	//checks and inputs state into array
            	for(int i = 0; i < lowList.length; i++) {
                	
            		for(int k = 0; k < lowList.length; k++) {
            			if(lowList[k] == currentNode) {
            				inList = true;
            			}
            		}
            		
                	if(!inList && (lowList[i].deathRate > currentNode.deathRate || lowList[i].deathRate == -1)) {
                		
                		for(int j = (lowList.length - 1); j > i; j--) {
                			
                			lowList[j] = lowList[j - 1];
                			
                		}
                		lowList[i] = currentNode;
                		
                	}
                }
                
                currentNode = currentNode.right;
            }
            else {
                //find rightmost node on left of current
                tempNode = currentNode.left;
                while (tempNode.right != null && tempNode.right != currentNode) {
                	tempNode = tempNode.right;
                }
 
                //links currentNode to the right of tempNode placing that branch on left side
                if (tempNode.right == null) {
                	tempNode.right = currentNode;
                    currentNode = currentNode.left;
                }
 
                //fixes the modifications to the tree
                else
                {
                    tempNode.right = null;
                	
                	inList = false;
                	//checks and inputs state into array
                	for(int i = 0; i < lowList.length; i++) {
                    	
                		for(int k = 0; k < lowList.length; k++) {
                			if(lowList[k] == currentNode) {
                				inList = true;
                			}
                		}
                		
                    	if(!inList && (lowList[i].deathRate > currentNode.deathRate || lowList[i].deathRate == -1)) {
                    		
                    		for(int j = (lowList.length - 1); j > i; j--) {
                    			
                    			lowList[j] = lowList[j - 1];
                    			
                    		}
                    		lowList[i] = currentNode;
                    		
                    	}
                    	
                    }
                    
                    currentNode = currentNode.right;
                }
            }
        }
        System.out.println("Top " + c + " states regarding DR: ");
		System.out.println("Name              Death Rate");
		System.out.println("----------------------------");
        
        for(int i = 0; i < lowList.length; i++) {
        	if(lowList[i].deathRate != -1) {
        		lowList[i].PrintNode();
        	}
        }
        
	}
}
