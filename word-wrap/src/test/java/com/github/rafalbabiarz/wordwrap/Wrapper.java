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
            String firstLine = findFirstLine(toWrap);
            String remainingLines = wrap(toWrap.substring(firstLine.length()));
            return firstLine + lineSeparator() + remainingLines;
        }
        return toWrap;
    }

    private String findFirstLine(String toWrap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < columnNumber; i++) {
            stringBuilder.append(toWrap.charAt(i));
        }
        return stringBuilder.toString();
    }

    public static String wrap(String string, int columnNumber) {
        if (columnNumber < 1) {
            throw new IllegalArgumentException(INVALID_COLUMN_NUMBER_ERROR_MESSAGE);
        }
        return new Wrapper(columnNumber).wrap(string);
    }
}
