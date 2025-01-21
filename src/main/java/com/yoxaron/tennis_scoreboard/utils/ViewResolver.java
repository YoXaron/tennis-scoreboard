package com.yoxaron.tennis_scoreboard.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ViewResolver {

    private static final String VIEW_FORMAT = "/WEB-INF/template/%s.jsp";

    public static String getPath(String viewName) {
        return String.format(VIEW_FORMAT, viewName);
    }
}
