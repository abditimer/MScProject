package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import data.AdvancedAnalysis;
import data.DataDetector;

public class AdvancedAnalysisTest {
	// Test 1
	String[] testA = { "0.0", "1.0", "2.0", "3.0", "4.0", "5.0" };
	DataDetector test = new DataDetector(testA);
	AdvancedAnalysis analysis = new AdvancedAnalysis(test);
	// Test 2
	String[] testB = { "1.0", "1.0", "1.0" };
	DataDetector test2 = new DataDetector(testB);
	AdvancedAnalysis analysis2 = new AdvancedAnalysis(test2);
	// Test 3
	String[] testC = { "2.0", "50.0", "2.0" };
	DataDetector test3 = new DataDetector(testC);
	AdvancedAnalysis analysis3 = new AdvancedAnalysis(test3);

	/**
	 * Tests
	 */
	@Test
	public void test1() {
		// Median
		Double Medianexpected = 2.5;
		Double Medianactual = analysis.median();
		assertEquals(Medianexpected, Medianactual);
		// Mean
		Double Meanexpected = 2.5;
		Double Meanactual = analysis.mean();
		assertEquals(Meanexpected, Meanactual);
		// MinVal
		Double minValexpected = 0.0;
		Double minValactual = analysis.minVal();
		assertEquals(minValexpected, minValactual);
		// maxVal
		Double maxValexpected = 5.0;
		Double maxValactual = analysis.maxVal();
		assertEquals(maxValexpected, maxValactual);
	}

	@Test
	public void test2() {
		// Median
		Double Medianexpected = 1.0;
		Double Medianactual = analysis2.median();
		assertEquals(Medianexpected, Medianactual);
		// Mean
		Double Meanexpected = 1.0;
		Double Meanactual = analysis2.mean();
		assertEquals(Meanexpected, Meanactual);
		// MinVal
		Double minValexpected = 1.0;
		Double minValactual = analysis2.minVal();
		assertEquals(minValexpected, minValactual);
		// maxVal
		Double maxValexpected = 1.0;
		Double maxValactual = analysis2.maxVal();
		assertEquals(maxValexpected, maxValactual);
	}
	
	@Test
	public void test3() {
		//Mode
		Double modeexpected = 2.0;
		Double modeactual = analysis3.mode();
		assertEquals(modeactual, modeexpected);
	}
}
