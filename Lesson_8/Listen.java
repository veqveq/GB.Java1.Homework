package Calculator;

public abstract class Listen {          //Абстрактный класс слушателя

    protected String key;               //Объявление поля для хранения названия нажатой кнопки

    public Listen(String key) {         //Конструктор абстрактного класса слушатель
        this.key = key;
    }

    protected abstract void listen();  //Объявление абстрактного метода Слушать
}