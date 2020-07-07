package Animals;

//Создание класса Пёс - наследника класса Животные
public class Dog extends Animals {

    //Создание конструктора для класса Пёс с предустановкой базовых ограничений по заданию
    public Dog(String name) {
        super(name, "Пёс", 500, 0.5, 10);
    }

    //Создание дополнительного конструктора класса Пёс с возможностью настройки ограничений
    public Dog(String name, double runLimit, double jumpLimit, double swimLimit) {
        super(name, "Пёс", runLimit, jumpLimit, swimLimit);
    }
}

