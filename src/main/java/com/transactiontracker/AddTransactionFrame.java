package com.transactiontracker;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddTransactionFrame extends JFrame {
    public AddTransactionFrame(User user) {
        setTitle("Adaugare Tranzactie");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300,300);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7,2,10,10));
        JComboBox<TransactionType>type=new JComboBox<>(TransactionType.values());
        JTextField suma=new JTextField();
        SpinnerDateModel dateModel=new SpinnerDateModel();
        JSpinner dataSpinner=new JSpinner(dateModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dataSpinner, "yyyy-MM-dd");
        dataSpinner.setEditor(editor);
        JTextField categorie=new JTextField();
        JTextField locatie=new JTextField();
        JComboBox<PaymentMethod>mod=new JComboBox<>(PaymentMethod.values());
        JButton addButton=new JButton("Adauga");

        panel.add(new JLabel("Tip:"));
        panel.add(type);
        panel.add(new JLabel("Suma:"));
        panel.add(suma);
        panel.add(new JLabel("Data: "));
        panel.add(dataSpinner);
        panel.add(new JLabel("Categorie: "));
        panel.add(categorie);
        panel.add(new JLabel("Locatie: "));
        panel.add(locatie);
        panel.add(new JLabel("Modul: "));
        panel.add(mod);
        panel.add(new JLabel(""));
        panel.add(addButton);

        add(panel);
        addButton.addActionListener(e -> {
            TransactionType selectedType=(TransactionType)type.getSelectedItem();
            double sumaValue;
            try{
             sumaValue=Double.parseDouble(suma.getText());
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Suma trebuie sa fie un numar real!","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            java.util.Date date = (java.util.Date) dataSpinner.getValue();
            LocalDate data = date.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();
            String categorieValue=categorie.getText();
            String locatieValue=locatie.getText();
            PaymentMethod paymentMethod=(PaymentMethod)mod.getSelectedItem();

            try
            {
                Transaction t=new TransactionManager().addTransaction(user,selectedType,sumaValue,data,categorieValue,locatieValue,paymentMethod);
                if(t!=null)
                {
                    JOptionPane.showMessageDialog(this,"Tranzactie adaugata cu succes!");
                    AddTransactionFrame frame=this;
                    frame.dispose();
                    new MainFrame(user).setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"Eroare la adaugarea tranzactiei!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        setVisible(true);

    }
}
