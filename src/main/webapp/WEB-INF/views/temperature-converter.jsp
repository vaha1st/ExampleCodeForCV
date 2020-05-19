<!-- создание тэга для spring-форм -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Страница для ввода данных в spring форму
  Autor: Руслан Вахитов
  Date: 04.05.2020
  Version: 0.2
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Temperature Converter</title>

    <style>
        .error {color:red}
    </style>
</head>
<body>

    <!-- Форма для ввода -->
    <form:form action="result" modelAttribute="input" method="post">

        <!-- Ввод значения температуры бина -->
        <form:input path="value" />
            <title> </title>
        <!-- Выбор едениц измерения из значений ENUM и присвоение соответствующим параметрам бина-->
        <form:select path="inUnit">
            <form:options items="${temperatureUnits}" itemLabel="unit"/>
        </form:select>
            <title>      </title>
        <form:select path="outUnit">
            <form:options items="${temperatureUnits}" itemLabel="unit"/>
        </form:select>

        <!-- Отображение результата -->
        <form:input path="result"/>

        <!-- Кнопка "подтвердить" -->
        <input type="submit" value="Конвертировать" />
        <!-- Вывод ошибок ввода -->
        <form:errors path="value" cssClass="error"/>

    </form:form>

    <!-- Кнопка для отображения истории -->
    <input type="button" value="Показать историю"
           onclick="window.location.href='input-with-history; return false'"
    />


</body>
</html>
