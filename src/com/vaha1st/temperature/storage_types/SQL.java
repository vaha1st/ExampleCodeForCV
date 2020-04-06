package com.vaha1st.temperature.storage_types;

import com.vaha1st.temperature.TemperatureUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

//@Component
public class SQL implements Storage {

    // Для подключения к БД в вашей системе, необходимо изменить эти 4 переменных на соответствующие.
    final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    final String DB_USER = "admin";
    final String DB_PASSWORD = "admin";

    // Создание SQL подключения, сообщения и результатов запроса.
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public SQL() {
        try {
            // Регистрация драйвера
            Class.forName(DB_DRIVER);
            // Подключение к локальной БД Oracle Database 18c Express Edition
            this.connection = getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Переменная для запросов SQL
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер не найден");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Не удается подключиться к базе данных. Проверьте параметры подключения в SQL.java");
        }
    }


    @Override
    public void write(double value, TemperatureUnits inUnit, TemperatureUnits outUnit, double result) {

        try {
            // Запрос в БД на создание таблицы
            statement.executeUpdate("CREATE TABLE history" +
                    "(id number primary key," +
                    "input_date date," +
                    "input_value number(29,9)," +
                    "input_unit varchar2(4)," +
                    "output_value number(29,9)," +
                    "output_unit varchar2(4))");

            // Запрос в БД на создание стандартной последовательности
            statement.executeUpdate("CREATE SEQUENCE history_id");

            // Обработка ошибок синтаксиса SQL запроса.
        } catch (SQLSyntaxErrorException e) {
            // Игнорировать "таблица или последовательность уже существует"
            if (e.getMessage().contains("ORA-00955")) {
            } else {
                e.printStackTrace();                                    // Вывод других ошибок
            }
        } catch (SQLException e) {
            System.out.println("Не удается подключиться к базе данных");
            e.printStackTrace();
        } finally {
            try {
                // Запрос в БД на запись в таблицу history строки данных текущей конвертации
                statement.executeUpdate("INSERT INTO history values " +
                        "(history_id.nextval, SYSDATE, " + value + ", '" +
                        inUnit.getUnit() + "', " +
                        result + ", '" + outUnit.getUnit() + "')");
                // Принудительное сохранение сессии.
                statement.executeUpdate("COMMIT");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void exit() {
        //Если был вызван выход, очищаются ресурсы и завершается программа.
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
        System.exit(0);
    }

    @Override
    public void clear() {
        try {
            // Очистка таблицы с историей по запросу
            statement.executeUpdate("TRUNCATE TABLE history");
            // Сброс последовательности
            statement.executeUpdate("DROP SEQUENCE history_id");
            // Создание "чистой" последовательности
            statement.executeUpdate("CREATE SEQUENCE history_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void history() {
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
