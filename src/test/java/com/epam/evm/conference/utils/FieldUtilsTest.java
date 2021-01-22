package com.epam.evm.conference.utils;

import org.junit.Assert;
import org.junit.Test;

public class FieldUtilsTest {

    @Test
    public void testIsValidShortLengthShouldReturnTrueWhenDataIsValid() {

        boolean actual = FieldUtils.isValidLength("v655v65", FieldUtils.SHORT_SIZE);

        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidShortLengthShouldReturnTrueWhenDataIsInvalid() {

        boolean actual = FieldUtils.isValidLength(
                "c6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sa", FieldUtils.SHORT_SIZE);

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShortLengthShouldReturnTrueWhenDataIsInvalidNull() {

        boolean actual = FieldUtils.isValidLength(null, FieldUtils.SHORT_SIZE);

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidMediumLengthShouldReturnTrueWhenDataIsValid() {

        boolean actual = FieldUtils.isValidLength("v655v65", FieldUtils.MID_SIZE);

        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidMediumLengthShouldReturnTrueWhenDataIsInvalid() {

        boolean actual = FieldUtils.isValidLength("c6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a" +
                "55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sa" +
                "c6a55c41sa", FieldUtils.MID_SIZE);

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidMediumLengthShouldReturnTrueWhenDataIsInvalidNull() {

        boolean actual = FieldUtils.isValidLength(null, FieldUtils.MID_SIZE);

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidLongLengthShouldReturnTrueWhenDataIsValid() {

        boolean actual = FieldUtils.isValidLength("v655v65", FieldUtils.LONG_SIZE);

        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidLongLengthShouldReturnTrueWhenDataIsInvalid() {

        boolean actual = FieldUtils.isValidLength("c6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6" +
                "a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41s" +
                "ac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c4" +
                "1sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sa", FieldUtils.LONG_SIZE);

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidLongLengthShouldReturnTrueWhenDataIsInvalidNull() {

        boolean actual = FieldUtils.isValidArrayElementsLength(null, FieldUtils.LONG_SIZE);

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidLongLengthShouldReturnTrueWhenDataIsValidArray() {

        boolean actual = FieldUtils.isValidArrayElementsLength(
                new String[]{"v655v65", "v655v65", "v655v65"}, FieldUtils.SHORT_SIZE);

        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidLongLengthShouldReturnTrueWhenDataIsInvalidArray() {

        boolean actual = FieldUtils.isValidArrayElementsLength(new String[]{"DCASC", "c6a55c41sac6a55c41sac6a55c41s" +
                "ac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55" +
                "c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sa", "daf"}, FieldUtils.MID_SIZE);

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidLongLengthShouldReturnTrueWhenDataIsInvalidArrayNull() {
        String[] element = null;

        boolean actual = FieldUtils.isValidArrayElementsLength(element, FieldUtils.LONG_SIZE);

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidLongLengthShouldReturnTrueWhenDataIsInvalidElementNull() {

        boolean actual = FieldUtils.isValidArrayElementsLength(
                new String[]{"v655v65", null, "v655v65"}, FieldUtils.LONG_SIZE);

        Assert.assertFalse(actual);
    }
}
