
import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;
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
	
	//Motor objects
	RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
	RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);

	//Chassis objects
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

			//Waits 2 seconds after a button has been pressed and moves on
			case 0:
				LCD.refresh();
				LCD.drawString("Press any button.", 1, 1);
				if (Button.getButtons() != 0) {
					Delay.msDelay(2000);
					DEObj.setMode(1);
				}
				break;

			//Linefollower part
			case 1:

				// Test timer display
//				long startTime = System.currentTimeMillis();
//				
//				while (DEObj.getMode() != 0 && DEObj.getMode() != 5) {
//					long elapsedTime = System.currentTimeMillis() - startTime;
//					long elapsedSeconds = elapsedTime / 1000;
//					long secondsDisplay = elapsedSeconds % 60;
//					long elapsedMinutes = elapsedSeconds / 60;
//				
//				System.out.println(elapsedMinutes + ":" + secondsDisplay);
//				
//				}
				
				LCD.clear();
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
					leftMotor.stop();
					rightMotor.stop();
					DEObj.setMode(2);
				}
				if (Button.getButtons() != 0) {
					break;
				}
				break;

			//Turning right before dodging obstacle
			case 2:
				rightMotor.setSpeed(300);
				rightMotor.rotate(-250);
				DEObj.setMode(3);
				if (Button.getButtons() != 0) {
					break;
				}
				break;

				//Dodging obstacle, calculated using chassis getspeed
			case 3:
				leftMotor.forward();
				rightMotor.forward();
				leftMotor.setSpeed(300);
				rightMotor.setSpeed(400);
				if (DEObj.getBrightness_value() < DEObj.getBrightnessThreshold()) {
					leftMotor.stop();
					rightMotor.stop();
					DEObj.setMode(4);
				}
				if (Button.getButtons() != 0) {
					break;
				}
				break;

			//Back to linefollowing
			case 4:
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
					leftMotor.stop();
					rightMotor.stop();
					DEObj.setMode(5);
				}
				if (Button.getButtons() != 0) {
					break;
				}
				break;

			//Victory music and movement
			case 5:
				LCD.drawString("COMPLETED!", 1, 1);
				chassis.setSpeed(300, 300);
				chassis.arc(0, 1000);
				Sound.setVolume(100);
				Sound.playSample(new File("stage clear.wav"), Sound.VOL_MAX);
				if (Button.getButtons() != 0) {
					break;
				}
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