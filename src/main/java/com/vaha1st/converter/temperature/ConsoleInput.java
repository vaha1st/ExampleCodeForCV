package com.vaha1st.converter.temperature;

import com.vaha1st.converter.temperature.storage_types.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * {@code ConsoleInput} осуществляет прием входных данных с консоли. Пользователь может запросить историю конвертаций,
 * очистку истории, выход из программы. Является синглтон-компонентом Spring.
 *
 * @author Руслан Вахитов
 * @version 1.00 7 Apr 2020
 */
@Component
public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    // Переменная типа хранилища данных, куда будет осуществляться запись истории
    private Storage storage;
    // Переменная класса, осуществляющего конвертацию
    private Conversion conversion;

    // Температура на входе
    private double value;
    // Тип температуры на входе (from)
    private TemperatureUnits inUnit;
    // Тип температуры на выходе (to)
    private TemperatureUnits outUnit;
    // Хранение результата
    private double result;

    // переменная ввода с консоли
    String val;

    String incorrectInput = "Неправильный ввод, пожалуйста повторите попытку.";

    // Внедерние зависимости типа хранилища данных. Для изменения необходимо указать нужный бин в @Qualifier
    @Autowired
    private ConsoleInput(@Qualifier("historyFile") Storage storage) {
        this.storage = storage;
    }

    /**
     * {@code performConvert} метод взаимодействия пользователя с консолью и конвертации полученных данных,
     * с последующим сохранением истории. Конвертация и вывод на консоль путем вызова класса Conversion и
     * подстановки входных параметров.
     */
    @Override
    public void performConvert() {
        System.out.println("Добро пожаловать в конвертер температур.");

        // Повторять запрос конвертации у пользователя, пока не будет вызвана команда "exit"
        while (true) {

            System.out.println("Нажмите \"Enter\" для запуска. Введите \"exit\" для выхода, " +
                    "\"history\" для вывода истории, \"clear\" для очистки истории:");
            // Переменная примет только один из вышеуказанных вариантов ввода, иначе повтор цикла
            val = scanner.nextLine();

            if (val.toLowerCase().equals("exit")) {             // Проверка на запрос выхода из программы
                storage.exit();
            } else if (val.toLowerCase().equals("clear")) {     // Проверка на запрос очистки истории
                storage.clear();
                System.out.println("История очищена");
            } else if (val.toLowerCase().equals("history")) {    // Проверка на запрос вывода истории
                System.out.println("История конвертаций:");
                storage.history();
            } else if (val.isEmpty()) {                          // Если пользователь выбрал запуск конвертера
                System.out.println("Пожалуйста введите температуру для конвертации в порядке:");

                // Ввод значения температуры в текстовом формате, с последующей конвертацией в Double, для обработки
                // ошибок, связанных с плавающей запятой и со стандартными форматами системы.
                System.out.print("ЗНАЧЕНИЕ ТЕМПЕРАТУРЫ(например 36,6): ");

                // Цикл повторяется пока не будет введен правильный формат значения
                while (val.isEmpty()) {
                    try {
                        val = scanner.nextLine();
                        // Замена делиметра, в случае ошибки выбора между "," и "."
                        if (val.contains(",")) {
                            val = val.replace(',', '.');
                        }
                        this.value = Double.parseDouble(val);        //Конвертация в Double и запись ввода
                    } catch (NumberFormatException e) {              //Обработка ошибки, если введено не число
                        System.out.println(incorrectInput);
                        val = "";
                    }
                }

                // Ввод еденицы измерения конвертируемой температуры.
                System.out.print("ЕДЕНИЦА ИЗМЕРЕНИЯ (C, F, K, D, Ra, N): ");
                this.inUnit = readTempType();

                // Ввод еденицы измерения температуры, в которую будет произведена конвертация.
                System.out.print("ЖЕЛАЕМАЯ ТЕМПЕРАТУРА (C, F, K, D, Ra, N): ");
                this.outUnit = readTempType();

                // Инициализация класса конвертации полученными входными данными
                conversion = new Conversion(value, inUnit, outUnit);
                // Конвертация и запись результата в переменную
                result = conversion.convert();
                // Запись в хранилище истории
                storage.write(value, inUnit, outUnit, result);

            } else {
                System.out.println(incorrectInput);
            }
        }
    }

    // Чтение еденицы измерения температуры с консоли
    private TemperatureUnits readTempType() {
        TemperatureUnits unit = null;
        // Сброс переменной ввода и проверка значения. Повтор запроса ввода, пока не удовлетворит параметрам.
        val = "";
        // Цикл повторяется пока не будет введен правильный формат значения
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
