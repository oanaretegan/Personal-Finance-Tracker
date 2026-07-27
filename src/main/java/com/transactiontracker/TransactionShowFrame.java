package com.transactiontracker;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionShowFrame extends JFrame {
    public TransactionShowFrame(User user){
        setTitle("Rapoarte Tranzactii");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        JPanel transactionPanel = new JPanel();
        transactionPanel.setLayout(new GridLayout(7,1,10,10));

        JButton dButton=new JButton("Azi");
        JButton wButton=new JButton("Aceasta saptamana");
        JButton mButton=new JButton("Aceasta luna");
        JButton m3Button=new JButton("Ultimele 3 luni");
        JButton m6Button=new JButton("Ultimele 6 luni");
        JButton yButton=new JButton("Anul acesta");
        JButton aButton=new JButton("Toate tranzactiile");

        transactionPanel.add(dButton);
        transactionPanel.add(wButton);
        transactionPanel.add(mButton);
        transactionPanel.add(m3Button);
        transactionPanel.add(m6Button);
        transactionPanel.add(yButton);
        transactionPanel.add(aButton);
        add(transactionPanel);

        dButton.addActionListener(e->{
            TransactionShow t=new TransactionShow();
            List<Transaction> todayTransactions=t.getTodayTransactions(user, LocalDate.now());
            TransactionsShowFrame todayFrame=new TransactionsShowFrame("Day",todayTransactions);
            todayFrame.setVisible(true);
        });

        wButton.addActionListener(e->{
            TransactionShow w=new TransactionShow();
            LocalDate endDate=LocalDate.now();
            LocalDate startDate=endDate.minusDays(7);
            List<Transaction> weekTransactions=w.getTransactionsByRangeDate(user, startDate,endDate);
            TransactionsShowFrame weekFrame=new TransactionsShowFrame("Week",weekTransactions);
            weekFrame.setVisible(true);
        });

        mButton.addActionListener(e->{
            TransactionShow m=new TransactionShow();
            LocalDate endDate=LocalDate.now();
            LocalDate startDate=endDate.minusDays(endDate.getDayOfMonth());
            List<Transaction> weekTransactions=m.getTransactionsByRangeDate(user, startDate,endDate);
            TransactionsShowFrame monthFrame=new TransactionsShowFrame("Month",weekTransactions);
            monthFrame.setVisible(true);
        });

        m3Button.addActionListener(e->{
            TransactionShow m3=new TransactionShow();
            LocalDate endDate=LocalDate.now();
            LocalDate startDate=endDate.minusMonths(3);
            List<Transaction> months3Transactions=m3.getTransactionsByRangeDate(user, startDate,endDate);
            TransactionsShowFrame months3Frame=new TransactionsShowFrame("3Months",months3Transactions);
            months3Frame.setVisible(true);
        });

        m6Button.addActionListener(e->{
            TransactionShow m6=new TransactionShow();
            LocalDate endDate=LocalDate.now();
            LocalDate startDate=endDate.minusMonths(6);
            List<Transaction> months6Transactions=m6.getTransactionsByRangeDate(user, startDate,endDate);
            TransactionsShowFrame months6Frame=new TransactionsShowFrame("6Months",months6Transactions);
            months6Frame.setVisible(true);
        });

        yButton.addActionListener(e->{
            TransactionShow y=new TransactionShow();
            LocalDate endDate=LocalDate.now();
            LocalDate startDate=LocalDate.now().withDayOfYear(1);
            List<Transaction> yearTransactions=y.getTransactionsByRangeDate(user, startDate,endDate);
            TransactionsShowFrame yearFrame=new TransactionsShowFrame("Year",yearTransactions);
            yearFrame.setVisible(true);
        });

        aButton.addActionListener(e->{
            TransactionShow a=new TransactionShow();
            List<Transaction> allTransactions=a.getAllTransactions(user);
            TransactionsShowFrame allFrame=new TransactionsShowFrame("All",allTransactions);
            allFrame.setVisible(true);
        });

        setVisible(true);
    }
}
