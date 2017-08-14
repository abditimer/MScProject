package map;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.javafx.MapView;

import csvReader.CSVReader;
import data.DataDetector;
import data.DataFormatFX;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MapCreatorTest extends Application {

	
	@Override
    public void init() throws Exception {
        MapView.InitJavaFX();
    }

    @Override
    public void start(Stage primaryStage) {
    	
    	
    	//Get the data wanted
    	CSVReader reader = new CSVReader("accidents.csv");
		DataFormatFX accidentData = new DataFormatFX(reader);
		//String[] of longtitudinal coordinates
		String[] longtitude = accidentData.getColumnData(3);
		//string[] of latitudes coordinates
		String[] latitude = accidentData.getColumnData(4);
    	
		//check that data type is of one type
		DataDetector detectDoublesLong = new DataDetector(longtitude);
		System.out.println("longtitude are of one type? : " + detectDoublesLong.contains1DataType());
		DataDetector detectDoublesLat = new DataDetector(longtitude);
		System.out.println("Latitude are of one type? : " + detectDoublesLat.contains1DataType());
		
		//convert into double[]
		ArrayList<Double> longtitudeDoubles = DataFormatFX.stringArrayToDouble(longtitude);
		ArrayList<Double> latitudeDoubles = DataFormatFX.stringArrayToDouble(latitude);
		
		
		for (double a : longtitudeDoubles) {
			System.out.println(Double.toString(a));
		}
		
    	
		//ArrayList<Double> longtitudeDoubles = new ArrayList<Double>(Arrays.asList(-1.0004, -1.0005,-1.0006,-1.0007,-1.0008,-1.0009,-1.0010));
		//ArrayList<Double> latitudeDoubles = new ArrayList<Double>(Arrays.asList(53.003,53.004,53.006,53.007,53.008,53.009,53.0010));
		
		MapCreator mapViewObj = new MapCreator(latitudeDoubles, longtitudeDoubles);
    	MapView mapView = mapViewObj.getMapView();
		
		
		
		Scene scene = new Scene(new BorderPane(mapView), 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    


    public static void main(String[] args) {
    	System.out.println("yo");
        launch(args);
    }
	
	
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
