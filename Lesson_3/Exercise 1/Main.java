package ru.geekbrains.catch_the_drop;

import java.util.Random;
import java.util.Scanner;

public class Main {

    //Метод для создания объекта класса Scanner
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int repeatGame;

        do{                                             //Начало игрового цикла
            int rndNumb = findRandomNumb();             //Инициализировать переменную с загаданным числом
            for (int i = 1; i <= 3; i++){               //Цикл отрабатывающий один игровой раунд из 3 попыток
                int userNumb = enterNumber();           //Запись в переменную введенного пользователем числа
                if (userNumb == rndNumb){               //Сравнение загаданного и введенного чисел
                    win(i, rndNumb);                    //Если числа равны, выполнить победный метод
                    break;                              //Выйти из цикла раунда
                }else {
                    wrong(i, rndNumb, userNumb);        //Если числа не равны, выполнить метод который будет ругать за ошибку
                    counterScore(i, rndNumb);           //И выполнить метод, считающий количество оставшихся попыток
                }
            }
            repeatGame = endOrContinue();               //После отработки цикла раунда выполнить метод, спрашивающий о желании продолжить
        }while (repeatGame == 1);                       //Конец игрового цикла. Условие выхода - пользователь нажал 2 (отказался продолжать) в предыдущем методе
    }

    //Метод для считывания введенного пользователем числа
    static int enterNumber(){
        System.out.println("Введите число от 0 до 9");
        return sc.nextInt();
    }

    //Метод для загадывания компьютером числа
    static int findRandomNumb (){
        Random rnd = new Random();
        return rnd.nextInt(9);
    }

    //Хвалебный метод. Выполняется в случае верного ответа пользователя
    static void win (int i, int rndNumb){
        System.out.println("Верно!");
        System.out.println("Было загадано число " + rndNumb);
        System.out.println("Вы угадали число c " + i +" попыток");
    }

    //Ругательный метод. Выполняется в случае неверного ответа пользователя
    static void wrong (int i, int rndNumb, int userNumb){
        System.out.println("Неверно!");
        if(userNumb < rndNumb){
            System.out.println("Загаданое число больше");
        }else {
            System.out.println("Загаданное число меньше");
        }
    }

    //Метод для подсчёта количества оставшихся попыток в раунде
    static void counterScore (int i, int rndNumb){
        if (3-i == 0) {
            System.out.println("Попыток не осталось. Вы проиграли!");
            System.out.println("Было загадано число " + rndNumb);
        }else {
            System.out.println("Осталось попыток - " + (3-i));
            System.out.println();
        }
    }

    //Метод для выяснения желания пользователя сыграть еще раз
    static int endOrContinue (){
        System.out.println("*****");
        System.out.println("Игра окончена");
        System.out.println("Желаете повторить?");
        System.out.println("1 - да / 2 - нет");
        return sc.nextInt();
    }
}
