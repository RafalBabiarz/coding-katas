package com.github.rafalbabiarz.romannumerals;

public class RomanNumeralConverter {
    public int convert(String romanNumeral) {
        int arabicValue = 0;
        for (int i = 0; i < romanNumeral.length() - 1; i++) {
            int romanLiteralArabicValue = transformRomanLiteral(romanNumeral.charAt(i));
            int nextRomanLiteralArabicValue = transformRomanLiteral(romanNumeral.charAt(i + 1));
            if (nextRomanLiteralArabicValue > romanLiteralArabicValue) {
                arabicValue -= romanLiteralArabicValue;
            } else {
                arabicValue += romanLiteralArabicValue;
            }
        }
        arabicValue += transformRomanLiteral(romanNumeral.charAt(romanNumeral.length() - 1));
        return arabicValue;
    }

    private int transformRomanLiteral(char romanLiteral) {
        switch (romanLiteral) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                throw new IllegalArgumentException();
        }
    }
}
