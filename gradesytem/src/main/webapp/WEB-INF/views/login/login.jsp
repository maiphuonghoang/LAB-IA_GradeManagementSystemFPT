<%-- 
    Document   : login
    Created on : Apr 6, 2023, 11:38:18 AM
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
            <h1>test</h1>
            ${students.size()}
            <c:forEach items="${students}" var="s">
                ${s.studentId}
            </c:forEach>
        </body>
    </html>
