package com.transactiontracker;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class RegisterFrame extends JFrame {
    public RegisterFrame(WelcomeFrame frame) {
        setTitle("Inregistrare");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new GridBagLayout());

        JPanel registerPanel=new JPanel();
        registerPanel.setLayout(new GridLayout(7, 1, 10, 10));
        JTextField usernameField = new JTextField(50);
        JPasswordField pinField = new JPasswordField(50);
        JTextField nameField=new JTextField(50);
        JTextField surnameField=new JTextField(50);
        JTextField soldCard=new JTextField(50);
        JTextField soldCash=new JTextField(50);
        JButton registerButton=new JButton("Inregistreaza-te");

        registerPanel.add(new JLabel("Nume:"));
        registerPanel.add(nameField);
        registerPanel.add(new JLabel("Prenume:"));
        registerPanel.add(surnameField);
        registerPanel.add(new JLabel("Username:"));
        registerPanel.add(usernameField);
        registerPanel.add(new JLabel("Pin:"));
        registerPanel.add(pinField);
        registerPanel.add(new JLabel("Sold Card:"));
        registerPanel.add(soldCard);
        registerPanel.add(new JLabel("Sold Cash:"));
        registerPanel.add(soldCash);
        registerPanel.add(new JLabel(""));
        registerPanel.add(registerButton);

        add(registerPanel);
        registerButton.addActionListener(e -> {
                String nume = nameField.getText();
                String prenume = surnameField.getText();
                String username = usernameField.getText();
                int pin=0;
                double cash=0.0;
                double card=0.0;

                try {
                    int pinTest=Integer.parseInt(new String(pinField.getPassword()));
                    if(pinTest>=1000 && pinTest<=9999)
                    {
                        pin=pinTest;
                    }
                    else { JOptionPane.showMessageDialog(null, "Pin-ul trebuie sa fie un numar intre 1000 si 9999"); return;}
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Introduce-ti un numar intreg ca si pin", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    cash = Double.parseDouble(soldCash.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Introduce-ti un numar real ca si sold cash", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    card = Double.parseDouble(soldCard.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Introduce-ti un numar real ca si sold card", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (new AuthService().register(username, pin, nume, prenume, cash, card)) {
                    JOptionPane.showMessageDialog(null, "Inregistrare efectuata cu succes! Va rugam sa va conectati!");
                    RegisterFrame registerFrame = this;
                    registerFrame.dispose();
                    frame.dispose();
                    new WelcomeFrame().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Username ul ales nu este disponibil! Incercati din nou");
                    return;
                }

        });
        setVisible(true);
    }
}

