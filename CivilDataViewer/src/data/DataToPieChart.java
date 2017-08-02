package data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.paint.Color;

/**
 * A subclass for getting the required data for a pie chart.
 * @author timer
 *
 */
public class DataToPieChart extends DataToJFXGraph {
	String[] colDataForPieChart;
	
	//MAKE SURE TO CHANGE DATA FORMAT
	public DataToPieChart(String fileName) {
		super(fileName);
		colDataForPieChart = super.getColumnData(1); 
	}
	
	/**
	 * Iterates over String array and returns observable list that can be used to draw a pie chart
	 * 
	 * @param arrayDataForPieChart String array of data to represent in a pie chart
	 * @return Returns observable list data that is used to draw graphs
	 */
	public ObservableList<PieChart.Data> getdataForPieChart(String[] arrayDataForPieChart) {
		
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(); 
		
        for(Entry<String, Integer> e : countAndMapData(arrayDataForPieChart).entrySet()) {
        	pieChartData.add(new PieChart.Data(e.getKey(), e.getValue()));
        }
        
        return pieChartData;
	}
	
	public ObservableList<PieChart.Data> piechartData() {
		ObservableList<PieChart.Data> piechartData = getdataForPieChart(colDataForPieChart);
		return piechartData;
	}
	
	
	public static void main(String[] args) {
		/*DataToPieChart a = new DataToPieChart("Pothole_Enquiries_2015");
		System.out.println(Arrays.toString(a.colData));*/
	}
}
