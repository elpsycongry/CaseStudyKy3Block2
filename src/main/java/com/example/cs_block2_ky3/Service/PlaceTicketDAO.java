package com.example.cs_block2_ky3.Service;

import com.example.cs_block2_ky3.Model.Tour;
import com.example.cs_block2_ky3.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaceTicketDAO implements iPlaceTicketDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/theband";
    private static final String USER = "tung";
    private static final String PASS = "tung";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    @Override
    public List<Tour> getAllTour() {
        List<Tour> list = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from tour");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tour tour = new Tour();
                tour.setId(rs.getInt("id"));
                tour.setNamePlace(rs.getString("namePlace"));
                tour.setUrlImage(rs.getString("urlImage"));
                tour.setDescription(rs.getString("description"));
                tour.setDate(rs.getDate("date").toLocalDate());
                tour.setNumberTicket(rs.getInt("numberTicket"));
                list.add(tour);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Tour getTour(int id) {
        Tour tour = new Tour();
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from tour where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tour.setId(rs.getInt("id"));
                tour.setNamePlace(rs.getString("namePlace"));
                tour.setUrlImage(rs.getString("urlImage"));
                tour.setDescription(rs.getString("description"));
                tour.setDate(rs.getDate("date").toLocalDate());
                tour.setNumberTicket(rs.getInt("numberTicket"));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tour;
    }

    @Override
    public void deleteTour(int id) {
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE from tour where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addTour(Tour tour) {
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("insert into tour (namePlace, urlImage, description, date, numberTicket) values (?,?,?,?,?)");
            ps.setString(1, tour.getNamePlace());
            ps.setString(2, tour.getUrlImage());
            ps.setString(3, tour.getDescription());
            ps.setString(4, String.valueOf(tour.getDate()));
            ps.setInt(5, tour.getNumberTicket());
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void editTour(Tour tour) {
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE tour set namePlace = ?, urlImage = ?, description = ?, date = ? , numberTicket = ? where id = ?");
            ps.setString(1, tour.getNamePlace());
            ps.setString(2, tour.getUrlImage());
            ps.setString(3, tour.getDescription());
            ps.setString(4, String.valueOf(tour.getDate()));
            ps.setInt(5, tour.getNumberTicket());
            ps.setInt(6, tour.getId());
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getUserRole(String username, String password) {
        Connection connection = getConnection();
        String role = null;
        try {
            CallableStatement cs = connection.prepareCall("{call getPermission(?,?)}");
            cs.setString(1,username);
            cs.setString(2,password);
            ResultSet rs = cs.executeQuery();
            if (rs.next()){
                role = rs.getString("permission");
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return role;
    }

    @Override
    public User getUser(String username, String password) {
        Connection connection = getConnection();
        User user = null;
        try {
            CallableStatement callableStatement = connection.prepareCall("{call getExitUser(?,?)}");
            callableStatement.setString(1,username);
            callableStatement.setString(2,password);
            ResultSet rs = callableStatement.executeQuery();
            if (rs.next()){
                user = new User(rs.getString("username"),rs.getString("password"),rs.getInt("role"));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        Connection connection = getConnection();
        try {
            CallableStatement callableStatement  =connection.prepareCall("call addUser(?,?,?)");
            callableStatement.setString(1,user.getUsername());
            callableStatement.setString(2,user.getPassword());
            callableStatement.setInt(3,user.getRole());
            callableStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
