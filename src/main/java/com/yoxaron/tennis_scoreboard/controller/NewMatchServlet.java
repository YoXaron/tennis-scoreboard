package com.yoxaron.tennis_scoreboard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class NewMatchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        отдает форму new-match

        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        проверяет существование игроков в БД в таблице players
//        если игрока не существует - создаем

//        Создаём экземпляр класса, содержащего айди игроков и текущий счёт,
//        и кладём в коллекцию текущих матчей (существующую только в памяти приложения, либо в key-value storage).
//        Ключом коллекции является UUID, значением - счёт в матче

//        Редирект на страницу /match-score?uuid=$match_id

        super.doPost(req, resp);
    }
}
