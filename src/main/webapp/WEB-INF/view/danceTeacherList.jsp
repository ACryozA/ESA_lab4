<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.model.DanceTeacher" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Dance Teachers</title>
    <style>
        table {
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #000;
        }
        th, td {
            padding: 6px 10px;
        }
        input[type="submit"] {
            padding: 8px 16px;
            font-size: 14px;
            cursor: pointer;
            border-radius: 4px;
        }
    </style>
</head>
<body>
<h1>The Dance Teachers page:</h1>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Age</th>
        <th>Phone</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<DanceTeacher> danceTeacherList =
                (List<DanceTeacher>) request.getAttribute("danceTeacherList");
        for (DanceTeacher teacher : danceTeacherList) {
    %>
    <tr>
        <td><%= teacher.getId() %></td>
        <td><%= teacher.getName() %></td>
        <td><%= teacher.getAge() %></td>
        <td><%= teacher.getPhone() %></td>
        <td>
            <a href="teachers?action=edit&id=<%= teacher.getId() %>">Edit</a> |
            <a href="teachers?action=delete&id=<%= teacher.getId() %>"
               onclick="return confirm('Delete this teacher?');">Delete</a>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<form action="teachers" method="post">
    <input type="hidden" name="id"
           value="<c:out value='${editTeacher.id}' />"/>

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required
           value="<c:out value='${editTeacher.name}' />"><br><br>

    <label for="age">Age:</label>
    <input type="text" id="age" name="age" required
           value="<c:out value='${editTeacher.age}' />"><br><br>

    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" required
           value="<c:out value='${editTeacher.phone}' />"><br><br>

    <input type="submit"
           value="<c:choose>
                     <c:when test='${not empty editTeacher}'>Update Teacher</c:when>
                     <c:otherwise>Add Teacher</c:otherwise>
                  </c:choose>">
</form>

<a href="/">Main page</a>
</body>
</html>
