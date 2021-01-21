package com.epam.evm.conference.utils;

import org.junit.Assert;
import org.junit.Test;

public class NumberUtilsTest {

    @Test
    public void testIsValidDigitShouldReturnTrueWhenDataIsValid(){
        NumberUtils numberUtils = new NumberUtils();

        boolean actual = numberUtils.isValidDigit("1234");

        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidDigitShouldReturnFalseWhenDataIsInvalid(){
        NumberUtils numberUtils = new NumberUtils();

        boolean actual = numberUtils.isValidDigit("12a34");

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidDigitShouldReturnFalseWhenDataIsNull(){
        NumberUtils numberUtils = new NumberUtils();
        String digit = null;

        boolean actual = numberUtils.isValidDigit(digit);

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidDigitShouldReturnTrueWhenDataIsValidArray(){
        NumberUtils numberUtils = new NumberUtils();

        boolean actual = numberUtils.isValidDigit(new String[]{"123", "11", "0"});

        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidDigitShouldReturnFalseWhenDataIsInvalidArray(){
        NumberUtils numberUtils = new NumberUtils();

        boolean actual = numberUtils.isValidDigit(new String[]{"123", "11d", "0"});

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidDigitShouldReturnFalseWhenDataIsInvalidNullArray(){
        NumberUtils numberUtils = new NumberUtils();

        boolean actual = numberUtils.isValidDigit(new String[]{"123", null, "0"});

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidDigitShouldReturnFalseWhenDataIsNullArray(){
        NumberUtils numberUtils = new NumberUtils();
        String digit = null;

        boolean actual = numberUtils.isValidDigit(digit);

        Assert.assertFalse(actual);
    }
}
