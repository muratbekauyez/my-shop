<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.webLanguageName}"/>
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.example.my_shop.util.constants.PageConstants"/>
<jsp:useBean id="clothDao" class="com.example.my_shop.database.dao.impls.ClothDAOImpl"/>
<jsp:useBean id="clothDetailsDao" class="com.example.my_shop.database.dao.impls.ClothDetailsDAOImpl"/>
<jsp:useBean id="orderDao" class="com.example.my_shop.database.dao.impls.OrderDAOImpl"/>
<c:if test="${sessionScope.loggedUser == null}">
    <c:redirect url="${pageConstants.loginPage}"/>
</c:if>

<!DOCTYPE html>
<html>
<head>
    <title>${sessionScope.loggedUser.username}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css'>
    <link rel="stylesheet" href="css/profile.css">
    <script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
</head>
<body>
<jsp:include page="${pageConstants.headerPage}"/>
<div class="container">
    <div class="main-body">
        <div class="row gutters-sm">
            <div class="col-md-12">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="profile.fullName"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${sessionScope.loggedUser.firstName} ${sessionScope.loggedUser.lastName}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="profile.username"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${sessionScope.loggedUser.username}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="profile.gender"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <c:if test="${sessionScope.loggedUser.genderId == 1}">
                                    <fmt:message key="profile.male"/>
                                </c:if>
                                <c:if test="${sessionScope.loggedUser.genderId == 2}">
                                    <fmt:message key="profile.female"/>
                                </c:if>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="profile.birthDate"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${sessionScope.loggedUser.birthDate}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="profile.registrationDate"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${sessionScope.loggedUser.registrationDate}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12">
                                <a class="btn btn-info" href="editProfile.jsp"><fmt:message key="profile.edit"/></a>
                                <a class="btn btn-info" href="editPassword.jsp"><fmt:message key="profile.editPassword"/></a>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <c:if test="${requestScope.successOrder == 'yes'}">
                                    <p><a class="text-success"><fmt:message key="profile.orderCompleted"/></a></p>
                                </c:if>
                                <c:if test="${requestScope.successOrder == 'no'}">
                                    <p><a class="text-danger"><fmt:message key="profile.orderError"/></a></p>
                                </c:if>
                                <c:if test="${requestScope.successProfileEdit == 'yes'}">
                                    <p><a class="text-success"><fmt:message key="profile.userUpdated"/></a></p>
                                </c:if>
                                <c:if test="${requestScope.successProfileEdit == 'no'}">
                                    <p><a class="text-danger"><fmt:message key="profile.userUpdateError"/></a></p>
                                </c:if>
                                <c:if test="${requestScope.successPasswordEdit == 'yes'}">
                                    <p><a class="text-success"><fmt:message key="profile.passwordUpdated"/></a></p>
                                </c:if>
                                <c:if test="${requestScope.successPasswordEdit != 'yes' && requestScope.successPasswordEdit != null}">
                                    <p><a class="text-danger"><fmt:message
                                            key="${requestScope.successPasswordEdit}"/> </a></p>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="${pageConstants.cartPage}"/>

<div class="container">
    <div class="col-md-12">
        <div class="offer-dedicated-body-left">
            <div class="tab-content" id="pills-tabContent">
                <div class="tab-pane fade active show" id="pills-reviews" role="tabpanel"
                     aria-labelledby="pills-reviews-tab">
                    <c:if test="${sessionScope.userOrders.size() != 0 or sessionScope.userOrders == null}">
                        <div class="bg-white rounded shadow-sm p-4 mb-4 restaurant-detailed-ratings-and-reviews">
                            <h5 class="mb-1"><fmt:message key="profile.allOrders"/></h5>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col"><fmt:message key="profile.number"/></th>
                                    <th scope="col"><fmt:message key="profile.date"/></th>
                                    <th scope="col"><fmt:message key="profile.totalPrice"/></th>
                                    <th scope="col"><fmt:message key="profile.status"/></th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach var="userOrder" items="${sessionScope.userOrders}">
                                    <form action="GetOrderDetailsPage" method="POST">
                                        <tr>
                                            <th scope="row"><button type="submit" name="orderId" value="${userOrder.id}" class="btn btn-link">${userOrder.id}</button></th>
                                            <td>${userOrder.date}</td>
                                            <td>₸${userOrder.totalPrice}</td>
                                            <c:if test="${sessionScope.webLanguageId == 1}">
                                                <td>${orderDao.statusName(userOrder.statusId)}</td>
                                            </c:if>
                                            <c:if test="${sessionScope.webLanguageId == 2}">
                                                <td>${orderDao.statusName(userOrder.statusId+1)}</td>
                                            </c:if>
                                        </tr>
                                    </form>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>