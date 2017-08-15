package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import csvReader.CSVReader;
import data.ArrayListCreator;

public class ArrayListCreatorTest {
	
	ArrayListCreator listCreator = new ArrayListCreator(new CSVReader("accidents.csv"));
	
	
	
	@Test
	public void test() {
		
	}

}
