package com.yoxaron.tennis_scoreboard.controller;

import com.yoxaron.tennis_scoreboard.model.domain.OngoingMatch;
import com.yoxaron.tennis_scoreboard.service.MatchScoreCalculationService;
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
    private final MatchScoreCalculationService calculationService = MatchScoreCalculationService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String matchUuid = req.getParameter("uuid");
        OngoingMatch ongoingMatch = ongoingMatchesService.get(UUID.fromString(matchUuid));
        req.setAttribute("ongoingMatch", ongoingMatch);
        req.getRequestDispatcher(getPath("match-score")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Long playerId = Long.valueOf(req.getParameter("winnerId"));

        OngoingMatch ongoingMatch = ongoingMatchesService.get(uuid);

        calculationService.updateScore(ongoingMatch, playerId);

        if (ongoingMatch.isFinished()) {
            ongoingMatchesService.remove(ongoingMatch.getUuid());
            req.setAttribute("ongoingMatch", ongoingMatch);
        }

        resp.sendRedirect("/match-score?uuid=" + uuid);
    }
}
