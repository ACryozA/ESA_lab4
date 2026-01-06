<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="com.example.demo.model.DanceLesson" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Dance Lessons</title>
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
<h1><%= "The Dance Lessons page:" %></h1>
<table>
   <thead>
<tr>
    <th>ID</th>
    <th>Style</th>
    <th>Level</th>
    <th>Schedule</th>
    <th>Teacher</th>
    <th>Actions</th>
</tr>
</thead>
<tbody>
<%
    List<DanceLesson> lessonsList =
            (List<DanceLesson>) request.getAttribute("danceLessonList");
    for (DanceLesson danceLesson : lessonsList) {
%>
<tr>
    <td><%= danceLesson.getId() %></td>
    <td><%= danceLesson.getStyle() %></td>
    <td><%= danceLesson.getLevel() %></td>
    <td><%= danceLesson.getSchedule() %></td>
    <td><%= danceLesson.getTeacher().getId() %></td>
    <td>
        <a href="lessons?action=edit&id=<%= danceLesson.getId() %>">Edit</a> |
        <a href="lessons?action=delete&id=<%= danceLesson.getId() %>"
           onclick="return confirm('Delete this lesson?');">Delete</a>
    </td>
</tr>
<%
    }
%>
</tbody>

</table>

<form action="lessons" method="post">
    <input type="hidden" name="id"
           value="<c:out value='${editLesson.id}' />"/>

    <label for="style">Style:</label>
    <input type="text" id="style" name="style" required
           value="<c:out value='${editLesson.style}' />"><br><br>

    <label for="level">Level:</label>
    <input type="text" id="level" name="level" required
           value="<c:out value='${editLesson.level}' />"><br><br>

    <label for="schedule">Schedule:</label>
    <input type="text" id="schedule" name="schedule" required
           value="<c:out value='${editLesson.schedule}' />"><br><br>

    <label for="teacherId">Teacher:</label>
    <select id="teacherId" name="teacherId" required>
        <option value="">-- Select Teacher --</option>
        <c:forEach var="teacher" items="${teachers}">
            <option value="${teacher.id}"
                <c:if test="${not empty editLesson && editLesson.teacher.id == teacher.id}">
                    selected
                </c:if>>
                ${teacher.id} - ${teacher.name} (${teacher.age} y.o.)
            </option>
        </c:forEach>
        <c:if test="${empty teachers}">
            <option value="">No teachers available</option>
        </c:if>
    </select><br><br>

    <input type="submit"
           value="<c:choose>
                     <c:when test='${not empty editLesson}'>Update Dance Lesson</c:when>
                     <c:otherwise>Add Dance Lesson</c:otherwise>
                  </c:choose>">
</form>


<a href="/">Main page</a>
</body>
</html>