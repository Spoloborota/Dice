package com.spoloborota.qiwi.dice.euler;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import com.spoloborota.qiwi.dice.Dice;
import com.spoloborota.qiwi.dice.Solver;


/**
 * Решение задачи о нахождении последовательности костяшек
 * выполняется по алгоритму нахождения Эйлерова цикла:
 *
 * stack St;
 * в St кладём любую вершину (стартовая вершина);
 * пока St не пустой
 *    пусть V - значение на вершине St;
 *    если степень(V) = 0, то
 *       добавляем V к ответу;
 *       снимаем V с вершины St;
 *    иначе
 *       находим любое ребро, выходящее из V;
 *       удаляем его из графа;
 *       второй конец этого ребра кладём в St;
 */

public class EulerSolver implements Solver{

    //Лист для хранения костяшек
    private List<Dice> dices;

    //Карта для костяшек-дублей
    private Map<Integer,Boolean> doubles = new HashMap<Integer, Boolean>(7);

    //Карта для хранения вершин и их степеней
    private ConcurrentHashMap<Integer,AtomicInteger> vertex = new ConcurrentHashMap<Integer, AtomicInteger>();

    //Вершины с нечетными степенями
    private int v1 = -1, v2 = -1;
    private int stFirst=1; //Начальная вершина

    //Лист для результирующего списка
    private ArrayList<Integer> result = new ArrayList<Integer>();

    //Строка для вывода
    private StringBuilder errorString = new StringBuilder();

    //Проверка на возможность формирования очереди
    private boolean resume = true;

    //Найти решение по алгоритму вычисления Эйлерова пути
    public List<Dice> findSolution(List<Dice> dicesInput){
        dices = dicesInput;
        if(dices.size() > 0){
            removeAndTestDoubles();
            if(resume){
                vertexDefine();
                vertexCheck();
                if(resume){
                    int[][] matrix = createMatrix();
                    findPath(matrix);
                    if(resume){
                        return createResultQueue();
                    }
                }
            }else{
                return dices;
            }
        }else{
            errorString.append("Выберите хотя бы одну костяшку!");
        }
        return null;
    }

    public String getErrorString() {
        return errorString.toString();
    }

    //Удаление дублей из набора костяшек. Проверка, являются ли дубли изолированными вершинами.
    private void removeAndTestDoubles(){
        System.out.println("");
        doubles.put(0,false);
        doubles.put(1,false);
        doubles.put(2,false);
        doubles.put(3, false);
        doubles.put(4,false);
        doubles.put(5,false);
        doubles.put(6,false);

        for(int i = 0; i<dices.size(); i++){
            if(dices.get(i).getA() == dices.get(i).getB()){
                int z = dices.get(i).getB();        //среди костяшек найден дубль цифры 'z'
                doubles.put(z,true);                //запоминаем дубль какой цифры есть
                dices.remove(i);                    //удаляем костяшку-дубль
                i--;

                //проверяем, есть ли костяшки, с которыми можно соединить костяшку-дубль числа 'z'
                boolean b = false;
                for(int k = 0; k < dices.size(); k++){
                    if(dices.get(k).getB() == z || dices.get(k).getA() == z){
                        b = true;
                        break;
                    }
                }
                if(!b){
                    if(dices.size() == 0){
                        //если в наборе была только одна костяшка
                        resume = false;
                        dices.clear();
                        dices.add(new Dice(z,z));
                        break;
                    }else{
                        //если такая не нашлась, то домино не выстроится из всех костяшек,
                        errorString.append("Последовательность из всех костяшек не выстроится! По крайней мере, костяшка ");
                        errorString.append(new Dice(z,z));
                        errorString.append(" этому помешает");
                        resume = false;
                        dices = null;
                        break;
                    }
                }
            }
        }

    }

    //Определение степеней вершин графа
    private void vertexDefine(){
        for(int i = 0; i < dices.size(); i++){
            vertex.putIfAbsent(dices.get(i).getA(),new AtomicInteger(0));
            vertex.get(dices.get(i).getA()).incrementAndGet();
            vertex.putIfAbsent(dices.get(i).getB(),new AtomicInteger(0));
            vertex.get(dices.get(i).getB()).incrementAndGet();
        }
        System.out.println("Вершины графа и их степени " + vertex);
    }

