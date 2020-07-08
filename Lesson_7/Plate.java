package Feed_cat;

//Инициализация класса "Тарелка"
public class Plate {

    //Блок объявления полей класса "Тарелка"
    private int food;

    //Конструктор класса "Тарелка"
    public Plate(int food) {
        this.food = food;
    }

    /**
     * Задание 6. Метод для добавления
     * еды на тарелку.
     */
    public void addFood(int complement) {
        this.food += complement;
        System.out.printf("В тарелку добавлено %d еды \n", complement);
    }

    //Метод уменьшения количества еды в тарелке
    public void decrease(int n) {
        food -= n;
    }

    //Метод для распечатки информации об остатке еды на тарелке
    public void info() {
        System.out.println("Остаток еды: " + food);
    }

    //Геттер возвращающий оставшееся количество еды
    public int getFood() {
        return food;
    }
}
