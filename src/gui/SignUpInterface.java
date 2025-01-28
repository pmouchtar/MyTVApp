package gui;

import api.Subscribe;
import api.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.IOException;

public class SignUpInterface extends JFrame implements ActionListener {
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel,nameLabel,surnameLabel,result;
    final JTextField  textField1, textField2,textField3,textField4;

    public SignUpInterface(){
        setSize(300,300);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        userLabel = new JLabel();
        userLabel.setText("Username");
        textField1 = new JTextField(15);
        passLabel = new JLabel();
        passLabel.setText("Password");
        textField2 = new JPasswordField(15);
        nameLabel = new JLabel();
        nameLabel.setText("Name");
        textField3 = new JTextField(15);
        surnameLabel = new JLabel();
        surnameLabel.setText("Surname");
        textField4 = new JTextField(15);

        b1 = new JButton("SUBMIT");
        result = new JLabel("");
        newPanel = new JPanel(new GridLayout(5,1));
        newPanel.add(userLabel);    //set username label to panel
        newPanel.add(textField1);   //set text field to panel
        newPanel.add(passLabel);    //set password label to panel
        newPanel.add(textField2);   //set text field to panel
        newPanel.add(nameLabel);
        newPanel.add(textField3);
        newPanel.add(surnameLabel);
        newPanel.add(textField4);
        newPanel.add(b1);
        newPanel.add(result);
        add(newPanel, BorderLayout.CENTER);
        b1.addActionListener(this);     //add action listener to button
        setTitle("SUBSCRIBE FORM");         //set title to the login form
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String userValue = textField1.getText();        //get user entered username from the textField1
        String passValue = textField2.getText();        //get user entered password from the textField2
        String nameValue = textField3.getText();
        String surnameValue = textField4.getText();

        try {
          //  Subscribe subscribe = new Subscribe(userValue,passValue,nameValue,surnameValue);
            User user = new User();
            if (user.searchIfExists(userValue)){
                result.setText("Subscribe done");
                Subscribe subscribe = new Subscribe(userValue,passValue,nameValue,surnameValue);
            }
            else {
                result.setText("Enter another username");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}
