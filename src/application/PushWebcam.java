package application;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PushWebcam extends Thread{
	BufferedImage data;
	Webcam main = null;
	InetAddress address;
	DatagramSocket socket;
	DatagramPacket request;
	boolean muted = false;
	ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
	int port = 1999;

	public PushWebcam(InetAddress address,int port) {
		this.address = address;
		this.port = port;
		main = Webcam.getDefault();
		// TODO Auto-generated constructor stub
	}
	
	public void run(){
		push();
	}
	
	public void push() {
		while(!muted) {
			data = main.getImage();
			try {
				ImageIO.write(data, "jpg", baos);
				baos.flush();
				 request = new DatagramPacket(baos.toByteArray(),baos.size(), address, port);
				 socket.send(request);
			} catch (IOException e) {
				continue;
			}
		}
	}
	
	public void toggleMute() {
		if(!muted) {
			muted = false;
		} else {
			muted = true;
		}
	}
	

}
