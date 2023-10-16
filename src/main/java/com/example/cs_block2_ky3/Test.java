package com.example.cs_block2_ky3;

import com.example.cs_block2_ky3.Model.Tour;
import com.example.cs_block2_ky3.Service.PlaceTicketDAO;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

public class Test{
    public static void main(String[] args) {
        PlaceTicketDAO placeTicketDAO = new PlaceTicketDAO();
        System.out.println(placeTicketDAO.getUserRole("ad","123"));
    }
}