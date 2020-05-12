package application;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class PullVideoFeed extends Thread{

	private DatagramSocket serverSocket;
	private TargetDataLine microphone;
	private SourceDataLine speakers;
	private AudioFormat format;
	private DataLine.Info info;
	private int numBytesRead;
	private int CHUNK_SIZE = 1024;
	private ByteArrayOutputStream out;
	private int bytesRead = 0;
	private DataLine.Info dataLineInfo;
	private byte[] data;
	private String hostname = "localhost";
	private int port = 5555;
	private byte[] receiveData;
	private byte[] sendData;
	private boolean muted = false;
	private BufferedImage frame;
			
public PullVideoFeed() {

	try {
	    out = new ByteArrayOutputStream();
	        serverSocket = new DatagramSocket(5555);
	        receiveData = new byte[1024];
	        sendData = new byte[1024];
	        

	    }catch (IOException ex) {
	        System.out.println("Client error: " + ex.getMessage());
	        ex.printStackTrace();
	    } catch(SecurityException  e) {
	    	
	    }catch(IllegalArgumentException e) {
	    	
	    } 
	}
	
public void run(){
	pull();
    System.out.println("Thread running");
 }
	
	
	public void pull() {
		try {
		 while (true) {
	            byte[] buffer = new byte[1024];
	            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
	            serverSocket.receive(response);
	            byte[] bytearray = response.getData();
	           out.write(response.getData(), 0, response.getData().length);
	           	frame = ImageIO.read(new ByteArrayInputStream(bytearray));
	            System.out.println(buffer);
	            System.out.println(response.getData());
	        }
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
public BufferedImage getFeed() {
		return frame;
}
	
public void toggleMute() {
		
		if(muted) {
			muted = false;
		} else {
			muted = true;
		}
}

}
