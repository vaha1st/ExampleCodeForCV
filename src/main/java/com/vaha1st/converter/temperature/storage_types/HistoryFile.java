package com.vaha1st.converter.temperature.storage_types;

import com.vaha1st.converter.temperature.TemperatureUnits;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * {@code HistoryFile} реализует создает текстовый файл истории. Осуществляет работу с файлом,
 * описанную в методах интерфейса Storage.
 * Является синглтон-компонентом Spring.
 *
 * @author Руслан Вахитов
 * @version 1.00 7 Apr 2020
 */
@Component
public class HistoryFile implements Storage {

    // Строковое представление пути к файлу ~/src/ConversionHistory.txt
    private final String path = new File("").getAbsolutePath() + "/src/ConversionHistory.txt";
    // Переменные для записи/чтения файла
    private BufferedWriter writer;
    private BufferedReader reader;
    private File history = new File(path);


    @Override
    public void write(double value, TemperatureUnits inUnit, TemperatureUnits outUnit, double result) {

        // Задается формат даты/времени
        SimpleDateFormat dateFormat = new SimpleDateFormat("E yyyy.MM.dd hh:mm:ss a zzz");

        // Создается файл ConversionHistory.txt если еще не существует
        try {
            if (!history.exists()) {
                history.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {

            // Запись в файл даты и данных конвертации и переход на новую строку.
            try {
                writer = new BufferedWriter(new FileWriter(path, true));

                writer.append(dateFormat.format(new Date()) + ": " + value + "" + inUnit.getUnit() +
                        " = " + result + "" + outUnit.getUnit());
                writer.append("\n");

                writer.close();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void exit() {
        try {
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }

    }

    @Override
    public void clear() {
        // Если файл существует, производится его очистка
        if (history.exists()) {
            try {
                writer = new BufferedWriter(new FileWriter(path));
                writer.write("");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else System.out.println("Файл истории не найден");
    }

    @Override
    public void history() {
        // Если файл существует, производится вывод сожержимого построчно
        if (history.exists()) {
            try {
                reader = new BufferedReader(new FileReader(path));
                String tempLine;

                while ((tempLine = reader.readLine()) != null) {
                    System.out.println(tempLine);
                }
                reader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
