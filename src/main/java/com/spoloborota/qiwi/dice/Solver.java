package com.spoloborota.qiwi.dice;

import java.util.List;

public interface Solver {
    List<Dice> findSolution(List<Dice> dices);
    String getErrorString();
}
