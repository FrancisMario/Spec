package application;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.imageio.ImageIO;


public class PushScreenFeed {

	BufferedImage data;
	Robot main = null;
    Rectangle screenRect;
	InetAddress address;
	DatagramSocket socket;
	DatagramPacket request;
	boolean muted = false;
	ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
	int port = 1999;

	public PushScreenFeed(InetAddress address,int port) throws AWTException {
		this.address = address;
		this.port = port;
		main  = new Robot();
		screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		// TODO Auto-generated constructor stub
	}
	
	public void run(){
		push();
	}
	
	public void push() {
		while(!muted) {
			data  = main.createScreenCapture(screenRect);
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
	

}
