package com.transactiontracker;

import javax.swing.*;
import java.awt.*;

public class WelcomeFrame extends JFrame {
    public WelcomeFrame() {
        setTitle("Bine ai venit");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));

        JButton loginbutton = new JButton("Log In");
        JButton registerbutton = new JButton("Inregistrare");

        panel.add(loginbutton);
        panel.add(registerbutton);
        add(panel);
        loginbutton.addActionListener(e -> {
            new LogInFrame(this).setVisible(true);
        });
        registerbutton.addActionListener(e -> {
            new RegisterFrame(this).setVisible(true);
        });

        setVisible(true);
    }
}
