package gui;

import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RatingDialog extends JDialog {
    private Controller controller;
    private Film film;
    private ArrayList<Rating> ratings;


    public RatingDialog(JFrame parent, Film film, String title, ArrayList<Rating> ratings) {
        super(parent, title, true);
        if (ratings == null) {
            setLocation(parent.getX() + 100, parent.getY() + 100);
        } else {
            setLocation(parent.getX() + 200, parent.getY() + 200);
        }
        this.film = film;
        this.ratings = ratings;
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
        String[] fields = {"Rating comment", "Rating (1-5)"};
        for (String field : fields) {
            JLabel label = new JLabel(field);
            label.setHorizontalAlignment(SwingConstants.RIGHT);
            panelLabels.add(label);
        }
        JTextField textFieldRatingComment = new JTextField(25);
        panelTextFields.add(textFieldRatingComment);
        JTextField textFieldRatingNumber = new JTextField();
        panelTextFields.add(textFieldRatingNumber);

//        if (film.getFilmRating() != null) {
//            textFieldRatingComment.setText(film.);
//            textFieldRatingNumber.setText();
//        }

        JPanel panelButtons = new JPanel();
        JButton buttonOk = new JButton("Ok");
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textFieldRatingComment.getText().isEmpty() && !textFieldRatingNumber.getText().isEmpty()) {
                    String comments = textFieldRatingComment.getText();
                    int rating = Integer.parseInt(textFieldRatingNumber.getText());
                    Rating rating1 = new Rating(comments,rating);
                    film.addRating(rating1);
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