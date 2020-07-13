package Calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

    private final JLabel input;                                         //Объявление поля ввод
    private final JLabel history;                                       //Объявление поля вывод истории
    private final JLabel answer;                                        //Объявление поля ответ
    private final JLabel memory;                                        //Объявление поля памяти калькулятора
    private final JLabel memoryIndicate;                                //Объявление поля с индикатором нахождения данных в памяти калькулятора
    private final StringBuilder text = new StringBuilder();             //Объявление поля с конструктором строк
    private boolean rewrite = true;                                     //Объявление переменной, разрешающей запрещающей перезапись поля ввод


    public ButtonListener(JLabel input, JLabel history, JLabel answer, JLabel memory, JLabel memoryIndicate) {    //Конструктор класса слушатель кнопок
        this.input = input;
        this.history = history;
        this.answer = answer;
        this.memory = memory;
        this.memoryIndicate = memoryIndicate;
    }

    @Override
    public void actionPerformed(ActionEvent e) {                //Метод приема действия
        JButton currentButton = (JButton) e.getSource();        //Прием в класс параметров нажатой кнопки и присвоение их созданной кнопке currentButton
        if (!checkSign(currentButton.getText())) {              //Если имя кнопки не является символом
            listenNumbers(currentButton);                       //Выполнить метод слушания действий для цифр
        } else if (currentButton.getText().charAt(0) == 'M') {  //Иначе если первый символ на нажатой кнопке M
            listenMemory(currentButton);                        //Выполнить метод слушания действий для кнопок работы с памятью
        } else {                                                //Иначе
            listenSigns(currentButton);                         //Выполнить метод слушания действий для символов
        }
    }

    //Метод слушания действий для цифр
    private void listenNumbers(JButton currentButton) {
        if (rewrite) {                                             //Если разрешена перезапись
            this.input.setText(currentButton.getText());           //Записать в поле ввод цифру нажатой кнопки
            rewrite = false;                                       //Запретить перезапись
        } else {                                                   //Иначе
            text.append(input.getText())                           //Считать в конструктор строк значение из поля ввод
                    .append(currentButton.getText());              //Добавить к нему цифру нажатой кнопки
            this.input.setText(text.toString());                   //Записать строку из конструктора в поле ввод
            text.setLength(0);                                     //Очистить конструктор строк
        }
    }

    //Метод слушания действий для символов
    private void listenSigns(JButton currentButton) {
        switch (currentButton.getText()) {                          //Проверка множественного условия
            case ".":                                               //Если нажата кнопка с точкой
                if (input.getText().indexOf('.') == -1) {           //Проверить, есть ли точки в строке ввод
                    text.append(input.getText())                    //Если нет, считать в конструктор строк поле ввод
                            .append(currentButton.getText());       //Добавить точку
                    input.setText(text.toString());                 //Записать в поле ввод значение конструктора строк
                    text.setLength(0);                              //Очистить конструктор
                }
                break;

            case "C":                                               //Если нажата кнопка "С"
                input.setText("0");                                 //Стереть информацию из поля ввод
                history.setText("0");                               //Стереть информацию из поля история
                answer.setText("0");                                //Стереть информацию из поля ответ
                rewrite = true;                                     //Разрешить перезапись
                break;

            case "\u2906":                                                  //Если нажата кнопка <- (стереть)
                if (input.getText().length() > 1) {                         //Если в поле ввод больше одного символа
                    text.append(input.getText()).                           //Считать в конструктор поле ввод
                            deleteCharAt(input.getText().length() - 1);     //Удалить последний символ
                    input.setText(text.toString());                         //Записать в поле ввод значение конструктора
                    text.setLength(0);                                      //Очистить конструктор
                } else {                                                    //Если в поле ввод один и меньше символов
                    input.setText("0");                                     //Записать в поле ввод ноль
                    rewrite = true;                                         //Разрешить перезапись
                }
                break;

            case "=":                                               //Если нажата кнопка =
                if (history.getText().indexOf('=') != -1) {         //Если в поле история уже есть символы равно
                    history.setText(answer.getText());              //Записать в поле история информацию из поля ответ
                    input.setText("0");                             //Записать в поле ввод ноль
                    rewrite = true;                                 //Разрешить перезапись
                    break;
                }
                if (history.getText() == "0" && input.getText() != null) {          //Если в поле история записан ноль, а поле ввод не пустое
                    history.setText(input.getText());                               //Записать в поле история значение из поля ввод
                    writeAnswer(String.valueOf(input.getText()), answer);           //Записать в поле ответ значение из поля ввод
                    rewrite = true;                                                 //Разрешить перезапись
                    break;
                }

                if (checkSign(String.valueOf(history.getText().charAt(history.getText().length() - 2))) && input.getText() != null) {  //Если в поле история на последнем месте записан символ и поле ввод не пустое
                    char earlySgn = history.getText().charAt(history.getText().length() - 2);         //Записать последний знак в переменную
                    writeAnswer(String.valueOf(calculation(earlySgn, answer, input)), answer);                //Вычислить результат последнего действия и записать результат в поле ответ
                    text.append(history.getText()).                                                   //Считать в конструктор поле история
                            append(input.getText()).                                                  //Добавить к нему содержимое поля ввод
                            append(" = ");                                                            //Добавить знак равно
                    history.setText(text.toString());                                                 //Записать в поле история значение из конструктора
                    input.setText(answer.getText());                                                  //Записать в поле ввод значение из поля ответ
                    rewrite = true;                                                                   //Разрешить перезапись
                    text.setLength(0);                                                                //Очистить конструктор
                    break;
                }

            default:                                                    //Если нажата кнопка с арифметическим знаком
                inputMathSign(currentButton.getText().charAt(0));       //Выполнить метод ввод арифметического знака. Аргумент - текст на нажатой кнопке
                rewrite = true;                                         //Разрешить перезапись
                break;
        }
    }

    //Метод слушания действий для кнопок работы с памятью
    private void listenMemory(JButton currentButton) {
        switch (currentButton.getText().charAt(1)) {                    //Проверка множественного условия
            case 'R':                                                   //Если 2 символ на кнопке R
                if (!memory.getText().equals("0")) {                    //Если в памяти есть число
                    input.setText(memory.getText());                    //Записать его в поле ввода
                }
                break;
            case 'C':                                                   //Если 2 символ на кнопке С
                memory.setText("0");                                    //Очистить память
                memoryIndicate.setText("");                             //Скрыть индикатор памяти
                break;
            default:
                char earlySgn = currentButton.getText().charAt(1);                              //Записать в переменную 2 символ с названия кнопки (+/-)
                writeAnswer(String.valueOf(calculation(earlySgn, memory, input)), memory);      //Добавить/вычесть в памяти значение из поля ввод
                memoryIndicate.setText("M");                                                    //Включить индикатор памяти
                rewrite = true;                                                                 //Разрешить перезапись
        }
    }

    //Метод ввод арифметического знака
    private void inputMathSign(char sgn) {
        if (history.getText() == "0") {                                              //Если в поле история записан ноль
            text.append(input.getText()).append(" ").                                //Считать поле ввод в конструктор
                    append(sgn).                                                     //Добавить символ арифметического действия (аргумент метода)
                    append(" ");
            history.setText(text.toString());                                        //Записать в поле история содержимое конструктора
            writeAnswer(String.valueOf(input.getText()), answer);                            //Записать в поле ответ содержимое поля ввод
            text.setLength(0);                                                       //Очистить конструктор
        } else {                                                                     //Иначе, если в поле история не ноль
            if (history.getText().charAt(history.getText().length() - 1) != ' ') {   //Если последний символ в поле история не пробел
                text.append(history.getText()).                                      //Считать в конструктор поле история
                        append(" ").
                        append(sgn).                                                 //Добавить символ арифметического действия
                        append(" ");
                history.setText(text.toString());                                    //Записать в поле история содержимое конструктора
                text.setLength(0);                                                   //Очитстить конструктор
            } else if (history.getText().indexOf('=') != -1) {                       //Если в поле история встречен знак равно
                text.append(answer.getText()).                                       //Считать поле ответ в конструктор
                        append(" ").
                        append(sgn).                                                 //Добавить символ арифметического действия
                        append(" ");
                history.setText(text.toString());                                    //Записать в поле история содержимое конструктора
                text.setLength(0);                                                   //Очистить конструктор
            } else if (checkSign(String.valueOf(history.getText().charAt(history.getText().length() - 2))) &&   //Если предпоследний знак в поле история является символом
                    history.getText().charAt(history.getText().length() - 2) != sgn &&                          //И этот символ не равен символу на нажатой кнопке
                    (input.getText() == "0" || input.getText() == answer.getText())) {                          //И в поле ввод стоит либо ноль, либо ответ
                text.append(history.getText()).                                                                 //Считать в конструктор поле история
                        delete(history.getText().length() - 3, history.getText().length()).                     //Удалить последние 3 символа
                        append(" ").
                        append(sgn).                                                                            //Добавить текущий символ арифметического действия
                        append(" ");
                history.setText(text.toString());                                                               //Записать в поле история содержимое конструктора
                text.setLength(0);                                                                              //Очистить конструктор
            } else {                                                                                            //Если не выполнено ни одно предыдущее условие
                char earlySgn = history.getText().charAt(history.getText().length() - 2);                       //Считать в переменную последний символ в строке
                writeAnswer(String.valueOf(calculation(earlySgn, answer, input)), answer);                              //Записать в поле ответ значение из поля ввод
                text.append(history.getText()).                                                                 //Считать в конструктор поле история
                        append(input.getText()).                                                                //Добавить содержимое поля ввод
                        append(" ").
                        append(sgn).                                                                            //Добавить знак арифметического действия
                        append(" ");
                history.setText(text.toString());                                                               //Записать в историю содержимое конструктора
                input.setText(answer.getText());                                                                //Записать в поле ввод содержимое поля ответ
                text.setLength(0);                                                                              //Очистить конструктор
            }
        }
    }

    //Метод, проверяющий - является ли символ числом
    private boolean checkSign(String sign) {                    //Аргумент метода - символ
        for (int i = 0; i <= 9; i++) {                          //Цикл проходит все однозначные числа
            if (sign.equals(String.valueOf(i))) return false;   //Если символ равен однозначному числу - вернуть false
        }
        return true;                                            //Иначе вернуть true
    }

    //Метод выполнения арифметического действия
    private double calculation(char sgn, JLabel answer, JLabel input) {     //Аргументы - символ действия, поля ответ и ввод
        double ans = Double.parseDouble(answer.getText());                  //Получить числовое значение из поля ответ
        double val = Double.parseDouble(input.getText());                   //Получить числовое значение из поля ввод
        switch (sgn) {                                                      //Проверка множественного условия
            case '+':                                                       //Если переданный символ - плюс
                ans += val;                                                 //Прибавить к ответу значение поля ввод
                break;
            case '-':                                                       //Если переданный символ - минус
                ans -= val;                                                 //Отнять от ответа значение поля ввод
                break;
            case 'x':                                                       //Если переданный символ - умножить
                ans *= val;                                                 //Умножить ответ на значение поля ввод
                break;
            case '\u00F7':                                                  //Если переданный символ - разделить
                ans /= val;                                                 //Разделить ответ на значение поля ввод
                break;
        }
        return ans;                                                         //Вернуть ответ
    }

    //Метод для форматирования поля - подавления хвостовых нулей
    private void writeAnswer(String str, JLabel field) {                //Аргумент метода - произвольная строка, поле для записи отформатированного значения
        char sgn;                                                       //Объявление переменной для хранения символа
        do {                                                            //Старт цикла с постусловием
            sgn = str.charAt(str.length() - 1);                         //Запись последнего символа строки в переменную
            if (sgn == '0') {                                           //Если в переменной записан ноль
                text.append(str).                                       //Записать значение строки в конструктор
                        setLength(str.length() - 1);                    //Уменьшить длину строки на единицу
                str = text.toString();                                  //Записать значение конструктора в переменную со строкой
            }
        } while (sgn == '0');                                           //Цикл выполняется пока в переменной записан ноль
        if (sgn == '.') {                                               //Проверка условия. Если в переменной записана точка (подавлены все нули дробной части)
            text.append(str).                                           //Записать значение строки в конструктор
                    setLength(str.length() - 1);                        //Уменьшить длину строки на единицу
            str = text.toString();                                      //Записать значение конструктора в переменную со строкой
        }
        text.setLength(0);                                              //Очистить конструктор
        field.setText(str);                                             //Записать значение переменной со строкой в поле ответ
    }
}