package com.yoxaron.tennis_scoreboard.controller.filter;

import com.yoxaron.tennis_scoreboard.utils.ViewResolver;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class ExceptionHandler extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(req, res);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher(ViewResolver.getPath("new-match")).forward(req, res);
        } catch (Exception e) {
            res.sendRedirect(req.getContextPath());
        }
    }
}
