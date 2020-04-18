<!-- создание тэга для spring-форм -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Страница для ввода данных в spring форму
  Autor: Руслан Вахитов
  Date: 11.04.2020
  Time: 21:31
  Version: 0.1
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
    <form:form action="result" modelAttribute="input">

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

</body>
</html>
