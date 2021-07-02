<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.webLanguageName}"/>
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.example.my_shop.util.constants.PageConstants"/>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/header.css">
    <link rel="stylesheet" href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css'>
    <link rel="stylesheet" href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css'>
    <script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
</head>
<body>
<div class="header2 bg-success-gradiant">
    <div class="">
        <nav class="navbar navbar-expand-lg h2-nav"><a class="navbar-brand" href="index.jsp"><fmt:message
                key="header.main"/></a>
            <div class="collapse navbar-collapse hover-dropdown" id="header2">
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="shop.jsp"><fmt:message key="header.store"/></a></li>
                    <li class="nav-item"><a class="nav-link" href="profile.jsp"><fmt:message key="header.profile"/></a>
                    </li>
                    <c:if test="${sessionScope.loggedUser.roleId == 1}">
                        <li class="nav-item dropdown position-relative"><a class="nav-link dropdown-toggle"
                                                                           id="h2-dropdown" data-toggle="dropdown"
                                                                           aria-haspopup="true" aria-expanded="false"><fmt:message key="header.adminPage"/><i class="fa fa-angle-down ml-1 font-12"></i> </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="addCloth.jsp"><fmt:message key="header.addClothes"/></a></li>
                                <li><a class="dropdown-item" href="editClothes.jsp"><fmt:message key="header.editClothes"/></a></li>
                                <li><a class="dropdown-item" href="editCompanies.jsp"><fmt:message key="header.editCompanies"/></a></li>
                                <li><a class="dropdown-item" href="editUsers.jsp"><fmt:message key="header.editUsers"/></a></li>
                            </ul>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="addCloth.jsp"></a></li>
                    </c:if>
                </ul>

                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link"><i class="icon-bubble"></i>
                            <form action="ChangeLanguage" method="POST">
                                <select name="languageToChange">
                                    <c:forEach var="language" items="${sessionScope.allLanguages}">
                                        <option value="${language.id}">${language.languageName}</option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" name="page" value="${pageContext.request.servletPath}"/>
                                <button type="submit" class="btn btn-outline-light">
                                    <fmt:message key="header.language.button"/>
                                </button>
                            </form>
                        </a>
                    </li>
                    <c:if test="${sessionScope.loggedUser == null}">
                        <li class="nav-item active"><a class="nav-link" href="login.jsp"><fmt:message
                                key="header.login"/> </a></li>
                        <li class="nav-item"><a class="btn rounded-pill btn-dark py-2 px-4"
                                                href=register.jsp><fmt:message key="header.signup"/> </a></li>
                    </c:if>
                    <c:if test="${sessionScope.loggedUser != null}">
                        <li class="nav-item">
                            <form action="Logout" method="post">
                                <a class="btn rounded-pill btn-dark py-2 px-4">
                                    <button type="submit" class="btn btn-dark">
                                        <fmt:message key="header.logout"/>
                                    </button>
                                </a>
                            </form>
                        </li>
                    </c:if>
                </ul>
            </div>
        </nav>
    </div>
</div>