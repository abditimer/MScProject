package tests;

import static org.junit.Assert.*;
import csvReader.CSVReader;
import data.DataDetector;
import org.junit.Test;

public class CSVReadDataDetectedTest {
	//all tests are based on this file
	CSVReader potholeData = new CSVReader("Pothole_Enquiries_2015.csv");
	CSVReader accidentsData = new CSVReader("accidents.csv");
	DataDetector dDetector;
	
	@Test
	public void test1() {
		String[] colTest = potholeData.getColumnData(0);
		dDetector = new DataDetector(colTest);
		
		boolean expected = true;
		boolean actual = dDetector.contains1DataType();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test2() {
		String[] colTest = potholeData.getColumnData(0);
		dDetector = new DataDetector(colTest);
		
		String expected = "String = 6403"
;
		String actual = dDetector.printDataTypes();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test3() {
		String[] colTest = potholeData.getColumnData(2);
		dDetector = new DataDetector(colTest);
		
		boolean expected = true;
		boolean actual = dDetector.containsString();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test4() {
		String[] colTest = accidentsData.getColumnData(3);
		dDetector = new DataDetector(colTest);
		
		boolean expected = true;
		boolean actual = dDetector.latLongDetector("Longitude");
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test5() {
		String[] colTest = accidentsData.getColumnData(3);
		dDetector = new DataDetector(colTest);
		
		String[] expected = {"Double"};
		String[] actual = dDetector.typesContained();
		
		assertArrayEquals(expected, actual);
	}

}
