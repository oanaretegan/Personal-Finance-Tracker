package com.transactiontracker;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class EditProfileFrame extends JFrame {
    public EditProfileFrame( User user) {
        setTitle("Editare Profil");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1,101,10));

        JButton nButton=new JButton("Editare Nume");
        JButton pButton=new JButton("Editare Prenume");
        JButton uButton=new JButton("Editare Username");
        JButton pinButton=new JButton("Editare Pin");
        panel.add(nButton);
        panel.add(pButton);
        panel.add(uButton);
        panel.add(pinButton);
        add(panel);

        nButton.addActionListener(e->{
            JFrame frame=new JFrame();
            frame.setSize(250,200);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new GridBagLayout());

            JPanel panel1=new JPanel();
            panel1.setLayout(new GridLayout(2,2,10,10));

            JButton updateButton=new JButton("Actualizeaza");
            JTextField name=new JTextField();
            JLabel nume=new JLabel("Nume");

            panel1.add(nume);
            panel1.add(name);
            panel1.add(new JLabel(""));
            panel1.add(updateButton);

            frame.add(panel1);
            frame.setVisible(true);

            updateButton.addActionListener(ev->{

                String numeNou=name.getText();
                try{

                    editSurname(user.getUsername(),numeNou);
                    user.setNume(numeNou);
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }

                frame.dispose();
                EditProfileFrame editProfileFrame=this;
                editProfileFrame.dispose();
                new MainFrame(user).setVisible(true);
            });
        });

        pButton.addActionListener(e->{
            JFrame frame=new JFrame();
            frame.setSize(250,200);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new GridBagLayout());

            JPanel panel1=new JPanel();
            panel1.setLayout(new GridLayout(2,2,10,10));

            JButton updateButton=new JButton("Actualizeaza");
            JTextField name=new JTextField();
            JLabel prenume=new JLabel("Prenume");

            panel1.add(prenume);
            panel1.add(name);
            panel1.add(new JLabel(""));
            panel1.add(updateButton);

            frame.add(panel1);
            frame.setVisible(true);

            updateButton.addActionListener(ev->{

                String prenumeNou=name.getText();
                try{

                    editName(user.getUsername(),prenumeNou);
                    user.setPrenume(prenumeNou);
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }

                frame.dispose();
                EditProfileFrame editProfileFrame=this;
                editProfileFrame.dispose();
                new MainFrame(user).setVisible(true);
            });
        });

        uButton.addActionListener(e->{
            JFrame frame=new JFrame();
            frame.setSize(250,200);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new GridBagLayout());

            JPanel panel1=new JPanel();
            panel1.setLayout(new GridLayout(2,2,10,10));
            JButton updateButton=new JButton("Actualizeaza");
            JTextField userName=new JTextField();
            JLabel numeUser=new JLabel("Username:");

            panel1.add(numeUser);
            panel1.add(userName);
            panel1.add(new JLabel(""));
            panel1.add(updateButton);
            frame.add(panel1);
            frame.setVisible(true);

            updateButton.addActionListener(ev->{
                String newUsername=userName.getText();

                try{
                    if(editUsername(user.getUsername(),newUsername))
                    {
                        user.setUsername(newUsername);
                    }
                    else return;
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }

                frame.dispose();
                EditProfileFrame editProfileFrame=this;
                editProfileFrame.dispose();
                new MainFrame(user).setVisible(true);
            });

        });

        pinButton.addActionListener(e->{
            JFrame frame=new JFrame();
            frame.setSize(250,200);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new GridBagLayout());

            JPanel panel1=new JPanel();
            panel1.setLayout(new GridLayout(2,2,10,10));

            JButton updateButton=new JButton("Actualizeaza");
            JTextField pin=new JTextField();
            JLabel pinText=new JLabel("Pin:");
            panel1.add(pinText);
            panel1.add(pin);
            panel1.add(new JLabel(""));
            panel1.add(updateButton);

            frame.add(panel1);
            frame.setVisible(true);

            updateButton.addActionListener(ev->{
                int pinNou=0;
                int pinTest=Integer.parseInt(pin.getText());
                if(pinTest>=1000 && pinTest<=9999)
                {
                    pinNou=pinTest;
                }
                else{ JOptionPane.showMessageDialog(null, "Pin-ul trebuie sa fie un numar intre 1000 si 9999"); return;}
                try{
                    editPin(user.getUsername(),pinNou);
                    user.setPin(pinNou);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                frame.dispose();
                EditProfileFrame editProfileFrame=this;
                editProfileFrame.dispose();
                new MainFrame(user).setVisible(true);
            });

        });
        setVisible(true);

    }

    public void editSurname(String username, String name )
    {
        String sql="{CALL sp_EditName(?,?)}";
        try(Connection con= DatabaseConnection.getConnection(); CallableStatement cstm=con.prepareCall(sql))
        {
            cstm.setString(1,username);
            cstm.setString(2,name);

            ResultSet rs=cstm.executeQuery();

            if(rs.next())
            {
                int result=rs.getInt("Result");
                if(result==1)
                {
                    JOptionPane.showMessageDialog(null, "Nume actualizat cu succes!");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Numele nu a putut fi actualizat!");
                }
            }
        }
        catch(SQLException e)
        {
            System.err.println("Nu s-a reusit conectarea la baza de date");
            e.printStackTrace();
        }
    }

    public void editName(String username, String prenume )
    {
        String sql="{CALL sp_EditPrenume(?,?)}";
        try(Connection con= DatabaseConnection.getConnection(); CallableStatement cstm=con.prepareCall(sql))
        {
            cstm.setString(1,username);
            cstm.setString(2,prenume);

            ResultSet rs=cstm.executeQuery();

            if(rs.next())
            {
                int result=rs.getInt("Result");
                if(result==1)
                {
                    JOptionPane.showMessageDialog(null, "Prenume actualizat cu succes!");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Prenumele nu a putut fi actualizat!");
                }
            }
        }
        catch(SQLException e)
        {
            System.err.println("Nu s-a reusit conectarea la baza de date");
            e.printStackTrace();
        }
    }

    public boolean editUsername(String username, String newUsername)
    {
        String  sql="{CALL sp_EditUsername(?,?)}";
        try(Connection con=DatabaseConnection.getConnection(); CallableStatement cstm=con.prepareCall(sql))
        {
            cstm.setString(1,username);
            cstm.setString(2,newUsername);

            ResultSet rs=cstm.executeQuery();
            if(rs.next())
            {
                int result=rs.getInt("Result");
                if(result==1)
                {
                    JOptionPane.showMessageDialog(null, "Username actualizat cu succes!");
                    return true;
                }
                else {
                    JOptionPane.showMessageDialog(null, "Acest username este deja folosit!");
                    return false;
                }
            }

        }
        catch(SQLException e)
        {
            System.err.println("Nu s-a reusit conectarea la baza de date");
            e.printStackTrace();
        }
        return false;
    }

    public void editPin(String username, int newPin)
    {
        String sql="{CALL sp_EditPin(?,?)}";
        try(Connection con=DatabaseConnection.getConnection(); CallableStatement cstm=con.prepareCall(sql))
        {
            cstm.setString(1,username);
            cstm.setInt(2,newPin);

            ResultSet rs=cstm.executeQuery();
            if(rs.next())
            {
                int result=rs.getInt("Result");
                if(result==1)
                {
                    JOptionPane.showMessageDialog(null, "Pin actualizat cu succes!");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Pin nu a putut fi actualizat!");
                }
            }

        }
        catch(SQLException e) {
            System.err.println("Nu s-a reusit conectarea la baza de date");
            e.printStackTrace();
        }
    }
}
