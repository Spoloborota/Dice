package com.spoloborota.qiwi.dice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import com.spoloborota.qiwi.dice.euler.EulerSolver;
import com.spoloborota.qiwi.dice.iteration.IterationSolver;

public class GUI extends JFrame {

    //Лист для ввода костяшек
    private List<Dice> dicesInput = new ArrayList<Dice>();

    //Лист для вывода костяшек
    private List<Dice> dicesOutput = null;

    //Радиокнопки и группа для них
    private JRadioButton radioEuler = new JRadioButton("Эйлеров путь");
    private JRadioButton radioIterate = new JRadioButton("Перебор");
    private ButtonGroup rgroup = new ButtonGroup();

    private JTextArea text = new JTextArea("Выберите тип решения задачи:");

    //Чек-боксы костяшек
    private JCheckBox check00 = new JCheckBox("0,0", false);
    private JCheckBox check10 = new JCheckBox("1,0", false);
    private JCheckBox check11 = new JCheckBox("1,1", false);
    private JCheckBox check20 = new JCheckBox("2,0", false);
    private JCheckBox check21 = new JCheckBox("2,1", false);
    private JCheckBox check22 = new JCheckBox("2,2", false);
    private JCheckBox check30 = new JCheckBox("3,0", false);
    private JCheckBox check31 = new JCheckBox("3,1", false);
    private JCheckBox check32 = new JCheckBox("3,2", false);
    private JCheckBox check33 = new JCheckBox("3,3", false);
    private JCheckBox check40 = new JCheckBox("4,0", false);
    private JCheckBox check41 = new JCheckBox("4,1", false);
    private JCheckBox check42 = new JCheckBox("4,2", false);
    private JCheckBox check43 = new JCheckBox("4,3", false);
    private JCheckBox check44 = new JCheckBox("4,4", false);
    private JCheckBox check50 = new JCheckBox("5,0", false);
    private JCheckBox check51 = new JCheckBox("5,1", false);
    private JCheckBox check52 = new JCheckBox("5,2", false);
    private JCheckBox check53 = new JCheckBox("5,3", false);
    private JCheckBox check54 = new JCheckBox("5,4", false);
    private JCheckBox check55 = new JCheckBox("5,5", false);
    private JCheckBox check60 = new JCheckBox("6,0", false);
    private JCheckBox check61 = new JCheckBox("6,1", false);
    private JCheckBox check62 = new JCheckBox("6,2", false);
    private JCheckBox check63 = new JCheckBox("6,3", false);
    private JCheckBox check64 = new JCheckBox("6,4", false);
    private JCheckBox check65 = new JCheckBox("6,5", false);
    private JCheckBox check66 = new JCheckBox("6,6", false);

    //Кнопка "Сформировать очередь!"
    private JButton button = new JButton("Сформировать очередь!");

