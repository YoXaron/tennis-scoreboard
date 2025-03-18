package com.yoxaron.tennis_scoreboard.controller;

import com.yoxaron.tennis_scoreboard.model.OngoingMatch;
import com.yoxaron.tennis_scoreboard.service.OngoingMatchesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

import static com.yoxaron.tennis_scoreboard.utils.ViewResolver.getPath;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String matchUuid = req.getParameter("uuid");
        OngoingMatch ongoingMatch = ongoingMatchesService.getOngoingMatch(UUID.fromString(matchUuid));
        System.out.println(ongoingMatch);
        req.setAttribute("ongoingMatch", ongoingMatch);
        req.getRequestDispatcher(getPath("match-score")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        В соответствии с тем, какой игрок выиграл очко, обновляет счёт матча

//        Если матч не закончился - рендерится таблица счёта матча с кнопками, описанными выше

//        Если матч закончился:
//        Удаляем матч из коллекции текущих матчей
//        Записываем законченный матч в SQL базу данных
//        Рендерим финальный счёт

//        Обрабатывает POST запросы к /match-score
//        Через OngoingMatchesService получает экземпляр класса Match для текущего матча, который является моделью/частью модели MatchScoreModel
//        Через MatchScoreCalculationService обновляет счёт в матче
//        Если матч закончился - через FinishedMatchesPersistenceService сохраняет законченный матч в базу данных
//        С помощью JSP шаблона отображает MatchScoreModel в виде отрендеренного HTML

        super.doPost(req, resp);
    }
}
