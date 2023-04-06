
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.utility.Delay;

public class Motor extends Thread {

	DataExchange DEObj;
	RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
	RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);

	Wheel wheel1 = WheeledChassis.modelWheel(leftMotor, 56).offset(-70);
	Wheel wheel2 = WheeledChassis.modelWheel(rightMotor, 56).offset(70);
	Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);

	public Motor(DataExchange DE) {
		DEObj = DE;
	}

	public void run() {

		while (true) {

			int mode = DEObj.getMode();
			switch (mode) {
			case 0:
				LCD.refresh();
				LCD.drawString("Press any button.", 1, 1);
				if (Button.getButtons() != 0) {
					Delay.msDelay(3000);
					DEObj.setMode(1);
				}
				break;
			case 1:
				
				//Test timer display
				long startTime = System.currentTimeMillis();
				
				while (DEObj.getMode() != 0 && DEObj.getMode() != 5) {
					long elapsedTime = System.currentTimeMillis() - startTime;
					long elapsedSeconds = elapsedTime / 1000;
					long secondsDisplay = elapsedSeconds % 60;
					long elapsedMinutes = elapsedSeconds / 60;
				
				System.out.println(elapsedMinutes + ":" + secondsDisplay);
				
				}

				leftMotor.forward();
				rightMotor.forward();
				if (DEObj.getBrightness_value() < DEObj.getBrightnessThreshold()) {
					leftMotor.setSpeed(250);
					rightMotor.setSpeed(80);
				} else {
					leftMotor.setSpeed(80);
					rightMotor.setSpeed(250);
				}
				if (DEObj.getDistancevalue() < DEObj.getSecurityDistance()) {
					DEObj.setMode(2);
				}
				break;
			case 2:
				leftMotor.stop();
				rightMotor.stop();
//				LCD.clear();
//				LCD.drawString("Object found!", 1, 1);
//				Sound.playSample(new File("smb_mat.wav"), Sound.VOL_MAX);
				rightMotor.setSpeed(300);
				rightMotor.rotate(-250);
				DEObj.setMode(3);
				break;

			case 3:
//				chassis.arc(-400, 180);
//				LCD.clear();
//				LCD.drawString("DOING ARC", 1, 1);
				leftMotor.forward();
				rightMotor.forward();
				leftMotor.setSpeed(200);
				rightMotor.setSpeed(300);
				if (DEObj.getBrightness_value() < DEObj.getBrightnessThreshold()) {
					DEObj.setMode(4);
				}
				break;
			case 4:
				leftMotor.forward();
				rightMotor.forward();
				if (DEObj.getBrightness_value() < DEObj.getBrightnessThreshold()) {
					leftMotor.setSpeed(300);
					rightMotor.setSpeed(80);
				} else {
					leftMotor.setSpeed(80);
					rightMotor.setSpeed(300);
				}
				if (DEObj.getDistancevalue() < DEObj.getSecurityDistance()) {
					DEObj.setMode(5);
				}
				break;
			case 5:
				LCD.clear();
				rightMotor.forward();
				rightMotor.rotate(180);
				leftMotor.forward();
				leftMotor.rotate(180);
				break;
			}


//				Sound.playNote(Sound.PIANO, 196, 50);
//				Sound.playNote(Sound.PIANO, 262, 50);
//				Sound.playNote(Sound.PIANO, 330, 50);
//				Sound.playNote(Sound.PIANO, 392, 50);
//				Sound.playNote(Sound.PIANO, 523, 50);
//				Sound.playNote(Sound.PIANO, 659, 50);
//				Sound.playNote(Sound.PIANO, 784, 200);
//				Sound.playNote(Sound.PIANO, 659, 200);
//
//				Sound.playNote(Sound.PIANO, 233, 50);
//				Sound.playNote(Sound.PIANO, 262, 50);
//				Sound.playNote(Sound.PIANO, 311, 50);
//				Sound.playNote(Sound.PIANO, 466, 50);
//				Sound.playNote(Sound.PIANO, 523, 50);
//				Sound.playNote(Sound.PIANO, 622, 50);
//				Sound.playNote(Sound.PIANO, 831, 200);
//				Sound.playNote(Sound.PIANO, 622, 200);
//			}


			if (Button.getButtons() != 0) {
				break;
			}
		}
	}

}
