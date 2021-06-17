import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
* prompts user for a csv file, adds the state objects to 4 priority queues 
* then removes them into a stack
*
* @author William Relken
* @version 2/18/2021
*/

public class Project2 {
	//		States2.csv

	public static void main(String[] args) {

		State[] database = new State[50];
		Stack stateStack = new Stack(database.length);
		int[] PQSizes = {0,0,0,0};
		
		PriorityQ VGood;
		PriorityQ good;
		PriorityQ fair;
		PriorityQ poor;
		
		Scanner input = new Scanner(System.in);
		boolean fileRead;
		
		do {
			System.out.print("Please input the file to parse(include file type): ");
			String fileName = input.next();
			fileRead = ScanFile(fileName, database);
		} while(fileRead != true);
		
		
//		this only serves to assign sizes to the priorityQs
		for(int i = 0; i < database.length; i++) {
//			each if corresponds with a priorityQ size in order from VGood to poor
			if(database[i].getDeathRate() < 50) 
				PQSizes[0]++;
			else if(database[i].getDeathRate() >= 50 && database[i].getDeathRate() < 100)
				PQSizes[1]++;
			else if(database[i].getDeathRate() >= 100 && database[i].getDeathRate() < 150)
				PQSizes[2]++;
			else
				PQSizes[3]++;
		}
		
		VGood = new PriorityQ(PQSizes[0]);
		good = new PriorityQ(PQSizes[1]);
		fair = new PriorityQ(PQSizes[2]);
		poor = new PriorityQ(PQSizes[3]);
		
//		insertion of states into priorityQ as doing it inside scanfile() leads to uninitialization issues
		for(int i = 0; i < database.length; i++) {
			
			if(database[i].getDeathRate() < 50) 
				VGood.InsertState(database[i]);
			else if(database[i].getDeathRate() >= 50 && database[i].getDeathRate() < 100)
				good.InsertState(database[i]);
			else if(database[i].getDeathRate() >= 100 && database[i].getDeathRate() < 150)
				fair.InsertState(database[i]);
			else
				poor.InsertState(database[i]);
		}
		
		
		System.out.println("\nPOOR priority queue contents: ");
		poor.PrintQueue();
		System.out.println("\nFAIR priority queue contents: ");
		fair.PrintQueue();
		System.out.println("\nGOOD priority queue contents: ");
		good.PrintQueue();
		System.out.println("\nVGOOD priority queue contents: ");
		VGood.PrintQueue();
		
		while(!poor.IsEmpty()) {
			stateStack.Push(poor.Remove());
		}
		while(!fair.IsEmpty()) {
			stateStack.Push(fair.Remove());
		}
		while(!good.IsEmpty()) {
			stateStack.Push(good.Remove());
		}
		while(!VGood.IsEmpty()) {
			stateStack.Push(VGood.Remove());
		}
		
		stateStack.PrintStack();
		
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
	public static boolean ScanFile(String fileName, State[] database) {
		int entries = 0;
		boolean result = false;
		
		try {
			File toScan = new File(fileName);
			Scanner input = new Scanner(toScan);
			input.useDelimiter(",|\r\n");
			
//			exists to negate useless line in file
			String temp = input.nextLine();
			
			for(int i = 0; i < database.length && input.hasNext(); i++, entries++) {
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
				database[i] = tempState;
			}
			
			result = true;
			
		} catch (IOException e) {
			System.out.println("Could not read file.");
			
		}
		return result;
	}
	
}
