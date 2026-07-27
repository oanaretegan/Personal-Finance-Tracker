package com.transactiontracker;

import javax.swing.*;
import java.awt.*;

public class LogInFrame extends JFrame {
    public LogInFrame(WelcomeFrame frame) {
        setTitle("Log In");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new GridBagLayout());

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JTextField usernameField = new JTextField(50);
        JPasswordField pinField = new JPasswordField(50);
        JButton loginButton = new JButton("Login");

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Pin:"));
        loginPanel.add(pinField);
        loginPanel.add(new JLabel(""));
        loginPanel.add(loginButton);
        add(loginPanel);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            try
            {
                int pin=Integer.parseInt(new  String(pinField.getPassword()));
                User u=new AuthService().logIn(username,pin);
                if(u!=null){
                    JOptionPane.showMessageDialog(null, "Logare efectuata cu succes!");
                    LogInFrame Lframe=this;
                    Lframe.dispose();
                    frame.dispose();
                    new MainFrame(u).setVisible(true);
                }
                else JOptionPane.showMessageDialog(null, "Username ul sau pin ul sunt gresite!");
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Pin ul trebuie sa fie un numar intreg!","Eroare",JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
