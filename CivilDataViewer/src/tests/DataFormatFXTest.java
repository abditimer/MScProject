package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import csvReader.CSVReader;
import data.DataDetector;
import data.DataFormatFX;
import org.junit.Test;

public class DataFormatFXTest {
	CSVReader potholeData = new CSVReader("Pothole_Enquiries_2015.csv");
	
	private List<String[]> columnData = potholeData.getData();
	String[] col = DataFormatFX.getColumnData(0, columnData);
	
	Map<String, Integer> actual = DataFormatFX.countAndMapData(col);
	Map<String, Integer> expected = new HashMap<>();
	
	@Test
	public void test1() {
		 expected.put("\"Pothole\"", 6403);
	    assertEquals(actual, expected);
	}
	
	@Test
	public void test2() {
		int expectedSize = 1;
	    assertEquals(actual.size(), expectedSize);
	}
	
	@Test
	public void test3() {
		String[] test = {"2.42542", "324.3222"};
		DataDetector typeDetector = new DataDetector(test);
		Boolean actual = typeDetector.contains1DataType();
		
		Boolean expected = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void test4() {
		String[] test = {"asdasdasdasd", "324.3222"};
		DataDetector typeDetector = new DataDetector(test);
		Boolean actual = typeDetector.contains1DataType();
		assertFalse(actual);
	}
	
	@Test
	public void test5() {
		String[] test = {"asdasdasdasd", "324.3222"};
		DataDetector typeDetector = new DataDetector(test);
		Boolean actual = typeDetector.contains1DataType();
		assertFalse(actual);
	}
	
	@Test
	public void test6() {
		CSVReader potholeData = new CSVReader("Pothole_Enquiries_2015.csv");
		
		List<String[]> columnData = potholeData.getData();
		String[] col = DataFormatFX.getColumnData(3, columnData);
		
		
		Map<String, Integer> actual = DataFormatFX.countAndMapData(col);
		
		Map<String, Integer> expected = new HashMap<>();
		expected.put("\"ALL CLIENTS\"", 6);
		expected.put("\"EASTERN DIVISION\"", 1005);
		expected.put("\"NORTHERN DIVISION\"", 1408);
		expected.put("\"SOUTHERN DIVISION\"", 2319);
		expected.put("\"WESTERN DIVISION\"", 1665);
		
		assertEquals(expected, actual);
	}


}
