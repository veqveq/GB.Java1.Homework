package Feed_cat;

public class Main {

    private static Plate plate = new Plate(100);

    public static void main(String[] args) {
        task1();    //Проверка задания 1
        task4();    //Проверка задания 2-4
        task6();    //Проверка задания 6
        task5();    //Проверка задания 5
    }

    //Задание 1. Исходная задача покормить одного котэ
    private static void task1() {
        Cat cat1 = new Cat("Басик", 5);
        plate.info();
        cat1.eat(plate);
        plate.info();
    }

    //Задания 2 - 4. Проверка
    private static void task4() {
        Cat cat2 = new Cat("Мурзик", 100);
        cat2.eat(plate);
        plate.info();
    }

    //Задание 6. Проверка
    private static void task6() {
        plate.addFood(5);
        plate.info();
    }


    //Задание 5. Массив котов
    private static void task5() {
        Cat[] cats = initCatsArr();
        feedCatsArr(cats);
        checkCatsSatiety(cats);
    }

    //Метод к заданию 5. Создание массива котов
    static Cat[] initCatsArr() {
        Cat[] cats = new Cat[5];
        cats[0] = new Cat("Джонни", 25);
        cats[1] = new Cat("Энтони", 65);
        cats[2] = new Cat("Валенок", 50);
        cats[3] = new Cat("Лета", 10);
        cats[4] = new Cat("Григорич", 50);
        return cats;
    }

    //Метод к заданию 5. Кормление всего массива котов
    static void feedCatsArr(Cat[] cats) {
        for (int i = 0; i < cats.length; i++) {
            cats[i].eat(plate);
        }
    }

    //Метод к заданию 5. Проверка сытости массива котов
    static void checkCatsSatiety(Cat[] cats) {
        System.out.println("**********\n" +
                "Сытость стада котов:");
        for (int i = 0; i < cats.length; i++) {
            System.out.printf("Кот: %-10s|Сытость: %s\n", cats[i].getName(), cats[i].getSatiety());
        }
    }
}
