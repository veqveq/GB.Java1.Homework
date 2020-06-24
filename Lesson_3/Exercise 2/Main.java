package ru.geekbrains.catch_the_drop;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String [] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado",      //Инициализация массива слов
                "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi",
                "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper",
                "pineapple", "pumpkin", "potato"};

        String randomWord = findRandWord(words);                                            //Инициализация переменной с записанным выбранным случайным словом.
        String userWord;                                                                    //Объявление переменной для хранения введенного пользователем слова
        int score = 0;                                                                      //Инициализация переменной для подсчёта кол-ва попыток

        do {                                                                                //Начало игрового цикла
            userWord = enterUserWord();                                                     //Запись в переменную введенного с клавиатуры слова
            score++;                                                                        //Учет сделанной попытки угадать слово
            if (randomWord.equals(userWord) == true) {
                win(randomWord, score);                                                     //Если слово угадано - вывести победный метод
            } else {
                wrong(userWord, randomWord);
                }
        } while (randomWord.equals(userWord) == false);                                     //Условие повторения цикла
    }

    //Метод для выбора случайного слова из массива
    static String findRandWord (String [] words) {
        Random rand = new Random();                           //Объявление переменной типа Random
        return  words[rand.nextInt(words.length-1)];    //Возвращение случайного слова в метод main()
    }

    //Метод для создания объекта класса Scanner
    public static Scanner sc = new Scanner(System.in);

    //Метод ля ввода слова с клавиатуры
    static String enterUserWord(){
        System.out.println("Введи слово");      //Вывод приглашения ввести слово
        String userWord = sc.nextLine();        //Инициализация переменной, хранящей прочитанное с клавиатуры слово
        return userWord;                        //Возвращение введенного слова в метод main();
    }

    //Победный метод
    static void win (String randomWord, int score){
        System.out.println("Правильно!");
        System.out.println("Было загадано слово " + randomWord);                  //Выводит загаданное слово
        System.out.println("Ты угадал слово с " + score + " попыток");            //Выводит количество попыток, за которое слово было отгадано
    }

    //Ругательный метод в случае неправильного ответа
    static void wrong (String userWord, String randomWord){
        for (int i = 0; i < 15; i++) {                                              //Если слово не угадано - запустить цикл для посимвольного сравнения
            if (i < randomWord.length() && i < userWord.length()) {                 //Если счётчик цикла меньше длины загаднного и введенного слов
                if (userWord.charAt(i) == randomWord.charAt(i)) {                   //То проверить равенство i-ого символа в загаданном и введенном слове
                    System.out.print(userWord.charAt(i));                           //Если равно - напечатать символ
                } else {
                    System.out.print("#");                                          //Если не равно - напечатать диез
                }
            } else {                                                                //Условие выполнится, если счётчик превысил длину загаднанного или введенного слова
                System.out.print("#");                                              //Напечатает диез
            }
        }
        System.out.println();                                                        //Переход на следующую строку
    }
}