    public GUI() {
        super("Определение последовательности из костяшек домино");
        this.setBounds(250,250,500,190);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());
        rgroup.add(radioEuler);
        rgroup.add(radioIterate);
        radioEuler.setSelected(true);
        container.add(check00);
        container.add(check10);
        container.add(check11);
        container.add(check20);
        container.add(check21);
        container.add(check22);
        container.add(check30);
        container.add(check31);
        container.add(check32);
        container.add(check33);
        container.add(check40);
        container.add(check41);
        container.add(check42);
        container.add(check43);
        container.add(check44);
        container.add(check50);
        container.add(check51);
        container.add(check52);
        container.add(check53);
        container.add(check54);
        container.add(check55);
        container.add(check60);
        container.add(check61);
        container.add(check62);
        container.add(check63);
        container.add(check64);
        container.add(check65);
        container.add(check66);
        container.add(text);
        container.add(radioIterate);
        container.add(radioEuler);
        button.addActionListener(new ButtonEventListener());
        container.add(button);
    }

    //Ввод костяшек через исходный код
    private void readDice(){
        dicesInput.add(new Dice(3, 6));
        dicesInput.add(new Dice(0, 0));
        dicesInput.add(new Dice(4, 6));
        dicesInput.add(new Dice(0, 4));
        dicesInput.add(new Dice(3, 2));
        dicesInput.add(new Dice(3, 1));
        dicesInput.add(new Dice(1, 0));

/*        dicesInput.add(new Dice(1,3));
        dicesInput.add(new Dice(4,1));
        dicesInput.add(new Dice(3,6));
        dicesInput.add(new Dice(3,4));
*/
/*        dicesInput.add(new Dice(1,5));
        dicesInput.add(new Dice(5,4));
        dicesInput.add(new Dice(4,2));
        dicesInput.add(new Dice(2,3));
        dicesInput.add(new Dice(3,1));
        */
    }

    //Ввод костяшек через GUI
    private void readDiceFromCheckBox(){
        if(check00.isSelected()) dicesInput.add(new Dice(0,0));
        if(check10.isSelected()) dicesInput.add(new Dice(1,0));
        if(check11.isSelected()) dicesInput.add(new Dice(1,1));
        if(check20.isSelected()) dicesInput.add(new Dice(2,0));
        if(check21.isSelected()) dicesInput.add(new Dice(2,1));
        if(check22.isSelected()) dicesInput.add(new Dice(2,2));
        if(check30.isSelected()) dicesInput.add(new Dice(3,0));
        if(check31.isSelected()) dicesInput.add(new Dice(3,1));
        if(check32.isSelected()) dicesInput.add(new Dice(3,2));
        if(check33.isSelected()) dicesInput.add(new Dice(3,3));
        if(check40.isSelected()) dicesInput.add(new Dice(4,0));
        if(check41.isSelected()) dicesInput.add(new Dice(4,1));
        if(check42.isSelected()) dicesInput.add(new Dice(4,2));
        if(check43.isSelected()) dicesInput.add(new Dice(4,3));
        if(check44.isSelected()) dicesInput.add(new Dice(4,4));
        if(check50.isSelected()) dicesInput.add(new Dice(5,0));
        if(check51.isSelected()) dicesInput.add(new Dice(5,1));
        if(check52.isSelected()) dicesInput.add(new Dice(5,2));
        if(check53.isSelected()) dicesInput.add(new Dice(5,3));
        if(check54.isSelected()) dicesInput.add(new Dice(5,4));
        if(check55.isSelected()) dicesInput.add(new Dice(5,5));
        if(check60.isSelected()) dicesInput.add(new Dice(6,0));
        if(check61.isSelected()) dicesInput.add(new Dice(6,1));
        if(check62.isSelected()) dicesInput.add(new Dice(6,2));
        if(check63.isSelected()) dicesInput.add(new Dice(6,3));
        if(check64.isSelected()) dicesInput.add(new Dice(6,4));
        if(check65.isSelected()) dicesInput.add(new Dice(6,5));
        if(check66.isSelected()) dicesInput.add(new Dice(6,6));
    }

    //Обработка нажатия на кнопку "Сформировать очередь!"
    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Solver solver;
            StringBuilder s = new StringBuilder("");

            //Выбираем костяшки ОДНИМ из 2х способов ниже
//            readDice();                 //вручную через код метода readDice(),
            readDiceFromCheckBox();     //либо считываем чекбоксы

            //Количество выбранных костяшек должно быть больше "0"
            if (dicesInput.size() < 1){
                s.append("Выберите хотя бы одну костяшку!");
            } else {

                //Выбираем вид решения
                if (radioEuler.isSelected()) {
                    solver = new EulerSolver();
                } else {
                    solver = new IterationSolver();
                }

                //Вычисляем... Ох уж этот полиморфизм! Сколько крови и пота пролилось, пока я тебя изучал! :)
                dicesOutput = solver.findSolution(dicesInput);

                //Проверяем, удалось ли вычислить путь
                if(dicesOutput != null){

                    //Преобразуем результирующий список костяшек в строку вывода
                    for(Dice d : dicesOutput){
                        s.append(d);
                    }
                } else {

                    //Сохраняем текст ошибки в строку вывода
                    s.append(solver.getErrorString());
                }
            }
            //Выводим результат в консоли и всплывающем окне
            System.out.println("\nИтоговый результат: " + s);
            JOptionPane.showMessageDialog(null,s,"Результат",JOptionPane.PLAIN_MESSAGE);

            //Очистка листов костяшек
            dicesInput.clear();

        }
    }
}
