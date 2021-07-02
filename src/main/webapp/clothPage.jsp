<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.webLanguageName}"/>
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.example.my_shop.util.constants.PageConstants"/>
<jsp:useBean id="sizeDao" class="com.example.my_shop.database.dao.impls.SizeDAOImpl"/>
<jsp:useBean id="companyDetailsDao" class="com.example.my_shop.database.dao.impls.CompanyDAOImpl"/>
<jsp:useBean id="userDao" class="com.example.my_shop.database.dao.impls.UserDAOImpl"/>
<c:if test="${sessionScope.loggedUser == null}">
    <c:redirect url="${pageConstants.loginPage}"/>
</c:if>
<!-- Navigation-->
<jsp:include page="${pageConstants.headerPage}"/>
<html>
<head>
    <link href="css/clothPage.css" rel="stylesheet"/>
    <script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
    <link rel="stylesheet" href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css'>
    <link rel="stylesheet" href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css'>
    <title>${sessionScope.clothDetails.name}</title>
</head>
<body>
<div class="container bootdey">
    <div class="col-md-12">
        <section class="panel">
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-6">
                        <div class="pro-img-details">
                            <img src="data:image/jpg;base64,${sessionScope.cloth.imageFromDd}" alt="">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <h4 class="pro-d-title">
                            <a class="">
                                ${sessionScope.clothDetails.name}
                            </a>
                        </h4>
                        <p class="fw-light">${sessionScope.cloth.vendorCode}</p>
                        <p>${sessionScope.clothDetails.about}</p>
                        <div class="product_meta">
                            <span class="posted_in"> <strong><fmt:message key="clothPage.company"/></strong> <a>${companyDetailsDao.getCompany(sessionScope.cloth.companyId).companyName}</a></span>
                            <span class="posted_in"> <strong><fmt:message key="clothPage.color"/></strong> <a>${sessionScope.clothDetails.color}<br></a></span>
                            <span class="posted_in"> <strong><fmt:message key="clothPage.numberOfPockets"/></strong> <a>${sessionScope.clothDetails.numberOfPockets}</a></span>
                            <span class="posted_in"> <strong><fmt:message key="clothPage.season"/></strong> <a>${sessionScope.clothDetails.season}</a></span>
                            <span class="posted_in"> <strong><fmt:message key="clothPage.pattern"/></strong> <a>${sessionScope.clothDetails.pattern}</a></span>
                            <span class="posted_in"> <strong><fmt:message key="clothPage.price"/>:</strong> <a>₸${sessionScope.cloth.price}</a></span>

                        </div>
                        <form action="AddCart" method="POST">
                            <div class="form-group">
                                <span class="posted_in"> <strong><fmt:message key="clothPage.size"/></strong>
                                    <select class="form-control" name="clothSizeId" required>
                                        <c:forEach var="clothSize" items="${sizeDao.allSizesOfCloth(sessionScope.cloth.id)}">
                                            <option value="${clothSize.id}">${clothSize.sizeName}</option>
                                        </c:forEach>
                                    </select>
                                </span>
                            </div>
                            <div class="form-group">
                                <label><fmt:message key="clothPage.quantity"/></label>
                                <input type="number" placeholder="1" min="0" class="form-control quantity" name="cartAmount" required>
                            </div>
                            <p>
                                <button class="btn btn-round btn-danger" type="submit"><i class="fa fa-shopping-cart"></i>
                                    <fmt:message key="clothPage.addToCart"/>
                                </button>
                                <c:if test="${requestScope.successCart == 'yes'}">
                                    <p><a class="text-success"><fmt:message key="clothPage.cartAdded"/></a></p>
                                </c:if>
                                <c:if test="${requestScope.successCart != 'yes' and requestScope.successCart != null}">
                                    <p><a class="text-danger"><fmt:message key="${requestScope.successCart}"/> </a></p>
                                </c:if>
                            </p>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>
<div class="container">
    <div class="col-md-12">
        <div class="offer-dedicated-body-left">
            <div class="tab-content" id="pills-tabContent">
                <div class="tab-pane fade active show" id="pills-reviews" role="tabpanel"
                     aria-labelledby="pills-reviews-tab">
                    <c:if test="${sessionScope.userReview != null}">
                        <div class="bg-white rounded shadow-sm p-4 mb-4 restaurant-detailed-ratings-and-reviews">
                            <h5 class="mb-1"><fmt:message key="clothPage.myReview"/></h5>
                            <div class="reviews-members pt-4 pb-4">
                                <div class="media">
                                    <div class="media-body">
                                        <div class="reviews-members-header">
                                            <p class="text-gray">${sessionScope.userReview.date}</p>
                                        </div>
                                        <div class="reviews-members-body">
                                            <p>${sessionScope.userReview.content}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr>
                    </c:if>
                    <div class="bg-white rounded shadow-sm p-4 mb-5 rating-review-select-page">
                        <h5 class="mb-4"><fmt:message key="clothPage.leaveReview"/></h5>
                        <c:if test="${sessionScope.userReview == null}">
                            <form action="AddReview" method="POST">
                                <div class="form-group">
                                    <label><fmt:message key="clothPage.myReview"/></label>
                                    <textarea class="form-control" name="reviewContent"></textarea>
                                </div>
                                <div class="form-group">
                                    <input type="submit" class="btn btn-primary btn-sm" value="Add Review">
                                </div>
                            </form>
                        </c:if>
                        <c:if test="${sessionScope.userReview != null}">
                            <form action="EditReview" method="POST">
                                <div class="form-group">
                                    <label><fmt:message key="clothPage.myReview"/></label>
                                    <textarea class="form-control" name="reviewContent"></textarea>
                                </div>
                                <div class="form-group">
                                    <input type="submit" class="btn btn-primary btn-sm" value="Edit Review">
                                </div>
                            </form>
                        </c:if>
                    </div>
                    <c:if test="${sessionScope.clothReviews != null}">
                        <div class="bg-white rounded shadow-sm p-4 mb-4 restaurant-detailed-ratings-and-reviews">
                            <h5 class="mb-1"><fmt:message key="clothPage.allReviews"/></h5>
                            <c:forEach var="clothReview" items="${sessionScope.clothReviews}">
                                <div class="reviews-members pt-4 pb-4">
                                    <div class="media">
                                        <div class="media-body">
                                            <div class="reviews-members-header">
                                                <h6 class="mb-1"><a
                                                        class="text-black">${userDao.getUserById(clothReview.userId).username}</a>
                                                </h6>
                                                <p class="text-gray">${clothReview.date}</p>
                                            </div>
                                            <div class="reviews-members-body">
                                                <p>
                                                        ${clothReview.content}
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                            </c:forEach>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
