

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>

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
                    <th>Subject Code</th>
                    <th>Subject Name</th>
                    <th>Credit</th>
                    <th>Term</th>
                    <th>Semester</th>
                    <th>Gpa</th>
                    <th>Status</th>
                </tr>
            </thead>

            <tbody>
                <c:set var="index" value="${0}" />
                <c:forEach items="${hm}" var="entry" varStatus="loop">
                    <tr>
                        <td><c:set var="index" value="${index+1}" /><c:out value="${index}"/></td>
                        <c:set var="values" value="${entry.value}" />
                        <c:forEach items="${values}" var="value">
                            <td><c:out value="${value}" /></td>
                        </c:forEach>
                    </tr>
                </c:forEach>
                    <c:set var="courseIndex" value="${index}" />
                <c:forEach items="${courses}" var="c">
                    <tr>
                        <td><c:set var="courseIndex" value="${courseIndex+1}" /><c:out value="${courseIndex}"/></td>
                        <td>${c.courseId}</td>
                        <td>${c.courseName}</td>
                        <td>${c.noCredit}</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>Not started</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
