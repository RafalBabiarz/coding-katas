package com.github.rafalbabiarz.romannumerals;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class RomanNumeralConverterTest {

    private RomanNumeralConverter romanNumeralConverter = new RomanNumeralConverter();

    @Test
    @Parameters({"I, 1", "V, 5", "X, 10", "L, 50", "C, 100", "D, 500", "M, 1000"})
    public void returnsValueForOneSymbolLongRomanNumeral(String romanNumeral, int arabicValue) {
        int returnedArabicValue = romanNumeralConverter.convert(romanNumeral);

        assertThat(returnedArabicValue).isEqualTo(arabicValue);
    }

    @Test
    public void addsAllLiteralsWhenTheyAreInDescendingOrder() {
        int returnedArabicValue = romanNumeralConverter.convert("MDIII");

        assertThat(returnedArabicValue).isEqualTo(1503);
    }

    @Test
    public void subtractsLiteralWhenItIsPreceedingHigherLiteral() {
        int returnedArabicValue = romanNumeralConverter.convert("IV");

        assertThat(returnedArabicValue).isEqualTo(4);
    }
}
