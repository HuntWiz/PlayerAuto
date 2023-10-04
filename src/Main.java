import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static int[] states = {0, 1, 2, 3};
    public static String[] stringstates = {"гангает", "гангает", "мертв", "фармит"};

/*состояния:
0) гангает
1) фармит
2) мертв
3) закуп*/

    public static String[] outsignals = {"Возвращаюсь на линию", "иду помогать",
                                        "я в таверне","закупаюсь", "иду забирать обьект"};

/*выходные сигналы:
0) "возвращаюсь на линию"
1) "иду помогать"
2) "я в таверне"
3) "закупаюсь"
4) "иду забирать обьект"*/

    public static String[] actions = {"Пришла пачка", "Пачка зачищена", "Умирает", "Возрождение обьекта"};
    // входные сигналы


    //таблица изменения состояний
// [signal][to state]
    // строки - входной сигнал, столбцы - изначальное состояние, итог - смена состояния
    public static int[][] firstTable = {
            {states[1], states[1], states[2], states[1]},
            {states[0], states[0], states[2], states[0]},
            {states[2], states[2], -1, states[3]},
            {states[3], states[0], states[2], states[0]}
    };

    //таблица выходных сигналов
// [signal][to state]
    // если строки нет - значит состояние не поменялось или поменялось само в себя
    public static String[][] secondTable = {
            {outsignals[0], "", "", outsignals[0]},
            {"", outsignals[1], "", outsignals[1] },
            {outsignals[2], outsignals[2], "", outsignals[2] },
            {outsignals[3], outsignals[1], "", outsignals[1]}
    };

    public static int currState;
    public static boolean work;
    public static int trueaction;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Выберете изначальное состояние: \n" +
                "0) гангает\n" +
                "1) фармит\n" +
                "2) мертв\n" +
                "3) закуп\n");
        int stat = scan.nextInt();
        currState = states[stat];//изначальное состояние
        work = true;
        while (work == true) {

            System.out.println("Выберете входной сигнал:");
            for (int i = 0; i < actions.length; i++) {
                System.out.println(i + ") " + actions[i]);
            }
            System.out.println("-1) закончить работу");
            trueaction = scan.nextInt();
            if (trueaction == -1){
                work = false;
                System.out.println("Завершение работы...");
            } else {
                changeState(trueaction);
            }
        }

    }

    // сделать так, чтобы при вводе входного, менялся currState и выводило текущее, действие и новое состояние*
    public static void changeState(int signal){
        System.out.println("текущее состояние = " + currState);
        System.out.println("действие = " + actions[signal]);
        if (firstTable[signal][currState] == -1){
            System.out.println("новое состояние = " + currState + " (действие не влияет)");
        } else {
            System.out.println("новое состояние = " + stringstates[firstTable[signal][currState]]);
            //стринг значение нового состояния
        }
        System.out.println("сообщение: "+ secondTable[signal][currState]);
        currState = firstTable[signal][currState];
    }


}

