import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;


/**
 * Contains a method for reading in flight data as well as for 
 * reformatting data into a hashmap.
 * @author andrew.mcmanus
 *
 */
public class DataReader {
	
	private HashMap<Integer,DataInstantiator> filghtMap = new 
			HashMap<Integer,DataInstantiator>();
	/**
	 * Takes a filename argument and reads in the desired filename. The file
	 * is then returned from the method to be used elsewhere.
	 * @param filename A file to be read into Java
	 * @return A scanner object that can be navigated and manipulated for data analysis.
	 */
	public HashMap<Integer, DataInstantiator> dataReadMethod(String filename) {
		
		Scanner scanner = null;	
		File file = new File(filename);
		try {
			scanner = new Scanner(file);
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found, try again");
		}
		scanner.nextLine();
		int i = 1;
		/**
		 * This while loop loops through each item in the CSV and adds it to 
		 * a DataInstantiator class that can then be stored in a hashmap.
		 * Empty cells in numeric columns are saved as "0" to avoid crashing.
		 * This does NOT affect any results.
		 */
		while(scanner.hasNextLine()) {
				//For each integer item, we input a 99 to indicate emptiness.
				String flightRows = scanner.nextLine();
				String[] flightColumns = flightRows.split(",");
				if(flightColumns[0].equals("")) {
					flightColumns[0] = "0";
				}
				int dayOfMonth = Integer.parseInt(flightColumns[0]);
				if(flightColumns[1].equals("")) {
					flightColumns[1] = "0";
				}
				int dayOfWeek = Integer.parseInt(flightColumns[1]);
				String flightDate = flightColumns[2];
				String uniqueCarrier = flightColumns[3];
				String tailNum = flightColumns[4];
				if(flightColumns[5].equals("")) {
					flightColumns[5] = "0";
				}
				int originAirportId = Integer.parseInt(flightColumns[5]);
				String origin = flightColumns[6];
				String originStateName = flightColumns[7];
				if(flightColumns[8].equals("")) {
					flightColumns[8] = "0";
				}
				int destAirportId = Integer.parseInt(flightColumns[8]);
				String dest = flightColumns[9];
				String destStateName = flightColumns[10];
				if(flightColumns[11].equals("")) {
					flightColumns[11] = "0";
				}
				int depTime = Integer.parseInt(flightColumns[11]);
				if(flightColumns[12].equals("")) {
					flightColumns[12] = "0";
				}
				int depDelay = Integer.parseInt(flightColumns[12]);
				if(flightColumns[13].equals("")) {
					flightColumns[13] = "0";
				}
				int wheelsOff = Integer.parseInt(flightColumns[13]);
				if(flightColumns[14].equals("")) {
					flightColumns[14] = "0";
				}
				int wheelsOn = Integer.parseInt(flightColumns[14]);
				if(flightColumns[15].equals("")) {
					flightColumns[15] = "0";
				}
				int arrTime = Integer.parseInt(flightColumns[15]);
				if(flightColumns[16].equals("")) {
					flightColumns[16] = "0";
				}
				int arrDelay = Integer.parseInt(flightColumns[16]);
				if(flightColumns[17].equals("")) {
					flightColumns[17] = "0";
				}
				int cancelled = Integer.parseInt(flightColumns[17]);
				String cancellationCode = flightColumns[18];
				if(flightColumns[19].equals("")) {
					flightColumns[19] = "99";
				}
				int diverted = Integer.parseInt(flightColumns[19]); 
				if(flightColumns[20].equals("")) {
					flightColumns[20] = "0";
				}
				int airTime = Integer.parseInt(flightColumns[20]);
				if(flightColumns[21].equals("")) {
					flightColumns[21] = "0";
				}
				int distance = Integer.parseInt(flightColumns[21]);
				DataInstantiator dataFrame = new DataInstantiator(
					dayOfMonth, dayOfWeek, flightDate, uniqueCarrier,
					tailNum, originAirportId, origin, originStateName, 
					destAirportId, dest, destStateName, depTime, 
					depDelay, wheelsOff, wheelsOn, arrTime, arrDelay, 
					cancelled, cancellationCode, diverted, airTime, 
					distance);
				filghtMap.put(i, dataFrame);	
				i++;
		}
		return filghtMap;	
	}
}
