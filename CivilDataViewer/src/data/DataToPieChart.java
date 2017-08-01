package data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.paint.Color;


public class DataToPieChart extends DataToJFXGraph {
	String[] colData;
	
	//MAKE SURE TO CHANGE DATA FORMAT
	public DataToPieChart(String fileName) {
		super(fileName);
		colData = super.getColumnData(1); 
	}
	
	public ObservableList<PieChart.Data> piechartData() {
		ObservableList<PieChart.Data> piechartData = super.getdataForPieChart(colData);
		return piechartData;
	}
	
	
	public static void main(String[] args) {
/*		DataToPieChart a = new DataToPieChart("Pothole_Enquiries_2015");
		System.out.println(Arrays.toString(a.colData));*/
	}
}
