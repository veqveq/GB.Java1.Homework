package Feed_cat;

//Инициализация класса "Кот"
public class Cat {

    //Блок объявления полей класса "Кот"
    private String name;        //Имя кота
    private int appetite;       //Аппетит кота
    private boolean satiety;    //Сытость кота

    //Конструктор класса "Кот"
    public Cat(String name, int appetite) {                                     //При инициализации объекта класса "Кот" указываются имя и аппетит
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;                                                   //Переменная "Сытость". При создании кота имеет значение false
    }

    /**
     * Задания 2-4. Корректировка метода "Поесть".
     * Если количество еды в тарелке не меньше
     * аппетита конкретного кота, то кот ест
     * переменная satiety получает значение true
     */
    public void eat(Plate p) {
        if (p.getFood() >= appetite) {                                          //Если остаток еды на тарелке больше чем аппетит
            p.decrease(appetite);                                               //Кот ест. Уменьшить количество еды в тарелке
            System.out.printf("Кот %s поел \n", name);                          //Вывести надпись о том что кот поел
            satiety = true;                                                     //Изменить значение сытости на true
        } else {                                                                //Иначе
            System.out.printf("Кот %s не поел. Добавь еды, хомо! \n", name);    //Кот не ест. Вывести надпись о необходимости добавить еды
        }
    }

    //Геттер, возвращающий сытость
    public boolean getSatiety() {
        return satiety;
    }

    //Геттер, возвращающий имя
    public String getName() {
        return name;
    }
}
