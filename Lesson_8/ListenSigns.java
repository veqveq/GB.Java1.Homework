package Calculator;

public class ListenSigns extends Listen {                               //Класс слушатель кнопок с символами

    private final StringBuilder text = new StringBuilder();             //Объявление поля с конструктором строк
    private final Utilities util;                                       //Объявление поля с утилитами

    public ListenSigns(String key, Utilities util) {                    //Конструктор класса слушатель символов
        super(key);
        this.util = util;
    }

    //Метод слушания действий для символов
    protected void listen() {
        switch (key) {                                              //Проверка множественного условия
            case ".":                                               //Если нажата кнопка с точкой
                if (MyWindow.rewrite) {                             //Если включена перезапись
                    text.append("0").                               //Вписать в конструктор 0
                            append(key);                            //Добавить в конце точку
                    MyWindow.input.setText(text.toString());        //Вписать в поле ввод содержимое конструктора
                    text.setLength(0);                              //Очистить конструктор
                    MyWindow.rewrite = false;                       //Выключить перезапись
                }
                if (!MyWindow.input.getText().contains(".")) {               //Проверить, есть ли точки в строке ввод
                    text.append(MyWindow.input.getText())                    //Если нет, считать в конструктор строк поле ввод
                            .append(key);                                    //Добавить точку
                    MyWindow.input.setText(text.toString());                 //Записать в поле ввод значение конструктора строк
                    text.setLength(0);                                       //Очистить конструктор
                }
                break;

            case "C":                                                        //Если нажата кнопка "С"
                MyWindow.input.setText("0");                                 //Стереть информацию из поля ввод
                MyWindow.history.setText("0");                               //Стереть информацию из поля история
                MyWindow.answer = "0";                                       //Стереть информацию из переменной ответ
                break;

            case "\u2906":                                                           //Если нажата кнопка <- (стереть)
                if (MyWindow.input.getText().length() > 1) {                         //Если в поле ввод больше одного символа
                    text.append(MyWindow.input.getText()).                           //Считать в конструктор поле ввод
                            deleteCharAt(MyWindow.input.getText().length() - 1);     //Удалить последний символ
                    MyWindow.input.setText(text.toString());                         //Записать в поле ввод значение конструктора
                    text.setLength(0);                                               //Очистить конструктор
                } else {                                                             //Если в поле ввод один и меньше символов
                    MyWindow.input.setText("0");                                     //Записать в поле ввод ноль
                    MyWindow.rewrite = true;                                         //Разрешить перезапись
                }
                break;

            case "=":                                                                                         //Если нажата кнопка =

                if (MyWindow.history.getText().equals("0") && MyWindow.input.getText().equals("0"))           //Если поля история и ввод пустые, то ничего не произойдет
                    break;

                if (!MyWindow.history.getText().equals(MyWindow.input.getText()) &&                                                     //Если содержимое истории не совпадает с содержимым ввода
                        !util.checkSign(String.valueOf(MyWindow.history.getText().charAt(MyWindow.history.getText().length() - 1)))) {  //И в конце поля история не символ
                    MyWindow.history.setText(util.formatString(MyWindow.input.getText()));                                              //Записать в историю содержимое поля ввод
                    MyWindow.answer = MyWindow.input.getText();                                                                         //Записать в переменную ответ содержимое поля ввод
                    MyWindow.rewrite = true;                                                                                            //Разрешить перезапись
                    break;
                }

                if (MyWindow.history.getText().contains("=")) {
                    if (MyWindow.input.getText().equals("0") ||
                            MyWindow.input.getText().equals(util.formatString(String.valueOf(MyWindow.answer)))) {
                        MyWindow.history.setText(util.formatString(String.valueOf(MyWindow.answer)));                           //Записать в поле история информацию из поля ответ
                    } else {
                        MyWindow.history.setText(util.formatString(String.valueOf(MyWindow.input.getText())));                  //Записать в поле история информацию из поля ответ
                        MyWindow.answer = MyWindow.input.getText();
                    }
                    MyWindow.input.setText("0");                                                                            //Записать в поле ввод ноль
                    MyWindow.rewrite = true;                                                                                //Разрешить перезапись
                    break;
                }

                if (MyWindow.history.getText().equals("0") && !MyWindow.input.getText().equals("0")) {         //Если в поле история записан ноль, а поле ввод не пустое
                    MyWindow.history.setText(util.formatString(MyWindow.input.getText()));                     //Добавить в поле история отформатированное значение поля ввод
                    MyWindow.answer = MyWindow.input.getText();                                                //Добавить в переменную значение из поля ввод
                    MyWindow.rewrite = true;                                                                   //Разрешить перезапись
                    break;
                }

                if (util.checkSign(String.valueOf(MyWindow.history.getText().charAt(MyWindow.history.getText().length() - 2)))) {   //Если в поле история на последнем месте записан символ и поле ввод не пустое
                    char earlySgn = MyWindow.history.getText().charAt(MyWindow.history.getText().length() - 2);                     //Записать последний знак в переменную
                    if (MyWindow.input.getText().charAt(0) == '.' && !util.errorDivZero(earlySgn)) {                                //Если в поле ввод на первом месте стоит точка
                        text.append(MyWindow.history.getText()).append("0");                                                        //Считать поле истоия в конструктор и добавить в конце ноль
                        MyWindow.history.setText(text.toString());                                                                  //Записать значение конструктора в поле история
                        text.setLength(0);                                                                                          //Очистить конструкт
                    }
                    if (!util.errorDivZero(earlySgn)) {                                                                               //Если в выражении не происходит деления на ноль
                        MyWindow.answer = util.calculation(earlySgn, MyWindow.answer, MyWindow.input.getText());                      //Вычислить результат последнего действия и записать результат в поле ответ
                        text.append(MyWindow.history.getText()).                                                                      //Считать в конструктор поле история
                                append(util.formatString(MyWindow.input.getText())).                                                  //Добавить к нему содержимое поля ввод
                                append(" = ");                                                                                        //Добавить знак равно
                        MyWindow.history.setText(text.toString());                                                                    //Записать в поле история значение из конструктора
                        MyWindow.input.setText(util.formatString(String.valueOf(MyWindow.answer)));                                   //Записать в поле ввод отформатированное значение из поля ответ
                        text.setLength(0);                                                                                            //Очистить конструктор
                        MyWindow.rewrite = true;                                                                                      //Разрешить перезапись
                    }
                    break;
                }

            default:                                                             //Если нажата кнопка с арифметическим знаком
                util.inputMathSign(key.charAt(0));                               //Выполнить метод ввод арифметического знака. Аргумент - текст на нажатой кнопке
                MyWindow.rewrite = true;                                         //Разрешить перезапись
                break;
        }
    }
}