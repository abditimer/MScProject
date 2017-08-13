package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import csvReader.CSVReader;
import data.DataDetector;
import data.DataFormatFX;
import org.junit.Test;

public class DataFormatFXTest {
	CSVReader reader = new CSVReader("Pothole_Enquiries_2015.csv");
	DataFormatFX potholeData = new DataFormatFX(reader);
	Map<String, Integer> actual = potholeData.countAndMapData(potholeData.getColumnData(0));
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
		DataFormatFX potholeData = new DataFormatFX(reader);
		String[] columnWanted = potholeData.getColumnData(3);
		Map<String, Integer> actual = potholeData.countAndMapData(columnWanted);
		
		Map<String, Integer> expected = new HashMap<>();
		expected.put("\"ALL CLIENTS\"", 6);
		expected.put("\"EASTERN DIVISION\"", 1005);
		expected.put("\"NORTHERN DIVISION\"", 1408);
		expected.put("\"SOUTHERN DIVISION\"", 2319);
		expected.put("\"WESTERN DIVISION\"", 1665);
		
		assertEquals(expected, actual);
	}

}
