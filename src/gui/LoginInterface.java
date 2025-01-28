package gui;

import api.Controller;
import api.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;

public class LoginInterface extends JFrame implements ActionListener {
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel,reuslt;
    final JTextField  textField1, textField2;

    public LoginInterface(){
        setSize(500,150);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        userLabel = new JLabel();
        userLabel.setText("Username");
        textField1 = new JTextField(15);
        passLabel = new JLabel();
        passLabel.setText("Password");
        textField2 = new JPasswordField(15);
        b1 = new JButton("SUBMIT");
        reuslt = new JLabel("");
        newPanel = new JPanel(new GridLayout(3,1));
        newPanel.add(userLabel);    //set username label to panel
        newPanel.add(textField1);   //set text field to panel
        newPanel.add(passLabel);    //set password label to panel
        newPanel.add(textField2);   //set text field to panel
        newPanel.add(b1);
        newPanel.add(reuslt);
        add(newPanel, BorderLayout.CENTER);
        b1.addActionListener(this);     //add action listener to button
        setTitle("LOGIN FORM");         //set title to the login form
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String userValue = textField1.getText();        //get user entered username from the textField1
        String passValue = textField2.getText();        //get user entered password from the textField2
        User user = new User(userValue);
        if (user.login(userValue,passValue)){
            reuslt.setText("access");
        }
        else {
            reuslt.setText("wrong username or password");
        }
    }

}
