package com.example.cs_block2_ky3.Service;

import com.example.cs_block2_ky3.Model.Tour;
import com.example.cs_block2_ky3.Model.User;

import java.util.List;

public interface iPlaceTicketDAO {
    public List<Tour> getAllTour();

    public Tour getTour(int placeID);

    public void deleteTour(int placeID);

    public void addTour(Tour tour);

    public void editTour(Tour tour);
    public String getUserRole(String username, String password);
    public User getUser(String username, String password);
    public void addUser(User user);
}
