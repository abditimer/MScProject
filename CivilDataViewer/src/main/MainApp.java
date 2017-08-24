package main;

import java.io.IOException;

import backgroundAudio.audioPlayMusic;
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
		//in Javafx, this method is called first when the application is loaded.
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Civil Data Viewer App");
		//This allows me to give my software an icon.
		this.primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));
		showHomeScreen();
		
	}
	
	/**
	 * This loads both the fxml file containing my gui and attaches the controller to it.
	 */
	public void showHomeScreen() {
		try {
			//load our FXML file containing our software
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/HomeScreen.fxml"));
			//sets it location as the AnchorPane.
			AnchorPane HomeScreen = (AnchorPane) loader.load();
			//Create a transition for when it loads up.
			FadeTransition ft = new FadeTransition(Duration.millis(3000), HomeScreen);
			ft.setFromValue(0.0);
			ft.setToValue(1.0);
			ft.play();
			Scene scene = new Scene(HomeScreen);
			//get our controller 
			HomeScreenController controller = loader.getController();
			//pass this class over to the controller
			controller.setMainApp(this);
			//show our application, without a border
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Main application failed to load, please try again.");
		}
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
