package com.example.crbyerscustomersupport;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "sessionListServlet", value = "/sessions")
public class SessionListServlet extends HttpServlet {
    //get my session information and calling it to log in
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("numSessions", SessionListUtil.getNumberOfSessions());
        request.setAttribute("sessionList", SessionListUtil.getAllSessions());
        request.getRequestDispatcher("/WEB-INF/jsp/view/sessions.jsp").forward(request,response);
    }

    //sending session information
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}