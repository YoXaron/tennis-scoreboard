package com.yoxaron.tennis_scoreboard.controller;

import com.yoxaron.tennis_scoreboard.dto.MatchDto;
import com.yoxaron.tennis_scoreboard.service.FinishedMatchesPersistenceService;
import com.yoxaron.tennis_scoreboard.utils.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    private static final int PAGE_SIZE = 5;
    private final FinishedMatchesPersistenceService persistenceService = FinishedMatchesPersistenceService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("filter_by_player_name");
        String pageParam = req.getParameter("page");

        int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;

        List<MatchDto> matches = persistenceService.find(name, page, PAGE_SIZE);
        int totalMatches = persistenceService.countMatches(name);

        int totalPages = totalMatches / PAGE_SIZE + 1;

        req.setAttribute("matches", matches);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("currentPage", page);

        req.getRequestDispatcher(ViewResolver.getPath("matches")).forward(req, resp);
    }
}
