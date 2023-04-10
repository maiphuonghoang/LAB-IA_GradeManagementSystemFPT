

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>JSP Page</title>
    </head>

    <body>
        <table>
            <thead>

                <tr>
                    <th>No</th>
                    <th>courseId</th>
                    <th>courseName</th>
                    <th>GroupName</th>
                    <th>Semester</th>
                    <th>StartDate</th>
                    <th>EndDate</th>
                    <th>Average Mark</th>
                    <th>Status</th>

                </tr>
            </thead>
            <c:forEach items="${curriculums[0].courses}" var="c" varStatus="index">
                <tr>
                    <td>${index.index}</td>
                    <td>${c.courseId}</td>
                    <td>${c.courseName}</td>
                    <c:forEach items="${c.groups}" var="g">
                        <td>${g.course.courseId eq c.courseId?g.groupName:""}</td>
                    </c:forEach>

                </tr>
            </c:forEach>
        </table>
    </body>
</html>
