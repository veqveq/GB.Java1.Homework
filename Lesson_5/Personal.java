package FirstClass;
//Создание класса Personal
public class Personal {

//Блок инициализации полей экземпляра класса
    private String name;
    private String position;
    private String email;
    private String phoneNumber;
    private float paycheck;
    private int age;

//Блок создания конструктора класса
    public Personal(String name, String position, String email,
                    String phoneNumber, float paycheck, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.paycheck = paycheck;
        this.age = age;
    }

//Метод распечатки информации объекта класса
    public void printInfo() {
        System.out.println(String.format("Имя: %-30s | Должность: %-20s | E-mail: %-20s | Телефон: %-15s | Зарплата: %-10.2f | Возраст: %-3d",
                name, position, email, phoneNumber, paycheck, age));
    }

//Геттер возвращающий возраст сотрудника
    public int getAge(){
        return age;
    }
}