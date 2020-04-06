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
        // Для этого бина необходимо указать какую именно зависимость использовать (@Qualifier).
        Input consoleInput = context.getBean("consoleInput", Input.class);

        // Тест конвертера разными типами ввода данных
//        simpleInput.performConvert();
        consoleInput.performConvert();

    }
}