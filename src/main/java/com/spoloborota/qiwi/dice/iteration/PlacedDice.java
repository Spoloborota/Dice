package com.spoloborota.qiwi.dice.iteration;

import com.spoloborota.qiwi.dice.Dice;

public class PlacedDice {

    private Dice dice;

    //ориентация костяшки
    private Orientation ori;

    public PlacedDice(Dice dice, Orientation ori) {
        this.dice = dice;
        this.ori = ori;
    }

    //Представление PlacedDice в виде Dice
    public Dice asDice(){
        return new Dice(getLeftNum(),getRightNum());
    }

    public int getLeftNum() {
        return ori == Orientation.STRICT ? dice.getA() : dice.getB();
    }

    public int getRightNum() {
        return ori == Orientation.STRICT ? dice.getB() : dice.getA();
    }

    //Проверка совпадения сторон костяшек
    public boolean matches(Dice otherDice, Orientation otherOri) {
        int rightNum = (ori == Orientation.STRICT ? dice.getB() : dice.getA());
        int otherLeftNum = (otherOri == Orientation.STRICT ? otherDice.getA() : otherDice.getB());
        return rightNum == otherLeftNum;
    }
}
