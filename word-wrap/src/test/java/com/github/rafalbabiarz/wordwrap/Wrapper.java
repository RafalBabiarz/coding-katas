package com.github.rafalbabiarz.wordwrap;

public class Wrapper {

    static final String INVALID_COLUMN_NUMBER_ERROR_MESSAGE = "Column number must be bigger than 0";

    public static String wrap(String string, int columnNumber) {
        throw new IllegalArgumentException(INVALID_COLUMN_NUMBER_ERROR_MESSAGE);
    }
}
