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
    <style>
        table, td, th {
            border: 1px solid black;
        }

        table {
            border-collapse: collapse;
        }

    </style>
    <body>


        <table>
            <thead>
                <tr>
                    <th>STT</th>
                    <th>SubjectCode</th>
                    <th>SubjectName</th>
                    <th>TermNo</th>
                </tr>
            </thead>
            <tbody>
                ${curriculum.curriculumId} ${curriculum.curriculumName} 
                <c:forEach items="${courses}" var="c" varStatus="index">
                    <tr>
                        <td>${index.index+1}</td>
                        <td>${c.courseId}</td>
                        <td>${c.courseName}</td>
                        <td>${c.termNo}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
