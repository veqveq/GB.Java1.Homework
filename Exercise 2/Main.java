package ru.geekbrains.catch_the_drop;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String [] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado",      //Инициализация массива слов
                "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi",
                "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper",
                "pineapple", "pumpkin", "potato"};

        String randomWord = FindRandWord(words);                                            //Инициализация переменной с записанным выбранным случайным словом.
        String UserWord;                                                                    //Объявление переменной для хранения введенного пользователем слова
        int score = 0;                                                                      //Инициализация переменной для подсчёта кол-ва попыток

        do {                                                                                //Начало игрового цикла
            UserWord = EnterUserWord();                                                     //Запись в переменную введенного с клавиатуры слова
            score++;                                                                        //Учет сделанной попытки угадать слово
            if (randomWord.equals(UserWord) == true) {                                      //Если слово угадано - вывести поюедную надпись
                System.out.println("Правильно!");
                System.out.println("Было загадано слово " + randomWord);
                System.out.println("Ты угадал слово с " + score + " попыток");
            } else {
                for (int i = 0; i < 15; i++) {                                              //Если слово не угадано - запустить цикл для посимвольного сравнения
                    if (i < randomWord.length() && i < UserWord.length()) {                 //Если счётчик цикла меньше длины загаднного и введенного слов
                        if (UserWord.charAt(i) == randomWord.charAt(i)) {                   //То проверить равенство i-ого символа в загаданном и введенном слове
                            System.out.print(UserWord.charAt(i));                           //Если равно - напечатать символ
                        } else {
                            System.out.print("#");                                          //Если не равно - напечатать диез
                        }
                    } else {                                                                //Условие выполнится, если счётчик превысил длину загаднанного или введенного слова
                        System.out.print("#");                                              //Напечатает диез
                    }
                }
            }
            System.out.println();                                                           //Переход на следующую строку
        } while (randomWord.equals(UserWord) == false);                                     //Условие повторения цикла
    }

    //Метод для выбора случайного слова из массива
    static String FindRandWord (String [] words) {
        Random rand = new Random();                           //Объявление переменной типа Random
        return  words[rand.nextInt(words.length-1)];    //Возвращение случайного слова в метод main()
    }

    //Метод для создания объекта класса Scanner
    public static Scanner sc = new Scanner(System.in);

    //Метод ля ввода слова с клавиатуры
    static String EnterUserWord(){
        System.out.println("Введи слово");      //Вывод приглашения ввести слово
        String UserWord = sc.nextLine();        //Инициализация переменной, хранящей прочитанное с клавиатуры слово
        return UserWord;                        //Возвращение введенного слова в метод main();
    }


}
