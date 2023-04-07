<%-- 
    Document   : viewCurriculum
    Created on : Apr 7, 2023, 9:40:39 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>JSP Page</title>
    </head>
    <body>

        <c:forEach items="${j}" var="">
            ${g.student.studentId}
            ${g.gradeValue}
        </c:forEach>
    </body>
</html>
