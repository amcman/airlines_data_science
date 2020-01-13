
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handles the analysis of each question asked in the assignment sheet.
 * The method for each question appears in the same order as the questions were
 * asked in the assignment sheet. 
 * @author andrew.mcmanus
 *
 */
public class DataAnalysis {

	/**
	 * Returns the carrier ID of the airline with the highest percentage 
	 * of cancellations, along with what that percentage is.
	 * @param dataMap A hash map of airline data
	 * @return A string value with the airline carrier ID and 
	 * the percentage of cancellations.
	 */
	public String cancelledFlights(HashMap <Integer, DataInstantiator> dataMap) {
		ArrayList<String> airlineNames = new ArrayList<String>();
		ArrayList<Double> airlineCancellations = new ArrayList<Double>();
		ArrayList<Double> cancellationPercentage = new ArrayList<Double>();
		//Obtain unique airline carrier ID names
		for(Integer key : dataMap.keySet()) {
			DataInstantiator data = dataMap.get(key);
			if(!airlineNames.contains(data.getUniqueCarrier())) {
				airlineNames.add(data.getUniqueCarrier());
			}	
		}
		//Determine number of cancellations per airline
		for(int i = 0; i < airlineNames.size(); i++) {
			double airlineCount = 0;
			for(Integer key : dataMap.keySet()) {
				DataInstantiator data = dataMap.get(key);
				if(airlineNames.get(i).equals(data.getUniqueCarrier()) && 
				   data.getCancelled() > 0) {
						airlineCount++;
				}	
			}
			airlineCancellations.add(airlineCount);
		}
		//Get total number of flights per carrier & % of cancellations
		for(int c = 0; c < airlineNames.size(); c++) {
			double airlineTotalFlightCount = 0;
			for(Integer key : dataMap.keySet()) {
				DataInstantiator data = dataMap.get(key);
				if(airlineNames.get(c).equals(data.getUniqueCarrier())) {
					airlineTotalFlightCount++;
				}	
			}
			double cancellationPercForIndividualFlight = 
				(airlineCancellations.get(c) / airlineTotalFlightCount) * 100; 
			cancellationPercentage.add(cancellationPercForIndividualFlight);
		}
		//Final loop for getting highest percentage and creating the output string
		String largestPercentageOfCancellations = "";
		for(int a = 0; a < cancellationPercentage.size(); a++) {
			int max = 0;
			for(int m = 0; m < cancellationPercentage.size(); m++) {
				if(cancellationPercentage.get(a) > cancellationPercentage.get(m)){
					max += 1;
				}
			}
			if(max == cancellationPercentage.size() - 1) {
				largestPercentageOfCancellations = airlineNames.get(a) + "," + 
				cancellationPercentage.get(a) + "%";
			}
		}
		return largestPercentageOfCancellations;
	}
	/**
	 * Calculates the most common reason for flight cancellations across all 
	 * airlines.
	 * @param dataMap A data frame with airline data.
	 * @return The code for the most common reason for cancellation.
	 */
	public String cancellationReasons(HashMap<Integer,DataInstantiator> dataMap)
		{
		ArrayList<String> cancellationReasons = new ArrayList<String>();		
		ArrayList<Integer> cancellationReasonCount = new ArrayList<Integer>();
		//Creates an arraylist of unique cancellation codes
		for(Integer key : dataMap.keySet()) {
			DataInstantiator data = dataMap.get(key);
			if(data.getCancelled() == 1 && 
					!cancellationReasons.contains(data.getCancellationCode())) {
				cancellationReasons.add(data.getCancellationCode());
			}
		}
		for(int c = 0; c < cancellationReasons.size(); c++) {
			int codeCount = 0;
			for(Integer key : dataMap.keySet()) {
				DataInstantiator data = dataMap.get(key);
				if(cancellationReasons.get(c).equals(data.getCancellationCode())){
					codeCount++;
				}
			}
			cancellationReasonCount.add(codeCount);
		}
		//Helper method for finding max value in a set of integers
		HelperMethods helper = new HelperMethods();
		int maxIndex = helper.maxValue(cancellationReasonCount);
		String mostCancellations = cancellationReasons.get(maxIndex);		
		return mostCancellations;
	}
		
