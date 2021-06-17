
/**
* Detailed description of the class.
*
* @author William Relken
* @version 1/27/2021
*/

public class States {
	public static String state;
	public static String capitol;
	public static String region;
	public static int houseSeats;
	public static long population;
	public static int covidCases;
	public static int covidDeaths;
	public static int medianHouseholdIncome;
	public static float crimeRate;
	
	public void PrintData() {
		
		//System.out.printf("%s | %s | %s | %d | %l | %d | %d | %d | %f ", state, capitol, region, houseSeats, population, covidCases, covidDeaths, medianHouseholdIncome, crimeRate);
		
		System.out.print(state + " ");
		System.out.print(capitol + " ");
		System.out.print(region + " ");
		System.out.print(houseSeats + " ");
		System.out.print(population + " ");
		System.out.print(covidCases + " ");
		System.out.print(covidDeaths + " ");
		System.out.print(medianHouseholdIncome + " ");
		System.out.print(crimeRate + "\n");
		
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public String getCapitol() {
		return capitol;
	}

	public void setCapitol(String capitol) {
		this.capitol = capitol;
	}


	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}


	public int getHouseSeats() {
		return houseSeats;
	}

	public void setHouseSeats(int houseSeats) {
		this.houseSeats = houseSeats;
	}


	public long getPopulation() {
		return population;
	}

	public void setPopulation(long population) {
		this.population = population;
	}


	public int getCovidCases() {
		return covidCases;
	}

	public void setCovidCases(int covidCases) {
		this.covidCases = covidCases;
	}


	public int getCovidDeaths() {
		return covidDeaths;
	}

	public void setCovidDeaths(int covidDeaths) {
		this.covidDeaths = covidDeaths;
	}

	
	public int getMedianHouseholdIncome() {
		return medianHouseholdIncome;
	}

	public void setMedianHouseholdIncome(int medianHouseholdIncome) {
		this.medianHouseholdIncome = medianHouseholdIncome;
	}


	public float getCrimeRate() {
		return crimeRate;
	}

	public void setCrimeRate(float crimeRate) {
		this.crimeRate = crimeRate;
	}

	
	
	
	
	
}
