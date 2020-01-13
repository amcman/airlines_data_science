
public class DataInstantiator {
	
	private int dayOfMonth;
	private int dayOfWeek;
	private String flightDate;
	private String uniqueCarrier;
	private String tailNum;
	private int originAirportId;
	private String origin;
	private String originStateName;
	private int destAirportId;
	private String dest;
	private String destStateName;
	private int depTime;
	private int depDelay;
	private int wheelsOff;
	private int wheelsOn;
	private int arrTime;
	private int arrDelay;
	private int cancelled;
	private String cancellationCode;
	private int diverted; 
	private int airTime;
	private int distance;
	
	public DataInstantiator(int dOfMonth, int dOfWeek, String dOfFlight, 
			String uCarrier, String tailNumber, int idOfOriginAirport,
			String originName, String originState, int destinationApId,
			String destination, String destinationStName, int departureTime,
			int departureDelay, int wheelsOffGround, int wheelsOnGround, 
			int arrivalTime, int arrivalDelay, int cancellationHere, 
			String cancelCode, int diversion, int timeInAir, int distanceTotal){

		dayOfMonth = dOfMonth;
		dayOfWeek = dOfWeek;
		flightDate = dOfFlight;
		uniqueCarrier = uCarrier;
		tailNum = tailNumber;
		originAirportId = idOfOriginAirport;
		origin = originName;
		originStateName = originState;
		destAirportId = destinationApId;
		dest = destination;
		destStateName = destinationStName;
		depTime = departureTime;
		depDelay = departureDelay;
		wheelsOff = wheelsOffGround;
		wheelsOn = wheelsOnGround;
		arrTime = arrivalTime;
		arrDelay = arrivalDelay;
		cancelled = cancellationHere;
		cancellationCode = cancelCode;
		diverted = diversion; 
		airTime = timeInAir;
		distance = distanceTotal;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public String getFlightDate() {
		return flightDate;
	}

	public String getUniqueCarrier() {
		return uniqueCarrier;
	}

	public String getTailNum() {
		return tailNum;
	}

	public int getOriginAirportId() {
		return originAirportId;
	}

	public String getOrigin() {
		return origin;
	}

	public String getOriginStateName() {
		return originStateName;
	}

	public int getDestAirportId() {
		return destAirportId;
	}

	public String getDest() {
		return dest;
	}

	public String getDestStateName() {
		return destStateName;
	}

	public int getDepTime() {
		return depTime;
	}

	public int getDepDelay() {
		return depDelay;
	}

	public int getWheelsOff() {
		return wheelsOff;
	}

	public int getWheelsOn() {
		return wheelsOn;
	}

	public int getArrTime() {
		return arrTime;
	}

	public int getArrDelay() {
		return arrDelay;
	}

	public int getCancelled() {
		return cancelled;
	}

	public String getCancellationCode() {
		return cancellationCode;
	}

	public int getDiverted() {
		return diverted;
	}

	public int getAirTime() {
		return airTime;
	}

	public int getDistance() {
		return distance;
	}
	
	
	
}
