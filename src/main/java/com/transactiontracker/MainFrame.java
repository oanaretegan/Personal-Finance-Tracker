package com.transactiontracker;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame(User user) {
        setTitle("Pagina Principala");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JLabel welcomeLabel= new JLabel("Buna "+user.getNume()+" "+user.getPrenume()+"!");
        welcomeLabel.setFont(new Font("Times New Roman",Font.BOLD,20));
        topPanel.add(welcomeLabel);

        add(topPanel,BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new GridLayout(6,1,10,10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));
        JButton seeS=new JButton("Vezi Sold");
        JButton addT=new JButton("Adauga Tranzactie");
        JButton seeT=new JButton("Vezi Tranzactii");
        JButton editP=new JButton("Editeaza profilul");
        JButton disconect=new JButton("Deconectare");
        JButton delete=new JButton("Sterge Cont");

        bottomPanel.add(seeS);
        bottomPanel.add(addT);
        bottomPanel.add(seeT);
        bottomPanel.add(editP);
        bottomPanel.add(disconect);
        bottomPanel.add(delete);

        add(bottomPanel,BorderLayout.CENTER);

        seeS.addActionListener(e ->{
            new SoldShowFrame(user).setVisible(true);
        });

        addT.addActionListener(e ->{
            new AddTransactionFrame(user).setVisible(true);
        });

        seeT.addActionListener(e ->{
            new TransactionShowFrame(user).setVisible(true);
        });

        editP.addActionListener(e ->{
            new EditProfileFrame(user).setVisible(true);
        });

        disconect.addActionListener(e ->{
            this.dispose();
            new WelcomeFrame().setVisible(true);
        });

        delete.addActionListener(e ->{
            try
            {
                User u=user;
                if(new AuthService().deleteAccount(u.getUsername()))
                {
                    JOptionPane.showMessageDialog(null,"Contul a fost sters cu succes!");
                    MainFrame frame=this;
                    this.dispose();
                    new WelcomeFrame().setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Contul nu a putut fi sters!","Eroare",JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"A aparut o eroare!","Eroare",JOptionPane.ERROR_MESSAGE);
            }

        });


        setVisible(true);
    }

}
