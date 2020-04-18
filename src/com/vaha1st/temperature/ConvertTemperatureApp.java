/**
 * Конвертер температур. Доступные еденицы измерения: ˚Цельсия, ˚Фаренгейта, ˚Кельвин, ˚Делиля, ˚Ранкина, ˚Ньютона.
 * Цель: упражнение кодинга и оформления кода. Использованы Spring IoC и DI, подключение к Oracle SQL БД.
 * <p>
 * <p>
 * Авторские права. У автора есть права.
 *
 * @author Руслан Вахитов
 * @version 1.0 7 Apr 2020
 */

package com.vaha1st.temperature;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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