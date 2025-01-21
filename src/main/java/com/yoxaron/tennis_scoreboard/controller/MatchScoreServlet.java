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

        super.doPost(req, resp);
    }
}
