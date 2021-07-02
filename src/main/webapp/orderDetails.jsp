<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.webLanguageName}"/>
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.example.my_shop.util.constants.PageConstants"/>
<jsp:useBean id="clothDao" class="com.example.my_shop.database.dao.impls.ClothDAOImpl"/>
<jsp:useBean id="clothDetailsDao" class="com.example.my_shop.database.dao.impls.ClothDetailsDAOImpl"/>
<jsp:useBean id="orderDao" class="com.example.my_shop.database.dao.impls.OrderDAOImpl"/>
<jsp:useBean id="sizeDao" class="com.example.my_shop.database.dao.impls.SizeDAOImpl"/>
<c:if test="${sessionScope.loggedUser == null}">
    <c:redirect url="${pageConstants.loginPage}"/>
</c:if>

<html>
<head>
    <title><fmt:message key="orderDetails.allOrders"/></title>
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
                        <h5 class="mb-1"><fmt:message key="orderDetails.allOrders"/></h5>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col"><fmt:message key="orderDetails.vendorCode"/></th>
                                <th scope="col"><fmt:message key="orderDetails.image"/></th>
                                <th scope="col"><fmt:message key="orderDetails.size"/></th>
                                <th scope="col"><fmt:message key="orderDetails.amount"/></th>
                                <th scope="col"><fmt:message key="orderDetails.price"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="cloth" items="${sessionScope.orderDetailsList}">
                                <tr>
                                    <form action="GetClothPage" method="GET">
                                        <th scope="row">
                                            <button type="submit" class="btn btn-link" name = "clothId" value="${cloth.productId}">
                                                    ${clothDao.getCloth(cloth.productId).vendorCode}
                                            </button>
                                        </th>
                                    </form>
                                    <td><img src="data:image/jpg;base64,${clothDao.getCloth(cloth.productId).imageFromDd}" width="130" height="50"/></td>
                                    <td>${sizeDao.getSizeName(cloth.sizeId)}</td>
                                    <td>${cloth.amount}</td>
                                    <td>${cloth.productPrice}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
