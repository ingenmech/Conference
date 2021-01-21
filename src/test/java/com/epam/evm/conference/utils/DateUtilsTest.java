package com.epam.evm.conference.utils;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

    @Test
    public void testIsValidDateShouldReturnTrueWhenDataIsValid(){
        DateUtils utils = new DateUtils();

        boolean actual = utils.isValidDate("2020-12-12");

        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidDateShouldReturnFalseWhenMonthIsInvalid(){
        DateUtils utils = new DateUtils();

        boolean actualFirst = utils.isValidDate("2020-14-12");

        Assert.assertFalse(actualFirst);
    }
    @Test
    public void testIsValidDateShouldReturnFalseWhenYearIsInvalid(){
        DateUtils utils = new DateUtils();

        boolean actualSecond = utils.isValidDate("20201-12-12");

        Assert.assertFalse(actualSecond);
    }
    @Test
    public void testIsValidDateShouldReturnFalseWhenDataIsInvalid(){
        DateUtils utils = new DateUtils();

        boolean actualThird = utils.isValidDate("2020-12-40");

        Assert.assertFalse(actualThird);
    }

    @Test
    public void testIsValidDateShouldReturnFalseWhenDataIsInvalidNull(){
        DateUtils utils = new DateUtils();

        boolean actualFirst = utils.isValidDate(null);

        Assert.assertFalse(actualFirst);
    }

    @Test
    public void testIsValidTimeShouldReturnTrueWhenDataIsValid(){
        DateUtils utils = new DateUtils();

        boolean actual = utils.isValidTime("12:23");

        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidTimeShouldReturnFalseWhenHourIsInvalid(){
        DateUtils utils = new DateUtils();

        boolean actualFirst = utils.isValidTime("24:23");

        Assert.assertFalse(actualFirst);
    }

    @Test
    public void testIsValidTimeShouldReturnFalseWhenMinuteIsInvalid(){
        DateUtils utils = new DateUtils();

        boolean actualSecond = utils.isValidTime("12:60");

        Assert.assertFalse(actualSecond);
    }

    @Test
    public void testIsValidTimeShouldReturnFalseWhenDataIsInvalidNull(){
        DateUtils utils = new DateUtils();

        boolean actualThird = utils.isValidTime(null);

        Assert.assertFalse(actualThird);
    }

}
