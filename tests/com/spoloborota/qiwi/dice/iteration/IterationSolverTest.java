package com.spoloborota.qiwi.dice.iteration;

import com.spoloborota.qiwi.dice.Dice;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IterationSolverTest {

    @Test
    public void testBadSequence() {
        List<Dice> dicesInput = new ArrayList<Dice>();
        dicesInput.add(new Dice(4,2));
        dicesInput.add(new Dice(5,1));
        dicesInput.add(new Dice(4,5));
        dicesInput.add(new Dice(0,1));
        dicesInput.add(new Dice(3,3));
        IterationSolver solver = new IterationSolver();
        Object o = solver.findSolution(dicesInput);
        Assert.assertNull("Обработка последовательности выполнена неверно!",o);
    }

    @Test
    public void testGoodSequence() {
        List<Dice> dicesInput = new ArrayList<Dice>();
        dicesInput.add(new Dice(4,2));
        dicesInput.add(new Dice(5,1));
        dicesInput.add(new Dice(4,5));
        dicesInput.add(new Dice(0,1));
        dicesInput.add(new Dice(1,1));
        IterationSolver solver = new IterationSolver();
        Object o = solver.findSolution(dicesInput);
        Assert.assertNotNull("Обработка последовательности выполнена неверно!",o);
    }

    @Test
    public void testCheckResultSequence() {
        List<Dice> dicesInput = new ArrayList<Dice>();
        List<Dice> dicesOutput;
        boolean b = true;
        dicesInput.add(new Dice(4,2));
        dicesInput.add(new Dice(5,1));
        dicesInput.add(new Dice(4,5));
        dicesInput.add(new Dice(0,1));
        dicesInput.add(new Dice(1,1));
        IterationSolver solver = new IterationSolver();
        dicesOutput = solver.findSolution(dicesInput);
        for(int i = 1; i < dicesOutput.size(); i++){
            if(dicesOutput.get(i-1).getB() != dicesOutput.get(i).getA()){
                b = false;
                break;
            }
        }
        Assert.assertTrue("Обработка последовательности выполнена неверно!",b);
    }
}
