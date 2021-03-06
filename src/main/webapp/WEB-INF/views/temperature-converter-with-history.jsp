<!-- создание тэгов для spring-форм и jstl-->
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
    <form:form action="resultWithHistory" modelAttribute="inputWH" method="post">

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

    <!--  Кнопка сокрытия истории  -->
    <input type="button" value="Скрыть историю"
           onclick="window.location.href='input; return false'"
    />
    <!--  Кнопка очистки истории, по клику защита от дурака -->
    <input type="button" value="Очистить историю"
           onclick="if ((confirm('Удалить историю безвозвратно?')))window.location.href='clear'; return false" />

    <!-- Таблица истории. Вывод по циклу forEach -->
    <table>
        <tr>
            <th>Температура</th>
            <th> (°T) </th>
            <th>Результат</th>
            <th> (°T) </th>
        </tr>

        <c:forEach var="tempInput" items="${inputHistory}">

            <c:url var="deleteLink" value="/temperature/delete">
                <c:param name="inputId" value="${tempInput.id}"/>
            </c:url>

            <tr>
                <td> ${tempInput.value} </td>
                <td> ${tempInput.inUnit} </td>
                <td> ${tempInput.result} </td>
                <td> ${tempInput.outUnit} </td>
                <td><a href="${deleteLink}">Удалить</a></td>
            </tr>
        </c:forEach>

    </table>


</body>
</html>
