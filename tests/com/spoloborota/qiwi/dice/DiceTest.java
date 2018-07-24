package com.spoloborota.qiwi.dice;

import org.junit.Test;

public class DiceTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeFirstNumber() {
        new Dice(-1, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeSecondNumber() {
        new Dice(3, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalFirstNumber() {
        new Dice(300, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalSecondNumber() {
        new Dice(1, 300);
    }

    @Test
    public void testValidDices() {
        new Dice(1, 1);
        new Dice(0, 0);
        new Dice(1, 6);
        new Dice(5, 3);
    }
}
