import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
* prompts user for a csv file, adds the state objects to a stack, removes them from the
* stack into the priority queue and prompts the user 
*
* @author William Relken
* @version 2/18/2021
*/

public class Project3 {
	//		States3.csv

	public static void main(String[] args) {

		Stack stateStack = new Stack();
		PriorityQ stateQ = new PriorityQ();
		int menu = 0;
		float range1 = 0;
		float range2 = 0;
		
		Scanner input = new Scanner(System.in);
		boolean fileRead;
		
		do {
			System.out.print("Please input the file to parse(include file type): ");
			String fileName = input.next();
			fileRead = ScanFile(fileName, stateStack);
		} while(fileRead != true);
		
		stateStack.PrintStack();
		
		while(!stateStack.IsEmpty()) {
			
			stateQ.InsertState(stateStack.Pop());
		}
		
		stateQ.PrintStack();
		
		while(menu != 3) {
			Scanner menuInput = new Scanner(System.in);
			System.out.println("\n[1] Enter a DR interval for deletions.");
			System.out.println("[2] Print Priority Queue.");
			System.out.println("[3] Exit Program.");
			System.out.print("Enter Choice: ");
			
			try {
				menu = menuInput.nextInt();
				if(menu < 1 || menu > 3) {
					System.out.println("Invalid choice, enter 1-3. ");
				}
				
			} catch(InputMismatchException e) {
				System.out.print("Invalid choice, enter 1-3. ");
				menu = 0;
				continue;
			}
			
			switch(menu) {
				
				case 1:
					try {
						System.out.print("Enter DR interval in format X Y : ");
						range1 = menuInput.nextFloat();
						range2 = menuInput.nextFloat();
					} catch(InputMismatchException e) {
						continue;
					}
					
					while(range1 > range2){
						Scanner errMenuInput = new Scanner(System.in);
						System.out.print("Invalid interval, enter in format X Y : ");
						try {
							range1 = errMenuInput.nextFloat();
							range2 = errMenuInput.nextFloat();
						} catch(InputMismatchException e) {
							continue;
						}	
					}
					
					if(stateQ.IntervalDelete(range1, range2))
						System.out.println("States of priority queue with DRs in " + range1 + " and " + range2 + " were deleted.");
					else
						System.out.println("no states within " + range1 + " and " + range2 + " were found");
					
					menu = 0;
					break;
				case 2:
					stateQ.PrintStack();
					break;
			}
		}
		
		System.out.println("Have a nice day. ");
	}
	
	/**
	* Takes the input parameter fileName and searches for it within the
	* project folder. Data is assigned to a temporary States object before
	* being assigned to database parameter.The returning statement is a boolean 
	* representing either a failed process or a success.
	*
	* @param fileName - The name of the file to be located
	* @param database - a database to hold all the states
	* @return scanCheck - Method success/failure
	*/
	public static boolean ScanFile(String fileName, Stack stateStack) {
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
				
				if(tempState.getDeathRate() < 150)
					stateStack.Push(tempState);
			}
			
			result = true;
			
		} catch (IOException e) {
			System.out.println("Could not read file.");
			
		}
		return result;
	}
	
}
