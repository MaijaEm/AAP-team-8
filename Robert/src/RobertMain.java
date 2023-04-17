

public class RobertMain {

	
	//Creating local variables


	private static DataExchange DE;
	private static ColorSensor CSObj;
	private static UltraSonic USObj;
	private static Motor MObj;
	

	//Creating the main method


	public static void main(String[] args) {
		DE = new DataExchange();
		USObj = new UltraSonic(DE);
		CSObj = new ColorSensor(DE);
		MObj = new Motor(DE);
		USObj.start();
		CSObj.start();
		MObj.start();
	}
}