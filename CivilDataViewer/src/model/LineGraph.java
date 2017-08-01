/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.application.Application;
import static javafx.application.Application.launch;

import dataDownloader.PotholeEnquiry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;




public class LineGraph extends Application {
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("XY charts");
        
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 500, 350);
        
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        
        //ScatterChart chart = new ScatterChart(xAxis, yAxis, getChartData());
        LineChart chart = new LineChart(xAxis, yAxis, getChartData());
        chart.setTitle("Speculations");
        
        root.getChildren().add(chart);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private ObservableList<Series<Double, String>> getChartData() {
    	
    	PotholeEnquiry a = new PotholeEnquiry();
    	
        
        ObservableList<XYChart.Series<Double, String>> data = FXCollections.observableArrayList();
        
        Series<Double, String> northingsGraph = new Series<>();
        northingsGraph.setName("Test graph for northings");
      
        
       
       for (int i = 0 ; i < a.getNorthingsDouble().size(); i++) {
    	   int count = 0;
	       for(double value : a.getNorthingsDouble()) {
	    	   if ( count < 30) {
	    		   northingsGraph.getData().add(new XYChart.Data(value, Integer.toString(i)));
	    		   count++;
	    	   }
	        }
       }
        
        
        data.addAll(northingsGraph);
       /* data.addAll(tMax, tMin, rainfall);*/
        
        return data;
    }
}
