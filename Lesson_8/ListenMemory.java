package Calculator;

public class ListenMemory extends Listen {                  //Класс слушатель кнопок памяти

    private final Utilities util;

    public ListenMemory(String key, Utilities util) {       //Конструктор класса слушатель кнопок памяти
        super(key);
        this.util = util;
    }

    //Метод слушания действий для кнопок работы с памятью
    protected void listen() {
        switch (key.charAt(1)) {                                                                                //Проверка множественного условия
            case 'R':                                                                                           //Если 2 символ на кнопке R
                if (!MyWindow.memory.equals("0")) {                                                             //Если в памяти есть число
                    MyWindow.input.setText(util.formatString(MyWindow.memory));                                 //Записать в поле ввод отформатированное значение переменной память
                }
                break;
            case 'C':                                                                                           //Если 2 символ на кнопке С
                MyWindow.memory = "0";                                                                          //Очистить память
                MyWindow.memoryIndicate.setText("");                                                            //Скрыть индикатор памяти
                break;
            default:
                char earlySgn = key.charAt(1);                                                                  //Записать в переменную 2 символ с названия кнопки (+/-)
                MyWindow.memory = util.calculation(earlySgn, MyWindow.memory, MyWindow.input.getText());        //Добавить/вычесть в памяти значение из поля ввод
                MyWindow.memoryIndicate.setText("M");                                                           //Включить индикатор памяти
                MyWindow.rewrite = true;                                                                        //Разрешить перезапись
        }
    }
}