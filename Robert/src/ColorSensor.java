
import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class ColorSensor extends Thread {
	DataExchange DEObj;

	private EV3ColorSensor cs;

	public ColorSensor(DataExchange DE) {
		DEObj = DE;
		cs = new EV3ColorSensor(SensorPort.S3);
	}

	public void run() {

		while (true) {

			if (DEObj.getMode() != 0 && DEObj.getMode() != 5) {

				cs.setFloodlight(true);

				SampleProvider sp = cs.getRedMode();

				float[] sampleBrightness = new float[1];
				sp.fetchSample(sampleBrightness, 0);
				DEObj.setBrightness_value((int) (sampleBrightness[0] * 100));

//				System.out.println("Brightness: " + DEObj.getBrightness_value());

				if (Button.getButtons() != 0) {
					break;
				}
			}
		}
	}
}