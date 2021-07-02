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
<jsp:useBean id="languageDao" class="com.example.my_shop.database.dao.impls.LanguageDAOImpl"/>
<c:if test="${sessionScope.loggedUser == null}">
    <c:redirect url="${pageConstants.loginPage}"/>
</c:if>

<html>
<head>
    <title><fmt:message key="editClothes.title"/></title>
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
                        <h5 class="mb-1"><fmt:message key="editClothes.allClothes"/></h5>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col"><fmt:message key="editClothes.vendorCode"/></th>
                                <th scope="col"><fmt:message key="editClothes.image"/></th>
                                <th scope="col"><fmt:message key="editClothes.company"/></th>
                                <th scope="col"><fmt:message key="editClothes.price"/></th>
                                <th scope="col"><fmt:message key="editClothes.edit"/></th>
                                <th scope="col"><fmt:message key="editClothes.remove"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="cloth" items="${clothDao.allClothes()}">
                                <tr>
                                    <form action="EditClothes" method="POST" enctype="multipart/form-data">
                                        <td>${cloth.vendorCode}<br><input type="text" name="vendorCode" required>
                                        </td>
                                        <td><img src="data:image/jpg;base64,${cloth.imageFromDd}" width="130"
                                                 height="50"/><br><input type="file" name="clothImage" required></td>
                                        <td>${companyDao.getCompany(cloth.companyId).companyName}<br>
                                            <select class="form-control" name="companyId" required>
                                                <c:forEach var="company" items="${companyDao.allCompanies}">
                                                    <option value="${company.id}">${company.companyName}</option>
                                                </c:forEach>
                                            </select></td>
                                        <td>${cloth.price}<br><input type="number" name="price" required></td>
                                        <td>
                                            <button type="submit" class="btn btn-warning" name="clothId"
                                                    value="${cloth.id}">
                                                <fmt:message key="editClothes.edit"/>
                                            </button>
                                        </td>
                                    </form>
                                    <form action="RemoveFromStore" method="POST">
                                        <td>
                                            <button type="submit" class="btn btn-danger" name="clothId"
                                                    value="${cloth.id}">
                                                <fmt:message key="editClothes.removeButton"/>
                                            </button>
                                        </td>
                                    </form>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <c:if test="${requestScope.successCloth == 'yes'}">
                            <p><a class="text-success"><fmt:message key="editClothes.clothEdited"/></a></p>
                        </c:if>
                        <c:if test="${requestScope.successCloth == 'no'}">
                            <p><a class="text-danger"><fmt:message key="editClothes.clothEditError"/></a></p>
                        </c:if>
                        <c:if test="${requestScope.deleteCloth == 'yes'}">
                            <p><a class="text-danger"><fmt:message key="editClothes.clothRemoved"/></a></p>
                        </c:if>
                        <c:if test="${requestScope.deleteCloth == 'no'}">
                            <p><a class="text-danger"><fmt:message key="editClothes.removeError"/></a></p>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div style="padding: 25px">
    <h5 class="mb-1"><fmt:message key="editClothes.clothDetails"/></h5>
    <c:if test="${requestScope.successClothDetails == 'yes'}">
        <p><a class="text-success"><fmt:message key="editClothes.clothDetailsEdited"/></a></p>
    </c:if>
    <c:if test="${requestScope.successClothDetails == 'no'}">
        <p><a class="text-danger"><fmt:message key="editClothes.clothDetailsEditError"/></a></p>
    </c:if>
    <c:if test="${requestScope.deleteClothDetails == 'yes'}">
        <p><a class="text-success"><fmt:message key="editClothes.clothDetailsRemoved"/></a></p>
    </c:if>
    <c:if test="${requestScope.deleteClothDetails == 'no'}">
        <p><a class="text-danger"><fmt:message key="editClothes.clothDetailsRemoveError"/></a></p>
    </c:if>
    <table class="table">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="editClothes.vendorCode"/></th>
            <th scope="col"><fmt:message key="editClothes.language"/></th>
            <th scope="col"><fmt:message key="editClothes.name"/></th>
            <th scope="col"><fmt:message key="editClothes.color"/></th>
            <th scope="col"><fmt:message key="editClothes.numberOfPockets"/></th>
            <th scope="col"><fmt:message key="editClothes.season"/></th>
            <th scope="col"><fmt:message key="editClothes.pattern"/></th>
            <th scope="col"><fmt:message key="editClothes.about"/></th>
            <th scope="col"><fmt:message key="editClothes.edit"/></th>
            <th scope="col"><fmt:message key="editClothes.delete"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="cloth" items="${clothDetailsDao.allClothDetails()}">
            <tr>
                <form action="EditClothDetails" method="POST" enctype="multipart/form-data">
                    <td>${clothDao.getCloth(cloth.id).vendorCode}</td>
                    <td>${languageDao.getLanguageName(cloth.languageID)}</td>
                    <td>${cloth.name}<br><input type="text" name="clothName" required></td>
                    <td>${cloth.color}<br><input type="text" name="clothColor" required></td>
                    <td>${cloth.numberOfPockets}<br><input type="number" name="numberOfPockets" required></td>
                    <td>${cloth.season}<br><input type="text" name="clothSeason" required></td>
                    <td>${cloth.pattern}<br><input type="text" name="clothPattern" required></td>
                    <td><textarea name="clothAbout" required></textarea></td>
                    <td>
                        <input type="hidden" name="clothLanguageId" value="${cloth.languageID}">
                        <button type="submit" class="btn btn-warning" name="clothId" value="${cloth.id}">
                            <fmt:message key="editClothes.edit"/>
                        </button>
                    </td>
                </form>
                <form action="DeleteClothDetails" method="POST">
                    <td>
                        <input type="hidden" name="clothLanguageId" value="${cloth.languageID}">
                        <button type="submit" class="btn btn-danger" name="clothId" value="${cloth.id}">
                            <fmt:message key="editClothes.delete"/>
                        </button>
                    </td>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
