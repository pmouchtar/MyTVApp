package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StarterInterface extends JFrame implements ActionListener {
    JButton goToLogin,goToSub;
    JPanel panel;
    JLabel label;
    ActionListener buttonListener;
    public StarterInterface(){
        setSize(200,100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        goToLogin = new JButton("Sign in");
        goToSub = new JButton("Sign up");
        //panel = new JPanel(new GridLayout(1,2));
        panel = new JPanel(new FlowLayout());
        label = new JLabel("Welcome to myTV");
        panel.add(label);
        panel.add(goToLogin);
        panel.add(goToSub);
        add(panel, BorderLayout.CENTER);
        goToSub.addActionListener(this);
        goToLogin.addActionListener(this);
        setTitle("Options");
        setVisible(true);
        buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(e.getActionCommand());
                SignUpInterface subscribeInterface = new SignUpInterface();
            }
        };
        goToSub.addActionListener(buttonListener);
        buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginInterface loginInterface = new LoginInterface();
            }
        };
        goToLogin.addActionListener(buttonListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
