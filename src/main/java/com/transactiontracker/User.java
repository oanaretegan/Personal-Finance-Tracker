package com.transactiontracker;

public class User {
    private int id;
    private String username;
    private int pin;
    private String nume;
    private String prenume;
    private double soldCash;
    private double soldCard;

    public User(int id,String username, int pin, String nume, String prenume, double soldCash, double soldCard) {
        this.id=id;
        this.username = username;
        this.pin = pin;
        this.nume = nume;
        this.prenume = prenume;
        this.soldCash = soldCash;
        this.soldCard = soldCard;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public double getSoldCash() {
        return soldCash;
    }

    public void setSoldCash(double soldCash) {
        this.soldCash = soldCash;
    }

    public double getSoldCard() {
        return soldCard;
    }

    public void setSoldCard(double soldCard) {
        this.soldCard = soldCard;
    }
}
