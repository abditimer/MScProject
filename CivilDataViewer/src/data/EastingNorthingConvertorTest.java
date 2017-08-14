package data;

import static org.junit.Assert.*;

import java.util.ArrayList;

import data.EastingNorthingConvertor;
import org.junit.Test;

public class EastingNorthingConvertorTest {
	
	
	
	@Test
	public void test() {
		String[] eastings = {"460830", "469375", "460679"};
	String[] northings = {"452510", "449837", "451868"};
	EastingNorthingConvertor convert = new EastingNorthingConvertor(eastings, northings);
		
		convert.convert();
		ArrayList<Double> longs = new ArrayList<>();
		longs.add(-1.0742631746813698);
		longs.add(-0.9446442413233298);
		longs.add(-1.076691957190896);
		
		ArrayList<Double> lats = new ArrayList<>();
		longs.add(53.965102352858445);
		longs.add(53.94000837322422);
		longs.add(53.95935070224746);
		
		assertEquals(convert.getLongtitude().get(0), longs.get(0));
		assertEquals(convert.getLongtitude().get(1), longs.get(1));
		assertEquals(convert.getLongtitude().get(2), longs.get(2));
		
		


	}

}
