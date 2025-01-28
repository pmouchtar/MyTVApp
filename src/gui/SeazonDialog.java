package gui;

import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SeazonDialog extends JDialog {
    private Controller controller;
    private Series series;

    private ArrayList<Seazon> seazons;

    public SeazonDialog(JFrame parent, Series series, String title,Seazon seazon) {
        super(parent, title, true);
        if (series == null) {
            setLocation(parent.getX() + 100, parent.getY() + 100);
        } else {
            setLocation(parent.getX() + 200, parent.getY() + 200);
        }
        this.controller = controller;
        this.series = series;
        this.seazons = seazons;
        buildUI();
        pack();
        setVisible(true);
    }

    final private void buildUI() {
        JPanel panelData = new JPanel();
        add(panelData, BorderLayout.CENTER);
        panelData.setLayout(new BorderLayout());
        JPanel panelLabels = new JPanel();
        panelLabels.setLayout(new GridLayout(0,1));
        panelData.add(panelLabels, BorderLayout.LINE_START);
        JPanel panelTextFields = new JPanel();
        panelTextFields.setLayout(new GridLayout(0,1));
        panelData.add(panelTextFields, BorderLayout.CENTER);
        String[] fields = {"Seazon Number", "Premier", "List of episodes"};
        for (String field : fields) {
            JLabel label = new JLabel(field);
            label.setHorizontalAlignment(SwingConstants.RIGHT);
            panelLabels.add(label);
        }
        JTextField textFieldSeazonNumber = new JTextField(30);
        panelTextFields.add(textFieldSeazonNumber);
        JTextField textFieldPremier = new JTextField(20);
        panelTextFields.add(textFieldPremier);
        JTextField textFieldEpisodes = new JTextField(20);
        panelTextFields.add(textFieldEpisodes);

//        if (series.getSeazons() != null) {
//            textFieldSeazonNumber.setText(series.);
//            textFieldDescription.setText(series.getDescription());
//            textForOver18.setText( series.isForOver18toString());
//            textCategory.setText(series.getCategory().toString());
//            textRelativeMovies.setText(series.getRelatives());
//            textStars.setText(series.getStars());
//        }

        JPanel panelButtons = new JPanel();
        JButton buttonOk = new JButton("Ok");
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (true) {
                    series.addSeazon(textFieldSeazonNumber.getText(),textFieldPremier.getText(),textFieldEpisodes.getText());
                    //controller.add(series);
                }
                else {
//                    series.setTitle(textFieldTitle.getText());
//                    series.setDescription(textFieldDescription.getText());
//                    series.setForOver18(Boolean.parseBoolean(textForOver18.toString()));
//                    series.setStars(textStars.getText());
//                    series.setCategory(FilmCategory.valueOf(textCategory.getText()));
//                    series.setRelatives(textRelativeMovies.getText());
                }
                dispose();
            }

        });
        panelButtons.add(buttonOk);
        JButton buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panelButtons.add(buttonCancel);
        add(panelButtons, BorderLayout.PAGE_END);
    }



}