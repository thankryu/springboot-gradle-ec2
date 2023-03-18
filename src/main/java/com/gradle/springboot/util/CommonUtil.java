package com.gradle.springboot.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Optional;

public class CommonUtil {

    /**
     * StringNumber in comma to Long Number
     * @Example 600,000 -> 600000
     * @param numberString example: 600,000
     * @return long
     */
    public static long strCommaConvertLong(String numberString){
        NumberFormat format = NumberFormat.getInstance();
        long number = 0L;

        if(Optional.ofNullable(numberString).orElse("").isEmpty()){
            return number;
        }

        try {
            number = format.parse(numberString).intValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return number;
    }

    /**
     * StringNumber in comma to BigDecimal Number
     * @param numberString
     * @return
     */
    public static BigDecimal strCommaConvertBigDecimal(String numberString){
        NumberFormat format = NumberFormat.getInstance();
        BigDecimal number = new BigDecimal(0);

        if(Optional.ofNullable(numberString).orElse("").isEmpty()){
            return number;
        }

        try {
            number =  new BigDecimal(format.parse(numberString).intValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return number;
    }

    /**
     * BigDecimal in comma to String Number
     * @param bigDecimal
     * @return
     */
    public static String bigDecimalConvertStr(BigDecimal bigDecimal){
        if(bigDecimal == null){
            return "0";
        } else {
            DecimalFormat df = new DecimalFormat("#,###");
            return df.format(bigDecimal);
        }
    }
}
