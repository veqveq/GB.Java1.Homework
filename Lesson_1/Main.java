
public class Main {

    /*
    Задание 1
    Описание метода main()
    */

    public static void main (String[] args) {

        /*
        Задание 2
        Инициализация примитивных переменных
         */

        byte a0 = 14;                            //Инициализация переменной типа byte
        short a1 = 1488;                         //Инициализация переменной типа short
        int a2 = 1920;                           //Инициализация переменной типа int
        float a3 = 24.5F;                        //Инициализация переменной типа float
        double a4 = 136.695D;                    //Инициализация переменной типа double
        char a5 ='a';                            //Инициализация переменной типа char
        boolean a6 = true;                       //Инициализация переменной типа boolean
        String a7 = "Hi";                        //Инициализация ссылочной переменной типа String

        System.out.println("***Задание 3***");
        System.out.println(method1(12.6,5.4,6.5,14.1));      //Проверка метода из Задания 3

        System.out.println("***Задание 4***");
        method2(10,10,10,20);                      //Проверка метода из Задания 4. Исход 1
        method2(15,10,10,20);                      //Проверка метода из Задания 4. Исход 2

        System.out.println("***Задание 5***");
        method3(-12);                                             //Проверка метода из Задания 5. Число >0
        method3(12);                                              //Проверка метода из Задания 5. Число <0
        method3(0);                                               //Проверка метода из Задания 5. Число = 0

        System.out.println("***Задание 6***");
        System.out.println(method4(-12));                                             //Проверка метода из Задания 6. Число <0
        System.out.println(method4(12));                                              //Проверка метода из Задания 6. Число >0
        System.out.println(method4(0));                                               //Проверка метода из Задания 6. Число = 0

        System.out.println("***Задание 7***");
        method5("Создатель");                                       //Проверка метода из задания 7

        System.out.println("***Задание 8***");
        method6(1200);                                               //Проверка метода из задания 8*. Исход 1
        method6(300);                                               //Проверка метода из задания 8*. Исход 2
        method6(2008);                                              //Проверка метода из задания 8*. Исход 3
    }

         /*
        Задание 3
        Написание метода для вычисления выражения
         */
    public static double method1(double a, double b, double c, double d){
        return a * (b + (c / d));           //Вычисление выражения и передача значения в метод main()
    }

        /*
        Задание 4
        Проверка принадлежности суммы чисел заданному диапазону;
         */
    public static void method2(double num1, double num2, int min, int max ){
        /*Переменные метода
        num1 - первое слагаемое;
        num2 - второе слагаемое;
        min - нижняя граница числового диапазона;
        max - верхняя граница числовог диапазона;
         */
        double sum = num1 + num2;           //Вычисление суммы переданных в метод чисел
        if (sum >= min && sum <= max){      //Проверка условия
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }

        /*
        Задание 5
        Определение знака числа, переданного в метод;
         */
    public static void method3(int numb3){
        if (numb3 >= 0){
            System.out.println("Число " + numb3 + " положительное");
        }else {
            System.out.println("Число " + numb3 + " отрицательное");
        }
    }

        /*
        Задание 6
        Определение отрицательного числа;
         */
    public static String method4(int numb4){
       if (numb4 < 0){
           return "True";
       }else{
           return "";
       }
    }

        /*
        Задание 7
        Приветственный метод
         */
    public static void method5 (String name){
        System.out.println("Привет, " + name + "!");
    }

        /*
        Задание 8
        Проверка високосности года
         */
    public static void method6 (int year){
        boolean rem4 = year%4 == 0;         //Сравнение остатка от деления года на 4. True - делится без остатка;
        boolean rem100 = year%100 == 0;     //Сравнение остатка от деления года на 100. True - делится без остатка;
        boolean rem400 = year%400 ==0;      //Сравнение остатка от деления года на 400. True - делится без остатка;
            /*
            Проверка условия. Остаток от деления на 4 нулевой, и выполняется любое из условий:
            1. Остаток от деления на 100 не нулевой;
            2. Остаток от деления на 400 нулевой;
             */
        if ((rem4 == true) && (rem100 == false || rem400 == true)){
            System.out.println(year + " год високосный");
        }else {
            System.out.println(year + " год не високосный");
        }
    }
}
