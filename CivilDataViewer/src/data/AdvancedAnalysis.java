package data;

import java.util.Arrays;
import java.util.List;

/**
 * This class has been created to do some simple analyses on our data.
 * @author timer
 *
 */
public class AdvancedAnalysis {
	private DataDetector dataDetected;
	private String columnName;
	private int colIndex;
	private Double[] m;
	
	Double minVal;
	Double maxVal;
	Double modeVal;
	Double medianVal;
	Double meanVal;
	
	public AdvancedAnalysis(DataDetector dataDetected) {
		this.dataDetected = dataDetected;
		this.columnName = columnName;
		this.colIndex = colIndex;
		this.m = dataDetected.getOnlyDoubles();
		//Now work everything out
		determineAll();
	}
	
	private void determineAll() {
		meanVal = mean();
		medianVal = median();
		modeVal = mode();
		minVal = minVal();
		maxVal = maxVal();
	}

	public double mean() {
	    double sum = 0;
	    for (int i = 0; i < m.length; i++) {
	        sum += m[i];
	    }
	    return sum / m.length;
	}
	

	// the array double[] m MUST BE SORTED
	public double median() {
		Arrays.sort(m);
	    int middle = m.length/2;
	    if (m.length%2 == 1) {
	        return m[middle];
	    } else {
	        return (m[middle-1] + m[middle]) / 2.0;
	    }
	}
	

	public Double mode() {
	    Double maxValue = 0.0;
	    Double maxCount = 0.0;

	    for (int i = 0; i < m.length; ++i) {
	        Double count = 0.0;
	        for (int j = 0; j < m.length; ++j) {
	            if (m[j] == m[i]) ++count;
	        }
	        if (count > maxCount) {
	            maxCount = count;
	            maxValue = m[i];
	        }
	    }

	    return maxValue;
	}
	
	public Double minVal() {
		Double smallest = Double.MAX_VALUE;
		for(int i=0;i<m.length;i++) {
		   if(m[i] < smallest){
		       smallest=m[i];
		   }
		}
		return smallest;
	}
	
	public Double maxVal() {
		Double biggest = Double.MIN_VALUE;
		for(int i=0;i<m.length;i++) {
		   if(m[i] > biggest){
		       biggest=m[i];
		   }
		}
		return biggest;
	}
		
	/**
	 * This will return the string to be displayed on the main UI.
	 * @return
	 */
	@Override
	public String toString() {
		String toReturn = "Further inspection reveals: " + "\n\n" +
	"Most common value: " +modeVal +".\n" +
				"Middle value: " + medianVal + ".\n" +
	"Average value: " + meanVal + ".\n" +
				"Minimum value: " +minVal+ ".\n" +
	"Maximum value: " +maxVal+ ".\n";
		return toReturn;
	}
	
	public static void main(String[] args) {
		String[] testC = { "2.0", "50.0", "2.0" };
		DataDetector test3 = new DataDetector(testC);
		AdvancedAnalysis analysis3 = new AdvancedAnalysis(test3);
		System.out.println(analysis3.toString());
	}
}
