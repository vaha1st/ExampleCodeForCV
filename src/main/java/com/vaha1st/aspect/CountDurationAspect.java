package com.vaha1st.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * {@code CountDurationAspect} аспект для подсчета времени выполнения операции конвертирования. Выводит название метода
 * и длительность выполнения на консоль.
 *
 * @author Руслан Вахитов
 * @version 1.0 15 May 2020
 */

@Aspect
@Component
public class CountDurationAspect {

    @Pointcut("execution(* com.vaha1st.controllers.*.processConversion*(..))")
    private void webConversion(){}

    @Around("webConversion()")
    public Object countDurationOfConversion(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // Отображение вызванного метода
        System.out.println("\n=====>> @Around advice для метода: " +
                proceedingJoinPoint.getSignature().toShortString()+"\n");

        // Старт выполнения
        long begin = System.currentTimeMillis();

        // Выполнение метода и обработка возможных ошибок
        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            System.err.println("Сбой в процессе конвертации! Проверьте настройки подключения к БД или запись в файл");
            throw e;
        }

        // Конец выполнения
        long finish = System.currentTimeMillis();

        System.out.println("\n=====>> Выполнение конвертации заняло "+ (finish-begin)/1000.0 +"сек\n");

        return result;
    }
}
