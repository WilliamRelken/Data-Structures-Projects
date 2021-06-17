import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
* COP 3530: Project 4 – Binary Search Trees
* <p>
* Creates a Binary search tree that is filled with data 
* taken from the state class during the process of
* reading the States4.csv file 
* <p> 
* Several options are presented to the user:
* 	1. Print inorder
* 	2. Print preorder
* 	3. Print postorder
*	4. Delete a state
*	5. Search and print path for a state
*	6. Print bottom states
*	7. Print top states
*	8. Exit
*
* @author William Relken
* @version 03/26/2021
*/
public class Project4 {

	public static void main(String[] args) {
		BinarySearchTree BST = new BinarySearchTree();
		int menu = 0;
		String temp;
		Scanner input = new Scanner(System.in);
		boolean fileRead;
		
		do {
			System.out.print("Please input the file to parse(include file type): ");
			String fileName = input.next();
			fileRead = ScanFile(fileName, BST);
		} while(fileRead != true);
		
		while(menu != 8) {
			input = new Scanner(System.in);
			menu = 0;
			
			System.out.println(" ");
			System.out.println("1) Print tree inorder");
			System.out.println("2) Print tree preorder");
			System.out.println("3) Print tree postorder");
			System.out.println("4) Delete a state for a given name");
			System.out.println("5) Search and print a state and its path for a given name");
			System.out.println("6) Print bottom states regarding DR");
			System.out.println("7) Print top states regarding DR");
			System.out.println("8) Exit");
			System.out.println(" ");
			System.out.print("Enter your choice: ");
			
			while(menu < 1 || menu > 8) {
				input = new Scanner(System.in);
				try {
					menu = input.nextInt();
					
					if(menu < 1 || menu > 8)
						System.out.print("Invalid choice, enter 1-8: ");
					
				} catch(InputMismatchException e) {
					System.out.print("Invalid choice, enter 1-8: ");
					menu = 0;
					continue;
				}
			}
			
			//consuming leftover newline so nextLine() can work
			input.nextLine();
				
			try {
				
				switch(menu) {
				case 1:
					BST.printInorder();
					break;
				case 2:
					BST.printPreorder();
					break;
				case 3:
					BST.printPostorder();
					break;
				case 4:
					System.out.print("Enter state name: ");
					temp = input.nextLine();
					BST.delete(temp);
					break;
				case 5:
					System.out.print("Enter state name: ");
					temp = input.nextLine();
					BST.printPathTo(temp);
					break;
				case 6:
					System.out.print("Enter the number of states: ");
					BST.printBottomStates(input.nextInt());
					break;
				case 7:
					System.out.print("Enter the number of states: ");
					BST.printTopStates(input.nextInt());
					break;
				
				}
			} catch (InputMismatchException e) {
				continue;
			}
			
		}
		
	}
	
	/**
	* Takes the input parameter fileName and searches for it within the
	* project folder. Data is assigned to a temporary States object before
	* the name and death rate are inserted into the BST.The returning 
	* statement is a boolean representing either a failed process 
	* or a success.
	*
	* @param fileName - The name of the file to be located
	* @param BST - Binary search tree where data will be inserted
	* @return scanCheck - Method success/failure
	*/
	public static boolean ScanFile(String fileName, BinarySearchTree BST) {
		int entries = 0;
		boolean result = false;
		
		try {
			File toScan = new File(fileName);
			Scanner input = new Scanner(toScan);
			input.useDelimiter(",|\n");
			
//			exists to negate useless line in file
			String temp = input.nextLine();
			
			for(int i = 0; input.hasNext(); i++, entries++) {
				State tempState = new State();
				
				tempState.setState(input.next());
				tempState.setCapitol(input.next());
				tempState.setRegion(input.next());
				tempState.setHouseSeats(input.nextInt());
				tempState.setPopulation(input.nextInt());
				tempState.setCovidCases(input.nextInt());
				tempState.setCovidDeaths(input.nextInt());
				tempState.setMedianHouseholdIncome(input.nextInt());
				tempState.setCrimeRate(input.nextFloat());
				tempState.setCFR();
				tempState.setCaseRate();
				tempState.setDeathRate();
				
				BST.insert(tempState.getState(), (double)tempState.getDeathRate());
			}
			
			result = true;
			
		} catch (IOException e) {
			System.out.println("Could not read file.");
			
		}
		return result;
	}

}
