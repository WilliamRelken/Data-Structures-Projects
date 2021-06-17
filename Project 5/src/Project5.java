import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
* COP 3530: Project 5 – Hash Tables
* <p>
* Menu allowing user to load a .csv file to a hash table and
* complete operations on the hash table 
* <p>
*
* @author William Relken
* @version 04/15/2021
*/
public class Project5 {
	
	public static void main(String args[]) {
		
		HashTable table = new HashTable();
		State[] stateList = new State[50];
		int menu = 0;
		String temp;
		State tempState;
		Scanner input = new Scanner(System.in);
		boolean fileRead;
		
		do {
			System.out.print("Please input the file to parse(include file type): ");
			String fileName = input.next();
			fileRead = ScanFile(fileName, table, stateList);
		} while(fileRead != true);
		
		System.out.println("\nThere were " + stateList.length + " state records read into the hash table.");
		
		while(menu != 6) {
			input = new Scanner(System.in);
			menu = 0;
			
			System.out.println(" ");
			System.out.println("1) Print hash table");
			System.out.println("2) Delete a state of a given name");
			System.out.println("3) Insert a state of a given name");
			System.out.println("4) Search and print a state and its DR for a given name");
			System.out.println("5) Print numbers of empty cells and collisions");
			System.out.println("6) Exit");
			System.out.println(" ");
			System.out.print("Enter your choice: ");
			
			while(menu < 1 || menu > 6) {
				input = new Scanner(System.in);
				try {
					menu = input.nextInt();
					
					if(menu < 1 || menu > 6)
						System.out.print("Invalid choice, enter 1-6: ");
					
				} catch(InputMismatchException e) {
					System.out.print("Invalid choice, enter 1-6: ");
					menu = 0;
					continue;
				}
			}
			
			//consuming leftover newline so nextLine() can work
			input.nextLine();
				
			try {
				
				switch(menu) {
				case 1:
					table.display();
					break;
				case 2:
					System.out.print("Enter state name: ");
					temp = input.nextLine();
					tempState = getStateData(stateList, temp);
					if(tempState != null) {
						table.delete(tempState.getState(), tempState.getPopulation(), tempState.getCovidDeaths());
					}
					else {
						System.out.println(temp + " is not a state");
					}
					
					break;
				case 3:
					System.out.print("Enter state name: ");
					temp = input.nextLine();
					tempState = getStateData(stateList, temp);
					if(tempState != null) {
						table.insert(tempState.getState(), tempState.getPopulation(), tempState.getCovidDeaths());
					}
					else {
						System.out.println(temp + " is not a state");
					}
					
					break;
				case 4:
					System.out.print("Enter state name: ");
					temp = input.nextLine();
					tempState = getStateData(stateList, temp);
					if(tempState != null) {
						table.find(tempState.getState(), tempState.getPopulation(), tempState.getCovidDeaths());
					}
					else {
						System.out.println(temp + " is not a state");
					}
					
					break;
				case 5:
					table.printEmptyAndCollisions();
					
					break;
				
				}
			} catch (InputMismatchException e) {
				continue;
			}
		}
		
	}
	
	/**
	* Searches the state array parameter for the state with name matching
	* name parameter and returns that state object.
	*
	* @param state list - the array of state classes
	* @param name - Name of the state
	* @return State - the state class corresponding to the string parameter
	*/
	public static State getStateData(State[] stateList, String name) {
		State returnState = null;
		
		for(int i = 0; i < stateList.length; i++) {
			if(stateList[i].getState().equalsIgnoreCase(name)) {
				returnState = stateList[i];
				break;
			}
		}
		
		return returnState;
	}
	
	/**
	* Takes the input parameter fileName and searches for it within the
	* project folder. Data is assigned to a temporary States object before
	* being inserted into a hash table and an array of state objects.The returning 
	* statement is a boolean representing either a failed process 
	* or a success.
	*
	* @param fileName - The name of the file to be located
	* @param table - hash table where data will be stored
	* @param stateList - array of states
	* @return scanCheck - Method success/failure
	*/
	public static boolean ScanFile(String fileName, HashTable table, State[] stateList) {
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
				
				table.insert(tempState.getState(), (long)tempState.getPopulation(), (long)tempState.getCovidDeaths());
				stateList[i] = tempState;
			}
			
			result = true;
			
		} catch (IOException e) {
			System.out.println("Could not read file.");
			
		}
		return result;
	}
	
}
