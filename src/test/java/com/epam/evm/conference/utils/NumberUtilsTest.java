package com.epam.evm.conference.utils;

import org.junit.Assert;
import org.junit.Test;

public class NumberUtilsTest {

    @Test
    public void testIsValidDigitShouldReturnTrueWhenDataIsValid(){

        boolean actual = NumberUtils.isValidDigit("1234");

        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidDigitShouldReturnFalseWhenDataIsInvalid(){

        boolean actual = NumberUtils.isValidDigit("12a34");

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidDigitShouldReturnFalseWhenDataIsNull(){
        String digit = null;

        boolean actual = NumberUtils.isValidDigit(digit);

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidDigitShouldReturnTrueWhenDataIsValidArray(){

        boolean actual = NumberUtils.isValidDigit(new String[]{"123", "11", "0"});

        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidDigitShouldReturnFalseWhenDataIsInvalidArray(){

        boolean actual = NumberUtils.isValidDigit(new String[]{"123", "11d", "0"});

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidDigitShouldReturnFalseWhenDataIsInvalidNullArray(){

        boolean actual = NumberUtils.isValidDigit(new String[]{"123", null, "0"});

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidDigitShouldReturnFalseWhenDataIsNullArray(){
        String digit = null;

        boolean actual = NumberUtils.isValidDigit(digit);

        Assert.assertFalse(actual);
    }
}
