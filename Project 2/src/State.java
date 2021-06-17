
/**
* Allows the storage and viewing of State data along with minor 
* calculations to get other necessary data.
*
* @author William Relken
* @version 2/5/2021
*/

public class State {
	
	private String state;
	private String capitol;
	private String region;
	private int houseSeats;
	private int population;
	private int covidCases;
	private int covidDeaths;
	private int medianHouseholdIncome;
	private float crimeRate;
	private float caseFatalityRate;
	private float caseRate;
	private float deathRate;
	private int caseRank;
	private int deathRank;
	private int MHIRank;
	private int VCRRank;
	
	/**
	 * Prints the State data on a single line.
	 *
	 */
	public void PrintData() {
		
		System.out.printf("%-15s | %-10d | %10.1f | %10.6f | %10.2f | %10.2f |\n", state, medianHouseholdIncome, crimeRate, caseFatalityRate, caseRate, deathRate);
		
		
	}
	
	/**
	 * Sets the covid fatality rate.
	 *
	 */
	public void setCFR() {
		caseFatalityRate = ((float)covidDeaths / (float)covidCases);
	}
	
	/**
	 * Gets the covid fatality rate.
	 *
	 * @return the covid fatality rate
	 */
	public float getCFR() {
		return caseFatalityRate;
	}
	
	/**
	 * Sets the case rate.
	 *
	 */
	public void setCaseRate() {
		caseRate = ((float)covidCases / (float)population) * 100000;
	}
	
	/**
	 * Gets the case rate.
	 *
	 * @return the case rate
	 */
	public float getCaseRate() {
		return caseRate;
	}
	
	/**
	 * Sets the death rate.
	 *
	 */
	public void setDeathRate() {
		deathRate = ((float)covidDeaths / (float)population) * 100000;
	}
	
	/**
	 * Gets the death rate.
	 *
	 * @return the death rate
	 */
	public float getDeathRate() {
		return deathRate;
	}
	
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}


	/**
	 * Gets the capitol.
	 *
	 * @return the capitol
	 */
	public String getCapitol() {
		return capitol;
	}

	/**
	 * Sets the capitol.
	 *
	 * @param capitol the new capitol
	 */
	public void setCapitol(String capitol) {
		this.capitol = capitol;
	}


	/**
	 * Gets the region.
	 *
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * Sets the region.
	 *
	 * @param region the new region
	 */
	public void setRegion(String region) {
		this.region = region;
	}


	/**
	 * Gets the house seats.
	 *
	 * @return the house seats
	 */
	public int getHouseSeats() {
		return houseSeats;
	}

	/**
	 * Sets the house seats.
	 *
	 * @param houseSeats the new house seats
	 */
	public void setHouseSeats(int houseSeats) {
		this.houseSeats = houseSeats;
	}


	/**
	 * Gets the population.
	 *
	 * @return the population
	 */
	public long getPopulation() {
		return population;
	}

	/**
	 * Sets the population.
	 *
	 * @param population the new population
	 */
	public void setPopulation(int population) {
		this.population = population;
	}


	/**
	 * Gets the covid cases.
	 *
	 * @return the covid cases
	 */
	public int getCovidCases() {
		return covidCases;
	}

	/**
	 * Sets the covid cases.
	 *
	 * @param covidCases the new covid cases
	 */
	public void setCovidCases(int covidCases) {
		this.covidCases = covidCases;
	}


	/**
	 * Gets the covid deaths.
	 *
	 * @return the covid deaths
	 */
	public int getCovidDeaths() {
		return covidDeaths;
	}

	/**
	 * Sets the covid deaths.
	 *
	 * @param covidDeaths the new covid deaths
	 */
	public void setCovidDeaths(int covidDeaths) {
		this.covidDeaths = covidDeaths;
	}

	
	/**
	 * Gets the median household income.
	 *
	 * @return the median household income
	 */
	public int getMedianHouseholdIncome() {
		return medianHouseholdIncome;
	}

	/**
	 * Sets the median household income.
	 *
	 * @param medianHouseholdIncome the new median household income
	 */
	public void setMedianHouseholdIncome(int medianHouseholdIncome) {
		this.medianHouseholdIncome = medianHouseholdIncome;
	}


	/**
	 * Gets the crime rate.
	 *
	 * @return the crime rate
	 */
	public float getCrimeRate() {
		return crimeRate;
	}

	/**
	 * Sets the crime rate.
	 *
	 * @param crimeRate the new crime rate
	 */
	public void setCrimeRate(float crimeRate) {
		this.crimeRate = crimeRate;
	}

	/**
	 * Gets the case rank.
	 *
	 * @return the case rank
	 */
	public int getCaseRank() {
		return caseRank;
	}

	/**
	 * Sets the case rank.
	 *
	 * @param caseRank the new case rank
	 */
	public void setCaseRank(int caseRank) {
		this.caseRank = caseRank;
	}

	/**
	 * Gets the death rank.
	 *
	 * @return the death rank
	 */
	public int getDeathRank() {
		return deathRank;
	}

	/**
	 * Sets the death rank.
	 *
	 * @param deathRank the new death rank
	 */
	public void setDeathRank(int deathRank) {
		this.deathRank = deathRank;
	}

	/**
	 * Gets the MHI rank.
	 *
	 * @return the MHI rank
	 */
	public int getMHIRank() {
		return MHIRank;
	}

	/**
	 * Sets the MHI rank.
	 *
	 * @param mHIRank the new MHI rank
	 */
	public void setMHIRank(int mHIRank) {
		MHIRank = mHIRank;
	}

	/**
	 * Gets the VCR rank.
	 *
	 * @return the VCR rank
	 */
	public int getVCRRank() {
		return VCRRank;
	}

	/**
	 * Sets the VCR rank.
	 *
	 * @param vCRRank the new VCR rank
	 */
	public void setVCRRank(int vCRRank) {
		VCRRank = vCRRank;
	}
	
}
