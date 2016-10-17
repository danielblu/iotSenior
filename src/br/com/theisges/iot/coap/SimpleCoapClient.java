package br.com.theisges.iot.coap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

import br.com.theisges.iot.readFile.ReadFile;

public class SimpleCoapClient {

	public static void main(String[] args) {
		
		//CoapClient client = new CoapClient("coap://californium.eclipse.org:5683/obs");
		//CoapClient client = new CoapClient("coap://iot.eclipse.org:5683/obs");
		CoapClient client = new CoapClient("coap://127.0.0.1:5683/theisges");
		
		ReadFile readFile = ReadFile.getInstance();
		
		while(readFile.hasNextData()) {
			String pubMsg = readFile.nextData();
			System.out.println("REQUEST: " + pubMsg);
		client.post(new CoapHandler() {
			@Override public void onLoad(CoapResponse response) {
				String content = response.getResponseText();
				System.out.println("RESPONSE: " + content);
			}
			
			@Override public void onError() {
				System.err.println("FAILED");
			}
		}, pubMsg, MediaTypeRegistry.TEXT_PLAIN);
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		}
	}
}
