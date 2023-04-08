

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>




<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>JSP Page</title>
    </head>

    <body>
        <style>
        </style>
        <div>
            <ul>
                <c:forEach items="${semesters}" var="s" varStatus="index">
                    <li> <a href="gradeReport?semesterId=${s.semesterId}">${s.semesterName}</a></li>
                    </c:forEach> 
            </ul>
            <ul>
                <c:forEach items="${courses}" var="c" varStatus="index">
                    <li> <a href="gradeReport?semesterId=<%= request.getParameter("semesterId")%>&courseId=${c.courseId}">${c.courseId} ${c.courseName}</a></li>
                    </c:forEach> 
            </ul>
            <table>
                <tr id = "header">
                    <td>GRADE CATEGORY</td>
                    <td>GRADE ITEM</td>
                    <td>WEIGHT</td>
                    <td>VALUE</td>

                </tr>


                <c:forEach items="${grades}" var="g" varStatus="index">

                    <tr>
                        <td>${g.gradeCategory.gradeCategoryName}</td>
                        <td>${g.gradeCategory.gradeItemName}</td>
                        <td>${g.gradeCategory.weight} %</td>
                        <td>${g.gradeValue}</td>
                    </tr>
                </c:forEach> 

            </table>
        </div>
    </body>
</html>
