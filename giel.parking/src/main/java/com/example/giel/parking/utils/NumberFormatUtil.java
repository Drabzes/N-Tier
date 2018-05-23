package com.example.giel.parking.utils;

import java.text.DecimalFormat;

@SuppressWarnings("unused")
public class NumberFormatUtil {

  private static final String ONE_DECIMALS = "#.0";
  private static final String TWO_DECIMALS = "#.00";
  private static final String THREE_DECIMALS = "#.000";
  private static final String FOUR_DECIMALS = "#.0000";

  private static final DecimalFormat decimalFormatter = new DecimalFormat();

  public static double formatDoubleWith1Decimal(double numberToFormat) {
    decimalFormatter.applyPattern(ONE_DECIMALS);
    return formatDouble(numberToFormat);
  }

  public static double formatDoubleWith2Decimals(double numberToFormat) {
    decimalFormatter.applyPattern(TWO_DECIMALS);
    return formatDouble(numberToFormat);
  }

  public static double formatDoubleWith3Decimals(double numberToFormat) {
    decimalFormatter.applyPattern(THREE_DECIMALS);
    return formatDouble(numberToFormat);
  }

  public static double formatDoubleWith4Decimals(double numberToFormat) {
    decimalFormatter.applyPattern(FOUR_DECIMALS);
    return formatDouble(numberToFormat);
  }

  private static double formatDouble(double numberToFormat) {
    return Double.valueOf(decimalFormatter.format(numberToFormat));
  }

}
