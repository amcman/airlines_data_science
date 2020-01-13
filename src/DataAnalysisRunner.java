/**
 * Runs methods in the DataAnalysis class and outputs the results to a CSV file.
 * @author andrew.mcmanus
 *
 */
public class DataAnalysisRunner {

	/**
	 * Runs the methods in DataAnalysis to output the results. 
	 * The user-chosen flight file to review can be inputted below.
	 * The program will take approximately 10-20 seconds to run, and the
	 * results will NOT be printed to the console. You will just get a message 
	 * indicating that the analysis is complete.
	 * @param args
	 */
	public static void main(String[] args) {
		DataAnalysis dataAnalysis = new DataAnalysis();
		dataAnalysis.runnerMethod("flights.csv");
	}
}
