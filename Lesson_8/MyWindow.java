package Calculator;

import javax.swing.*;
import java.awt.*;

public class MyWindow extends JFrame {
    public MyWindow() {
        //Инициализация окна программы
        setTitle("Калькулятор");                                      //Название
        setBounds(100, 100, 400, 500);              //Размеры
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);      //Действие при нажатии кнопки закрыть
        setMinimumSize(new Dimension(350, 450));          //Задание минимального размера окна

        //Инициализация панелей с контейнерами
        JPanel inpPrint = new JPanel();                                 //Панель ввода-вывода
        JPanel memInpPrint = new JPanel();                              //Панель ввода-вывода + поле с индикатором памяти
        JPanel keyboard = new JPanel();                                 //Панель цифровой клавиатуры
        JPanel operations = new JPanel();                               //Панель операторов

        setLayout(new GridLayout(3, 1));                      //Задание стиля разметки страницы
        keyboard.setLayout(new GridLayout(3, 3));             //Задание стиля разметки панели с клавиатурой
        operations.setLayout(new GridLayout(3, 4));           //Задание стиля разметки панели со знаками
        inpPrint.setLayout(new GridLayout(2, 1));             //Задание стиля разметки панели ввода-вывода
        memInpPrint.setLayout(new GridBagLayout());                     //Задание стиля разметки панели ввода-вывода-памяти


        JLabel history = new JLabel("0", SwingConstants.RIGHT);        //Создание поля для вывода записанного выражения
        JLabel input = new JLabel("0", SwingConstants.RIGHT);          //Создание поля для ввода текщего числа и вывода результата
        JLabel answer = new JLabel("0");                               //Создание поля для хранения результата вычислений
        JLabel memory = new JLabel("0");                               //Создание поля памяти калькулятора
        JLabel memoryIndicate = new JLabel();                              //Создание поля для вывода индикатора присутствия данных в памяти калькулятора
        memoryIndicate.setVerticalAlignment(SwingConstants.BOTTOM);        //Настройка выравнивания индикатора памяти калькулятора
        history.setFont(new Font("", Font.PLAIN, 30));          //Настройка шрифта поля вывода
        input.setFont(new Font("", Font.BOLD, 80));             //Настройка шрифта поля ввода
        memoryIndicate.setFont(new Font("", Font.BOLD, 50));    //Настройка шрифта поля ввода
        inpPrint.add(history);                                            //Добавления поля вывода в панель
        inpPrint.add(input);                                              //Добавление поля ввода в панель
        GridBagConstraints gbc = new GridBagConstraints();                //Создание стиля разметки с гибкой сеткой компонентов
        gbc.weighty = 1f;                                                 //Высота контейнера индикатора памяти не фиксирована
        gbc.weightx = 0f;                                                 //Ширина контейнера индикатора памяти фиксирована
        gbc.fill = GridBagConstraints.BOTH;
        memInpPrint.add(memoryIndicate,gbc);                              //Добавление индикатора памяти в панель ввод-вывод-память
        gbc.weightx = 1f;                                                 //Ширина панели ввода-вывода не фиксирована
        memInpPrint.add(inpPrint,gbc);                                    //Добавление панели ввода-вывода в панель ввод-вывод-память

        ButtonListener buttonListener = new ButtonListener(input, history, answer, memory, memoryIndicate);   //Создание слушателя действий для кнопок

        JButton[] numbs = new JButton[10];                                          //Объявление массива кнопок с цифрами
        for (int i = 0; i < numbs.length; i++) {                                    //Счётчик прохода всех однозначных чисел
            numbs[i] = new JButton(String.valueOf(i));                              //Создание новой кнопки с номером счётчика и запись в массив
            numbs[i].setFont(new Font("", Font.PLAIN, 24));               //Настройка шрифта на кнопке
            numbs[i].addActionListener(buttonListener);                             //Назначение кнопке слушателя действий
            keyboard.add(numbs[i]);                                                 //Добавление кнопки в панель клавиатура
        }

        JButton[] operators = new JButton[8];                                       //Объявление массива кнопок с операциями
        operators[0] = new JButton(".");                                        //Заполнение массива кнопок с операциями
        operators[1] = new JButton("=");                                        // --//--//--//--//--//--//--//--//--//--
        operators[2] = new JButton("C");                                        // --//--//--//--//--//--//--//--//--//--
        operators[3] = new JButton("\u2906");                                   // --//--//--//--//--//--//--//--//--//--
        operators[4] = new JButton("+");                                        // --//--//--//--//--//--//--//--//--//--
        operators[5] = new JButton("-");                                        // --//--//--//--//--//--//--//--//--//--
        operators[6] = new JButton("\u00F7");                                   // --//--//--//--//--//--//--//--//--//--
        operators[7] = new JButton("x");                                        // --//--//--//--//--//--//--//--//--//--

        JButton[] memoryKeys = new JButton[4];                                      //Объявление массива кнопок для работы с памятью калькулятора
        memoryKeys[0] = new JButton("MR");                                      //MR - вернуть данные из памяти
        memoryKeys[1] = new JButton("M+");                                      //М+ - добавить данные в память
        memoryKeys[2] = new JButton("M-");                                      //M- - вычесть данные из памяти
        memoryKeys[3] = new JButton("MC");                                      //MC - очистить память

        for (int i = 0; i < memoryKeys.length; i++) {                               //Цикл для инициализации массива кнопок для работы с памятью
            memoryKeys[i].setFont(new Font("", Font.PLAIN, 18));          //Настройка шрифта кнопки
            memoryKeys[i].setBackground(new Color(205,205,205));            //Изменение цвета кнопки
            memoryKeys[i].addActionListener(buttonListener);                        //Добавление кнопке слушателя действий
            operations.add(memoryKeys[i]);                                          //Добавление кнопки в панель операций
        }

        for (int i = 0; i < operators.length; i++) {                                //Цикл для инициализации массива кнопок с операциями
            operators[i].setFont(new Font("", Font.PLAIN, 24));           //Настройка шрифта на кнопке
            operators[i].addActionListener(buttonListener);                         //Добавление кнопке слушателя действий
            operations.add(operators[i]);                                           //Добавление кнопки в панель операции
        }


        add(memInpPrint);                                                           //Добавление панели ввода-вывода-памяти на форму
        add(operations);                                                            //Добавление панели операции на форму
        add(keyboard);                                                              //Добавление панели клавиатура на форму

        setVisible(true);                                                           //Включение видимости
    }
}

