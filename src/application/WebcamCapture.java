package application;

import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;

import javafx.scene.image.Image;

public class WebcamCapture extends Thread{
	private Webcam webcam;
	private BufferedImage frame;
	private boolean capture;

	
public WebcamCapture() {
//		Dectecting webcam
		webcam = Webcam.getDefault();
		if (webcam != null) {
			System.out.println("Webcam: " + webcam.getName());
			webcam.open();
		} else {
			System.out.println("No webcam detected");
		}

	}

//starts the capture operation
private void startCapture() {
	while(capture) {
		frame = webcam.getImage();
	}
}
	
	
//	run method
public void run() {
	System.out.println("Starting capture");
	startCapture();
	System.out.println("Capture Started");
	
} 
//	  returns frames
public BufferedImage getframe() {
	return frame;
}


}
