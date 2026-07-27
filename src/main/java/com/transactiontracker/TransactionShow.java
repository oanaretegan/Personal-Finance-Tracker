package com.transactiontracker;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TransactionShow {
    public JPanel afisareTranzactie(Transaction t)
    {
        JPanel panel=new JPanel(new GridLayout(1,5,5,5));
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        panel.add(new JLabel(t.getTip().toString()));
        panel.add(new JLabel(t.getSuma()+" LEI"));
        panel.add(new JLabel(t.getData().toString()));
        panel.add(new JLabel(t.getCategorie()));
        panel.add(new JLabel(t.getLocatie()));

        return panel;
    }

    public void showTransactions(JPanel containerPanel, List<Transaction> tranzactii)
    {
        containerPanel.removeAll();
        containerPanel.setLayout(new BoxLayout(containerPanel,BoxLayout.Y_AXIS));
        for(Transaction t:tranzactii)
        {
            JPanel singlePanel=afisareTranzactie(t);
            containerPanel.add(singlePanel);
        }
        containerPanel.validate();
        containerPanel.repaint();
    }

    public List<Transaction> getTodayTransactions(User user, LocalDate today)
    {
        List<Transaction> transactions=new ArrayList<>();
        String sql="SELECT ID,User_ID,Tip,Suma,Data,Categorie,Locatie,ModPlata FROM [Transaction] WHERE User_ID=? AND Data=?";
        try(Connection con=DatabaseConnection.getConnection(); PreparedStatement pstmt=con.prepareStatement(sql))
        {
            pstmt.setInt(1,user.getId());
            pstmt.setDate(2, Date.valueOf(today));

            ResultSet rs=pstmt.executeQuery();
            while(rs.next())
            {
                int id=rs.getInt("ID");
                TransactionType tip = TransactionType.valueOf(rs.getString("Tip"));
                double suma = rs.getDouble("Suma");
                LocalDate data = rs.getDate("Data").toLocalDate();
                String locatie = rs.getString("Locatie");
                String categorie = rs.getString("Categorie");
                PaymentMethod mod = PaymentMethod.valueOf(rs.getString("ModPlata"));

                Transaction t = new Transaction(id, user.getId(), tip, suma, data, categorie, locatie, mod);
                transactions.add(t);
            }
            transactions.sort(Comparator.comparing(Transaction::getData).reversed());
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return transactions;
    }

    public List<Transaction>getTransactionsByRangeDate(User user, LocalDate startDate, LocalDate endDate)
    {
        List<Transaction> transactions=new ArrayList<>();
        String sql="SELECT ID,User_ID,Tip,Suma,Data,Categorie,Locatie,ModPlata FROM [Transaction] WHERE User_ID=? AND Data BETWEEN ? AND ?";
        try(Connection con=new DatabaseConnection().getConnection();PreparedStatement pstmt=con.prepareStatement(sql))
        {
            pstmt.setInt(1,user.getId());
            pstmt.setDate(2, Date.valueOf(startDate));
            pstmt.setDate(3, Date.valueOf(endDate));

            ResultSet rs=pstmt.executeQuery();
            while(rs.next())
            {
                int id=rs.getInt("ID");
                TransactionType tip = TransactionType.valueOf(rs.getString("Tip"));
                double suma = rs.getDouble("Suma");
                LocalDate data = rs.getDate("Data").toLocalDate();
                String locatie = rs.getString("Locatie");
                String categorie = rs.getString("Categorie");
                PaymentMethod mod = PaymentMethod.valueOf(rs.getString("ModPlata"));
                Transaction t = new Transaction(id, user.getId(), tip, suma, data, categorie, locatie, mod);
                transactions.add(t);
            }
            transactions.sort(Comparator.comparing(Transaction::getData).reversed());
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return transactions;
    }

    public List<Transaction>getAllTransactions(User user)
    {
        List<Transaction> transactions=new ArrayList<>();
        String sql="SELECT ID,User_ID,Tip,Suma,Data,Categorie,Locatie,ModPlata FROM [Transaction] WHERE User_ID=?";
        try(Connection con=DatabaseConnection.getConnection(); PreparedStatement pstmt=con.prepareStatement(sql))
        {
            pstmt.setInt(1,user.getId());

            ResultSet rs=pstmt.executeQuery();
            while(rs.next())
            {
                int id=rs.getInt("ID");
                TransactionType tip = TransactionType.valueOf(rs.getString("Tip"));
                double suma = rs.getDouble("Suma");
                LocalDate data = rs.getDate("Data").toLocalDate();
                String locatie = rs.getString("Locatie");
                String categorie = rs.getString("Categorie");
                PaymentMethod mod = PaymentMethod.valueOf(rs.getString("ModPlata"));

                Transaction t = new Transaction(id, user.getId(), tip, suma, data, categorie, locatie, mod);
                transactions.add(t);
            }
            transactions.sort(Comparator.comparing(Transaction::getData).reversed());
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return transactions;
    }

}
