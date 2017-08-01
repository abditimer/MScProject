package view;

import java.util.Map.Entry;

import dataDownloader.PotholeEnquiry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class GraphData {
	PotholeEnquiry potholeData = new PotholeEnquiry();
	
	/**
	 * Method that will be able to pass over the list of approval status
	 */
	public ObservableList<PieChart.Data> getPieChartData() {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(); 
        for(Entry<String, Double> e : potholeData.getEnquiryInfo().entrySet()) {
        	pieChartData.add(new PieChart.Data(e.getKey(), e.getValue()));
        }
        
        return pieChartData;
	}
	
	
	
}
