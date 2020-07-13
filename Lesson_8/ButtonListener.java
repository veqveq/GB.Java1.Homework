package Calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

    private final JLabel input;                                         //Объявление поля ввод
    private final JLabel history;                                       //Объявление поля вывод истории
    private final JLabel answer;                                        //Объявление поля ответ
    private final StringBuilder text = new StringBuilder();             //Объявление поля с конструктором строк

    public ButtonListener(JLabel input, JLabel history, JLabel answer) {    //Конструктор класса слушатель кнопок
        this.input = input;
        this.history = history;
        this.answer = answer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {                //Метод приема действия
        JButton currentButton = (JButton) e.getSource();        //Прием в класс параметров нажатой кнопки и присвоение их созданной кнопке currentButton

        if (!checkSign(currentButton.getText())) {              //Если имя кнопки не является символом
            listenNumbers(currentButton);                       //Выполнить метод слушания действий для цифр
        } else {                                                //Иначе
            listenSigns(currentButton);                         //Выполнить метод слушания действий для символов
        }
    }

    //Метод слушания действий для цифр
    private void listenNumbers(JButton currentButton) {
        if (input.getText().equals(answer.getText())) {          //Если значение в поле ввод совпадает с полем история
            this.input.setText("0");                            //Вывести в поле ввод ноль
        }

        if (!input.getText().equals(answer.getText()) && history.getText().indexOf('=') != -1) {     //Если значение в поле ввод не совпадает с полем история и в истории есть знак равно
            this.history.setText("0");                                                              //Вывести в поле история ноль
            this.answer.setText("0");                                                               //Вывести в поле ввод ноль
        }

        if (input.getText().charAt(0) == '0') {                    //Если значение в поле ввод равно нулю
            this.input.setText(currentButton.getText());           //Записать в поле ввод цифру нажатой кнопки
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
                break;

            case "\u2906":                                                  //Если нажата кнопка <- (стереть)
                if (input.getText().length() > 1) {                         //Если в поле ввод больше одного символа
                    text.append(input.getText()).                           //Считать в конструктор поле ввод
                            deleteCharAt(input.getText().length() - 1);     //Удалить последний символ
                    input.setText(text.toString());                         //Записать в поле ввод значение конструктора
                    text.setLength(0);                                      //Очистить конструктор
                } else {                                                    //Если в поле ввод один и меньше символов
                    input.setText("0");                                     //Записать в поле ввод ноль
                }
                break;

            case "=":                                               //Если нажата кнопка =
                if (history.getText().indexOf('=') != -1) {         //Если в поле история уже есть символы равно
                    history.setText(answer.getText());              //Записать в поле история информацию из поля ответ
                    input.setText("0");                             //Записать в поле ввод ноль
                    break;
                }
                if (history.getText() == "0" && input.getText() != null) {  //Если в поле история записан ноль, а поле ввод не пустое
                    history.setText(input.getText());                       //Записать в поле история значение из поля ввод
                    writeAnswer(String.valueOf(input.getText()));           //Записать в поле ответ значение из поля ввод
                    break;
                }

                if (checkSign(String.valueOf(history.getText().charAt(history.getText().length() - 2))) && input.getText() != null) {  //Если в поле история на последнем месте записан символ и поле ввод не пустое
                    char earlySgn = history.getText().charAt(history.getText().length() - 2);         //Записать последний знак в переменную
                    writeAnswer(String.valueOf(calculation(earlySgn, answer, input)));                //
                    text.append(history.getText()).
                            append(input.getText()).
                            append(" = ");
                    history.setText(text.toString());
                    input.setText(answer.getText());
                    text.setLength(0);
                    break;
                }

            default:
                inputMathSign(currentButton.getText().charAt(0));
                break;
        }
    }


    private void inputMathSign(char sgn) {
        if (history.getText() == "0") {
            text.append(input.getText()).append(" ").
                    append(sgn).
                    append(" ");
            history.setText(text.toString());
            writeAnswer(String.valueOf(input.getText()));
            text.setLength(0);
        } else {
            if (history.getText().charAt(history.getText().length() - 1) != ' ') {
                text.append(history.getText()).append(" ").
                        append(sgn).
                        append(" ");
                history.setText(text.toString());
                text.setLength(0);
            } else if (history.getText().indexOf('=') != -1) {
                text.append(answer.getText()).append(" ").
                        append(sgn).
                        append(" ");
                history.setText(text.toString());
                text.setLength(0);
            } else if (checkSign(String.valueOf(history.getText().charAt(history.getText().length() - 2))) && history.getText().charAt(history.getText().length() - 2) != sgn && (input.getText() == "0" || input.getText() == answer.getText())) {
                text.append(history.getText()).
                        delete(history.getText().length() - 3, history.getText().length()).append(" ").append(sgn).append(" ");
                history.setText(text.toString());
                text.setLength(0);
            } else {
                char earlySgn = history.getText().charAt(history.getText().length() - 2);
                writeAnswer(String.valueOf(calculation(earlySgn, answer, input)));
                text.append(history.getText()).
                        append(input.getText()).
                        append(" ").
                        append(sgn).
                        append(" ");
                history.setText(text.toString());
                input.setText(answer.getText());
                text.setLength(0);
            }
        }
    }

    private boolean checkSign(String sign) {
        for (int i = 0; i <= 9; i++) {
            if (sign.equals(String.valueOf(i))) return false;
        }
        return true;
    }

    private double calculation(char sgn, JLabel answer, JLabel input) {
        double ans = Double.parseDouble(answer.getText());
        double val = Double.parseDouble(input.getText());
        switch (sgn) {
            case '+':
                ans += val;
                break;
            case '-':
                ans -= val;
                break;
            case 'x':
                ans *= val;
                break;
            case '\u00F7':
                ans /= val;
                break;
        }
        return ans;
    }

    private void writeAnswer(String str) {
        char sgn;
        do {
            sgn = str.charAt(str.length() - 1);
            if (sgn == '0') {
                text.append(str).setLength(str.length() - 1);
                str = text.toString();
            }
        } while (sgn == '0');
        if (sgn == '.') {
            text.append(str).setLength(str.length() - 1);
            str = text.toString();
        }
        text.setLength(0);
        answer.setText(str);
    }
}