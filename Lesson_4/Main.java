package ru.geekbrains.catch_the_drop;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TicTacToy();                        //Запуск игры;
    }

    //Игра крестики-нолики
    static void TicTacToy(){                        //Метод запускающий игру
        int mapSize = 5;                            //Переменная с размером игрового поля
        int signsToWin = 4;                         //Переменная с победным кол-вом фишек в ряд
        char[][] map = new char[mapSize][mapSize];  //Объявление массива с игровым полем
        char emptySign = '-';                       //Символ пустой клетки поля
        char userSign = 'X';                        //Символ пользователя
        char[] aiSigns = {'O', 'V', '|'};           //Инициализация массива с символами игроков ИИ
        char currentSign;                           //Объявление переменной для хранения символа ходящего игрока

        do {                                                                            //Глобальный игровой цикл. Отвечает за перезапуск игры
            initMap(map, emptySign);                                                    //Вызов метода инициализирующего игровое поле
            currentSign  = userSign;                                                    //Первый ход в раунде отдается человеку
            do {                                                                        //Внутренний цикл. Отвечает за выполнение одного хода
                if (currentSign == userSign) printMap(map);                             //Если ход человека - то выполни метод для печати поля
                newTurn(map, userSign, emptySign, currentSign, aiSigns, signsToWin);    //Выполни метод нового хода
                if (checkWin(map, currentSign, mapSize, signsToWin)) {                  //Просканируй поле на предмет выигрышной комбинации
                    printMap(map);                                                      //Если выигрыш есть - напечатай поле
                    identificationWinner(currentSign, userSign, aiSigns);               //Выполни метод по идентификации победителя
                    break;                                                              //И выйди из внутреннего цикла во внешний. Раунд закончен
                }
                currentSign = rotateSign(currentSign, userSign, aiSigns);               //Если победы нет - передай ход другому игроку
            } while (!checkFullMap(map, emptySign));                                    //Внутренний цикл выполняется пока на поле есть свободные ячейки
        } while (restart());                                                            //Внешний цикл выполняется пока пользователь согласен сыграть еще
    }

    //Метод для инициализации чистого поля
    static void initMap(char[][] map, char emptySign) {
        for (int i = 0; i < map.length; i++) {              //Цикл прохода по строкам поля
            for (int j = 0; j < map[i].length; j++) {       //Цикл прохода по столбцам поля
                map[i][j] = emptySign;                      //Заполни массив символами пустой клетки
            }
        }
    }

    //Метод для отрисовки поля
    static void printMap(char[][] map) {
        System.out.println("Загрузка...");
        System.out.print(" ");
        for (int i = 1; i <= map.length; i++) {         //Цикл для вывода шапки игрового поля (Номера столбцов)
            System.out.printf("%2d", i);
        }
        System.out.println();
        for (int i = 0; i < map.length; i++) {          //Цикл для прохода по строкам поля
            System.out.print(i + 1);                    //Вывод номера строки поля
            for (int j = 0; j < map[i].length; j++) {   //Цикл прохода по столбцам поля
                System.out.printf("%2s", map[i][j]);    //Вывод содержимого ячейки поля
            }
            System.out.println();
        }
    }

    //Метод совершения хода
    static void newTurn(char[][] map, char userSign, char emptySign, char currentSign, char[] aiSigns, int signsToWin) {
        if (currentSign == userSign) {                                                                                  //Если текущий ход делает человек
            userTurn(map, userSign, emptySign, currentSign);                                                            //То выполни метод "ход Юзера"
        } else {                                                                                                        //Если нет
            compTurn(currentSign, map, map.length, emptySign, userSign, aiSigns, signsToWin);                           //То выполни метод "ход компьютера"
        }
    }

    //Метод для передачи хода другому игроку
    static char rotateSign(char currentSign, char userSign, char[] aiSigns) {
        int currentAi = 0;                                      //Переменная для хранения номера ИИ, сделавшего ход
        for (int i = 0; i < aiSigns.length; i++) {              //Цикл для определения порядкового номера ИИ, сделавшего ход
            if (currentSign == aiSigns[i]) currentAi = i + 1;
        }
        if (currentAi == aiSigns.length) {                      //Если номер ходившегося ИИ последний в массиве, передай ход человеку
            return userSign;
        } else {                                                //Иначе передай ход следующему ИИ
            return aiSigns[currentAi];                          //Индекс следующего ИИ в массиве соответствует порядковому номеру предыдущего ходившего
        }
    }

    //Метод для вывода надписи об исходе раунда
    static void identificationWinner(char currentSign, char userSign, char[] aiSigns) {
        if (currentSign == userSign) {                          //Если ходивший - человек - хвалим, поздравляем
            System.out.println("Поздравляю! Ты победил!");
        } else {                                                //Если ходивший - ИИ
            for (int i = 0; i < aiSigns.length; i++) {          //Циклом вычисляем его номер
                if (aiSigns[i] == currentSign) {                //Выводим ругательную надпись
                    System.out.println(String.format("Ты проиграл! Победил ИИ-%s [%s]", i + 1, currentSign));
                }
            }
        }
    }

    //Метод для проверки заполнения поля. Если выполнился - наступает ничья
    static boolean checkFullMap(char[][] map, char emptySign) {
        for (int i = 0; i < map.length; i++) {                  //Проходим циклами все ячейки массива
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == emptySign) return false;       //Как только встречается пустая - цикл не выполняется. Можно играть дальше
            }
        }
        System.out.println("Поле заполнено! Ничья");
        return true;
    }

    //Метод проверки победы на поле
    static boolean checkWin(char[][] map, char currentSign, int size, int signsToWin) {
        for (int i = 0; i < size; i++) {                    //Циклы прохода по ячейкам
            for (int j = 0; j < size; j++) {
                int line = 0;                               //Переменная для хранения длины комбинации символов в строке
                int column = 0;                             //--//--//--//-- в столбце
                int diag1 = 0;                              //--//--//--//-- по диагонали слева направо
                int diag2 = 0;                              //--//--//--//-- по диагонали справа налево
                if (map[i][j] == currentSign) {             //Если символ в массиве равен символу игрока, чья победа проверяется
                    for (int k = 1; k < signsToWin; k++) {          //Запускаем цикл, считающий количество одинаковых знаков на интервале, равном победному
//                        Проверка следующего символа по горизонтали
                        if ((j + k) < size) {                       //Проверка, попадает ли следующий в интервале элемент в границы массива
                            if (map[i][j] == map[i][j + k]) line++; //Сравниваем следующий элемент с символом игрока. Если равно - увеличиваем счётчик
                        }
//                        Проверка следующего символа по вертикали
                        if ((i + k) < size) {
                            if (map[i][j] == map[i + k][j]) column++;
                        }
//                        Проверка следующего символа по диагонали слева направо
                        if ((i + k) < size && (j + k) < size) {
                            if (map[i][j] == map[i + k][j + k]) diag1++;
                        }
//                        Проверка следующего символа по диагонали справа налево
                        if ((i - k) >= 0 && (j + k) < size) {
                            if (map[i][j] == map[i - k][j + k]) diag2++;
                        }
                    }
                }
//                Проверка счётчиков. Если хотя бы один счётчик равен победной комбинации минус один (исходный элемент с которым шло сравнение) то это победа
                if (line == signsToWin - 1 || column == signsToWin - 1 || diag1 == signsToWin - 1 || diag2 == signsToWin - 1)
                    return true;
            }
        }
        return false;
    }

    //Метод для выяснения желания сыграть еще раз
    static boolean restart() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Повторить игру? 1 - да / 0 - нет");
        return sc.nextInt() == 1;
    }

    //Метод хода игрока
    static void userTurn(char[][] map, char userSign, char emptySign, char currentSign) {
        int x;
        int y;
        do {
            y = inputCoordinate('x', map.length) - 1;
            x = inputCoordinate('y', map.length) - 1;
        } while (!occupateCoordinate(map, x, y, emptySign, userSign, currentSign));
        map[x][y] = userSign;
        System.out.println(String.format("Ты поставил фишку в [%d|%d]", y + 1, x + 1));
    }

    //Метод ввода координат игроком через консоль
    static int inputCoordinate(char coordName, int size) {
        Scanner sc = new Scanner(System.in);
        System.out.println(String.format("Введите координату %s от 1 до %s", coordName, size));
        int coord = sc.nextInt();
        while (!correctInput(coord, size)) {
            System.out.println(String.format("Координата %s  введена не корректно. Введите %s от 1 до %s", coordName, coordName, size));
            coord = sc.nextInt();
        }
        return coord;
    }

    //Проверка попадания введенных координат в игровое поле
    static boolean correctInput(int coord, int size) {
        return coord <= size && coord >= 0;
    }

    //Метод для проверки свободности ячейки, в которую игрок намеревается поставить свою фишку
    static boolean occupateCoordinate(char[][] map, int x, int y, char emptySign, char userSign, char currentSign) {
        if (map[x][y] == emptySign) {
            return true;
        } else {
            if (currentSign == userSign) {
                System.out.println(String.format("Координата [%d|%d] занята. Выбери другую ячейку", y + 1, x + 1));     //Вывод пояснительной строки только для человека
            }
            return false;
        }
    }

    //Метод хода ИИ
    static void compTurn(char currentSign, char[][] map, int size, char emptySign, char userSign, char[] aiSigns, int signsToWin) {
        Random rnd = new Random();
        int x;
        int y;
        do {
            x = rnd.nextInt(size);
            y = rnd.nextInt(size);
        } while (!occupateCoordinate(map, x, y, emptySign, userSign, currentSign));             //Цикл выполняется пока не найдется свободная ячейка
        if (!blockTurnEnemy(map, aiSigns, userSign, signsToWin, currentSign, emptySign)) {      //Если метод блокировки ходов ничего не нашел
            writeAiCoordinates(map, x, y, currentSign);                                         //Заполни случайную ячейку, найденную выше
        }
    }

    //Метод для заполения ячейки координатамии ИИ
    static void writeAiCoordinates(char [][] map, int x, int y, char currentSign){
        map[x][y] = currentSign;
        System.out.println(String.format("Игрок [%s] поставил фишку в [%d|%d]", currentSign, y + 1, x + 1));
    }

    /*Алгоритм работы ИИ
     *Алгоритм работы:
     * На 1 этапе проверяются и блокируются все комбинации, выстроенные на поле игроком, в которых до победы осталось поставить одну фишку
     * На 2 этапе проверяются и блокируются все предпобедные комбинации других ИИ
     * На 3 этапе циклически проверяются все комбинации игрока, содержащие не менее 2 символов в комбинации. Данная проверка выполняется с вероятностью 50%
     * На 4 этапе циклически проверяются все комбинации всех ИИ, содержащие не менее 2 символов в комбинации. Данная проверка выполняется с вероятностью 50%
     * Если не один из этапов не выполнен, считается что блокировать нечего и фишка ставится случайным образом
     * */

    static boolean blockTurnEnemy(char[][] map, char[] aiSigns, char userSign, int signsToWin, char currentSign, char emptySign) {

//1 этап

        if (findBlockTurn(map, userSign, signsToWin, emptySign, currentSign, userSign)) return true;

//2 этап

        for (int i = 0; i < aiSigns.length; i++) {
            if (currentSign != aiSigns[i] && (findBlockTurn(map, aiSigns[i], signsToWin, emptySign, currentSign, userSign)))
                return true;
        }
//3 этап

        Random rnd = new Random();
        for (int i = signsToWin - 1; i >= 2; i--) {
            if (rnd.nextBoolean() && findBlockTurn(map, userSign, i, emptySign, currentSign, userSign)) return true;
        }

//4 этап

        for (int i = 0; i < aiSigns.length; i++) {
            for (int j = signsToWin - 1; j >= 2; j--) {
                if (rnd.nextBoolean() && currentSign != aiSigns[i] && (findBlockTurn(map, aiSigns[i], j, emptySign, currentSign, userSign)))
                    return true;
            }
        }
        return false;
    }

    //Метод для поиска линии по которой возможна блокировка хода соперника;
    static boolean findBlockTurn(char[][] map, char taskSign, int taskCombinationLength, char emptySign, char currentSign, char userSign) {
        if(blockLine(map, taskSign, taskCombinationLength, emptySign, currentSign, userSign)){
            return true;
        }else if(blockColumn(map, taskSign, taskCombinationLength, emptySign, currentSign, userSign)){
            return true;
        }else if(blockdiag1(map, taskSign, taskCombinationLength, emptySign, currentSign, userSign)){
            return true;
        }else if(blockdiag2(map, taskSign, taskCombinationLength, emptySign, currentSign, userSign)){
            return true;
        }else
            return false;
    }

    //Метод для поиска комбинаций соперника по горизонтали
    public static boolean blockLine(char[][] map, char taskSign, int taskCombinationLength, char emptySign, char currentSign, char userSign) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                int di = i;
                int dj = j;
                int sum = 0;
                for (int k = 0; k < taskCombinationLength; k++) {
                    if (j + k < map.length) {
                        if (map[i][j + k] == emptySign) {
                            di = i;
                            dj = j + k;
                        }
                        if (map[i][j + k] == taskSign){
                            sum++;
                        }else if (map[i][j + k] != emptySign){
                            break;
                        }
                    }
                    if (sum == taskCombinationLength - 1 && occupateCoordinate(map, di, dj, emptySign, userSign, currentSign)) {
                        writeAiCoordinates(map, di, dj, currentSign);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Метод для поиска комбинаций соперника по вертикали
    public static boolean blockColumn(char[][] map, char taskSign, int taskCombinationLength, char emptySign, char currentSign, char userSign) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                int di = i;
                int dj = j;
                int sum = 0;
                for (int k = 0; k < taskCombinationLength; k++) {
                    if (i + k < map.length) {
                        if (map[i + k][j] == emptySign) {
                            di = i + k;
                            dj = j;
                        }
                        if (map[i + k][j] == taskSign){
                            sum++;
                        }else if (map[i + k][j] != emptySign){
                            break;
                        }
                    }
                    if (sum == taskCombinationLength - 1 && occupateCoordinate(map, di, dj, emptySign, userSign, currentSign)) {
                        writeAiCoordinates(map, di, dj, currentSign);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Метод для поиска комбинаций соперника по диагонали слева направо
    public static boolean blockdiag1(char[][] map, char taskSign, int taskCombinationLength, char emptySign, char currentSign, char userSign) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                int di = i;
                int dj = j;
                int sum = 0;
                for (int k = 0; k < taskCombinationLength; k++) {
                    if (i + k < map.length && j + k < map.length) {
                        if (map[i + k][j + k] == emptySign) {
                            di = i + k;
                            dj = j + k;
                        }
                        if (map[i + k][j + k] == taskSign){
                            sum++;
                        }else if (map[i + k][j + k] != emptySign) {
                            break;
                        }
                    }
                    if (sum == taskCombinationLength - 1 && occupateCoordinate(map, di, dj, emptySign, userSign, currentSign)) {
                        writeAiCoordinates(map, di, dj, currentSign);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Метод для поиска комбинаций соперника по диагонали справа налево
    public static boolean blockdiag2(char[][] map, char taskSign, int taskCombinationLength, char emptySign, char currentSign, char userSign) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                int di = i;
                int dj = j;
                int sum = 0;
                for (int k = 0; k < taskCombinationLength; k++) {
                    if (i - k >= 0 && j + k < map.length) {
                        if (map[i - k][j + k] == emptySign) {
                            di = i - k;
                            dj = j + k;
                        }
                        if (map[i - k][j + k] == taskSign) {
                            sum++;
                        }else if (map[i - k][j + k] != emptySign) {
                            break;
                        }
                    }
                    if (sum == taskCombinationLength - 1 && occupateCoordinate(map, di, dj, emptySign, userSign, currentSign)) {
                        writeAiCoordinates(map, di, dj, currentSign);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}