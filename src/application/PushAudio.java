package application;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class PushAudio extends Thread{
	
	private AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, true);
    private TargetDataLine microphone;
    private SourceDataLine speakers;

    // Configure the ip and port
    String hostname = "localhost";
    int port = 5555;
    DataLine.Info info;
    ByteArrayOutputStream out;
    DataLine.Info dataLineInfo;
    InetAddress address;
    DatagramSocket socket;
    int numBytesRead;
    int CHUNK_SIZE = 1024;
    byte[] data;
    byte[] buffer;
    private boolean muted = false;
    
static AudioFormat getAudioFormat() {
		        float sampleRate = 14000;
		        int sampleSizeInBits = 8;
		        int channels = 2;
		        boolean signed = true;
		        boolean bigEndian = true;
		        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
		                                             channels, signed, bigEndian);
		        return format;
		    }

	public PushAudio() {
		  
		    try {
		        microphone = AudioSystem.getTargetDataLine(format);
		        info = new DataLine.Info(TargetDataLine.class, format);
		        microphone = (TargetDataLine) AudioSystem.getLine(info);
		        microphone.open(format);

		        out = new ByteArrayOutputStream();
		        data = new byte[microphone.getBufferSize() / 5];
		        microphone.start();


		        dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
		        speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
		        speakers.open(format);
		        speakers.start();



		        address = InetAddress.getByName(hostname);
		        socket = new DatagramSocket();
		        buffer = new byte[1024];

		    } catch (LineUnavailableException  e) {
		        e.printStackTrace();
		    } catch(SecurityException  e) {
		    	
		    }catch(IllegalArgumentException e) {
		    	
		    } catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
	}
	
	public void push() {
		 while(!muted) {                
	            numBytesRead = microphone.read(data, 0, CHUNK_SIZE);
	          //  bytesRead += numBytesRead;
	            // write the mic data to a stream for use later
	            out.write(data, 0, numBytesRead); 
	            // write mic data to stream for immediate playback
	            speakers.write(data, 0, numBytesRead);            
	            DatagramPacket request = new DatagramPacket(data,numBytesRead, address, port);
	            System.out.println(request);
	            try {
					socket.send(request);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	        }
	}
public void run(){
	       System.out.println("Thread running");
	       push();
}
	
	public void toggleMute() {
		
		if(muted) {
			muted = false;
		} else {
			muted = true;
		}
	}

}
