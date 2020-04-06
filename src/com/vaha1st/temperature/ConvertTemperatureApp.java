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

import com.vaha1st.temperature.storage_types.Storage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import static java.sql.DriverManager.getConnection;

public class ConvertTemperatureApp {

    public static void main(String[] args) {

        // Создание spring контейнера с помощью java конфигурационного класса
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        // Создание бинов
        Input simpleInput = context.getBean("simpleValuesInput", Input.class);
        Input consoleInput = context.getBean("consoleInput", Input.class);

        // Тест конвертера разными типами ввода данных
        simpleInput.performConvert();
        consoleInput.performConvert();


//        convertWithHistoryInSQL();      //Что бы метод работал, необходимо подключиться к своей БД с правами
                                        //на создание таблицы. Измените идентификаторы БД в полях тела метода.

    }

/*
    private static void convertWithHistoryInSQL() {
        boolean exit = input.command == 1;                  //случай выхода
        boolean clear = input.command == 2;                 //случай очистки
        boolean history = input.command == 3;               //случай вызова истории







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
    }*/

}
