package com.yoxaron.tennis_scoreboard.controller;

import com.yoxaron.tennis_scoreboard.service.OngoingMatchesService;
import com.yoxaron.tennis_scoreboard.service.PlayerService;
import com.yoxaron.tennis_scoreboard.utils.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.yoxaron.tennis_scoreboard.utils.ViewResolver.getPath;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private final PlayerService playerService = PlayerService.getInstance();
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(getPath("new-match")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var pName1 = req.getParameter("playerOne");
        var pName2 = req.getParameter("playerTwo");

        ValidationUtil.checkNames(pName1, pName2);

        var player1 = playerService.findOrSaveByName(pName1.toUpperCase());
        var player2 = playerService.findOrSaveByName(pName2.toUpperCase());

        var ongoingMatch = ongoingMatchesService.createOngoingMatch(player1, player2);

        resp.sendRedirect("/match-score?uuid=" + ongoingMatch.getUuid());
    }
}
