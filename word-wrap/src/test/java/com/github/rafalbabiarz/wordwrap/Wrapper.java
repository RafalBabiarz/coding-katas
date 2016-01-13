package com.github.rafalbabiarz.wordwrap;

import static java.lang.System.lineSeparator;

public class Wrapper {
    static final String INVALID_COLUMN_NUMBER_ERROR_MESSAGE = "Column number must be bigger than 0";
    private int columnNumber;

    public Wrapper(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    private String wrap(String toWrap) {
        if (toWrap.length() > columnNumber) {
            return toWrap.substring(0, columnNumber) + lineSeparator() + toWrap.substring(columnNumber);
        }
        return toWrap;
    }

    public static String wrap(String string, int columnNumber) {
        if (columnNumber < 1) {
            throw new IllegalArgumentException(INVALID_COLUMN_NUMBER_ERROR_MESSAGE);
        }
        return new Wrapper(columnNumber).wrap(string);
    }
}
