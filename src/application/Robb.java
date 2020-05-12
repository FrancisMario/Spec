package application;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;

public class Robb {

	public Robb() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Robot r = new Robot();
			Dimension dimen = new Dimension();
			r.createScreenCapture(dimen);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
