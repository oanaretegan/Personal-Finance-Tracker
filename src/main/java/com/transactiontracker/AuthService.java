package com.transactiontracker;

import java.sql.*;

public class AuthService {

    public User logIn(String username, int pin) {
        String sql = "{CALL sp_LogIn(?,?)}";
        try (Connection con = DatabaseConnection.getConnection(); CallableStatement cstmt = con.prepareCall(sql)) {
            cstmt.setString(1, username);
            cstmt.setInt(2, pin);

            ResultSet rs = cstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("ID");
                String nume = rs.getString("Nume");
                String prenume = rs.getString("Prenume");
                double soldCash = rs.getDouble("SoldCash");
                double soldCard = rs.getDouble("SoldCard");

                return new User(id, username, pin, nume, prenume, soldCash, soldCard);
            }

        } catch (SQLException e) {
            System.err.println("Nu s-a reusit conectarea la baza de date");
        }
        return null;
    }

    public boolean register(String username, int pin, String nume, String prenume, double soldCash, double soldCard) {
        String sql = "{CALL sp_Register(?,?,?,?,?,?)}";
        try (Connection con = DatabaseConnection.getConnection(); CallableStatement cstmt = con.prepareCall(sql)) {
            cstmt.setString(1, username);
            cstmt.setInt(2, pin);
            cstmt.setString(3, nume);
            cstmt.setString(4, prenume);
            cstmt.setDouble(5, soldCash);
            cstmt.setDouble(6, soldCard);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                int result = rs.getInt("Result");
                if (result != -1)
                    return true;
                else return false;
            }
        } catch (SQLException e) {
            System.err.println("Nu s-a reusit conectarea la baza de date");
        }
        return false;
    }

    public boolean deleteAccount(String username)
    {
        String sql = "{CALL sp_DeleteAccount(?)}";
        try(Connection con=DatabaseConnection.getConnection(); CallableStatement cstm=con.prepareCall(sql))
        {
            cstm.setString(1,username);
            ResultSet rs=cstm.executeQuery();
            if(rs.next())
            {
                int result=rs.getInt("Result");
                if(result!=-1)
                    return true;
                else return false;
            }
        }catch(SQLException e)
        {
            System.err.println("Nu s a reusit conectarea la baza de date");
        }
        return false;
    }
}

