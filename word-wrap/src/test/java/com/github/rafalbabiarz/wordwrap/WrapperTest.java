package com.github.rafalbabiarz.wordwrap;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.github.rafalbabiarz.wordwrap.Wrapper.wrap;

public class WrapperTest {
    private static final String ANY_STRING = "any string";

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void throwsInvalidArgumentExceptionWhenColumnNumberIsSmallerThanOne() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Column number must be bigger than 0");

        wrap(ANY_STRING, 0);
    }
}
