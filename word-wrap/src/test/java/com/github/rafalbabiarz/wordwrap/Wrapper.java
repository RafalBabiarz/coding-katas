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
            String remainingLines = wrap(toWrap.substring(firstLine.length()).trim());
            return firstLine + lineSeparator() + remainingLines;
        }
        return toWrap;
    }

    private String findFirstLine(String toWrap) {
        String[] split = toWrap.split(" ");
        String firstWord = split[0];
        if (firstWord.length() <= columnNumber) {
            return appendRemainingWords(firstWord, split);

        }
        return firstWord.substring(0, columnNumber);
    }

    private String appendRemainingWords(String firstWord, String[] split) {
        StringBuilder actualLine = new StringBuilder(firstWord);
        for (int i = 1; i < split.length; i++) {
            if (wordFitsIntoLine(split[i], actualLine)) {
                actualLine.append(" ");
                actualLine.append(split[i]);
            }
        }
        return actualLine.toString();
    }

    private boolean wordFitsIntoLine(String proposedWord, StringBuilder actualLine) {
        return proposedWord.length() + actualLine.length() < columnNumber;
    }

    public static String wrap(String string, int columnNumber) {
        if (columnNumber < 1) {
            throw new IllegalArgumentException(INVALID_COLUMN_NUMBER_ERROR_MESSAGE);
        }
        return new Wrapper(columnNumber).wrap(string);
    }
}
