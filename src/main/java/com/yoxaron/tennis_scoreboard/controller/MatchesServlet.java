package com.yoxaron.tennis_scoreboard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MatchesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        Адрес - /matches?page=$page_number&filter_by_player_name=$player_name. GET параметры:

//        page - номер страницы. Если параметр не задан, подразумевается первая страница
//        filter_by_player_name - имя игрока, матчи которого ищем. Если параметр не задан, отображаются все матчи

        super.doGet(req, resp);
    }
}
