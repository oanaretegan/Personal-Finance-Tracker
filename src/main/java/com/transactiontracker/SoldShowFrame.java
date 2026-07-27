package com.transactiontracker;

import javax.swing.*;
import java.awt.*;

public class SoldShowFrame extends JFrame {
    public SoldShowFrame(User user) {
        setTitle("Sold");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,200);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        JPanel panel=new JPanel(new GridLayout(2,2,10,10));
        JLabel t1=new JLabel("Sold Card");
        JLabel soldCard=new JLabel(String.valueOf(user.getSoldCard())+" LEI");
        JLabel t2=new JLabel("Sold Cash");
        JLabel soldCash=new JLabel(String.valueOf(user.getSoldCash())+ " LEI");

        panel.add(t1);
        panel.add(soldCard);
        panel.add(t2);
        panel.add(soldCash);
        add(panel);
        setVisible(true);

    }
}
