<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <title>New Match</title>
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">--%>
</head>

<body>
<main>
    <div class="container">
        <h1>Welcome to Tennis Scoreboard</h1>
        <p>Manage your tennis matches, record results, and track rankings</p>
        <div class="welcome-image"></div>
        <div class="form-container center">
            <a class="homepage-action-button" href="/new-match">
                <button class="btn start-match">
                    Start a new match
                </button>
            </a>
            <a class="homepage-action-button" href="/matches">
                <button class="btn view-results">
                    View match results
                </button>
            </a>
        </div>
    </div>
</main>

</body>
</html>
