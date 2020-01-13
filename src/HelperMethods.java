import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Provides helper methods for actions that are repeatedly performed in assignment 5.
 * Helps ensure I follow DRY.
 * @author andrew.mcmanus
 *
 */
public class HelperMethods {
	
	/**
	 * Finds the maximum value in an array list of integers and returns 
	 * the index value of the element.
	 * @param arrayList An array list of integers
	 * @return The index number of the maximum value.
	 */
	public int maxValue(ArrayList<Integer> arrayList) {
		int maximum = 0;
		for(int a = 0; a < arrayList.size(); a++) {
			int max = 0;
			for(int m = 0; m < arrayList.size(); m++) {
				if(arrayList.get(a) > arrayList.get(m)) {
					max += 1;
				}
			}
			if(max == arrayList.size() - 1) {
				maximum = a;
			}
		}
		return maximum;
	}
	
	/**
	 * Performs same actions as maxValue() but for doubles.
	 * @param arrayList An arrayList of doubles.
	 * @return The index of the maximum value.
	 */
	public int maxValueDoubles(ArrayList<Double> arrayList) {
		int maximum = 0;
		for(int a = 0; a < arrayList.size(); a++) {
			int max = 0;
			for(int m = 0; m < arrayList.size(); m++) {
				if(arrayList.get(a) > arrayList.get(m)) {
					max += 1;
				}
			}
			if(max == arrayList.size() - 1) {
				maximum = a;
			}
		}
		return maximum;
	}
	
	/**
	 * Filters out cancelled & diverted flights from the airlines data set
	 * @param dataMap A hashmap data frame of airline data
	 * @return A filtered version of that data frame.
	 */
	public HashMap<Integer, DataInstantiator> dataFilter(HashMap<Integer,
			DataInstantiator> dataMap){
		HashMap<Integer,DataInstantiator> filteredFlightData = new HashMap<
				Integer, DataInstantiator>();
		for(Integer key: dataMap.keySet()) {
			DataInstantiator data = dataMap.get(key);
			if(data.getCancelled() != 1 && data.getDiverted() != 1) {
				filteredFlightData.put(key, data);
			}
		}
		return filteredFlightData;
	}	
	
	/**
	 * Calculates scores that will then be used in the sink and source questions.
	 * The lowest score is the answer to Sink and the highest score is the answer
	 * to source. Those two methods simply look for the minimum and maximum values
	 * in the hashmap that is outputted from this method.
	 * @param dataMap A hashmap with data on airlines.
	 * @return A hashmap with scores to be used in sink and source questions.
	 */
	public HashMap<Integer,Integer> calculateAirportScores(HashMap<Integer,
			DataInstantiator> dataMap){
		
		ArrayList<Integer> sourceAirports = new ArrayList<Integer>();
		ArrayList<Integer> destinationAirports = new ArrayList<Integer>();
		HashMap<Integer,Integer> airportSends = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> airportReceives = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> sourceScores = new HashMap<Integer,Integer>();
		HelperMethods helper = new HelperMethods();
		
		for(Integer key: dataMap.keySet()) {
			DataInstantiator data = dataMap.get(key);
			if(!sourceAirports.contains(data.getOriginAirportId())) {
				airportSends.put(data.getOriginAirportId(),1);
				sourceAirports.add(data.getOriginAirportId());
			}
			else{
				int updatedDepartureCount = 1 + airportSends.get(data.getOriginAirportId());
				airportSends.replace(data.getOriginAirportId(), updatedDepartureCount);
			}
		}
		for(Integer key: dataMap.keySet()) {
			DataInstantiator data = dataMap.get(key);
			if(!destinationAirports.contains(data.getDestAirportId())) {
				airportReceives.put(data.getDestAirportId(),1);
				destinationAirports.add(data.getDestAirportId());
			}
			else{
				int updatedDepartureCount = 1 + airportReceives.get(data.getDestAirportId());
				airportReceives.replace(data.getDestAirportId(), updatedDepartureCount);
			}
		}
		if(airportSends.size() >= airportReceives.size()) {
			for(Integer key2 : airportSends.keySet()) {
				int values = 0;
				if(airportSends.keySet() == null) {
					values = 0 - airportReceives.get(key2);
				}
				else {
					values = airportSends.get(key2) - airportReceives.get(key2);
				}
				sourceScores.put(key2,values);
			}
		}
		else {
			for(Integer key2 : airportReceives.keySet()) {
				int values = 0;
				if(airportSends.get(key2) == null) {
					values = 0 - airportReceives.get(key2);
				}
				else {
					values = airportSends.get(key2) - airportReceives.get(key2);
				}
				sourceScores.put(key2,values);
			}
		}
		return sourceScores;
	}
}
