package ru.geekbrains.catch_the_drop;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        InvertAnArr();                  //Проверка задания 1
        WriteDataInLoop();              //Проверка задания 2
        RedoubleValueOfCriterion();     //Проверка задания 3
        WriteNumberOneOnDiagonals();    //Проверка задания 4
        FindExtremsOfArray();           //Проверка задания 5

        //Проверка задания 6
        System.out.println("");
        System.out.println("Задание 6");
        int [] symmetricArr = {1,1,1,2,1};                     //Инициализация исходного массива
        System.out.println(Arrays.toString(symmetricArr));     //Вывод исходного массива в консоль
        FindAxisOfSymmetry(symmetricArr);                      //Вызов метода для нахождения оси симметрии массива

        //Проверка задания 7
        System.out.println("");
        System.out.println("");
        System.out.println("Задание 7");
        int [] NotMovedArr = {1,13,25,12,6,20,-2,15,-34,-55};   //Инициализация входного массива
        int offset = 5;                                         //Инициализация числа-количества сдвигаемых позиций
        System.out.println(Arrays.toString(NotMovedArr));       //Вывод исходного массива в консоль
        MoveArr(NotMovedArr, offset);                           //Вызов метода для сдвижки позиций в массиве
    }

            /*
            Задание 1.
            Замена значений массива по условию
             */
    static void InvertAnArr() {
        System.out.println("Задание 1");
        int[] binaryArr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};       //Инициализация исходного массива
        System.out.println(Arrays.toString(binaryArr));         //Вывод в консоль исходного массива
        for (byte i = 0; i < binaryArr.length; i++) {           //Цикл для прохода по всем элементам массива
            if (binaryArr[i] == 1) {                            //Проверка равенства текущего элемента единице
                binaryArr[i] = 0;                               //Если равно - заменить нулем
            } else {
                binaryArr[i] = 1;                               //Если не равно - заменить единицей
            }
            ;
        }
        System.out.println(Arrays.toString(binaryArr));         //Проверка. Вывод в консоль измененного массива
    }

        /*
        Задание 2.
        Заполнение массива с помощью цикла
         */
    static void WriteDataInLoop() {
        System.out.println("");
        System.out.println("Задание 2");
        int[] fillLoop = new int[8];                        //Объявление исходного одномерного массива
        for (byte i = 0; i < fillLoop.length; i++) {                //Цикл для прохода по всем элементам массива
            fillLoop[i] = i * 3;                                 //Инициализация каждого элемента по условию задачи
        }
        System.out.println(Arrays.toString(fillLoop));       //Проверка. Вывод в консоль инициализированного массива
    }

        /*
        Задание 3.
        Умножение элементов массива меньше 6 на 2
         */
    static void RedoubleValueOfCriterion() {
        System.out.println("");
        System.out.println("Задание 3");
        int[] doublingArr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};   //Инициализация исходного массива
        System.out.println(Arrays.toString(doublingArr));            //Вывод в консоль исходного массива
        for (byte i = 0; i < doublingArr.length; i++) {              //Цикл для прохода по всем элементам массива
        if (doublingArr[i] < 6) {                                    //Сравнение элемента массива с шестеркой
            doublingArr[i] *= 2;                                     //Если равно 6 - удвоить значение
        }
    }
    System.out.println(Arrays.toString(doublingArr));              //Проверка. Вывод в консоль измененного массива
    }

        /*
        Задание 4.
        Квадратный массив с единицами по диагонали
         */
    static void WriteNumberOneOnDiagonals() {
        System.out.println("");
        System.out.println("Задание 4");
        int sqrEdge = 6;                                         //Переменная, обозначающая длину стороны квадрата
        int[][] sqrArr = new int[sqrEdge][sqrEdge];              //Объявление массива. Размерность в обе стороны равна стороне квадрата
        for (int i = 0; i < sqrArr.length; i++) {                //Цикл прохода по строкам массива
            for (int j = 0; j < sqrArr[i].length; j++) {         //Цикл прохода по столбцам массива
                /*
                Условие.
                Если номер столбца равен номеру строки то ставить единицу - заполняется диагональ слева направо
                Если номер столбца последний в первой строке, предпоследний во второй,
                а в каждой следующей строке смещается на один элемент влево - заполняется диагональ справа налево.
                Минус единица - для учета того что размерность строки отличается от порядкового номера
                последнего столбца на единицу т.к. нумерация элементов начинается с 0.
                */
                if (j == i || j == sqrArr[i].length - i - 1) {      //Проверка условия
                    sqrArr[i][j] = 1;                               //Если выполняется - ставить единицу
                }
            }
            System.out.println(Arrays.toString(sqrArr[i]));         //Проверка. Вывод всех элементов строки массива
        }
    }
        /*
        Задание 5.
        Максимальный и минимальный элементы массива
         */
    static void FindExtremsOfArray() {
        System.out.println("");
        System.out.println("Задание 5");
        int[] OriginArr = {12, 6, 8, -24, -12, 0, 42, 108, -112, 13, 55, 6};     //Инициализация исходного массива
        int OriginArMax = OriginArr[0];                                          //Инициализация переменной для хранения максимального значения
        int OriginArMin = OriginArr[0];                                          //Инициализация переменной для хранения минимального значения
        for (byte i = 0; i < OriginArr.length; i++) {                            //Цикл для прохода по значениям массива
            if (OriginArr[i] < OriginArMin) {                                    //Сравнение текущего элемента массива с минимальным
                OriginArMin = OriginArr[i];                                      //Если меньше - записать в переменную OriginArMin
            }
            if (OriginArr[i] > OriginArMax) {                                    //Сравнение текущего элемента с максимальным
                OriginArMax = OriginArr[i];                                      //Если больше - записать в переменную OriginArMax
            }
        }
        System.out.println(Arrays.toString(OriginArr));                          //Вывести исходный массив в консоль
        System.out.println("Максимальный элемент: " + OriginArMax);              //Вывести максимальный элемент в консоль
        System.out.println("Минимальный элемент: " + OriginArMin);               //Вывести минимальный элемент в консоль
    }

        /*
        Задание 6.
        Нахождение оси симметрии массива
        */
    public static void FindAxisOfSymmetry (int symmetricArr[]){                 //Объявление метода. Входные данные - массив symmetricArr
        /*
        Основной цикл для прохода между элементами массива.
        С его помощью назначается элемент слева от оси симметрии
        Минус один ставится для учета того факта что промежутков между
        элементами на 1 меньше чем элементов в массиве
         */
        for (int i=0; i<symmetricArr.length-1; i++) {
            int sum_left = 0;                                       //Здесь будем хранить сумму слева от оси симметрии
            int sum_right = 0;                                      //Здесь будем хранить сумму справа от оси симметрии
            for (int j = 0; j < symmetricArr.length; j++) {         //Вложенный цикл для прохода по значениям массива
                if (j <= i){                                        //Сравнение номера текущего элемента с элементом слева от оси в основном цикле
                    sum_left += symmetricArr[j];                    //Если номер во вложенном цикле меньше/равен в основном - плюсуем в сумму слева
                }
                if (j > i){                                         //Если номер во вложенном цикле больше чем в основном - плюсуем в сумму справа
                    sum_right += symmetricArr[j];
                }
            }
            if (sum_left == sum_right){                                     //Проверка равенства суммы слева и суммы справа от оси при текущем i основного цикла
                System.out.println("True");                                 //Если суммы равны, то пишем True и выполняем цикл показа положения оси симметрии
                for (int k = 0; k < symmetricArr.length; k++){              //Промежуточный вложенный цикл для показа оси симметрии
                    if (k == i+1){                                          //Проверка равенства номера текущего элемента номеру элемента справа от оси симметрии
                        System.out.print("|| " + symmetricArr[k] + " ");    //Если номер равен - перед ним рисуем значок оси симметрии
                    }else {
                        System.out.print(symmetricArr[k] + " ");            //Если не равен - выводим текущий элемент в цикле k
                    }
                }
            }
        }

    }

       /*
       Задание 7.
       Сдвижка позиций в массиве
       */
    public static void MoveArr(int NotMovedArr[], int offset){      //Объявление метода. Входные данные - массив NotMovedArr, число сдвигаемых позиций offset
            if (offset >=0){                                        //Определение знака числа - определение направления сдвижки (влево/вправо)
                for (int j = NotMovedArr.length-1; j >= 0; j--){    //Цикл для сдвижки массива вправо. Минус единица - для избежания выхода за границы сущ. массива при замене
                    if (j<offset){                                  //Проверка условия. Если номер текущего элемента меньше кол-ва сдвигаемых позиций
                        NotMovedArr[j] = 0;                         //То элемент равен нулю
                    }else{
                        NotMovedArr[j]=NotMovedArr[j-offset];       //Если больше, то текущий элемент равен элементу на offset позиций левее
                    }
                }
                System.out.println("Исходный массив сдвинут на " + offset + " элементов вправо");    //Вывод пояснительной надписи с направлением и размером сдвижки
            }else{
                for (int j = 0; j<NotMovedArr.length;j++){           //Цикл для сдвижки массива влево
                    if (j > NotMovedArr.length+offset-1){            //Проверка условия. Если номер текущего элемента выходит за границу массива после сдвижки
                        NotMovedArr[j] = 0;                          //То элемент равен нулю
                    }else{
                        NotMovedArr[j]=NotMovedArr[j-offset];        //Если больше, то текущий элемент равен элементу на offset позиций правее
                    }
                }
                System.out.println("Исходный массив сдвинут на " + Math.abs(offset) + " элементов влево");   //Вывод пояснительной надписи с направлением и размером сдвижки
            }
        System.out.println(Arrays.toString(NotMovedArr));             //Вывод сдвинутого массива
    }
}
