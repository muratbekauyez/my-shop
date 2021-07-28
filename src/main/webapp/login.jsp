<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.webLanguageName}" />
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.example.my_shop.util.constants.PageConstants"/>
<c:if test="${sessionScope.loggedUser != null}">
    <c:redirect url="${pageConstants.profilePage}"/>
</c:if>
<jsp:include page="${pageConstants.headerPage}"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="login.login"/> </title>
    <link rel="stylesheet" type="text/css" href="css/header.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <link rel="stylesheet" type="text/css" href="css/profile.css">
    <link rel="stylesheet" href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css'>
    <link rel="stylesheet" href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css'>
    <script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
</head>
<body>

<div class="row justify-content-md-center h-100">
    <div class="card-wrapper">
        <div class="brand">
            <p></p>
        </div>
        <div class="card fat">
            <div class="card-body">
                <h4 class="card-title"><fmt:message key="login.login"/></h4>
                <form method="POST" class="my-login-validation" action="Login">
                    <div class="form-group">
                        <label for="username"><fmt:message key="login.username"/></label>
                        <input id="username" type="text" class="form-control" name="username" value="" autofocus required>
                    </div>

                    <div class="form-group">
                        <label for="password"><fmt:message key="login.password"/></label>
                        <input id="password" type="password" class="form-control" name="password" data-eye required>
                    </div>


                    <div class="form-group m-0">
                        <button type="submit" class="btn btn-primary btn-block">
                            <fmt:message key="login.login"/>
                        </button>
                    </div>
                    <c:if test="${requestScope.errorLogin != null}">
                        <p><a class="text-danger"><fmt:message key="${requestScope.errorLogin}"/> </a></p>
                    </c:if>
                    <div class="mt-4 text-center">
                        <fmt:message key="login.notHaveAccount?"/> <a href="register.jsp"><fmt:message key="login.createOne"/></a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>