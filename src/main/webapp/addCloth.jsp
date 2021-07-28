<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.webLanguageName}" />
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.example.my_shop.util.constants.PageConstants"/>
<jsp:useBean id="parameterConstants" class="com.example.my_shop.util.constants.ParameterConstants"/>
<jsp:useBean id="CompanyDao" class="com.example.my_shop.database.dao.impls.CompanyDAOImpl"/>
<jsp:useBean id="ClothDao" class="com.example.my_shop.database.dao.impls.ClothDAOImpl"/>
<jsp:useBean id="SizeDao" class="com.example.my_shop.database.dao.impls.SizeDAOImpl"/>
<c:if test="${sessionScope.loggedUser.roleId != parameterConstants.adminRoleId}">
    <c:redirect url="${pageConstants.profilePage}"/>
</c:if>

<jsp:include page="${pageConstants.headerPage}"/>
<html>
<head>
    <title><fmt:message key="addCloth.addCloth"/></title>

    <link rel="stylesheet" href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css'>
</head>
<body>

<div class="container">
    <div class="brand">
        <p><br></p>
    </div>


    <div class="row">
        <div class="col-xl-4 col-md-8 mb-6">
            <h1><fmt:message key="addCloth.addCompany"/> </h1>
            <form action="AddCompany" method="POST">
                <div class="form-group">
                    <label><fmt:message key="addCloth.companyName"/></label>
                    <input type="text" class="form-control" name="companyName" placeholder="Adidas" required>
                </div>
                <div class="form-group">
                    <label><fmt:message key="addCloth.companyCountry"/></label>
                    <input type="text" class="form-control" name="companyCountry" placeholder="Germany" required>
                </div>
                <button type="submit" class="btn btn-outline-dark btn-lg"><fmt:message key="addCloth.button"/></button>
                <c:if test="${requestScope.successCompany == 'yes'}">
                    <p><a class="text-success"><fmt:message key="addCloth.companyAdded"/></a></p>
                </c:if>
                <c:if test="${requestScope.successCompany == 'no'}">
                    <p><a class="text-danger"><fmt:message key="addCloth.companyError"/></a></p>
                </c:if>
            </form>
        </div>


        <div class="col-xl-4 col-md-8 mb-6">
            <h1><fmt:message key="addCloth.addCloth"/></h1>
            <form action="AddCloth" method="POST" enctype="multipart/form-data">
                <div class="form-group">
                    <label><fmt:message key="addCloth.vendorCode"/></label>
                    <input type="text" class="form-control" name="vendorCode" placeholder="UX2315" required>
                </div>
                <div class="form-group">
                    <label><fmt:message key="addCloth.price"/></label>
                    <input type="number" class="form-control" name="price" placeholder="1000" required>
                </div>
                <div class="form-group">
                    <label><fmt:message key="addCloth.companyName"/></label>
                    <select class="form-control" name="companyId" required>
                        <c:forEach var="company" items="${CompanyDao.allCompanies}">
                            <option value="${company.id}">${company.companyName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label><fmt:message key="addCloth.image"/></label>
                    <input type="file" class="form-control-file" name="clothImage">
                </div>
                <div class="form-group">
                    <label><fmt:message key="addCloth.gender"/>
                        <select class="form-control" name="gender" required>
                            <option value="1"><fmt:message key="addCloth.male"/></option>
                            <option value="2"><fmt:message key="addCloth.female"/></option>
                        </select>
                    </label>
                </div>

                <button type="submit" class="btn btn-outline-dark btn-lg"><fmt:message key="addCloth.button"/></button>
                <c:if test="${requestScope.successCloth == 'yes'}">
                    <p><a class="text-success"><fmt:message key="addCloth.clothAdded"/></a></p>
                </c:if>
                <c:if test="${requestScope.successCloth == 'no'}">
                    <p><a class="text-danger"><fmt:message key="addCloth.clothError"/></a></p>
                </c:if>
            </form>

            <hr>
            <h1>Add Cloth Sizes</h1>
            <form action="AddClothSize" method="POST">
                <div class="form-group">
                    <label><fmt:message key="addCloth.selectCloth"/></label>
                    <select class="form-control" name="clothId" required>
                        <c:forEach var="cloth" items="${ClothDao.allClothes}">
                            <option value="${cloth.id}">${cloth.vendorCode}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label>Cloth Size</label>
                    <select class="form-control" name="clothSizeId" required>
                        <c:forEach var="clothSize" items="${SizeDao.allSizes}">
                            <option value="${clothSize.id}">${clothSize.sizeName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label>Amount</label>
                    <input type="number" class="form-control" name="clothAmount" placeholder="100" required>
                </div>

                <button type="submit" class="btn btn-outline-dark btn-lg"><fmt:message key="addCloth.button"/></button>
                <c:if test="${requestScope.successClothSize == 'yes'}">
                    <p><a class="text-success"><fmt:message key="addCloth.sizeAdded"/></a></p>
                </c:if>
                <c:if test="${requestScope.successClothSize == 'no'}">
                    <p><a class="text-danger"><fmt:message key="addCloth.sizeError"/></a></p>
                </c:if>
            </form>
        </div>


        <div class="col-xl-4 col-md-8 mb-6">
            <h1><fmt:message key="addCloth.addClothDetails"/></h1>
            <form action="AddClothDetails" method="POST">
                <div class="form-group">
                    <label><fmt:message key="addCloth.selectCloth"/></label>
                    <select class="form-control" name="clothId" required>
                        <c:forEach var="cloth" items="${ClothDao.allClothes}">
                            <option value="${cloth.id}">${cloth.vendorCode}</option>
                        </c:forEach>
                    </select>

                </div>

                <div class="form-group">
                    <label><fmt:message key="addCloth.selectLanguage"/></label>
                    <select class="form-control" name="clothLanguageId" required>
                        <c:forEach var="language" items="${sessionScope.allLanguages}">
                            <option value="${language.id}">${language.languageName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label><fmt:message key="addCloth.clothName"/></label>
                    <input type="text" class="form-control" name="clothName" placeholder="Shirt" required>
                </div>

                <div class="form-group">
                    <label><fmt:message key="addCloth.color"/></label>
                    <input type="text" class="form-control" name="clothColor" placeholder="white" required>
                </div>

                <div class="form-group">
                    <label><fmt:message key="addCloth.numberOfPockets"/></label>
                    <input type="number" class="form-control" name="numberOfPockets" placeholder="1" required>
                </div>

                <div class="form-group">
                    <label><fmt:message key="addCloth.season"/></label>
                    <input type="text" class="form-control" name="clothSeason" placeholder="summer" required>
                </div>

                <div class="form-group">
                    <label><fmt:message key="addCloth.pattern"/></label>
                    <input type="text" class="form-control" name="clothPattern" placeholder="Monochromatic" required>
                </div>

                <div class="form-group">
                    <label><fmt:message key="addCloth.about"/></label>
                    <textarea class="form-control" name="clothAbout" required></textarea>
                </div>

                <button type="submit" class="btn btn-outline-dark btn-lg"><fmt:message key="addCloth.button"/></button>
                <c:if test="${requestScope.successClothDetails == 'yes'}">
                <p><a class="text-success"><fmt:message key="addCloth.clothDetailsAdded"/></a></p>
                </c:if>
                <c:if test="${requestScope.successClothDetails == 'no'}">
                <p><a class="text-danger"><fmt:message key="addCloth.clothDetailsError"/></a></p>
                </c:if>
            </form>
        </div>

    </div>

    <div class="brand">
        <p><br></p>
    </div>
</div>
<jsp:include page="${pageConstants.footerPage}"/>
</body>
</html>
