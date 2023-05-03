
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.Enumeration;

//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.Invocation.Builder;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;

import lejos.hardware.Button;
import lejos.hardware.Sound;

public class EV3data extends Thread {

	DataExchange DEObj;

	public EV3data(DataExchange DE) {
		DEObj = DE;
	}

	public void run() {

		while (true) {

			//Put thread to sleep for two seconds
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			URL url = null;
			HttpURLConnection conn = null;
			InputStreamReader isr = null;
			BufferedReader br = null;

			String s = null;
			try {
				//Wifi connection and rest service path
				url = new URL("http://192.168.1.33:8080/rest/ev3/getall");
				conn = (HttpURLConnection) url.openConnection();
				System.out.println(conn.toString()); // Prints URL
//			if (conn==null) {
//	  			System.out.println("No connection!!!");
//			}
				InputStream is = null;
				try {
					is = conn.getInputStream();
				} catch (Exception e) {
					System.out.println("Exception conn.getInputSteam()");
					e.printStackTrace();
					System.out.println("Cannot get InputStream!");
				}
				isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
//			while ((s=br.readLine())!=null){
//				System.out.println(s);
//			}
				s = br.readLine();
				String[] info = s.split(" ");

				//Set values according to database string
				DEObj.setSpeedMotor1(info[0]);
				DEObj.setSpeedMotor2(info[1]);
				DEObj.setRotation(info[2]);
				DEObj.setSpeed1(info[3]);
				DEObj.setSpeed2(info[4]);
				DEObj.setSecurityDistance(info[5]);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Some problem!");
			}
		}
	}
}