    //Проверка количества вершин с нечетными степенями
    private void vertexCheck(){
        for (ConcurrentHashMap.Entry<Integer,AtomicInteger> entry: vertex.entrySet()){
            if((entry.getValue().intValue() % 2) == 1){
                if(v1 == -1){
                    v1 = entry.getKey();
                } else {
                    if(v2 == -1){
                        v2 = entry.getKey();
                    }else{
                        errorString.append("Найдено более 2х вершин с нечетными степенями!");
                        errorString.append("\nПоследовательность из всех костяшек выстроить, увы, не удастся.");
                        resume = false;
                        break;
                    }
                }
            }
        }
    }

    //Составление матрицы смежности
    private int[][] createMatrix(){
        for (Dice dice : dices) {
            System.out.println("Костяшка " + dice);
        }
        System.out.println("Нечетные вершины: v1 = " + v1 + ", v2 = " + v2);

        ArrayList<Integer> vArray = new ArrayList<Integer>();

        //Копирование элементов вершин из карты в лист
        for (ConcurrentHashMap.Entry<Integer,AtomicInteger> entry: vertex.entrySet()){
            vArray.add(entry.getKey());
        }
        System.out.println("Обрабатываемые вершины графа " + vArray);

        //Заполнение нулевых столбца и строки значениями вершин
        int matrix[][] = new int[vertex.size()+1][vertex.size()+1];
        for(int i = 1; i < vertex.size()+1; i++){
            matrix[i][0] = vArray.get(i-1);
            matrix[0][i] = vArray.get(i-1);
        }

        //Заполнение матрицы смежности ребрами
        for(int i = 0; i < dices.size(); i++){
            for(int j = 1; j < vertex.size()+1; j++){
                if(dices.get(i).getA() == matrix[j][0]){
                    for(int k = 1; k < vertex.size()+1; k++){
                        if(dices.get(i).getB() == matrix[0][k]){
                            matrix[j][k] = matrix[k][j] = 1;
                        }
                    }
                }
            }
        }

        //Добавление ребра между нечетными вершинами
        if(v1 != -1){
            for(int i = 1; i < vertex.size()+1; i++){
                if(v1 == matrix[i][0]){
                    for(int j = 1; j < vertex.size()+1; j++){
                        if(v2 == matrix[0][j]){
                            matrix[i][j]++;
                            matrix[j][i]++;
                            stFirst = i;
                        }
                    }
                }
            }
        }
        return matrix;
    }

    //Нахождение Эйлерова пути
    private void findPath(int[][] matrix){

        System.out.println("Обрабатываемая матрица:");
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(stFirst);
        while(!stack.isEmpty()){
            int v = stack.getFirst();
            int i;
            for(i = 1; i < vertex.size()+1; i++){
                if(matrix[v][i] != 0)
                    break;
            }
            if(i == vertex.size()+1){
                result.add(matrix[v][0]);
                stack.pop();
                System.out.println("Результирующие вершины: " + result);
            }
            else{
                matrix[v][i]--;
                matrix[i][v]--;
                stack.push(i);
            }
        }
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        metka2:
        for(int i = 1; i < matrix.length; i++){
            for(int j = 1; j < matrix.length; j++){
                if(matrix[i][j] == 1){
                    errorString.append("Обнаружен несвязный граф!");
                    errorString.append("\nПоследовательность из всех костяшек выстроить, увы, не получится.");
                    resume = false;
                    break metka2;
                }
            }
        }
    }

    //Формирование листа с результирующей последовательностью
    private List<Dice> createResultQueue(){

        //Учет добавленного ребра
        if (v1 != -1) {
            for(int i = 0; i < result.size()-1; i++){
                if((v1 == result.get(i)) && (v2 == result.get(i+1)) || (v1 == result.get(i+1)) && (v2 == result.get(i))){
                    if(i == 0){
                        result.remove(0);
                        break;
                    } else if (i == (result.size()-2)) {
                        result.remove(result.size()-1);
                        break;
                    } else {
                        errorString.append("Обнаружен несвязный граф!");
                        errorString.append("\nПоследовательность из всех костяшек выстроить, к сожалению, не удастся.");
                        return null;
                    }
                }
            }
        }

        //Вставка костяшек-дублей
        for (Map.Entry<Integer, Boolean> entry : doubles.entrySet()){
            if(entry.getValue()){
                int z = entry.getKey();
                for(int i = 0; i< result.size(); i++){
                    if(z == result.get(i)){
                        result.add(i,z);
                        break;
                    }
                }
            }
        }

        //Формирование итога
        dices.clear();
        for(int i = 1; i < result.size(); i++){
            dices.add(new Dice(result.get(i - 1),result.get(i)));
        }
        return dices;
    }
}




































