package com.example.cs_block2_ky3.Controller;

import com.example.cs_block2_ky3.Model.Tour;
import com.example.cs_block2_ky3.Service.PlaceTicketDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "TheBandController", value = "/main")
public class MainController extends HttpServlet {
    private List<Tour> listTour;
    PlaceTicketDAO placeTicketDAO;

    @Override
    public void init() throws ServletException {
        placeTicketDAO = new PlaceTicketDAO();
        listTour = placeTicketDAO.getAllTour();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // To control the direction
        String directTo = req.getParameter("directTo");
        if (directTo == null) {
            directTo = "";
        }
        switch (directTo) {
            case "editForm":
                directToEditForm(req, resp);
                break;
            case "deleteAction":
                deleteTour(req, resp);
                break;
                case "addForm":
                directToAddForm(req, resp);
                break;
            default:
                viewMain(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // To Control CRUD action
        String action = req.getParameter("action");
        System.out.println(action);

        switch (action) {
            case "editTour":
                editTour(req, resp);
                break;
            case "addTour":
                addTour(req,resp);
                break;
            default:
                doGet(req,resp);
        }
    }

    public void viewMain(HttpServletRequest req, HttpServletResponse resp)  {
        HttpSession session = req.getSession();
        session.setAttribute("listTour", listTour);
        try {
            req.getRequestDispatcher("/view/test.jsp").include(req,resp);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private void directToAddForm(HttpServletRequest req, HttpServletResponse resp) {

    }

    public void directToEditForm(HttpServletRequest req, HttpServletResponse resp)  {
        int id = Integer.parseInt(req.getParameter("idTour"));
        Tour tourNeedEdit = placeTicketDAO.getTour(id);
        System.out.println(tourNeedEdit);
        req.setAttribute("tourNeedEdit", tourNeedEdit);
        try {
            req.getRequestDispatcher("/CRUD-view/edit.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void editTour(HttpServletRequest req, HttpServletResponse resp) {
        int id  = Integer.parseInt(req.getParameter("id"));
        int numberTicket = Integer.parseInt(req.getParameter("numberTicket"));
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        String description = req.getParameter("description");
        String urlImage = req.getParameter("urlImage");
        String namePlace = req.getParameter("namePlace");
        Tour tour = new Tour(id,numberTicket,date,description,urlImage,namePlace);
        placeTicketDAO.editTour(tour);
        listTour = updateListTour();
        viewMain(req,resp);
    }

    private void addTour(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        int numberTicket = Integer.parseInt(req.getParameter("numberTicket"));
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        String description = req.getParameter("description");
        String urlImage = req.getParameter("urlImage");
        String namePlace = req.getParameter("namePlace");
        Tour tour = new Tour(numberTicket,date,description,urlImage,namePlace);
        placeTicketDAO.addTour(tour);
        listTour = updateListTour();
        viewMain(req, resp);
    }

    public void deleteTour(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("idTour"));
        placeTicketDAO.deleteTour(id);
        listTour = updateListTour();
        viewMain(req, resp);
    }



    public List<Tour> updateListTour() {
        PlaceTicketDAO placeTicketDAO = new PlaceTicketDAO();
        return placeTicketDAO.getAllTour();
    }

}
