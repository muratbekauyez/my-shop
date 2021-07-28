<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.webLanguageName}"/>
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.example.my_shop.util.constants.PageConstants"/>
<jsp:useBean id="userDao" class="com.example.my_shop.database.dao.impls.UserDAOImpl"/>
<c:if test="${sessionScope.loggedUser == null}">
    <c:redirect url="${pageConstants.loginPage}"/>
</c:if>

<html>
<head>
    <title><fmt:message key="editUsers.title"/></title>
</head>
<body>
<jsp:include page="${pageConstants.headerPage}"/>
<div style="padding: 25px">
    <h5 class="mb-1"><fmt:message key="editUsers.editPassword"/></h5>
    <table class="table">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="editUsers.username"/></th>
            <th scope="col"><fmt:message key="editUsers.password"/></th>
            <th scope="col"><fmt:message key="editUsers.re-passsword"/></th>
            <th scope="col"><fmt:message key="editUsers.edit"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userDao.allUsers}">
            <tr>
                <form action="EditUsersPassword" method="POST" enctype="multipart/form-data">
                    <td>${user.username}</td>
                    <td><input type="password" name="password" required></td>
                    <td><input type="password" name="re-password" required></td>
                    <td>
                        <button type="submit" class="btn btn-warning" name="userId" value="${user.id}">
                                <fmt:message key="editUsers.edit"/>
                        </button>
                    </td>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${requestScope.successPasswordEdit == 'yes'}">
        <p><a class="text-success"><fmt:message key="editUsers.passwordEdited"/></a></p>
    </c:if>
    <c:if test="${requestScope.successPasswordEdit == 'no'}">
        <p><a class="text-success"><fmt:message key="parameters.passwordsNotMatch"/></a></p>
    </c:if>
</div>
<div style="padding: 25px">
    <h5 class="mb-1"><fmt:message key="editUsers.editProfile"/></h5>
    <table class="table">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="editUsers.username"/></th>
            <th scope="col"><fmt:message key="editUsers.firstName"/></th>
            <th scope="col"><fmt:message key="editUsers.lastName"/></th>
            <th scope="col"><fmt:message key="editUsers.birthDate"/></th>
            <th scope="col"><fmt:message key="editUsers.registrationDate"/></th>
            <th scope="col"><fmt:message key="editUsers.gender"/></th>
            <th scope="col"><fmt:message key="editUsers.edit"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userDao.allUsers}">
            <tr>
                <form action="EditProfile" method="POST" enctype="multipart/form-data">
                    <td>${user.username}</td>
                    <td>${user.firstName}<br><input type="text" name="firstName" required></td>
                    <td>${user.lastName}<br><input type="text" name="lastName" required></td>
                    <td>${user.birthDate}<br><input type="date" name="birthDate" required></td>
                    <td>${user.registrationDate}</td>
                    <td>
                        <select class="form-control" name="gender" required>
                            <option value="1"><fmt:message key="editUsers.male"/></option>
                            <option value="2"><fmt:message key="editUsers.female"/></option>
                        </select>
                    </td>
                    <td>
                        <input type="hidden" name="pageName" value="editUsers.jsp">
                        <button type="submit" class="btn btn-warning" name="userId"
                                value="${user.id}">
                                <fmt:message key="editUsers.edit"/>
                        </button>
                    </td>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${requestScope.successProfileEdit == 'yes'}">
        <p><a class="text-success"><fmt:message key="editUsers.profileEdited"/></a></p>
    </c:if>
    <c:if test="${requestScope.successProfileEdit == 'no'}">
        <p><a class="text-success"><fmt:message key="editUsers.profileError"/></a></p>
    </c:if>
</div>

</body>
</html>