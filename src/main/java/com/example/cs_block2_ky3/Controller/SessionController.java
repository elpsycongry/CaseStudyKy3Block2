package com.example.cs_block2_ky3.Controller;

import com.example.cs_block2_ky3.Model.User;
import com.example.cs_block2_ky3.Service.PlaceTicketDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "SessionController", value = "/sessionController")
public class SessionController extends HttpServlet {
    PlaceTicketDAO placeTicketDAO ;

    @Override
    public void init() throws ServletException {
        placeTicketDAO = new PlaceTicketDAO();
    }
    private final String CUSTOMER = "customer";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println(action);
        if (action == null){
            action = "";
        }
        switch (action) {
            case "login":
                login(req, resp);
                break;
            case "register":
                register(req,resp);
                break;
            default:
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("role",CUSTOMER);
        System.out.println("Da dang xuat va chuyen ve trang chu voi role" + req.getSession().getAttribute("role"));
        resp.sendRedirect("/view/test.jsp");
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("userName");
        String pass = req.getParameter("password");
        String role = placeTicketDAO.getUserRole(user,pass);
        if (role != null){
            req.getSession().setAttribute("role",role);
            resp.sendRedirect("/main");
        }
        else {
            resp.sendRedirect("/login_logout/login");
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("userName");
        String pass = req.getParameter("password");
        int role = Integer.parseInt(req.getParameter("role"));

        User user = placeTicketDAO.getUser(username,pass);
        if (user != null){
            resp.sendRedirect("/login_logout/logout.jsp");
        }
        else {
            user = new User(username,pass,role);
            placeTicketDAO.addUser(user);
            resp.sendRedirect("/main");
        }

    }
}
