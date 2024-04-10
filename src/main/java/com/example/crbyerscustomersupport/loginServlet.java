package com.example.crbyerscustomersupport;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

@WebServlet(name = "loginServlet", value = "/login")
public class loginServlet extends HttpServlet {

    public static final Map<String, String> userDB = new Hashtable<>();
    static {
        userDB.put("Chelsie","admin123");
        userDB.put("Oryol","password123");
        userDB.put("Amanda","password456");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        //check if logged in and go to main page
        if (session.getAttribute("username")!= null) {
            response.sendRedirect("ticket");
            return;
        }

        //not logged in, go to log in page
        request.setAttribute("loginFailed", false);
        request.getRequestDispatcher("WEB-INF/jsp/view/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //check if already logged in
        if (session.getAttribute("username") != null) {
            response.sendRedirect("ticket");
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //check values and log in issues
        if (username ==null || password == null || !loginServlet.userDB.containsKey(username)
                ||!password.equals(loginServlet.userDB.get(username))) {
            request.setAttribute("loginFailed", true);
            request.getRequestDispatcher("WEB-INF/jsp/view/login.jsp").forward(request, response);
        }
        //login successful
        else {
            session.setAttribute("username", username);
            request.changeSessionId();
            response.sendRedirect("ticket");
        }
    }
}
