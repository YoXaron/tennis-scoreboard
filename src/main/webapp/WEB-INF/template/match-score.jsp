<%@ page import="com.yoxaron.tennis_scoreboard.model.domain.OngoingMatch" %>
<%@ page import="com.yoxaron.tennis_scoreboard.utils.PointsFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Match Score</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<main>
    <div class="container">
        <h1>Current match</h1>
        <section class="score">
            <table class="table">
                <thead class="result">
                <tr>
                    <th class="table-text">Player</th>
                    <th class="table-text">Sets</th>
                    <th class="table-text">Games</th>
                    <th class="table-text">Points</th>
                </tr>
                </thead>
                <tbody>
                <tr class="player1">
                    <td class="table-text">${requestScope.ongoingMatch.playerOne.name()}</td>
                    <td class="table-text">${requestScope.ongoingMatch.playerOneScore.sets}</td>
                    <td class="table-text">${requestScope.ongoingMatch.playerOneScore.games}</td>
                    <td class="table-text">
                        <%= PointsFormatter.format(
                                ((OngoingMatch) request.getAttribute("ongoingMatch")).getPlayerOneScore(),
                                ((OngoingMatch) request.getAttribute("ongoingMatch")).getPlayerTwoScore())
                        %>
                    </td>
                    <td class="table-text">
                        <form action="${pageContext.request.contextPath}/match-score" method="post">
                            <input type="hidden" name="uuid" value="${requestScope.ongoingMatch.uuid}"/>
                            <input type="hidden" name="winnerId" value="${requestScope.ongoingMatch.playerOne.id()}"/>
                            <button type="submit" class="score-btn">Score</button>
                        </form>
                    </td>
                </tr>
                <tr class="player2">
                    <td class="table-text">${requestScope.ongoingMatch.playerTwo.name()}</td>
                    <td class="table-text">${requestScope.ongoingMatch.playerTwoScore.sets}</td>
                    <td class="table-text">${requestScope.ongoingMatch.playerTwoScore.games}</td>
                    <td class="table-text">
                        <%= PointsFormatter.format(
                                ((OngoingMatch) request.getAttribute("ongoingMatch")).getPlayerTwoScore(),
                                ((OngoingMatch) request.getAttribute("ongoingMatch")).getPlayerOneScore())
                        %>
                    </td>
                    <td class="table-text">
                        <form action="${pageContext.request.contextPath}/match-score" method="post">
                            <input type="hidden" name="uuid" value="${requestScope.ongoingMatch.uuid}"/>
                            <input type="hidden" name="winnerId" value="${requestScope.ongoingMatch.playerTwo.id()}"/>
                            <button type="submit" class="score-btn">Score</button>
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </section>
    </div>
</main>

</body>
</html>