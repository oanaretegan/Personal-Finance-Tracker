package com.transactiontracker;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private int userId;
    private TransactionType tip;
    private double suma;
    private LocalDate data;
    private String categorie;
    private String locatie;
    private PaymentMethod mod;

    public Transaction(int id, int userId, TransactionType tip, double suma, LocalDate data, String categorie, String locatie, PaymentMethod mod) {
        this.id = id;
        this.userId = userId;
        this.tip = tip;
        this.suma = suma;
        this.data = data;
        this.categorie = categorie;
        this.locatie = locatie;
        this.mod = mod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public TransactionType getTip() {
        return tip;
    }

    public void setTip(TransactionType tip) {
        this.tip = tip;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public PaymentMethod getMod() {
        return mod;
    }

    public void setMod(PaymentMethod mod) {
        this.mod = mod;
    }
}
