package com.vaha1st.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * {@code MyLoggingAspect} аспект для логирования протекания web программы. Простой вывод имен методов и их аргументов.
 *
 * @author Руслан Вахитов
 * @version 1.0 14 May 2020
 */

@Aspect
@Component
public class MyLoggingAspect {

    // Объявление срезов для каждого пакета протекания web программы

    @Pointcut("execution(* com.vaha1st.controllers.*.*(..))")
    private void forController() {
    }

    @Pointcut("execution(* com.vaha1st.service.*.*(..))")
    private void forService() {
    }

    @Pointcut("execution(* com.vaha1st.dao.*.*(..))")
    private void forDao() {
    }

    // Объявление среза для комбинаци этих пакетов
    @Pointcut("forService() || forDao() || forController()")
    private void forAppFlow() {
    }


    // @Before и @AfterReturning для логирования процесса протекания программы
    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint) {

        // Отображение вызванного метода
        System.out.println("=====>> @Before | вызван метод: " + theJoinPoint.getSignature().toShortString());

        // Отображение аргументов метода
        Object[] args = theJoinPoint.getArgs();
        for (Object o : args) {
            System.out.println("=====>> аргументы: " + o.toString());
        }

    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result")
    public void afterReturning(JoinPoint theJoinPoint, Object result) {

        // Отображение вызванного метода
        System.out.println("====> @AfterReturning | вызван метод: " + theJoinPoint.getSignature().toShortString());

        // Отображение возвращаемого
        System.out.println("====> результат: " + result);
    }
}
