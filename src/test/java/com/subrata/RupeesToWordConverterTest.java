package com.subrata;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.subrata.exception.BusinessException;
import com.subrata.util.RupeesToWordConverter;

public class RupeesToWordConverterTest {	
	private static RupeesToWordConverter rupeesToWordConverter;
	
	@BeforeClass
	public static void initCalculator() {
		rupeesToWordConverter = new RupeesToWordConverter();
	}


	@Before
	public void beforeEachTest() {
		System.out.println("This is executed before each Test");
	}

	@After
	public void afterEachTest() {
		System.out.println("This is exceuted after each Test");
	}
	
	
	
	@SuppressWarnings("static-access")
	@Test
	public void testSum() throws BusinessException {
		String result = rupeesToWordConverter.englishNumberConverter(new BigDecimal(56945781));
		System.out.println(result);
		//assertEquals("One", result);
	}
	

}
