package model;

import java.util.Map.Entry;

import dataDownloader.PotholeEnquiry;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;

public class PieChartVisual extends Application {

	PotholeEnquiry potholeData = new PotholeEnquiry();

	@Override 
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Pothole approval status");
        stage.setWidth(900);
        stage.setHeight(900);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(); 
        
        for(Entry<String, Double> e : potholeData.getEnquiryInfo().entrySet()) {
        	pieChartData.add(new PieChart.Data(e.getKey(), e.getValue()));
        }
        
        
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Pothole info");
        chart.setLabelLineLength(10);
        chart.setMinSize(800, 800);
        chart.autosize();
        chart.setLegendSide(Side.LEFT);

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }
 
	public static void main(String[] args) {
		launch(args);
	}
}