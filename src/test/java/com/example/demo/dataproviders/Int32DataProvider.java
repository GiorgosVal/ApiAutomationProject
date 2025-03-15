package com.example.demo.dataproviders;

import org.testng.annotations.DataProvider;

/**
 * Data provider for the {@link Integer} data type
 */
public class Int32DataProvider {

    /**
     * Generates valid boundary values of the {@link Integer} data type
     *
     * @return Valid boundary values to feed the tests
     */
    @DataProvider(name = "int32InsideBoundaryValues")
    public static Object[][] int32InsideBoundaryValues() {
        return new Object[][]{
                {Integer.MAX_VALUE}, {Integer.MIN_VALUE}, {0}
        };
    }

    /**
     * Generates invalid boundary values of the {@link Integer} data type. Since the values are invalid
     * (the outside boundaries), the returning values are of type {@link Long}
     *
     * @return Invalid boundary values to feed the tests
     */
    @DataProvider(name = "int32OutsideBoundaryValues")
    public static Object[][] int32OutsideBoundaryValues() {
        return new Object[][]{
                {(long) Integer.MAX_VALUE + 1}, {(long) Integer.MIN_VALUE - 1}
        };
    }

    /**
     * Generates invalid values of the {@link Integer} data type. Since the values are invalid
     * (the outside boundaries), the returning values are of type {@link String}
     *
     * @return Invalid values to feed the tests
     */
    @DataProvider(name = "int32InvalidValues")
    public static Object[][] int32InvalidValues() {
        return new Object[][]{
                {String.valueOf((long) Integer.MAX_VALUE + 1)}, {String.valueOf((long) Integer.MIN_VALUE - 1)}, {"1s"}
        };
    }
}
