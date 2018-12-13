package com.expedia.adaptivealerting.anomdetect.holtwinters;

import org.junit.Test;

import static org.junit.Assert.*;

public class SeasonalityTypeParserTest {
    private SeasonalityTypeParser subject = new SeasonalityTypeParser();

    @Test
    public void parse() {
        assertEquals(SeasonalityType.ADDITIVE, subject.parse("ADDITIVE"));
        assertEquals(SeasonalityType.ADDITIVE, subject.parse("additive"));
        assertEquals(SeasonalityType.MULTIPLICATIVE, subject.parse("MULTIPLICATIVE"));
        assertEquals(SeasonalityType.MULTIPLICATIVE, subject.parse("multiplicative"));
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidTextInput() {
        subject.parse("bad_seasonality");
    }
}