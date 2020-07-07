package Animals;

//Создание класса Кот - наследника класса Животные
public class Cat extends Animals {

    //Создание конструктора для класса Кот с предустановкой базовых ограничений по заданию
    public Cat(String name) {
        super(name, "Кот", 200, 2, 0);
    }

    //Создание дополнительного конструктора класса Кот с возможностью настройки ограничений
    public Cat(String name, double runLimit, double jumpLimit, double swimLimit) {
        super(name, "Кот", runLimit, jumpLimit, swimLimit);
    }
}
