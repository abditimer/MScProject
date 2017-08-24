package animationImages;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SlideShowTest extends Application{
	
	public static void main(String[] args) {
	    launch(args);
	  }

	  @Override
	  public void start(Stage primaryStage) throws Exception {
		  System.out.println("test");
		  SlideShow simpleSlideShow = new SlideShow();
	    Scene scene = new Scene(simpleSlideShow.getRoot());
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    //simpleSlideShow.start();
	  }
	  
}
