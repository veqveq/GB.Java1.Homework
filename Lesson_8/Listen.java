package Calculator;

public abstract class Listen {          //Абстрактный класс слушателя

    String key;               //Объявление поля для хранения названия нажатой кнопки

    protected Listen(String key) {         //Конструктор абстрактного класса слушатель
        this.key = key;
    }

    protected abstract void listen();  //Объявление абстрактного метода Слушать
}