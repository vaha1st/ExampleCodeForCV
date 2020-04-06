package com.vaha1st.temperature;

import com.vaha1st.temperature.storage_types.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInput implements Input {

    Scanner scanner = new Scanner(System.in);

    Storage storage;
    Conversion conversion;

    double value;
    TemperatureUnits inUnit;
    TemperatureUnits outUnit;
    double result;

    // переменная ввода с консоли
    String val;

    String incorrectInput = "Неправильный ввод, пожалуйста повторите попытку.";

    @Autowired
    private ConsoleInput(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void performConvert() {
        System.out.println("Добро пожаловать в конвертер температур.");

        while (true) {

            System.out.println("Нажмите \"Enter\" для запуска. Введите \"exit\" для выхода, " +
                    "\"history\" для вывода истории, \"clear\" для очистки истории:");
            val = scanner.nextLine();

            if (val.toLowerCase().equals("exit")) {             //Проверка на запрос выхода из программы.
                storage.exit();
            } else if (val.toLowerCase().equals("clear")) {     //Проверка на запрос очистки истории
                storage.clear();
                System.out.println("История очищена");
            } else if (val.toLowerCase().equals("history")) {    //Проверка на запрос вывода истории
                System.out.println("История конвертаций:");
                storage.history();
            } else if (val.isEmpty()) {                          //Если пользователь выбрал запуск конвертера
                System.out.println("Пожалуйста введите температуру для конвертации в порядке:");

                //Ввод значения температуры в текстовом формате, с последующей конвертацией в Double, для обработки
                //ошибок, связанных со стандартными форматами системы.
                System.out.print("ЗНАЧЕНИЕ ТЕМПЕРАТУРЫ(например 36,6): ");
                while (val.isEmpty()) {
                    try {
                        val = scanner.nextLine();
                        //Замена делиметра, в случае ошибки выбора между "," и "."
                        if (val.contains(",")) {
                            val = val.replace(',', '.');
                        }
                        this.value = Double.parseDouble(val);        //Конвертация в Double и запись ввода
                    } catch (NumberFormatException e) {              //Обработка ошибки, если введено не число
                        System.out.println(incorrectInput);
                        val = "";
                    }
                }

                //Ввод еденицы измерения конвертируемой температуры.
                System.out.print("ЕДЕНИЦА ИЗМЕРЕНИЯ (C, F, K, D, Ra, N): ");
                this.inUnit = readTempType();

                //Ввод еденицы измерения температуры, в которую будет произведена конвертация.
                System.out.print("ЖЕЛАЕМАЯ ТЕМПЕРАТУРА (C, F, K, D, Ra, N): ");
                this.outUnit = readTempType();

                conversion = new Conversion(value, inUnit, outUnit);
                result=conversion.convert();

                storage.write(value, inUnit, outUnit, result);

            } else {
                System.out.println(incorrectInput);
            }

        }

    }

    // Чтение еденицы измерения температуры с консоли
    private TemperatureUnits readTempType() {
        TemperatureUnits unit = null;
        //Сброс переменной ввода и проверка значения. Повтор запроса ввода, пока не удовлетворит параметрам или exit.
        val = "";
        while (val.isEmpty()) {
            val = scanner.nextLine().toUpperCase();
            switch (val) {
                case "C":
                    unit = TemperatureUnits.CELSIUS;
                    break;
                case "F":
                    unit = TemperatureUnits.FAHRENHEIT;
                    break;
                case "K":
                    unit = TemperatureUnits.KELVIN;
                    break;
                case "D":
                    unit = TemperatureUnits.DELISLE;
                    break;
                case "RA":
                    unit = TemperatureUnits.RANKINE;
                    break;
                case "N":
                    unit = TemperatureUnits.NEWTON;
                    break;
                default:
                    System.out.println(incorrectInput);
                    val = "";
            }
        }
        return unit;
    }

}
