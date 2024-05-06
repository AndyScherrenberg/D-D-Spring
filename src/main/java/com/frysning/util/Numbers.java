package com.frysning.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Numbers {

	public static Double ReadableDecimal(double value){
		return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

}
