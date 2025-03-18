<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Match Score</title>
</head>
<body>
<!--Таблица с именами игроков, текущим счётом-->
<!--Формы и кнопки для действий - “игрок 1 выиграл текущее очко”, “игрок 2 выиграл текущее очко”-->
<!--Нажатие кнопок приводит к POST запросу по адресу /match-score?uuid=$match_id, -->
<!--в полях отправленной формы содержится айди выигравшего очко игрока-->

HELLO MATCH SCORE


<p>${requestScope.ongoingMatch}</p>

<main>
    <div class="container">
        <h1>Current match</h1>
        <div class="current-match-image"></div>
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
                    <td class="table-text">${requestScope.ongoingMatch.getPlayer1().name()}</td>
                    <td class="table-text">2</td>
                    <td class="table-text">4</td>
                    <td class="table-text">40</td>
                    <td class="table-text">
                        <div class="score-btn">Score</div>
                    </td>
                </tr>
                <tr class="player2">
                    <td class="table-text">Roger Federer</td>
                    <td class="table-text">2</td>
                    <td class="table-text">3</td>
                    <td class="table-text">15</td>
                    <td class="table-text">
                        <div class="score-btn">Score</div>
                    </td>
                </tr>
                </tbody>
            </table>
        </section>
    </div>
</main>

</body>
</html>
