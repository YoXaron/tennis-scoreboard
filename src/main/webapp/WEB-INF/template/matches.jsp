<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Match Results</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>

<main>
    <div class="container">
        <h1>Match Results</h1>

        <div class="search-form">
            <form action="${pageContext.request.contextPath}/matches" method="get" class="form-matches">
                <div class="input-container">
                    <input type="text"
                           id="filter_by_player_name"
                           name="filter_by_player_name"
                           placeholder="Enter player name"
                           value="${param.filter_by_player_name}"
                           class="input-filter">
                    <button type="submit" class="btn-filter">Search</button>
                </div>
                <c:if test="${not empty param.filter_by_player_name}">
                    <div>
                        <a href="${pageContext.request.contextPath}/matches">Clear search</a>
                    </div>
                </c:if>
            </form>
        </div>

        <table class="table-matches">
            <thead>
            <tr>
                <th>Player 1</th>
                <th>Player 2</th>
                <th>Winner</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="match" items="${matches}">
                <tr>
                    <td>${match.playerOne().name()}</td>
                    <td>${match.playerTwo().name()}</td>
                    <td>
                        <span class="winner-name-td">${match.winner().name()}</span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <c:if test="${totalPages > 1}">
            <div class="pagination">
                <ul class="pagination" style="list-style-type:none;">
                    <c:if test="${currentPage > 1}">
                        <li>
                            <a class="prev"
                               href="${pageContext.request.contextPath}/matches?page=${currentPage - 1}${not empty param.filter_by_player_name ? '&filter_by_player_name='.concat(param.filter_by_player_name) : ''}">
                                &laquo;
                            </a>
                        </li>
                    </c:if>

                    <c:choose>
                        <c:when test="${totalPages <= 5}">
                            <c:forEach begin="1" end="${totalPages}" var="i">
                                <li>
                                    <a class="num-page ${currentPage == i ? 'current' : ''}"
                                       href="${pageContext.request.contextPath}/matches?page=${i}${not empty param.filter_by_player_name ? '&filter_by_player_name='.concat(param.filter_by_player_name) : ''}">
                                            ${i}
                                    </a>
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a class="num-page ${currentPage == 1 ? 'current' : ''}"
                                   href="${pageContext.request.contextPath}/matches?page=1${not empty param.filter_by_player_name ? '&filter_by_player_name='.concat(param.filter_by_player_name) : ''}">
                                    1
                                </a>
                            </li>

                            <c:if test="${currentPage > 3}">
                                <li><span>...</span></li>
                            </c:if>

                            <c:forEach begin="${Math.max(2, currentPage - 1)}"
                                       end="${Math.min(currentPage + 1, totalPages - 1)}" var="i">
                                <li>
                                    <a class="num-page ${currentPage == i ? 'current' : ''}"
                                       href="${pageContext.request.contextPath}/matches?page=${i}${not empty param.filter_by_player_name ? '&filter_by_player_name='.concat(param.filter_by_player_name) : ''}">
                                            ${i}
                                    </a>
                                </li>
                            </c:forEach>

                            <c:if test="${currentPage < totalPages - 2}">
                                <li><span>...</span></li>
                            </c:if>

                            <c:if test="${totalPages > 1}">
                                <li>
                                    <a class="num-page ${currentPage == totalPages ? 'current' : ''}"
                                       href="${pageContext.request.contextPath}/matches?page=${totalPages}${not empty param.filter_by_player_name ? '&filter_by_player_name='.concat(param.filter_by_player_name) : ''}">
                                            ${totalPages}
                                    </a>
                                </li>
                            </c:if>
                        </c:otherwise>
                    </c:choose>

                    <c:if test="${currentPage < totalPages}">
                        <li>
                            <a class="next"
                               href="${pageContext.request.contextPath}/matches?page=${currentPage + 1}${not empty param.filter_by_player_name ? '&filter_by_player_name='.concat(param.filter_by_player_name) : ''}">
                                &raquo;
                            </a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </c:if>
    </div>
</main>
</body>
</html>