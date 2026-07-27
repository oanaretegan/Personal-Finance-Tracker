package com.transactiontracker;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionManager {
    private void modifySold(User user, TransactionType tip,PaymentMethod mod, double suma) {
        if(tip.toString().equals("VENIT"))
        {
            if(mod.toString().equals("CASH"))
            {
                double cash=user.getSoldCash()+suma;
                user.setSoldCash(cash);
            }
            else {
                double card=user.getSoldCard()+suma;
                user.setSoldCard(card);
            }
        }
        else
        {
            if(mod.toString().equals("CASH"))
            {
                double cash=user.getSoldCash()-suma;
                user.setSoldCash(cash);
            }
            else {
                double card=user.getSoldCard()-suma;
                user.setSoldCard(card);
            }
        }
    }
    public Transaction addTransaction(User user, TransactionType tip, double suma, LocalDate data, String categorie, String locatie, PaymentMethod mod)
    {
        String sql="{CALL sp_AddTransaction(?,?,?,?,?,?,?)}";
        try(Connection con=DatabaseConnection.getConnection(); CallableStatement cstm=con.prepareCall(sql))
        {
            cstm.setString(1,user.getUsername());
            cstm.setString(2,tip.toString());
            cstm.setDouble(3,suma);
            cstm.setObject(4,java.sql.Date.valueOf(data));
            cstm.setString(5,locatie);
            cstm.setString(6,categorie);
            cstm.setString(7,mod.toString());
            ResultSet rs=cstm.executeQuery();
            if(rs.next())
            {
                int id=rs.getInt("ID");
                modifySold(user,tip,mod,suma);
                return new Transaction(id,user.getId(),tip,suma,data,categorie,locatie,mod);
            }
        }
        catch(Exception e)
        {
            System.err.println("Nu s-a reusit conectarea la baza de date");
            e.printStackTrace();
        }
        return null;
    }

}
