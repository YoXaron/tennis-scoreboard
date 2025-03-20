<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Finish match</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <section class="match-result">
        <div class="match-block">
            <h1 class="match-title">Match Result</h1>
        </div>
    </section>
    <section class="finish-result">
        <div class="finish-result__block">
            <div class="winner">
                <h2>Winner</h2>
                <div class="winner-name">
                    <p class="winner-name__text">${finishedMatch.winner().name()}</p>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>
