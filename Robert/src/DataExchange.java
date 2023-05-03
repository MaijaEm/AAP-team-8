
public class DataExchange {
	
	//Local variables
	private boolean obstacleDetected = false;
	private int mode = 0;
	private int brightness_value;
	private int brightnessThreshold = 20;
	private int securityDistance;
	private int distancevalue;
	private int SpeedMotor1;
	private int SpeedMotor2;
	private int Rotation;
	private int Speed1;
	private int Speed2;
	

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
	
	public void setSecurityDistance(String securityDistance) {
        try {
            this.securityDistance = Integer.parseInt(securityDistance);
        }
        catch(NumberFormatException e) {
            this.securityDistance=0;
        }
    }
	public int getDistancevalue() {
		return distancevalue;
	}

	public void setDistancevalue(int distancevalue) {
		this.distancevalue = distancevalue;
	}

	public int getSpeedMotor1() {
		return SpeedMotor1;
	}

	public void setSpeedMotor1(int speedMotor1) {
		this.SpeedMotor1 = speedMotor1;
	}
	
	public void setSpeedMotor1(String speedMotor1) {
        try {
            this.SpeedMotor1 = Integer.parseInt(speedMotor1);
        }
        catch(NumberFormatException e) {
            this.SpeedMotor1=0;
        }
    }

	public int getSpeedMotor2() {
		return SpeedMotor2;
	}

	public void setSpeedMotor2(int speedMotor2) {
		this.SpeedMotor1 = speedMotor2;
	}
	
	public void setSpeedMotor2(String speedMotor2) {
        try {
            this.SpeedMotor1 = Integer.parseInt(speedMotor2);
        }
        catch(NumberFormatException e) {
            this.SpeedMotor2=0;
        }
    }
	public int getRotation() {
		return Rotation;
	}

	public void setRotation(int rotation) {
		this.Rotation = rotation;
	}
	
	public void setRotation(String rotation) {
        try {
            this.Rotation = Integer.parseInt(rotation);
        }
        catch(NumberFormatException e) {
            this.Rotation=0;
        }
    }

	public int getSpeed1() {
		return Speed1;
	}

	public void setSpeed1(int speed1) {
		this.Speed1 = speed1;
	}
	
	public void setSpeed1(String speed1) {
        try {
            this.Speed1 = Integer.parseInt(speed1);
        }
        catch(NumberFormatException e) {
            this.Speed1=0;
        }
    }

	public int getSpeed2() {
		return Speed2;
	}

	public void setSpeed2(int speed2) {
		this.Speed2 = speed2;
	}
	
	public void setSpeed2(String speed2) {
        try {
            this.Speed2 = Integer.parseInt(speed2);
        }
        catch(NumberFormatException e) {
            this.Speed2=0;
        }
    }
}