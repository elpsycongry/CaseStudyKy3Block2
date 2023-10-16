package com.example.cs_block2_ky3.Model;

import java.time.LocalDate;

public class Tour {
    private int id;
    private int numberTicket;
    private LocalDate date;
    private String description;
    private String urlImage;
    private String namePlace;

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", numberTicket=" + numberTicket +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", namePlace='" + namePlace + '\'' +
                '}';
    }



    public Tour() {
    }

    public Tour(int numberTicket, LocalDate date, String description, String urlImage, String namePlace) {
        this.numberTicket = numberTicket;
        this.date = date;
        this.description = description;
        this.urlImage = urlImage;
        this.namePlace = namePlace;
    }

    public Tour(int id, int numberTicket, LocalDate date, String description, String urlImage, String namePlace) {
        this.id = id;
        this.numberTicket = numberTicket;
        this.date = date;
        this.description = description;
        this.urlImage = urlImage;
        this.namePlace = namePlace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberTicket() {
        return numberTicket;
    }

    public void setNumberTicket(int numberTicket) {
        this.numberTicket = numberTicket;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getNamePlace() {
        return namePlace;
    }

    public void setNamePlace(String namePlace) {
        this.namePlace = namePlace;
    }
}
