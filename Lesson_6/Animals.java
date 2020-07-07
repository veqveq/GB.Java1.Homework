package Animals;

//Создание абстрактного класса Животные
public abstract class Animals {
    //Блок объявления полей класса
    private String name;        //Поле имени
    private String type;        //Поле вида животного (кот/пёс)
    private double runLimit;    //Поле с ограничением на бег
    private double jumpLimit;   //Поле с ограничением на прыжок
    private double swimLimit;   //Поле с ограничением на плавание

    //Конструктор суперкласса Животные
    public Animals(String name, String type, double runLimit, double jumpLimit, double swimLimit) {
        this.name = name;
        this.type = type;
        this.runLimit = runLimit;
        this.jumpLimit = jumpLimit;
        this.swimLimit = swimLimit;
    }

    //Метод для выполнения функции БЕГ
    public void run(double distance) {
        checkLimit("пробежал", "пробежать", distance, runLimit);
    }

    //Метод для выполнение функции ПРЫЖОК
    public void jump(double hight) {
        checkLimit("подпрыгнул на", "подпрыгнуть на", hight, jumpLimit);
    }

    //Метод для выполнения функции ПЛАВАНИЕ
    public void swim(double distance) {
        checkLimit("проплыл", "проплыть", distance, swimLimit);
    }

    //Сеттер для изменения ограничения для животного на БЕГ
    public void setRunLimit(double runLimit) {
        this.runLimit = runLimit;
    }

    //Сеттер для изменения ограничения для животного на ПРЫЖОК
    public void setJumpLimit(double jumpLimit) {
        this.jumpLimit = jumpLimit;
    }

    //Сеттер для изменения ограничения для животного на ПЛАВАНИЕ
    public void setSwimLimit(double swimLimit) {
        this.swimLimit = swimLimit;
    }

    //Метод для проверки введенного значения дистанции/высоты на неотрицательность и не превышение лимита
    private void checkLimit(String action1, String action2, double value, double limit) {
        if (value < 0) {                                                                                               //Если переданная в метод дистанция отрицательная
            System.out.printf("Введенное значение %.2f некорректно. Расстояние не может быть отрицательным", value);   //Выдать ошибку
        } else if (value <= limit) {                                                                                   //Если число положительное - сравнить с лимитом. Если не превышает
            printAction(action1, value);                                                                               //Напечатать сообщение о выполненном действии
        } else {
            printExcessLimit(action2, limit, value);                                                                   //Иначе поругать пользователя и указать на превышение лимита
        }
    }

    //Метод для вывода в консоль информации о выполненном действии
    private void printAction(String action, double value) {
        System.out.printf("%s %s %s %.2f метров \n", type, name, action, value);
    }

    //Метод для вывода в консоль ругательства о превышении лимита
    private void printExcessLimit(String action, double limit, double value) {
        System.out.printf("%s %s не может %s %.2f метров. Максимальное расстояние для %s - %.2f метров.\n", type, name, action, value, name, limit);
    }
}

