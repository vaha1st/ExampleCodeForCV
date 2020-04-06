package com.vaha1st.temperature.storage_types;

import com.vaha1st.temperature.TemperatureUnits;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class HistoryFile implements Storage {

    // Строка пути к файлу ~/src/ConversionHistory.txt
    private final String path = new File("").getAbsolutePath()+"/src/ConversionHistory.txt";
    // Переменные для записи/чтения файла
    private BufferedWriter writer;
    private BufferedReader reader;
    private File history = new File(path);


    @Override
    public void write(double value, TemperatureUnits inUnit, TemperatureUnits outUnit, double result) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("E yyyy.MM.dd hh:mm:ss a zzz");

        try {
            if (!history.exists()) {
                history.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
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
        if (history.exists()) {
            try {
                writer = new BufferedWriter(new FileWriter(path));
                writer.write("");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else System.out.println("History file not found");
    }

    @Override
    public void history() {
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
