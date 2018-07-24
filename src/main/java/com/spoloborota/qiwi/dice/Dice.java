package com.spoloborota.qiwi.dice;

public class Dice {

    final int a;
    final int b;

    public Dice(int a,int b){
        validateNumber(a);
        validateNumber(b);
        this.a = a;
        this.b = b;
    }

    private void validateNumber(int number) {
        if (number < 0 || number > 6) {
            throw new IllegalArgumentException("Цифры на костяшках должны быть от \"0\" до \"6\", а не " + number);
        }
    }

    public int getA() {
        return a;
    }
    public int getB() {
        return b;
    }

    @Override
    public String toString() {
        return ("[" + a + " : " + b + "]");
    }
}
