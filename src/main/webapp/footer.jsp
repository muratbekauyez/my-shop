<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.webLanguageName}" />
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.example.my_shop.util.constants.PageConstants"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/footer.css">
    <link rel="stylesheet" href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css'>
    <link rel="stylesheet" href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css'>
    <script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
</head>
<body>
<footer>
    <div class="footer">
        <div class="card card0 border-0">
            <div class="bg-blue py-4">
                <div class="row px-3"><small class="ml-4 ml-sm-5 mb-2"><fmt:message key="footer.copyright"/></small>
                    <div class="social-contact ml-4 ml-sm-auto"></div>
                </div>
            </div>
        </div>
    </div>

</footer>
</body>
</html>