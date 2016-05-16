package com.github.rafalbabiarz.wordwrap;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.generator.java.lang.StringGenerator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static com.github.rafalbabiarz.wordwrap.Wrapper.wrap;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitQuickcheck.class)
public class WrapperPropertyBasedTest {
    private static String removeWhitespaces(String string) {
        return string.replaceAll("\\s", "");
    }

    @Property(shrink = false)
    public void stringBeforeAndAfterWrappingIsTheSameIgnoringWhitespaces(
            @From(StringWithSpacesGenerator.class) String stringToWrap, @InRange(min = "1", max = "10") int columns) {
        String wrapped = wrap(stringToWrap, columns);

        assertIsEqualAfterRemovingWhitespace(wrapped, stringToWrap, columns);
    }

    private void assertIsEqualAfterRemovingWhitespace(String wrapped, String stringToWrap, int columns) {
        assertThat(removeWhitespaces(wrapped))
                .describedAs("String <%s> should be equal to <%s> after removing whitespaces. Wrapped for <%s> columns", wrapped, stringToWrap, columns)
                .isEqualTo(removeWhitespaces(stringToWrap));
    }

    @Property(shrink = false)
    public void eachLineShouldHaveAtMostColumnsNumberOfCharacters(
            @From(StringWithSpacesGenerator.class) String stringToWrap, @InRange(min = "1", max = "10") int columns) {
        String wrapped = wrap(stringToWrap, columns);

        assertThat(wrapped.split("\\s"))
                .describedAs("Wrapped string <%s> contains line which is longer than <%s> columns. Result was <%s>", wrapped, columns, stringToWrap)
                .allMatch(line -> line.length() <= columns);
    }

    public static class StringWithSpacesGenerator extends StringGenerator {
        private static final double SPACE_PROBABILITY = 0.2;

        @Override
        protected int nextCodePoint(SourceOfRandomness random) {
            if (random.nextDouble() < SPACE_PROBABILITY) {
                return ' ';
            }
            return random.nextChar('a', 'z');
        }
    }
}
