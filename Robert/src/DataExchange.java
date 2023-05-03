
public class DataExchange {
	
	//Local variables
	private boolean obstacleDetected = false;
	private int mode = 0;
	private int brightness_value;
	private int brightnessThreshold = 20;
	private int securityDistance = 10;
	private int distancevalue;
	private String SpeedMotor1;
	private String SpeedMotor2;
	

	public DataExchange() {
	}

	//Getters and Setters
	public void setObstacleDetected(boolean status) {
		obstacleDetected = status;
	}

	public boolean getObstacleDetected() {
		return obstacleDetected;
	}

	public void setMode(int command) {
		mode = command;
	}

	public int getMode() {
		return mode;
	}

	public int getBrightness_value() {
		return brightness_value;
	}

	public void setBrightness_value(int brightness_value) {
		this.brightness_value = brightness_value;
	}

	public int getBrightnessThreshold() {
		return brightnessThreshold;
	}

	public void setBrightnessThreshold(int brightnessThreshold) {
		this.brightnessThreshold = brightnessThreshold;
	}

	public int getSecurityDistance() {
		return securityDistance;
	}

	public void setSecurityDistance(int securityDistance) {
		this.securityDistance = securityDistance;
	}

	public int getDistancevalue() {
		return distancevalue;
	}

	public void setDistancevalue(int distancevalue) {
		this.distancevalue = distancevalue;
	}

	public int getSpeedMotor1() {
		return Integer.parseInt(SpeedMotor1);
	}

	public void setSpeedMotor1(String speedMotor1) {
		SpeedMotor1 = speedMotor1;
	}

	public int getSpeedMotor2() {
		return Integer.parseInt(SpeedMotor2);
	}

	public void setSpeedMotor2(String speedMotor2) {
		SpeedMotor2 = speedMotor2;
	}
	
}