<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.webLanguageName}"/>
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.example.my_shop.util.constants.PageConstants"/>
<jsp:useBean id="clothDao" class="com.example.my_shop.database.dao.impls.ClothDAOImpl"/>
<jsp:useBean id="sizeDao" class="com.example.my_shop.database.dao.impls.SizeDAOImpl"/>
<jsp:useBean id="clothDetailsDao" class="com.example.my_shop.database.dao.impls.ClothDetailsDAOImpl"/>
<c:if test="${sessionScope.loggedUser == null}">
    <c:redirect url="${pageConstants.loginPage}"/>
</c:if>
<html>
<head>
    <title><fmt:message key="cart.title"/></title>
    <link rel="stylesheet" href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css'>
    <link rel="stylesheet" href="css/cart.css">
    <script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
</head>
<body>
<c:if test="${fn:endsWith(pageContext.request.requestURI, '/cart.jsp')}">
    <jsp:include page="${pageConstants.headerPage}"/>
</c:if>


<c:if test="${sessionScope.userCartClothes.size() != 0 or sessionScope.userCartClothes == null}">
    <div class="cart-box">
        <div class="order_summary">
            <div class="summary_card">

                <c:forEach var="cartCloth" items="${sessionScope.userCartClothes}">
                    <div class="card_item">
                        <div class="product_img">
                            <img src="data:image/jpg;base64,${clothDao.getCloth(cartCloth.productId).imageFromDd}"
                                 alt=""/>
                        </div>
                        <form action="EditCartClothAmount" method="POST">
                            <div class="product_info">
                                <input type="hidden" name="clothId" value="${cartCloth.productId}">
                                <input type="hidden" name="clothSizeId" value="${cartCloth.sizeId}">
                                <h1>${clothDetailsDao.getClothDetails(cartCloth.productId, sessionScope.webLanguage).name}</h1>
                                <p><fmt:message key="cart.size"/> ${sizeDao.getSizeName(cartCloth.sizeId)}</p>
                                <div class="product_rate_info">
                                    <h1>₸${clothDao.getCloth(cartCloth.productId).price}</h1>
                                    <input type="number" name="cartAmount" value="${cartCloth.amount}" min="0" required>
                                </div>
                                <div class="product_rate_info">
                                    <button type="submit" class="btn btn-dark"><fmt:message key="cart.edit"/></button>
                                </div>
                            </div>
                        </form>
                        <form action="DeleteCart" method="POST">
                            <div class="product_info">
                                <div class="product_rate_info">
                                    <input type="hidden" name="clothId" value="${cartCloth.productId}">
                                    <button type="submit" class="btn btn-danger"><fmt:message key="cart.delete"/></button>
                                </div>
                            </div>
                        </form>
                    </div>
                </c:forEach>

                <c:if test="${requestScope.successCartUpdate == 'no'}">
                    <p><a class="text-danger"><fmt:message key="cart.updateError"/></a></p>
                </c:if>
                <hr/>
                <form action="MakeOrder" method="POST">
                    <div class="order_total">
                        <p><fmt:message key="cart.totalAmount"/></p>
                        <input type="hidden" name="totalPrice" value="${sessionScope.cartSum}">
                        <h4>₸${sessionScope.cartSum}</h4>
                        <button type="submit" class="btn btn-success"><fmt:message key="cart.buy"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</c:if>
</body>
</html>
