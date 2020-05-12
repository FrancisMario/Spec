package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class JoinMeetingProcessor extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// original code, nope.!, i won't delete it.
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
//			loader.setController(controller);
//			Parent root = loader.load();

			MainController controller = new MainController();
			FXMLLoader fx= new FXMLLoader();
			fx.setController(controller);
			BorderPane root = (BorderPane) fx.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
