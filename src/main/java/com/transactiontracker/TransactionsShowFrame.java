package com.transactiontracker;

import javax.swing.*;
import java.util.List;

public class TransactionsShowFrame extends JFrame {
    public TransactionsShowFrame(String period,List<Transaction> transactions) {
        switch(period){
            case "Day":
                setTitle("Tranzactiile din ziua de azi");
                break;
            case "Week":
                setTitle("Tranzactiile din ultima saptamana");
                break;
            case "Month":
                setTitle("Tranzactiile din luna curenta");
                break;
            case "3Months":
                setTitle("Tranzactiile din ultimele 3 luni");
                break;
            case "6Months":
                setTitle("Tranzactiile din ultimele 6 luni");
                break;
            case "Year":
                setTitle("Tranzactiile din anul curent");
                break;
            case "All":
                setTitle("Toate tranzactiile");
                break;
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450,400);
        setLocationRelativeTo(null);
        JPanel transactionPanel = new JPanel();
        TransactionShow t=new TransactionShow();
        t.showTransactions(transactionPanel, transactions);
        add(transactionPanel);
        setVisible(true);
    }
}
