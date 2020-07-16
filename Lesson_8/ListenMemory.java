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
                if (!MyWindow.memoryIndicate.getText().equals("")) {                                                    //Если в памяти есть число
                    MyWindow.input.setText(util.formatString(MyWindow.getMemory()));                                 //Записать в поле ввод отформатированное значение переменной память
                }
                break;
            case 'C':                                                                                           //Если 2 символ на кнопке С
                MyWindow.setMemory("0");                                                                          //Очистить память
                MyWindow.memoryIndicate.setText("");                                                          //Скрыть индикатор памяти
                break;
            default:
                char earlySgn = key.charAt(1);                                                                  //Записать в переменную 2 символ с названия кнопки (+/-)
                MyWindow.setMemory(util.calculation(earlySgn, MyWindow.getMemory(), MyWindow.input.getText()));        //Добавить/вычесть в памяти значение из поля ввод
                MyWindow.memoryIndicate.setText("M");                                                           //Включить индикатор памяти
                MyWindow.setRewrite(true);                                                                       //Разрешить перезапись
        }
    }
}