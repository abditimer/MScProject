package main;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import view.HomeScreenController;

public class MainApp extends Application {

	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Civil Data Viewer App");
		this.primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));
		showHomeScreen();
	}
	
	/**
	 * This loads both the fxml file containing my gui and attaches the controller to it.
	 */
	public void showHomeScreen() {
		try {
			//load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/HomeScreen.fxml"));
			AnchorPane HomeScreen = (AnchorPane) loader.load();
			FadeTransition ft = new FadeTransition(Duration.millis(3000), HomeScreen);
			ft.setFromValue(0.0);
			ft.setToValue(1.0);
			ft.play();
			Scene scene = new Scene(HomeScreen);
			
			HomeScreenController controller = loader.getController();
			controller.setMainApp(this);
			
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void main(String[] args) {
		System.out.println("running main app");
		launch(args);
		
	}
}
