package com.yoxaron.tennis_scoreboard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MatchScoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        Извлекает из коллекции экземпляр класса Match

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
