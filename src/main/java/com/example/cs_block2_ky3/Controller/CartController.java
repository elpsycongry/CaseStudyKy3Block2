package com.example.cs_block2_ky3.Controller;

import com.example.cs_block2_ky3.Model.Tour;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartController", value = "/cart-controller")
public class CartController extends HttpServlet {
    private List<Tour> cartList ;

    @Override
    public void init() throws ServletException {
        cartList = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cartAction = req.getParameter("cartAction");
        if (cartAction == null){
            cartAction = "";
        }
        switch (cartAction){
            case "buy":
                buyItem(req,resp);
                break;
            default:
                directTOCartView(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id  = Integer.parseInt(req.getParameter("id"));
        int numberTicket = Integer.parseInt(req.getParameter("numberTicket"));
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        String description = req.getParameter("description");
        String urlImage = req.getParameter("urlImage");
        String namePlace = req.getParameter("namePlace");
        Tour tour = new Tour(id,numberTicket,date,description,urlImage,namePlace);
        cartList.add(tour);
        HttpSession session = req.getSession();
        session.setAttribute("cartList", cartList);
        resp.sendRedirect("/main");
    }

    private void buyItem(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void directTOCartView(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/view/cartview.jsp").forward(req,resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
