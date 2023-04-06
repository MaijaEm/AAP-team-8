
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
//				long startTime = System.currentTimeMillis();
//				
//				while (DEObj.getMode() != 0 && DEObj.getMode() != 5) {
//					long elapsedTime = System.currentTimeMillis() - startTime;
//					long elapsedSeconds = elapsedTime / 1000;
//					long secondsDisplay = elapsedSeconds % 60;
//					long elapsedMinutes = elapsedSeconds / 60;
//					
//
//				}

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
				LCD.clear();
				LCD.drawString("Object found!", 1, 1);
//				Sound.playSample(new File("smb_mat.wav"), Sound.VOL_MAX);
				rightMotor.setSpeed(300);
				rightMotor.rotate(-250);
				DEObj.setMode(3);
				break;

			case 3:
//				chassis.arc(-400, 180);
				LCD.clear();
				LCD.drawString("DOING ARC", 1, 1);
				leftMotor.forward();
				rightMotor.forward();
				leftMotor.setSpeed(200);
				rightMotor.setSpeed(300);
				if (DEObj.getBrightness_value() < DEObj.getBrightnessThreshold()) {
					DEObj.setMode(4);
//					leftMotor.stop();
//					rightMotor.stop();
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

//			if (DEObj.getMode() == 0) {
//				System.out.println("Press a button to start.");
//				if (Button.getButtons() != 0) {
//					Delay.msDelay(3000);
//					DEObj.setCMD(1);
//				}
//			}

//			if (DEObj.getMode() == 1) {
//				leftMotor.forward();
//				rightMotor.forward();
//				if (DEObj.getBrightness_value() < DEObj.getBrightnessThreshold()) {
//					leftMotor.setSpeed(300);
//					rightMotor.setSpeed(100);
//				} else {
//					leftMotor.setSpeed(100);
//					rightMotor.setSpeed(300);
//				}
//				if (DEObj.getDistancevalue() < DEObj.getSecurityDistance()) {
//					DEObj.setMode(2);
//				}
//			}
//			if (DEObj.getCMD() == 2) {
//				leftMotor.stop();
//				rightMotor.stop();
//				System.out.println("Object found!");
//				Sound.playSample(new File("smb_mat.wav"), Sound.VOL_MAX);
//				rightMotor.setSpeed(300);
//				rightMotor.rotate(-300);
//				DEObj.setMode(3);
//			}

//			if (DEObj.getCMD() == 3) {
//				Wheel wheel1 = WheeledChassis.modelWheel(MotorA, 81.6).offset(-70);
//				Wheel wheel2 = WheeledChassis.modelWheel(MotorB, 81.6).offset(70);
//				Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
////				chassis.setSpeed(200, 200);
//				chassis.arc(-300, 100);
//				if (DEObj.getBrightness_value() < DEObj.getBrightnessThreshold()) {
//					chassis.stop();
//					MotorA.stop();
//					MotorB.stop();
//					DEObj.setCMD(4);
//				}
//			}

//			if (DEObj.getCMD() == 4) {
//				MotorA.forward();
//				MotorB.forward();
//				if (DEObj.getBrightness_value() < DEObj.getBrightnessThreshold()) {
//					MotorA.setSpeed(300);
//					MotorB.setSpeed(75);
//				} else {
//					MotorA.setSpeed(75);
//					MotorB.setSpeed(300);
//				}
//				if (DEObj.getDistancevalue() < DEObj.getSecurityDistance()) {
//					DEObj.setCMD(5);
//				}
//			}

//			if (DEObj.getCMD() == 3) {
//				// chassis.setSpeed(200, 200);
//				chassis.arc(-300, 100);
//				if (DEObj.getBrightness_value() < DEObj.getBrightnessThreshold()) {
//					DEObj.setCMD(4);
//				}
//			}

//			if (DEObj.getCMD() == 4) {
//				leftMotor.forward();
//				rightMotor.forward();
//				if (DEObj.getBrightness_value() < DEObj.getBrightnessThreshold()) {
//					leftMotor.setSpeed(300);
//					rightMotor.setSpeed(75);
//					if (!stopChassisArc) {
//						chassis.stop(); // stop the chassis arc
//						stopChassisArc = true; // set the flag to stop the chassis arc
//					}
//				} else {
//					leftMotor.setSpeed(75);
//					rightMotor.setSpeed(300);
//					if (stopChassisArc) {
//						stopChassisArc = false; // reset the flag
//					}
//				}
//				if (DEObj.getDistancevalue() < DEObj.getSecurityDistance()) {
//					DEObj.setCMD(5);
//				}
//			}

//			if (DEObj.getCMD() == 5) {
//				
//				chassis.arc(0, 500);
//
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
//
//			}

			if (Button.getButtons() != 0) {
				break;
			}
		}
	}

}
