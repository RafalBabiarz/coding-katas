package com.github.rafalbabiarz.wordwrap;

import java.util.Stack;

import static java.lang.System.lineSeparator;

public class Wrapper {
    static final String INVALID_COLUMN_NUMBER_ERROR_MESSAGE = "Column number must be bigger than 0";
    private final StringBuilder resultBuilder = new StringBuilder();
    private int columnNumber;
    private int currentLineLength = 0;

    private Wrapper(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public static String wrap(String string, int columnNumber) {
        if (columnNumber < 1) {
            throw new IllegalArgumentException(INVALID_COLUMN_NUMBER_ERROR_MESSAGE);
        }
        return new Wrapper(columnNumber).wrap(string);
    }

    private String wrap(String toWrap) {
        return wrapWords(
                asStackWithFirstWordOnPeek(tokenize(toWrap))
        );
    }

    private Stack<String> asStackWithFirstWordOnPeek(String[] words) {
        Stack<String> stack = new Stack<>();
        for (int i = words.length - 1; i > -1; i--) {
            stack.push(words[i]);
        }
        return stack;
    }

    private String[] tokenize(String toWrap) {
        return toWrap.trim().split("\\s+");
    }

    private String wrapWords(Stack<String> words) {
        while (!words.isEmpty()) {
            String nextWord = words.pop();
            if (nextWordDoesNotFitInLine(nextWord)) {
                String remainingPartOfWord = writePartOfFirstWordIfLineIsEmpty(nextWord);
                startNewLine();
                words.push(remainingPartOfWord);
            } else {
                appendWordToLine(nextWord);
            }
        }
        return resultBuilder.toString();
    }

    private boolean nextWordDoesNotFitInLine(String nextWord) {
        return currentLineLength + nextWord.length() > columnNumber;
    }

    private String writePartOfFirstWordIfLineIsEmpty(String nextWord) {
        if (currentLineLength == 0) {
            appendWordToLine(nextWord.substring(0, columnNumber));
            nextWord = nextWord.substring(columnNumber);
        }
        return nextWord;
    }

    private void startNewLine() {
        resultBuilder.append(lineSeparator());
        currentLineLength = 0;
    }

    private void appendWordToLine(String nextWord) {
        if (currentLineLength > 0) {
            resultBuilder.append(" ");
        }
        resultBuilder.append(nextWord);
        currentLineLength += nextWord.length() + 1;
    }
}
