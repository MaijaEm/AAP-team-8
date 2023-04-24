
import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class UltraSonic extends Thread {
	private DataExchange DEObj;

	private EV3UltrasonicSensor us;

	public UltraSonic(DataExchange DE) {
		DEObj = DE;
		us = new EV3UltrasonicSensor(SensorPort.S1);
	}

	public void run() {

		while (true) {

			if (DEObj.getMode() != 0 && DEObj.getMode() != 6) {

				SampleProvider sp = us.getDistanceMode();

				// Ultrasonic sample
				float[] sampleDistance = new float[sp.sampleSize()];
				sp.fetchSample(sampleDistance, 0);
				DEObj.setDistancevalue((int) (sampleDistance[0] * 100));
//			System.out.println("Distance: " + distanceValue);

				if (Button.getButtons() != 0) {
					break;
				}
			}
		}
	}
}