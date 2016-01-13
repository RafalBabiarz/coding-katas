package com.github.rafalbabiarz.wordwrap;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.github.rafalbabiarz.wordwrap.Wrapper.wrap;
import static java.lang.String.join;
import static java.lang.System.lineSeparator;
import static org.assertj.core.api.Assertions.assertThat;

public class WrapperTest {
    private static final String ANY_STRING = "any string";

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void returnsSameStringWhenItsShorterThanColumnNumber() {
        String wrapped = wrap(ANY_STRING, ANY_STRING.length() + 1);

        assertThat(wrapped).isEqualTo(ANY_STRING);
    }

    @Test
    public void wrapsSingleWordToMultipleLinesWhenItsLongerThanColumnNumber() {
        String wrapped = wrap("abcde", 2);

        assertThat(wrapped).isEqualTo(joinWithNewLines("ab", "cd", "e"));
    }

    private String joinWithNewLines(String... words) {
        return join(lineSeparator(), words);
    }

    @Test
    public void wrapsWordsOnSpacesWhenTheyDontFitIntoOneLine() {
        String wrapped = wrap("abc def", 5);

        assertThat(wrapped).isEqualTo(joinWithNewLines("abc", "def"));
    }

    @Test
    public void throwsInvalidArgumentExceptionWhenColumnNumberIsSmallerThanOne() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Column number must be bigger than 0");

        wrap(ANY_STRING, 0);
    }
}
