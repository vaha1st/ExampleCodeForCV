<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 11.04.2020
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Temperature Converter</title>
</head>
<body>

    <form:form action="result" modelAttribute="input">

        <form:input path="value" />
            <title> </title>
        <form:select path="inUnit">
            <form:options items="${temperatureUnits}" itemLabel="unit"/>
        </form:select>
            <title>      </title>
        <form:select path="outUnit">
            <form:options items="${temperatureUnits}" itemLabel="unit"/>
        </form:select>
        <form:input path="result"/>


        <input type="submit" value="Submit" />

    </form:form>

</body>
</html>