	/**
	 * Calculates which plane has flown the furthest cumulative distance.
	 * @param dataMap A hashmap contianing airline data.
	 * @return The tail number of the plane that flew the furthest.
	 */
	public String planeThatFlewTheFurthest(HashMap<Integer,DataInstantiator> 
										   dataMap) {
		ArrayList<String> uniqueTailNums = new ArrayList<String>();
		HashMap<String, Integer> flightDistancesMap = new HashMap<String, Integer>();
		
		for(Integer key : dataMap.keySet()) {
			DataInstantiator data = dataMap.get(key);
			if(!uniqueTailNums.contains(data.getTailNum())) {
				flightDistancesMap.put(data.getTailNum(),data.getDistance());
				uniqueTailNums.add(data.getTailNum());
			}
			else {
				int newDistance = data.getDistance() + flightDistancesMap.get(data.getTailNum());
				flightDistancesMap.replace(data.getTailNum(),newDistance);
			}
		}
		int maxValue = 0;
		String maxPlane = "";
		for(String key2 : flightDistancesMap.keySet()) {
			int values = flightDistancesMap.get(key2);
			if(values > maxValue) {
				maxValue = values;
				maxPlane = key2;
			}
		}
		return maxPlane;
	}

	/**
	 * Returns the airport ID for the airport that is overall most active.
	 * @param dataMap A hash map of airline data.
	 * @return An integer value for the ID of the most active airport.
	 */
	public int mostActiveAirport(HashMap<Integer,DataInstantiator> dataMap) {
		ArrayList<Integer> airportIDs = new ArrayList<Integer>();
		HashMap<Integer,Integer> airportNumberOfFlights = new HashMap<Integer,Integer>();
		HelperMethods helper = new HelperMethods();
		//Get a list of each unique airport ID (might need to loop through
		for (Integer key : dataMap.keySet()) {
			DataInstantiator data = dataMap.get(key);
			if (!airportIDs.contains(data.getOriginAirportId())) {
				airportNumberOfFlights.put(data.getOriginAirportId(), 1);
				airportIDs.add(data.getOriginAirportId());
			} else {
				int newFlightCount = 1 + airportNumberOfFlights.get(data.getOriginAirportId());
				airportNumberOfFlights.replace(data.getOriginAirportId(), newFlightCount);
			}
		}
		for (Integer key : dataMap.keySet()) {
			DataInstantiator data = dataMap.get(key);
			if (!airportIDs.contains(data.getDestAirportId())) {
				airportNumberOfFlights.put(data.getDestAirportId(), 1);
				airportIDs.add(data.getDestAirportId());
			} else {
				int newFlightCount = 1 + airportNumberOfFlights.get(data.getDestAirportId());
				airportNumberOfFlights.replace(data.getDestAirportId(), newFlightCount);
			}
		}
		int maxValue = 0;
		int maxAirportCode = 0;
		for(Integer key2 : airportNumberOfFlights.keySet()) {
			int values = airportNumberOfFlights.get(key2);
			if(values > maxValue) {
				maxValue = values;
				maxAirportCode = key2;
			}
		}
		return maxAirportCode; 
}
	
	/**
	 * Determines the airport that is the biggest source of flights.
	 * @param dataMap A hashmap with source scores from the HelperMethods
	 * class. 
	 * @return The airport code representing the airport 
	 * that is the biggest source of flights.
	 */
	public int biggestSourceAirport(HashMap<Integer,Integer> sourceScores) {
			
			int maximumValue = 0;
			int maximumAirport = 0;
			for(Integer key3 : sourceScores.keySet()) {
				int value = sourceScores.get(key3);
				if(value > maximumValue) {
					maximumValue = value;
					maximumAirport = key3;
				}
			}
			return maximumAirport;
	}

	/**
	 * Determines the airport that is the biggest sink of flights.
	 * @param sourceScores A hashmap with source scores from the HelperMethods
	 * class. 
	 * @return The airport code representing the airport that is 
	 * the biggest sink of flights.
	 */
	public int biggestSinkAirport(HashMap<Integer,Integer> sourceScores) {
		int minimumValue = 0;
		int minimumAirport = 0;
		for(Integer key3 : sourceScores.keySet()) {
			int value = sourceScores.get(key3);
			if(value < minimumValue) {
				minimumValue = value;
				minimumAirport = key3;
			}
		}
		return minimumAirport;
	}
	
