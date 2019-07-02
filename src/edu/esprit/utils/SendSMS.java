/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.utils;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SendSMS {
	public static String sendSms(String msg,String phones) {
		try {
			// Construct data
			String apiKey = "apikey=" + URLEncoder.encode("/cSvK2IrhKE-i2Ff5IfSx6FfGgD2lgt7neNoEhA4Po", "UTF-8");
			String message = "&message=" + URLEncoder.encode(msg, "UTF-8");
			String sender = "&sender=" + URLEncoder.encode("TechEvents", "UTF-8");
			String numbers = "&numbers=" + URLEncoder.encode(phones, "UTF-8");

			// Send data
			String data = "https://api.txtlocal.com/send/?" + apiKey + numbers + message + sender;
			URL url = new URL(data);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			String sResult="";
			while ((line = rd.readLine()) != null) {
			// Process line...
				sResult=sResult+line+" ";
			}
			rd.close();

			return sResult;
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
}
