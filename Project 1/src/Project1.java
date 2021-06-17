import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
* COP 3530: Project 1 – Array Searches and Sorts
* <p>
* Description of the class using as many lines as needed
* with <p> between paragraphs. Including descriptions of the
* input required and output generated.
*
* @author William Relken
* @version 1/27/2021
*/

public class Project1 {
	
	/**
	* Description of the purpose of the method, the meaning of the
	* input parameters (if any) and the meaning of the return values
	* (if any).
	*
	*/
	public static void main(String[] args) {
		ArrayList<States> mainDatabase = new ArrayList<States>();
		Scanner input = new Scanner(System.in);
		int userInput = 0;
		boolean fileRead;
		
		do {
			System.out.println("Please input the file to parse(include file type): ");
//			String fileName = input.next();
			String fileName = "State1.csv";
			fileRead = ScanFile(fileName, mainDatabase);
		} while(fileRead != true);
		
		
		
		
//		while(userInput != 7) {
//			System.out.println("Please select from the options: ");
//			System.out.println("[1] States Report");
//			System.out.println("[2] Sort by Name");
//			System.out.println("[3] Sort by COVID-19 case fatality rate");
//			System.out.println("[4] Sort by Median  Household Income");
//			System.out.println("[5] Search for State");
//			System.out.println("[6] Show Spearmans p Correlation Matrix");
//			System.out.println("[7] Quit");
//			userInput = input.nextInt();
//			
//			if(userInput == 1) {
//				mainDatabase.get(0).PrintData();
//				mainDatabase.get(1).PrintData();
//			}
//			
//		}
		
		for(int i = 0; i < 50; i++) {
			mainDatabase.get(i).PrintData();
		}
		
		

	}
	
	
	/**
	* Takes the input parameter fileName and searches for it within the
	* project folder. Data is assigned to a temporary States object before
	* being assigned to database parameter.The returning statement is a boolean 
	* representing either a failed process or a success.
	*
	* @param fileName - The name of the file to be located
	* @param database - Database to store States objects
	* @return scanCheck - Method success/failure
	*/
	public static boolean ScanFile(String fileName, ArrayList<States> database) {
		
		boolean result = false;
		Scanner input;
		File toScan = new File(fileName);
		try {
			input = new Scanner(toScan);
		
			input.useDelimiter(",|\n");
			
//			exists to negate useless line in file
			String test = input.nextLine();
			States tempState = new States();
			
			while(input.hasNext()) {
				
				tempState.setState(input.next());
				tempState.setCapitol(input.next());
				tempState.setRegion(input.next());
				tempState.setHouseSeats(input.nextInt());
				tempState.setPopulation(input.nextLong());
				tempState.setCovidCases(input.nextInt());
				tempState.setCovidDeaths(input.nextInt());
				tempState.setMedianHouseholdIncome(input.nextInt());
				tempState.setCrimeRate(input.nextFloat());
				
				database.add(tempState);
				
			}
			result = true;
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return result;
	}

	
	public static void StateReport(ArrayList<States> database) {
		
	}
}
