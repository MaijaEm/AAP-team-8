

public class RobertMain {

	private static DataExchange DE;
	private static ColorSensor CSObj;
	private static UltraSonic USObj;
	private static Motor MObj;
	
	public static void main(String[] args) {
		DE = new DataExchange();
		USObj = new UltraSonic(DE);
		CSObj = new ColorSensor(DE);
		MObj = new Motor(DE);
		USObj.start();
		CSObj.start();
		MObj.start();
		
//		LCD.drawString("Finished", 0, 7);
//		LCD.refresh();
//		System.exit(0);
	}
}
