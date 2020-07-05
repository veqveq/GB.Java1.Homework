package FirstClass;

public class Main {

//Создание массива для хранения данных о сотрудниках
    private static Personal [] persons = new Personal[5];

    public static void main(String[] args) {
        initPersonArray(persons);
        changePersonOlder40(persons);
    }

//Метод инициализации созданного массива
    private static void initPersonArray(Personal[] persons){
        persons [0] = new Personal("Иванов Иван Иванович", "Хранитель традиций", "ivanov@gmail.com", "8-800-555-35-35",12550, 37);
        persons [1] = new Personal("Рубленштейн Давид Матвеич", "Консьерж", "rdv@mail.ru", "8-910-399-99-55",15000, 41);
        persons [2] = new Personal("Шабарина Роза Германовна", "Уборщица", "dirtygirl@gmail.com", "8-915-451-36-49",48000, 45);
        persons [3] = new Personal("Главарь Лев Борисыч", "Генеральный директор", "glavarLB@mail.ru", "8-961-311-68-20",200000.00F, 62);
        persons [4] = new Personal("Чапаев Василий Петрович", "Охранник", "redcomandante@yandex.ru", "8-920-666-36-36",12550, 12);
    }

//Метод вывода данных о сотрудниках старше 40 лет
    private static void changePersonOlder40(Personal [] persons){
        for (int i = 0; i < persons.length; i++) {
            if(persons[i].getAge() >= 40){
                persons[i].printInfo();
            }
        }
    }
}