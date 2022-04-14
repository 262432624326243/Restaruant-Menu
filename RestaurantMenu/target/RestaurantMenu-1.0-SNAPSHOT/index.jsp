<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Restaurant Menu" %>
</h1>
<a href="hello-servlet"><h3>Hello Servlet</h3></a>
<a href="addDishToTable.html"><h3>Add dish to menu</h3></a>
<a href="viewMenu.html"><h3>View dishes</h3></a>
</body>
</html>
<%--Создать таблицу «Меню в ресторане». Колонки: название блюда,--%>
<%--его стоимость, вес, наличие скидки. Написать код для--%>
<%--добавления записей в таблицу и их выборки по критериям--%>
<%--«стоимость от-до», «только со скидкой», выбрать набор блюд--%>
<%--так, чтобы их суммарный вес был не более 1 КГ--%>