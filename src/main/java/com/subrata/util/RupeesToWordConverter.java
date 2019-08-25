package com.subrata.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import com.subrata.exception.BusinessException;

public class RupeesToWordConverter {
	
	/** The Constant to_19. */
	private static final String[] TO_19 = { "Zero", "One", "Two", "Three",
			"Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
			"Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
			"Seventeen", "Eighteen", "Nineteen" };

	/** The Constant tens. */
	private static final String[] TENS = { "Twenty", "Thirty", "Fourty",
			"Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };

	/** The Constant denom. */
	private static final String[] DENOM = { "", "Thousand", "Million",
			"Billion", "Trillion", "quadrillion", "quintillion", "sextillion",
			"septillion", "octillion", "nonillion", "decillion", "undecillion",
			"duodecillion", "tredecillion", "quattuordecillion",
			"sexdecillion", "septendecillion", "octodecillion",
			"novemdecillion", "vigintillion" };

	/**
	 * convert a value < 100 to English.
	 */
	private static String convertNN(int val) throws BusinessException {
		if (val < 20) {
			return TO_19[val];
		}

		for (int v = 0; v < TENS.length; v++) {
			String dcap = TENS[v];
			int dval = 20 + 10 * v;
			if (dval + 10 > val) {
				if ((val % 10) != 0) {
					return dcap + "-" + TO_19[val % 10];
				}
				return dcap;
			}
		}
		throw new BusinessException("Should never get here, less than 100 failure");
	}

	/**
	 * convert a value < 1000 to english
	 */
	private static String convertNNN(int val) throws BusinessException {
		String word = "";
		int rem = val / 100;
		int mod = val % 100;
		if (rem > 0) {
			word = TO_19[rem] + " Hundred";
			if (mod > 0) {
				word = word + " and ";
			}
		}
		if (mod > 0) {
			word = word + convertNN(mod);
		}
		return word;
	}

	/**
	 * English number.
	 */
	public static String englishNumber(int val) throws BusinessException {
		if (val < 100) {
			return convertNN(val);
		}
		if (val < 1000) {
			return convertNNN(val);
		}
		for (int v = 0; v < DENOM.length; v++) {
			int didx = v - 1;
			int dval = new Double(Math.pow(1000, v)).intValue();
			if (dval > val) {
				int mod = new Double(Math.pow(1000, didx)).intValue();
				int l = val / mod;
				int r = val - (l * mod);
				String ret = convertNNN(l) + " " + DENOM[didx];
				if (r > 0) {
					ret = ret + " " + englishNumber(r);
				}
				return ret;
			}
		}
		throw new BusinessException(
				"Should never get here, bottomed out in english_number");
	}
	
	
	private static String convertNNConverter(int val, int paise) throws BusinessException {
		if (val < 20) {			
			return TO_19[val];
		}

		for (int v = 0; v < TENS.length; v++) {
			String dcap = TENS[v];			
			int dval = 20 + 10 * v;			
			if (dval + 10 > val) {
				if ((val % 10) != 0) {					
					return dcap + "-" + TO_19[val % 10];
				}				
				return dcap;
			}
		}
		throw new BusinessException("Should never get here, less than 100 failure");
	}
	
	private static String convertNNNConverter(int val, int paise) throws BusinessException {
		String word = "";
		int rem = val / 100;
		int mod = val % 100;
		if (rem > 0) {
			word = TO_19[rem] + " Hundred";
			if (mod > 0) {
				word = word + " ";
			}
		}
		if (mod > 0) {
			word = word +"and "+ convertNN(mod);
		}else{
			word = word;
		}		
		return word;
	}
	
	public static String englishNumberConverter(BigDecimal value) throws BusinessException {
		int val = value.intValue();
		int paise =value.remainder(BigDecimal.ONE).setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).intValue();
		
		if (val < 100) {
			return convertNNConverter(val,paise);
		}
		if (val < 1000) {
			return convertNNNConverter(val,paise);
		}
		for (int v = 0; v < DENOM.length; v++) {
			int didx = v - 1;
			int dval = new Double(Math.pow(1000, v)).intValue();
			if (dval > val) {
				int mod = new Double(Math.pow(1000, didx)).intValue();
				int l = val / mod;
				int r = val - (l * mod);
				String ret = convertNNN(l) + " " + DENOM[didx];
				if (r > 0) {
					ret = ret + " " + englishNumber(r);
				}else{
					ret = ret;
				}				
				return ret;
			}
		}
		throw new BusinessException(
				"Should never get here, bottomed out in english_number");
	}
	
	
}
