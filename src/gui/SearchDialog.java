package gui;

import api.Controller;
import api.Film;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchDialog extends JFrame {
    SearchDialog searchDialog = this;
    JButton buttonSearch;
    JTextField textField1,textField2,textField3,textField4,textField5,textField6;
    //JRadioButton byTitle,byType,byStar,byProperness,byCategory,byRate;
    //JCheckBox byTitle,byType,byStar,byProperness,byCategory,byRate;
    JLabel byTitle,byType,byStar,byProperness,byCategory,byRate;
    JLabel resultLabel;
    ActionListener buttonSearchListener;
    Controller controller;

    SearchDialog(Controller controller){
        this.controller=controller;
        setTitle("Search");
        setLocationRelativeTo(null);
        setSize(400,200);
        setResizable(true);
        setLayout(new FlowLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,2));
        panel.setLocation(this.getX()+20,this.getY()+20);
        byTitle = new JLabel("Title Filter");
        byType = new JLabel("Type Filter");
        byStar = new JLabel("Star Filter");
        byProperness = new JLabel("Properness Filter");
        byCategory = new JLabel("Category Filter");
        byRate = new JLabel("Rate Filter");
        textField1=new JTextField();
        textField2=new JTextField();
        textField3=new JTextField();
        textField4=new JTextField();
        textField5=new JTextField();
        textField6=new JTextField();
        panel.add(byTitle);
        panel.add(textField1);
        panel.add(byType);
        panel.add(textField2);
        panel.add(byStar);
        panel.add(textField3);
        panel.add(byProperness);
        panel.add(textField4);
        panel.add(byCategory);
        panel.add(textField5);
        panel.add(byRate);
        panel.add(textField6);
//        byTitle.setHorizontalAlignment(SwingConstants.LEFT);
//        panel.add(byTitle);
//        byType.setHorizontalAlignment(SwingConstants.LEFT);
//        panel.add(byType);
//        byStar.setHorizontalAlignment(SwingConstants.LEFT);
//        panel.add(byStar);
//        byProperness.setHorizontalAlignment(SwingConstants.LEFT);
//        panel.add(byProperness);
//        byCategory.setHorizontalAlignment(SwingConstants.LEFT);
//        panel.add(byCategory);
//        byRate.setHorizontalAlignment(SwingConstants.LEFT);
//        panel.add(byRate);
        add(panel);


        buttonSearch = new JButton("Search");
       // textField = new JTextField(20);
       // textField.setPreferredSize(new Dimension(100,30));
        resultLabel = new JLabel("results here");
       // add(textField);
        add(buttonSearch);
        add(resultLabel);

        buttonSearchListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Film> films = new ArrayList<>();
                if (!textField1.getText().isEmpty()){
                    films=controller.searchByTitle(textField1.getText());
                }
                else {
                    films=controller.getFilms();
                }
                if (!textField2.getText().isEmpty()) {
                    ArrayList<Film> films1=opposites(controller.searchByType(textField2.getText()));
                    films.removeAll(films1);
                }
                if (!textField3.getText().isEmpty()){
                    ArrayList<Film> films1=opposites(controller.searchByStar(textField3.getText()));
                    films.removeAll(films1);
                }
                if (!textField4.getText().isEmpty()){
                    ArrayList<Film> films1=opposites(controller.searchByProperness(Boolean.valueOf(textField4.getText())));
                    films.removeAll(films1);
                }
                if (!textField5.getText().isEmpty()){
                    ArrayList<Film> films1=opposites(controller.searchByCategory(textField5.getText()));
                    films.removeAll(films1);
                }
                if (!textField6.getText().isEmpty()){
                    ArrayList<Film> films1=opposites(controller.searchByRate(Integer.parseInt(textField6.getText())));
                    films.removeAll(films1);
                }
                resultLabel.setText(films.toString());
                new SearchListShow(searchDialog,films,controller);
               // resultLabel.setText(films.toString());
            }

            public ArrayList<Film> opposites(ArrayList<Film> films){
                ArrayList<Film> filmss= controller.getFilms();
                filmss.removeAll(films);
                return filmss;
            }
        };

        buttonSearch.addActionListener(buttonSearchListener);




        setVisible(true);
    }
}
