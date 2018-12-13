package com.expedia.adaptivealerting.anomdetect.holtwinters;

import org.junit.Test;

import static org.junit.Assert.*;

public class HoltWintersParamsTest {
    private HoltWintersParams subject = new HoltWintersParams();

    @Test(expected = IllegalArgumentException.class)
    public void testDefaultsAreInvalid() {
        subject.validate();
    }

    @Test
    public void testDefaultsPlusPeriodAreValid() {
        subject.setPeriod(24);
        subject.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSeasonalityType() {
        subject.setSeasonalityType(null);
        subject.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPeriod() {
        subject.setPeriod(-1);
        subject.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAlphaLessThanZero() {
        subject.setAlpha(-0.1);
        subject.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAlphaGreaterThanOne() {
        subject.setAlpha(1.1);
        subject.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidBetaLessThanZero() {
        subject.setBeta(-0.1);
        subject.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidBetaGreaterThanOne() {
        subject.setBeta(1.1);
        subject.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidGammaLessThanZero() {
        subject.setGamma(-0.1);
        subject.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidGammaGreaterThanOne() {
        subject.setGamma(1.1);
        subject.validate();
    }

}