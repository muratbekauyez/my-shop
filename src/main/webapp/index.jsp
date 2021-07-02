<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.webLanguageName}" />
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.example.my_shop.util.constants.PageConstants"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="index.title"/></title>
    <style>
        html,body{
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
<jsp:include page="${pageConstants.headerPage}"/>
<div class="brand">
    <p></p>
</div>
<div class="container">
    <div class="row">

        <div class="col-xl-6 col-md-12 mb-8">
            <div class="card border-0 shadow">
                <a href="shop.jsp"><img src="img/1.jpg" class="card-img-top" alt="..." height="350" width="450"></a>
                <div class="card-body text-center">
                    <h5 class="card-title mb-0"><fmt:message key="index.shop"/></h5>
                </div>
            </div>
        </div>

        <div class="col-xl-6 col-md-12 mb-8">
            <div class="card border-0 shadow">
                <a href="profile.jsp"><img src="img/2.jpg" class="card-img-top" alt="..." height="350" width="450"></a>
                <div class="card-body text-center">
                    <h5 class="card-title mb-0"><fmt:message key="index.profile"/> </h5>
                </div>
            </div>
        </div>
    </div>


</div>
</body>

</html>