package Calculator;

public class ListenNumbs extends Listen {                               //Класс слушатель кнопок с цифрами

    private final StringBuilder text = new StringBuilder();             //Объявление поля с конструктором строк

    public ListenNumbs(String key) {                                    //Конструктор класса слушатель кнопок с цифрами
        super(key);
    }

    protected void listen() {
        if (MyWindow.input.getText().equals("0")) {                         //Если в поле ввод стоит только ноль
            MyWindow.setRewrite(true);                                      //Включить перезапись
        }

        if (MyWindow.isRewrite()) {                                         //Если разрешена перезапись
                MyWindow.input.setText(key);                                //Записать в поле ввод цифру нажатой кнопки
                MyWindow.setRewrite(false);                                 //Запретить перезапись
        } else {                                                            //Иначе
            text.append(MyWindow.input.getText())                           //Считать в конструктор строк значение из поля ввод
                    .append(key);                                           //Добавить к нему цифру нажатой кнопки
            MyWindow.input.setText(text.toString());                        //Записать строку из конструктора в поле ввод
            text.setLength(0);                                              //Очистить конструктор строк
        }
    }
}