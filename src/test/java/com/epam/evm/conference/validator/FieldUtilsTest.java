package com.epam.evm.conference.validator;

import org.junit.Assert;
import org.junit.Test;

public class FieldUtilsTest {

    @Test
    public void testIsValidShortLengthShouldReturnTrueWhenDataIsValid() {
        FieldUtils fieldUtils = new FieldUtils();

        boolean actual = fieldUtils.isValidShortLength("v655v65");

        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidShortLengthShouldReturnTrueWhenDataIsInvalid() {
        FieldUtils fieldUtils = new FieldUtils();

        boolean actual = fieldUtils.isValidShortLength("c6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sa");

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidShortLengthShouldReturnTrueWhenDataIsInvalidNull() {
        FieldUtils fieldUtils = new FieldUtils();

        boolean actual = fieldUtils.isValidShortLength(null);

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidMediumLengthShouldReturnTrueWhenDataIsValid() {
        FieldUtils fieldUtils = new FieldUtils();

        boolean actual = fieldUtils.isValidMediumLength("v655v65");

        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidMediumLengthShouldReturnTrueWhenDataIsInvalid() {
        FieldUtils fieldUtils = new FieldUtils();

        boolean actual = fieldUtils.isValidMediumLength("c6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a" +
                "55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sa" +
                "c6a55c41sa");

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidMediumLengthShouldReturnTrueWhenDataIsInvalidNull() {
        FieldUtils fieldUtils = new FieldUtils();
        String element = null;

        boolean actual = fieldUtils.isValidMediumLength(element);

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidLongLengthShouldReturnTrueWhenDataIsValid() {
        FieldUtils fieldUtils = new FieldUtils();

        boolean actual = fieldUtils.isValidMediumLength("v655v65");

        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidLongLengthShouldReturnTrueWhenDataIsInvalid() {
        FieldUtils fieldUtils = new FieldUtils();

        boolean actual = fieldUtils.isValidMediumLength("c6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6" +
                "a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41s" +
                "ac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c4" +
                "1sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sa");

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidLongLengthShouldReturnTrueWhenDataIsInvalidNull() {
        FieldUtils fieldUtils = new FieldUtils();
        String element = null;

        boolean actual = fieldUtils.isValidMediumLength(element);

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidLongLengthShouldReturnTrueWhenDataIsValidArray() {
        FieldUtils fieldUtils = new FieldUtils();

        boolean actual = fieldUtils.isValidMediumLength(new String[]{"v655v65", "v655v65", "v655v65"});

        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidLongLengthShouldReturnTrueWhenDataIsInvalidArray() {
        FieldUtils fieldUtils = new FieldUtils();

        boolean actual = fieldUtils.isValidMediumLength(new String[]{"DCASC", "c6a55c41sac6a55c41sac6a55c41s" +
                "ac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sac6a55" +
                "c41sac6a55c41sac6a55c41sac6a55c41sac6a55c41sa", "dad"});

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidLongLengthShouldReturnTrueWhenDataIsInvalidArrayNull() {
        FieldUtils fieldUtils = new FieldUtils();
        String[] element = null;

        boolean actual = fieldUtils.isValidMediumLength(element);

        Assert.assertFalse(actual);
    }

    @Test
    public void testIsValidLongLengthShouldReturnTrueWhenDataIsInvalidElementNull() {
        FieldUtils fieldUtils = new FieldUtils();

        boolean actual = fieldUtils.isValidMediumLength(new String[]{"v655v65", null, "v655v65"});

        Assert.assertFalse(actual);
    }

}
