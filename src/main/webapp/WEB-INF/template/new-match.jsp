<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>New Match</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<main>
    <div class="container">
        <h1>Start new match</h1>
        <div class="form-container center">
            <form method="post" action="new-match">
                <c:if test="${not empty errorMessage}">
                    <p class="error-message">${errorMessage}</p>
                </c:if>
                <label class="label-player" for="playerOne">Player one</label>
                <input class="input-player" id="playerOne" name="playerOne" placeholder="Name" type="text" required>
                <label class="label-player" for="playerTwo">Player two</label>
                <input class="input-player" id="playerTwo" name="playerTwo" placeholder="Name" type="text" required>
                <input class="form-button" type="submit" value="Start">
            </form>
        </div>
    </div>
</main>
</body>
</html>
