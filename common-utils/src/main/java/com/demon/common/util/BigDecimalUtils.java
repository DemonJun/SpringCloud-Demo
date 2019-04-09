package com.demon.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 金钱工具类, 精度为小数点后n位
 */
public class BigDecimalUtils {

  public static BigDecimal add(BigDecimal a, BigDecimal b, int newScale) {
    return a.add(b).setScale(newScale, RoundingMode.HALF_EVEN);
  }

  public static BigDecimal subtract(BigDecimal a, BigDecimal b, int newScale) {
    return a.subtract(b).setScale(newScale, RoundingMode.HALF_EVEN);
  }

  public static BigDecimal multiply(BigDecimal a, BigDecimal b, int newScale) {
    return a.multiply(b).setScale(newScale, RoundingMode.HALF_EVEN);
  }

  public static BigDecimal divide(BigDecimal a, BigDecimal b, int newScale) {
    return a.divide(b, RoundingMode.HALF_EVEN).setScale(newScale, RoundingMode.HALF_EVEN);
  }

  public static int compareTo(BigDecimal a, BigDecimal b, int newScale) {
    return a.setScale(newScale, RoundingMode.HALF_EVEN)
        .compareTo(b.setScale(newScale, RoundingMode.HALF_EVEN));
  }
}
