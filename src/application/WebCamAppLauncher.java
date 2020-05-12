package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * This example demonstrates how to use Webcam Capture API via FXML in a JavaFX
 * application.
 * 
 * @author Rakesh Bhatt (rakeshbhatt10)
 */
public class WebCamAppLauncher extends Application {

	@Override
	public void start(Stage primaryStage) {

//		Parent root = null;
		BorderPane root = null;
		FXMLLoader fx= new FXMLLoader();
		try {
			 root = (BorderPane) fx.load(getClass().getResource("/application/WebCamPreview.fxml"));
//			root = FXMLLoader.load(getClass().getResource("/application/WebCamPreview.fxml"));
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		Scene scene = new Scene(root, 900, 690);

		primaryStage.setTitle("WebCam Capture Sarxos API using JavaFx with FXML ");
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}