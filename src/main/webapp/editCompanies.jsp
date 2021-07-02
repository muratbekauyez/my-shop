<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.webLanguageName}"/>
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.example.my_shop.util.constants.PageConstants"/>
<jsp:useBean id="clothDao" class="com.example.my_shop.database.dao.impls.ClothDAOImpl"/>
<jsp:useBean id="companyDao" class="com.example.my_shop.database.dao.impls.CompanyDAOImpl"/>
<jsp:useBean id="clothDetailsDao" class="com.example.my_shop.database.dao.impls.ClothDetailsDAOImpl"/>
<jsp:useBean id="sizeDao" class="com.example.my_shop.database.dao.impls.SizeDAOImpl"/>
<c:if test="${sessionScope.loggedUser == null}">
    <c:redirect url="${pageConstants.loginPage}"/>
</c:if>

<html>
<head>
    <title><fmt:message key="editCompanies.title"/></title>
</head>
<body>
<jsp:include page="${pageConstants.headerPage}"/>
<div class="container">
    <div class="col-md-12">
        <div class="offer-dedicated-body-left">
            <div class="tab-content" id="pills-tabContent">
                <div class="tab-pane fade active show" id="pills-reviews" role="tabpanel"
                     aria-labelledby="pills-reviews-tab">
                    <div class="bg-white rounded shadow-sm p-4 mb-4 restaurant-detailed-ratings-and-reviews">
                        <h5 class="mb-1"><fmt:message key="editCompanies.allCompanies"/></h5>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col"><fmt:message key="editCompanies.companyName"/></th>
                                <th scope="col"><fmt:message key="editCompanies.companyCountry"/></th>
                                <th scope="col"><fmt:message key="editCompanies.edit"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="company" items="${companyDao.allCompanies}">
                                <tr>
                                    <form action="EditCompany" method="POST">
                                        <td>${company.companyName}<br><input type="text" name="companyName" required></td>
                                        <td>${company.country}<br><input type="text" name="companyCountry" required></td>
                                        <td>
                                            <button type="submit" class="btn btn-warning" name="companyId" value="${company.id}">
                                                    <fmt:message key="editCompanies.edit"/>
                                            </button>
                                        </td>
                                    </form>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <c:if test="${requestScope.successCompany == 'yes'}">
                            <p><a class="text-success"><fmt:message key="editCompanies.companyEdited"/></a></p>
                        </c:if>
                        <c:if test="${requestScope.successCompany == 'no'}">
                            <p><a class="text-danger"><fmt:message key="editCompanies.companyError"/></a></p>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
