package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainController implements Initializable{
	
	@FXML private BorderPane borderpane;
	@FXML private JFXButton Host;
	@FXML private JFXButton Join;
	@FXML private JFXButton Schedule;
	@FXML private JFXButton Study_group;
	@FXML private JFXButton settings;
	@FXML private JFXButton close;
		  private Pane view;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	

	
	
	public void launchHostLogin(Event e) throws IOException {
		loadUi("HostLogin");
	}
	
	public void launchJoinForm(Event e) throws IOException {
		loadUi("JoinMeeting");
		
	}
	
	public void launchScheduleForm(Event e) throws IOException {
		loadUi("ScheduleMeeting");
		
	}
	
	public void launchSettingsForm(Event e) throws IOException {
		loadUi("HostLogin");
	}
	
	public void close() {
		
	}
	
	
	public void scheduleMeeting() {
		
	}
	
	public void loadUi(String file) throws IOException {
		borderpane.setCenter(getPage(file));
	}
	
	public Pane getPage(String file) {
		try {
			URL fileUrl = Main.class.getResource("/application/"+file+".fxml");
			if(fileUrl == null) {
			
				}
		
			view = new FXMLLoader().load(fileUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return view;
		
	}
	
	
}
