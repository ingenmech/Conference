package com.epam.evm.conference.utils;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

    @Test
    public void testIsValidDateShouldReturnTrueWhenDataIsValid(){

        boolean actual = DateUtils.isValidDate("2020-12-12");

        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidDateShouldReturnFalseWhenMonthIsInvalid(){

        boolean actualFirst = DateUtils.isValidDate("2020-14-12");

        Assert.assertFalse(actualFirst);
    }
    @Test
    public void testIsValidDateShouldReturnFalseWhenYearIsInvalid(){

        boolean actualSecond = DateUtils.isValidDate("20201-12-12");

        Assert.assertFalse(actualSecond);
    }
    @Test
    public void testIsValidDateShouldReturnFalseWhenDataIsInvalid(){

        boolean actualThird = DateUtils.isValidDate("2020-12-40");

        Assert.assertFalse(actualThird);
    }

    @Test
    public void testIsValidDateShouldReturnFalseWhenDataIsInvalidNull(){

        boolean actualFirst = DateUtils.isValidDate(null);

        Assert.assertFalse(actualFirst);
    }

    @Test
    public void testIsValidTimeShouldReturnTrueWhenDataIsValid(){

        boolean actual = DateUtils.isValidTime("12:23");

        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidTimeShouldReturnFalseWhenHourIsInvalid(){

        boolean actualFirst = DateUtils.isValidTime("24:23");

        Assert.assertFalse(actualFirst);
    }

    @Test
    public void testIsValidTimeShouldReturnFalseWhenMinuteIsInvalid(){

        boolean actualSecond = DateUtils.isValidTime("12:60");

        Assert.assertFalse(actualSecond);
    }

    @Test
    public void testIsValidTimeShouldReturnFalseWhenDataIsInvalidNull(){

        boolean actualThird = DateUtils.isValidTime(null);

        Assert.assertFalse(actualThird);
    }

}
