package application;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class PullAudio extends Thread{

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
			
public PullAudio() {

	try {
	    format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, true);
	    microphone = AudioSystem.getTargetDataLine(format);

	    info = new DataLine.Info(TargetDataLine.class, format);
	    microphone = (TargetDataLine) AudioSystem.getLine(info);
	    microphone.open(format);

	    out = new ByteArrayOutputStream();
	    data = new byte[microphone.getBufferSize() / 5];
	    microphone.start();

	    dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
	    speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
//	    speakers.open(format);
	    speakers.start();

//	        InetAddress address = InetAddress.getByName(hostname);
//	        DatagramSocket socket = new DatagramSocket();

	        serverSocket = new DatagramSocket(5555);
	        receiveData = new byte[1024];
	        sendData = new byte[1024];
	        

	    }catch (IOException ex) {
	        System.out.println("Client error: " + ex.getMessage());
	        ex.printStackTrace();
	    } catch (LineUnavailableException  e) {
	        e.printStackTrace();
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

	            out.write(response.getData(), 0, response.getData().length);
	            speakers.write(response.getData(), 0, response.getData().length);
	            String quote = new String(buffer, 0, response.getLength());

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
	
public void toggleMute() {
		
		if(muted) {
			muted = false;
		} else {
			muted = true;
		}
}

}
