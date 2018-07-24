package com.spoloborota.qiwi.dice.iteration;

import java.util.ArrayList;
import java.util.List;
import com.spoloborota.qiwi.dice.Dice;
import com.spoloborota.qiwi.dice.Solver;

public class IterationSolver implements Solver{

    //Лист для хранения костяшек
    private List<Dice> dices;

    //Переменная для отладочного вывода на экран
    private int gg = 0;

    //Найти решение методом перебора
    public List<Dice> findSolution(List<Dice> dicesInput) {
        dices = dicesInput;

        //Запуск рекурсивного метода
        List<PlacedDice> solution = addDicesToSolution(new ArrayList<PlacedDice>(), dices);

        //Если результат пуст, возвращается null, наоборот - возвращается лист костяшек
        if (solution == null) {
            return null;
        } else {
            return asList(solution);
        }
    }

    public String getErrorString(){
        return "Последовательность из всех костяшек выстроить, увы, не получится.";
    }

    /**
     * Рекурсивно пытается добавить все переданные костяшки к решению
     * Если удалось - возвращает дополненное решение
     * Иначе возвращает null
     * solution - неполное решение (может быть и пустой список)
     * freeDices - костяшки, которые нужно обработать
     */
    private List<PlacedDice> addDicesToSolution(List<PlacedDice> solution, List<Dice> freeDices) {

        //Если список костяшек для обработки пустой, значит мы обработали все его элементы.
        //Начинается выход из рекурсии
        if (freeDices.isEmpty()) {
            return solution;
        }
                                        gg+=5;
        //Запускается обработка костяшек
        for(int i =0; i<freeDices.size(); i++) {
            Dice someDice = freeDices.get(i);
                                        {
                                            for(int ll = 0; ll < gg; ll++){
                                                System.out.print(" ");
                                            }
                                            System.out.println("Выберем очередную необработанную костяшку" + someDice);
                                        }
            //Перебираются оба положения костяшки
            for (Orientation ori : Orientation.values()) {
                if (couldAppend(solution, someDice, ori)) {
                                        {
                                            for(int ll = 0; ll < gg; ll++){
                                                System.out.print(" ");
                                            }
                                            System.out.println("Подходит!" + (new PlacedDice(someDice,ori).asDice()));
                                        }
                    //Создание для рекурсии отдельного списка предполагаемого решения на основе существующего
                    List<PlacedDice> possibleSolution = new ArrayList<PlacedDice>(solution);

                    //... и добавление в него новой подходящей костяшки
                    possibleSolution.add(new PlacedDice(someDice, ori));

                    //Получение оставшихся необработанных костяшек на основе имеющегося
                    List<Dice> remainingDices = new ArrayList<Dice>(freeDices);

                    //... и удаление обработанной костяшки из него
                    remainingDices.remove(someDice);
                                        {
                                            for(int ll = 0; ll < gg; ll++){
                                                System.out.print(" ");
                                            }
                                            System.out.print("Запускаем рекурсию! Возможно, результирующие: ");
                                            for(Dice d : asList(possibleSolution)){
                                                System.out.print(d);
                                            }
                                            System.out.print(" Оставшиеся: ");
                                            for(Dice d : remainingDices){
                                                System.out.print(d);
                                            }
                                            System.out.println("");
                                        }
                    List<PlacedDice> finalSolution = addDicesToSolution(possibleSolution, remainingDices);

                    // возвращаем выше и выше, пока не выйдем из рекурсии
                    if (finalSolution != null){
                                        {
                                            System.out.println("Выход из рекурсии: ");
                                            for(Dice d : asList(finalSolution)){
                                                System.out.print(d);
                                            }
                                            System.out.println("");
                                        }
                        return finalSolution;
                    }
                                        gg-=5;
                }
            }
                                        {
                                            for(int ll = 0; ll < gg; ll++){
                                                System.out.print(" ");
                                            }
                                            System.out.print("Костяшка " + someDice + " не подошла из набора: ");
                                            for(Dice d : freeDices){
                                                System.out.print(d);
                                            }
                                            System.out.println("");
                                        }
        }
        return null;
    }

    //Проверка на возможность соединения последней костяшки из обработанного списка
    //и следующей костяшки из необработанного списка
    private static boolean couldAppend(List<PlacedDice> solution, Dice dice, Orientation ori) {
        if (solution.isEmpty()) {
            return true;
        } else {
            PlacedDice last = solution.get(solution.size() - 1);
            return last.matches(dice, ori);
        }
    }

    //Преобразование списка PlacedDice в Dice
    private List<Dice> asList(List<PlacedDice> solution) {
        List<Dice> dices = new ArrayList<Dice>();
        for(int i = 0; i < solution.size(); i++){
            dices.add(new Dice(solution.get(i).getLeftNum(),solution.get(i).getRightNum()));
        }
        return dices;
    }
}