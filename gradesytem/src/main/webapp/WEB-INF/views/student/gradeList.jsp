

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>




<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>JSP Page</title>
    </head>

    <body>
        <div style="display: flex">
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
            <c:if test="${grades.size()>0}">

                <table>
                    <thead>
                        <tr>

                            <th>GRADE CATEGORY</th>
                            <th>GRADE ITEM</th>
                            <th>WEIGHT</th>
                            <th>VALUE</th>
                            <th>COMMENT</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="isPass" value = "true"></c:set>
                        <c:forEach items="${grades}" var="g" varStatus="index">
                            <c:if test="${g.gradeCategory.gradeCategoryName != 'Final Exam Resit' and g.gradeCategory.gradeItemName eq 'Total' and g.gradeValue eq 0}">
                                <c:set var="isPass" value="false"></c:set>
                            </c:if>
                            <tr>
                                <td>
                                    <%--<c:if test="${g.gradeCategory.gradeItemName != 'Total'}">--%>
                                        ${g.gradeCategory.gradeCategoryName}
                                    <%--</c:if>--%>         
                                </td>
                                <td>${g.gradeCategory.gradeItemName}</td>
                                <td>${g.gradeCategory.weight} %</td>
                                <td>${g.gradeValue}</td>
                            </tr>
                        </c:forEach> 
                        <c:if test="${sum<5}"><c:set var="isPass" value="false"></c:set></c:if>
                            </tbody>

                            <tfoot>
                                <tr>
                                    <td rowspan="2">Course total</td>
                                    <td>Average</td>
                                    <td colspan="3"> <fmt:formatNumber value="${sum}" pattern="#,##0.0" /></td>
                        </tr>
                        <tr>
                            <td>Status</td>
                            <td colspan="3" style="color: ${isPass?"green":"red"}">
                                ${isPass?"Passed":"Not Passed"}
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </c:if>

        </div>
    </body>
</html>