	/**
	 * Finds the number of departure OR arrival delays for AA flights that
	 * were greater than or equal to 60 minutes. A delay in departure AND arrival
	 * for the same flight is not double counted.
	 * @param dataMap A hash map with airline data.
	 * @return An integer value with the number of delays >= 60 min.
	 */
	public int numberOfAAFightsDelayed60Min(HashMap<Integer,DataInstantiator> dataMap) {
		HelperMethods helper = new HelperMethods();
		int delaysOver60Min = 0;
		for(Integer key : dataMap.keySet()) {
			DataInstantiator data = dataMap.get(key);
			if(data.getUniqueCarrier().equals("AA") && (data.getArrDelay() >= 60 || 
					data.getDepDelay() >= 60)){
				delaysOver60Min++;
			}
		}
		return delaysOver60Min;
	}
	/**
	 * Determines the day, tail number, and delay time of the flight that
	 * had the largest delay but was made up (either arrived on time or early).
	 * @param dataMap A hash map of airline data.
	 * @return The day, tail number, and delay time of the flight.
	 */
	public String largestDelayMadeUp(HashMap<Integer,DataInstantiator> dataMap) {
		String maxTailNum= "TailNum";
		int maxDepartureDelayMadeUp = 0;
		int maxDate = 0;
		for(Integer key : dataMap.keySet()) {
			DataInstantiator data = dataMap.get(key);
			if(data.getDepDelay() > maxDepartureDelayMadeUp && data.getArrDelay() <= 0) {
				maxDepartureDelayMadeUp = data.getDepDelay();
				maxDate = data.getDayOfMonth();
				maxTailNum = data.getTailNum();
			}
		}
		String bestMakeUpTime = maxDate + ","+ maxDepartureDelayMadeUp + "," + maxTailNum;
		return bestMakeUpTime;
	}
	
	/**
	 * Finds the shortest flight that is run out of Boston Massachusetts and 
	 * returns the tail number, distance, and destination. This is question 9 where
	 * we were instructed to create our own question and solution.
	 * @param dataMap A hashmap with airline data.
	 * @return A string that provides information on the shortest flight from Boston.
	 */
	public String shortestFlight(HashMap<Integer,DataInstantiator> dataMap) {
		
		int minimumDistance = 200;
		String tailNumber = "";
		String state = "";
		String airport = "";
		String destState = "";
		String destAirport = "";
		
		for(Integer key : dataMap.keySet()) {
			DataInstantiator data = dataMap.get(key);
			if(data.getOrigin().equals("BOS") && data.getDistance() < minimumDistance) {
				minimumDistance = data.getDistance();
				tailNumber = data.getTailNum();
				state = data.getOriginStateName();
				airport = data.getOrigin();
				destAirport = data.getDest();
				destState = data.getDestStateName();
			}
		}
		String shortestFlight = "Shortest Flight: " + tailNumber + " From "  + airport + " in "
								+ state + " to " + destAirport + " in " + destState + " "
								+ "for a total distance of " + minimumDistance + " miles.";
		return shortestFlight;
	}
	
	/**
	 * Runs all the methods, adds the answer to each to the output file and then
	 * outputs that text file to the user's directory where this program is stored.
	 * @param fileName
	 */
	public static void runnerMethod(String fileName) {
		
		DataReader readInData = new DataReader();
		FormattedOutput format = new FormattedOutput();
		HashMap<Integer, DataInstantiator> p = readInData.dataReadMethod(fileName);
		DataAnalysis datAnalysis = new DataAnalysis();
		HelperMethods helper = new HelperMethods();
		String question1 = datAnalysis.cancelledFlights(p);
		String question2 = datAnalysis.cancellationReasons(p);
		HashMap<Integer,DataInstantiator> filteredData = helper.dataFilter(p);
		String question3 = datAnalysis.planeThatFlewTheFurthest(filteredData);
		int question4 = datAnalysis.mostActiveAirport(filteredData);
		HashMap<Integer,Integer> sourceData = helper.calculateAirportScores(filteredData);
		int question5 = datAnalysis.biggestSourceAirport(sourceData);
		int question6 = datAnalysis.biggestSinkAirport(sourceData);
		int question7 = datAnalysis.numberOfAAFightsDelayed60Min(filteredData);
		String question8 = datAnalysis.largestDelayMadeUp(filteredData);
		String question9 = datAnalysis.shortestFlight(filteredData);
		format.addAnswer(1, question1);
		format.addAnswer(2, question2);
		format.addAnswer(3, question3);
		format.addAnswer(4, question4);
		format.addAnswer(5, question5);
		format.addAnswer(6, question6);
		format.addAnswer(7, question7);
		format.addAnswer(8, question8);
		format.addAnswer(9, question9);
		format.writeAnswers();
		System.out.println("ANALYSIS COMPLETE.\n"
						 + "Check your directory for answers in an answers.txt file.");
	}
	
}
