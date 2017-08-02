package data;

import java.util.Map.Entry;

import csvReader.CSVReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;


/**
 * A subclass for getting the required data as an observable List.
 * 
 * @author timer
 *
 */
public class ObservableListProvider extends DataFormatFX {
	private String[] singleStringObservableList;
	private String[] doubleStringObservableList;
	private CSVReader reader;
	
	public ObservableListProvider(String fileName) {
		super(fileName);
		reader = super.reader;
	}
	
	/**
	 * Selects and sets a single column
	 */
	public void setSingleObservableList(int column) {
		singleStringObservableList = super.getColumnData(column); 
	}
	
	/**
	 * Selects and sets a single column
	 */
	public void setDoubleObservableList(int col1, int col2) {
		//TODO: write method for observablelist of two datasets
	}
	
	/**
	 * Iterates over String array and returns observable list that can be used to draw a pie chart
	 * 
	 * @return Returns observable list data that is used to draw graphs
	 */
	public ObservableList<PieChart.Data> getPieChartObservableList() {
		
		ObservableList<PieChart.Data> observableList = FXCollections.observableArrayList(); 
		
        for(Entry<String, Integer> e : countAndMapData(singleStringObservableList).entrySet()) {
        	observableList.add(new PieChart.Data(e.getKey(), e.getValue()));
        }
        
        return observableList;
	}

	
	public static void main(String[] args) {
		ObservableListProvider a = new ObservableListProvider("Pothole_Enquiries_2015");
		//a.printHeaders();
		a.setSingleObservableList(4);
		
		
	}
}
