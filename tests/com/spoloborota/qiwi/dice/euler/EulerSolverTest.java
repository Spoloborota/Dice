package com.spoloborota.qiwi.dice.euler;

import java.util.ArrayList;
import java.util.List;
import com.spoloborota.qiwi.dice.Dice;
import org.junit.Assert;
import org.junit.Test;

public class EulerSolverTest {

    @Test
    public void testEmptyDices() {
        List<Dice> dicesInput = new ArrayList<Dice>();
        EulerSolver solver = new EulerSolver();
        solver.findSolution(dicesInput);
        String result = solver.getErrorString();

        boolean b = result.endsWith("Выберите хотя бы одну костяшку!");
        Assert.assertTrue("Обработка пустого Dices произведена некорректно!",b);
    }

    @Test
    public void testAllDices() {
        List<Dice> dicesInput = new ArrayList<Dice>();
        dicesInput.add(new Dice(0,0));
        dicesInput.add(new Dice(1,0));
        dicesInput.add(new Dice(1,1));
        dicesInput.add(new Dice(2,0));
        dicesInput.add(new Dice(2,1));
        dicesInput.add(new Dice(2,2));
        dicesInput.add(new Dice(3,0));
        dicesInput.add(new Dice(3,1));
        dicesInput.add(new Dice(3,2));
        dicesInput.add(new Dice(3,3));
        dicesInput.add(new Dice(4,0));
        dicesInput.add(new Dice(4,1));
        dicesInput.add(new Dice(4,2));
        dicesInput.add(new Dice(4,3));
        dicesInput.add(new Dice(4,4));
        dicesInput.add(new Dice(5,0));
        dicesInput.add(new Dice(5,1));
        dicesInput.add(new Dice(5,2));
        dicesInput.add(new Dice(5,3));
        dicesInput.add(new Dice(5,4));
        dicesInput.add(new Dice(5,5));
        dicesInput.add(new Dice(6,0));
        dicesInput.add(new Dice(6,1));
        dicesInput.add(new Dice(6,2));
        dicesInput.add(new Dice(6,3));
        dicesInput.add(new Dice(6,4));
        dicesInput.add(new Dice(6,5));
        dicesInput.add(new Dice(6,6));
        EulerSolver solver = new EulerSolver();
        solver.findSolution(dicesInput);
        String result = solver.getErrorString();

        boolean b = result.isEmpty();
        Assert.assertTrue("Обработка всех костяшек произведена некорректно! Ошибка: " + solver.getErrorString(),b);
    }

    @Test
    public void testOneDoubleDice() {
        List<Dice> dicesInput = new ArrayList<Dice>();
        dicesInput.add(new Dice(3,3));
        EulerSolver solver = new EulerSolver();
        String result = solver.getErrorString();

        boolean b = result.isEmpty();
        Assert.assertTrue("Обработка костяшки-дубля произведена неверно! Ошибка: " + solver.getErrorString(),b);
    }

    @Test
    public void testIsolatedVertex() {
        List<Dice> dicesInput = new ArrayList<Dice>();
        dicesInput.add(new Dice(3,3));
        dicesInput.add(new Dice(1,3));
        dicesInput.add(new Dice(1,2));
        dicesInput.add(new Dice(2,5));
        dicesInput.add(new Dice(6,6));
        EulerSolver solver = new EulerSolver();
        solver.findSolution(dicesInput);
        String result = solver.getErrorString();

        boolean b = result.endsWith(" этому помешает");
        Assert.assertTrue("Неверно просчитываются изолированные вершины!",b);
    }

    @Test
    public void testOddVertexes() {
        List<Dice> dicesInput = new ArrayList<Dice>();
        dicesInput.add(new Dice(3,3));
        dicesInput.add(new Dice(1,3));
        dicesInput.add(new Dice(1,2));
        dicesInput.add(new Dice(4,5));
        dicesInput.add(new Dice(4,6));
        EulerSolver solver = new EulerSolver();
        solver.findSolution(dicesInput);
        String result = solver.getErrorString();

        boolean b = result.endsWith(" увы, не удастся.");
        Assert.assertTrue("Неверно просчитывается количество нечетных вершин!",b);
    }

    @Test
    public void testCycleAndOdds() {
        List<Dice> dicesInput = new ArrayList<Dice>();
        dicesInput.add(new Dice(2,2));
        dicesInput.add(new Dice(2,3));
        dicesInput.add(new Dice(1,5));
        dicesInput.add(new Dice(6,2));
        dicesInput.add(new Dice(3,6));
        EulerSolver solver = new EulerSolver();
        solver.findSolution(dicesInput);
        String result = solver.getErrorString();

        boolean b = result.endsWith(" увы, не получится.");
        Assert.assertTrue("Неверно просчитывается случай с циклом и 2мя нечетными вершинами!",b);
    }

    @Test
    public void testEdgeBetweenOddsAndCycle1() {
        List<Dice> dicesInput = new ArrayList<Dice>();
        dicesInput.add(new Dice(1,0));
        dicesInput.add(new Dice(2,0));
        dicesInput.add(new Dice(1,2));
        dicesInput.add(new Dice(5,2));
        dicesInput.add(new Dice(3,5));
        EulerSolver solver = new EulerSolver();
        solver.findSolution(dicesInput);
        String result = solver.getErrorString();

        boolean b = result.endsWith("к сожалению, не удастся.");
        Assert.assertTrue("Неверно просчитывается случай 1 с ребром между циклом и изолированными вершинами",b);
    }

    @Test
    public void testEdgeBetweenOddsAndCycle2() {
        List<Dice> dicesInput = new ArrayList<Dice>();
        dicesInput.add(new Dice(1,2));
        dicesInput.add(new Dice(3,1));
        dicesInput.add(new Dice(3,2));
        dicesInput.add(new Dice(6,3));
        dicesInput.add(new Dice(4,6));
        EulerSolver solver = new EulerSolver();
        solver.findSolution(dicesInput);
        String result = solver.getErrorString();

        boolean b = result.endsWith("к сожалению, не удастся.");
        Assert.assertTrue("Неверно просчитывается случай 2 с ребром между циклом и изолированными вершинами",b);
    }
}
