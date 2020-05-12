package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
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
			Parent root =  fx.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root);
//			scene.setFill(Color.TRANSPARENT);
			primaryStage.setScene(scene);
//			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		launch(args);
		Application.launch(Main.class, new String[0]);
	}
}
