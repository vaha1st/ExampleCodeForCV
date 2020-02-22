/**
 * Конвертер температур. Доступные еденицы измерения: ˚Цельсия, ˚Фаренгейта, ˚Кельвин, ˚Делиля, ˚Ранкина, ˚Ньютона.
 * Цель: упражнение кодинга и оформления кода.
 * <p>
 * <p>
 * Авторские права. У автора есть права.
 *
 * @author Руслан Вахитов
 * @version 0.9 12 Feb 2020
 */

package com.vaha1st.temperature;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class ConvertTemperature {
    //Создание объекта входных данных
    private static Input input = new Input();
    //Создание пустых SQL подключения, сообщения и результатов запроса.
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public static void main(String[] args) {

//        convert(new Input(557.55, TemperatureUnits.RANKINE, TemperatureUnits.CELSIUS));

//        convertManualInput();

//        convertAndStoreSessionHistory();

        convertWithHistoryInSQL();      //Что бы метод работал, необходимо подключиться к своей БД с правами
                                        //на создание таблицы. Измените идентификаторы БД в полях тела метода.

    }

    /**
     * {@code convert} метод выполняет конвертацию и вывод на консоль
     *
     * @return возвращает значение температуры после конвертации.
     */
    private static double convert(Input input) {
        input.getInUnit().getTemperature().process(input.getOutUnit().getId(), input.getValue());
        System.out.println(input.getValue() + "" + input.getInUnit().getUnit() + " " +
                input.getInUnit().getTemperature() + "" + input.getOutUnit().getUnit());
        return input.getInUnit().getTemperature().getTemperature();
    }

    /**
     * {@code convertManualInput} метод реализует конвертацию температуры, введенной с консоли.
     *
     * @return возвращает объект Runs с входными данными и результатом конвертации.
     */
    protected static Runs convertManualInput() {
        Scanner scanner = new Scanner(System.in);
        boolean isSQL = input.command == 10;                    //случай sql
        boolean fromBeginning = true;                           //по этой булевой цикл будет завершаться
        String val;                                             //переменная ввода с консоли

        String incorrectInput = "Неправильный ввод, пожалуйста повторите попытку.";

        System.out.println("Добро пожаловать в конвертер температур.");
        //Для метода, работающего с sql {convertWithHistoryInSQL()} реализованы расширенные команды консоли.
        //В свою очередь этот метод перед выполнением функционального кода присваивает input.command = 10.
        //Проверяется какой медот ввода с консоли используется.
        if (isSQL) {
            System.out.println("Нажмите \"Enter\" для запуска. Введите \"exit\" для выхода, " +
                    "\"history\" для вывода истории, \"clear\" для очистки истории:");
        } else {
            System.out.println("Нажмите \"Enter\" для запуска. Введите \"exit\" для выхода");
        }
        while (fromBeginning) {
            val = scanner.nextLine();
            if (val.toLowerCase().equals("exit")) {             //Проверка на запрос выхода из программы. Два варианта
                if (isSQL) {                                    //исполнения. Для sql метода и для простого.

                    //Переход в начало метода SQL, где реализовано освобождения ресурсов перед выходом
                    input.command = 1;
                    convertWithHistoryInSQL();

                } else System.exit(0);

            } else if (val.toLowerCase().equals("clear")) {     //Проверка на запрос очистки истории

                //Переход в начало метода SQL, где реализован запрос в БД на очистку таблицы.
                input.command = 2;
                System.out.println("История очищена");
                convertWithHistoryInSQL();

            } else if (val.toLowerCase().equals("history")) {    //Проверка на запрос вывода истории

                //Переход в начало метода SQL, где реализован запрос в БД на вывод всех данных таблицы.
                input.command = 3;
                convertWithHistoryInSQL();

            } else if (val.isEmpty()) {                         //Если пользователь выбрал запуск конвертера
                fromBeginning = false;                          //Корректный ввод, не повторять цикл ввода
                System.out.println("Пожалуйста введите температуру для конвертации в порядке:");

                //Ввод значения температуры в текстовом формате, с последующей конвертацией в Double, для обработки
                //ошибок, связанных со стандартными форматами системы.
                System.out.print("ЗНАЧЕНИЕ ТЕМПЕРАТУРЫ(например 36,6): ");
                while (val.isEmpty()) {
                    try {
                        val = scanner.nextLine();
                        if (val.contains(",")) {              //Замена делиметра, в случае ошибки выбора между "," и "."
                            val = val.replace(',', '.');
                        }
                        input.value = Double.parseDouble(val);       //Конвертация в Double и запись ввода
                    } catch (NumberFormatException e) {              //Обработка ошибки, если введено не число
                        System.out.println(incorrectInput);
                        val = "";
                    }
                }

                //Ввод еденицы измерения конвертируемой температуры.
                System.out.print("ЕДЕНИЦА ИЗМЕРЕНИЯ (C, F, K, D, Ra, N): ");
                val = "";                                           //Сброс переменной ввода и проверка значения. Повтор
                while (val.isEmpty()) {                             //запроса ввода, пока не удовлетворит параметрам или exit.
                    val = scanner.nextLine().toUpperCase();
                    switch (val) {
                        case "C":
                            input.inUnit = TemperatureUnits.CELSIUS;
                            break;
                        case "F":
                            input.inUnit = TemperatureUnits.FAHRENHEIT;
                            break;
                        case "K":
                            input.inUnit = TemperatureUnits.KELVIN;
                            break;
                        case "D":
                            input.inUnit = TemperatureUnits.DELISLE;
                            break;
                        case "RA":
                            input.inUnit = TemperatureUnits.RANKINE;
                            break;
                        case "N":
                            input.inUnit = TemperatureUnits.NEWTON;
                            break;
                        default:
                            System.out.println(incorrectInput);
                            val = "";
                            break;
                    }
                }


                //Ввод еденицы измерения температуры, в которую будет произведена конвертация.
                System.out.print("ЖЕЛАЕМАЯ ТЕМПЕРАТУРА (C, F, K, D, Ra, N): ");
                val = "";
                while (val.isEmpty()) {
                    val = scanner.nextLine().toUpperCase();
                    switch (val) {
                        case "C":
                            input.outUnit = TemperatureUnits.CELSIUS;
                            break;
                        case "F":
                            input.outUnit = TemperatureUnits.FAHRENHEIT;
                            break;
                        case "K":
                            input.outUnit = TemperatureUnits.KELVIN;
                            break;
                        case "D":
                            input.outUnit = TemperatureUnits.DELISLE;
                            break;
                        case "RA":
                            input.outUnit = TemperatureUnits.RANKINE;
                            break;
                        case "N":
                            input.outUnit = TemperatureUnits.NEWTON;
                            break;
                        default:
                            System.out.println(incorrectInput);
                            val = "";
                    }
                }
            } else {
                System.out.println(incorrectInput);
            }

        }
        return new Runs(input, convert(input));  //Вызов метода, реализующего конвертацию и создание возвращаемого рана
    }

    /**
     * {@code convertAndStoreSessionHistory} метод реализует конвертацию температуры, введенной с консоли и сохраняет
     * историю в хэшмапу. Вывод истории после каждого рана.
     */
    private static void convertAndStoreSessionHistory() {
        int id = 0;
        Map<Integer, Runs> history = new HashMap<>();

        while (true) {
            history.put(++id, convertManualInput());
            System.out.println(Arrays.asList(history));
        }
    }

    /**
     * {@code convertWithHistoryInSQL} метод реализует конвертацию температуры, введенной с консоли и сохраняет
     * историю в Oracle SQL базу данных. Создается новая sql таблица "history", последовательность "history_id",
     * если таковых не имеется.
     * Вывод истории после каждого рана.
     *
     * Метод создавался для демонстрации знаний работы с SQL. Что бы метод работал, необходимо подключиться к своей БД
     * с правами на создание таблицы.
     */
    private static void convertWithHistoryInSQL() {
        boolean exit = input.command == 1;                  //случай выхода
        boolean clear = input.command == 2;                 //случай очистки
        boolean history = input.command == 3;               //случай вызова истории

        //Для работоспособности метода в вашей системе, необходимо изменить эти 4 переменных на соответствующие.
        final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
        final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
        final String DB_USER = "admin";
        final String DB_PASSWORD = "admin";


        //Переменная рана, в которую будут сохранятся данные каждой проходки конвертера и откуда они будут извлекаться
        //при сохранении в БД
        Runs run;

        //Если был вызван выход, очищаются ресурсы и завершается программа.
        if (exit) {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            input.command = 0;
            System.exit(0);
        }
            try {
                //Регистрация драйвера
                Class.forName(DB_DRIVER);

                //Подключение к локальной БД Oracle Database 18c Express Edition
                connection = getConnection(DB_URL, DB_USER, DB_PASSWORD);

                //Переменная для запросов SQL
                statement = connection.createStatement();
                try {
                    //Обработка случаев очистки истории и вызова истории
                    if (clear) {
                        statement.executeUpdate("TRUNCATE TABLE history");   //Очистка таблицы с историей по запросу
                        statement.executeUpdate("DROP SEQUENCE history_id"); //Сброс последовательности
                    } else if (history) {
                        System.out.println("Сохраненная история:");
                        resultSet = statement.executeQuery("SELECT id, to_char(input_date, 'dd.mm.yy hh:mi') as " +
                                "date_and_time, input_value, input_unit, output_value, output_unit FROM HISTORY" +
                                " order by id");
                        System.out.println("__________________________________________________________________________________________________");
                        System.out.println("|ID |      Time      |      Input Value      | Input Unit |      Output Value      | Output Unit |");
                        System.out.println("|___|________________|_______________________|____________|________________________|_____________|");
                        while (resultSet.next()) {
                            int tableID = resultSet.getInt(1);
                            String dateTime = resultSet.getString(2);
                            double inVal = resultSet.getDouble(3);
                            String inUnit = resultSet.getString(4);
                            double outVal = resultSet.getDouble(5);
                            String outUnit = resultSet.getString(6);
                            System.out.printf("|%3d|%16s|%23.3f|%12s|%24.3f|%13s|%n",
                                    tableID, dateTime, inVal, inUnit, outVal, outUnit);
                        }
                        System.out.println("__________________________________________________________________________________________________");
                    } else {
                        //Запрос в БД на создание таблицы
                        statement.executeUpdate("CREATE TABLE history" +
                                "(id number primary key," +
                                "input_date date," +
                                "input_value number(29,9)," +
                                "input_unit varchar2(4)," +
                                "output_value number(29,9)," +
                                "output_unit varchar2(4))");
                    }
                    //Запрос в БД на создание стандартной последовательности
                    statement.executeUpdate("CREATE SEQUENCE history_id");

                    //Обработка ошибок синтаксиса SQL запроса.
                } catch (SQLSyntaxErrorException e) {
                    if (e.getMessage().contains("ORA-00955")) {                 //Игнорировать "таблица уже существует"
                    } else {
                        e.printStackTrace();                                    //Вывод других ошибок
                    }
                } finally {
                    // Цикл прервется либо завершением программы, либо вводом "exit" пользователем
                    while (true) {
                        //Сообщение методу convertManualInput() что выбран вариант его исполнения с БД SQL.
                        input.command = 10;
                        //Конвертация и сохранение в переменную.
                        run = convertManualInput();
                        //Запрос в БД на запись в таблицу history строки данных текущей конвертации
                        statement.executeUpdate("INSERT INTO history values " +
                                "(history_id.nextval, SYSDATE, " + run.input.value + ", '" +
                                run.input.getInUnit().getUnit() + "', " +
                                run.result + ", '" + run.input.outUnit.getUnit() + "')");
                        //Принудительное сохранение сессии.
                        statement.executeUpdate("COMMIT");

                    }

                }
            } catch (ClassNotFoundException e) {
                System.out.println("Драйвер не найден");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Не удается подключиться к базе данных");
                e.printStackTrace();
            } finally {
                // Освобождение ресурсов, если заняты.
                try {
                    if (resultSet != null)
                        resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    if (statement != null)
                        statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }


    /**
     * класс {@code Input} создан для удобства реализации ввода данных.
     *
     * @author Руслан Вахитов
     * @version 0.01 26 Oct 2019
     */
    static class Input {

        /**
         * Температура на входе
         */
        double value;

        /**
         * Тип температуры на входе (from)
         */
        TemperatureUnits inUnit;

        /**
         * Тип температуры на выходе (to)
         */
        TemperatureUnits outUnit;

        /**
         * Служебная переменная, для распознавания дополнительных комманд с консоли. Приняты ключевые значения:
         * 1 - exit. Сообщает выполняемому методу что небходимо завершить программу;
         * 2 - clear. Сообщает выполняемому методу что необходимо очистить историю;
         * 3 - history. Сообщает выполняемому методу что необходимо вывести историю на консоль;
         * 10 - SQL identifier. Сообщает выполняемому методу что выбран расширенный список комманд применимый к методу
         * {@code convertWithHistoryInSQL}.
         */
        int command;

        public double getValue() {
            return value;
        }

        public TemperatureUnits getInUnit() {
            return inUnit;
        }

        public TemperatureUnits getOutUnit() {
            return outUnit;
        }

        public Input(double in, TemperatureUnits inUnit, TemperatureUnits outUnit) {
            this.value = in;
            this.inUnit = inUnit;
            this.outUnit = outUnit;
        }

        public Input() {}
    }
}
