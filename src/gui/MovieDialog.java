package gui;

import api.Controller;
import api.FilmCategory;
import api.Movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieDialog extends JDialog {
    private Controller controller;
    private Movie movie;

    public MovieDialog(JFrame parent, Controller controller, String title, Movie movie) {
        super(parent, title, true);
        if (movie == null) {
            setLocation(parent.getX() + 100, parent.getY() + 100);
        } else {
            setLocation(parent.getX() + 200, parent.getY() + 200);
        }
        this.controller = controller;
        this.movie = movie;
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
        String[] fields = {"Title", "Description", "forOver18", "Category", "Relative Movies","Stars","Duration","Premier"};
        for (String field : fields) {
            JLabel label = new JLabel(field);
            label.setHorizontalAlignment(SwingConstants.RIGHT);
            panelLabels.add(label);
        }
        JTextField textFieldTitle = new JTextField(30);
        panelTextFields.add(textFieldTitle);
        JTextField textFieldDescription = new JTextField(20);
        panelTextFields.add(textFieldDescription);
        JTextField textForOver18 = new JTextField(20);
        panelTextFields.add(textForOver18);
        JTextField textCategory = new JTextField(20);
        panelTextFields.add(textCategory);
        JTextField textRelativeMovies = new JTextField(20);
        panelTextFields.add(textRelativeMovies);
        JTextField textStars = new JTextField(20);
        panelTextFields.add(textStars);
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(90,0,1000,1);
        JSpinner spinnerDuration = new JSpinner(spinnerModel);
        panelTextFields.add(spinnerDuration);
        JTextField textPremier = new JTextField(40);
        panelTextFields.add(textPremier);

        if (movie != null) {
            textFieldTitle.setText(movie.getTitle());
            textFieldDescription.setText(movie.getDescription());
            textForOver18.setText( movie.isForOver18toString());
            textCategory.setText(movie.getCategory().toString());
            textRelativeMovies.setText(movie.getRelatives());
            textStars.setText(movie.getStars());
            spinnerModel.setValue(movie.getDuration());
            textPremier.setText(String.valueOf(movie.getPremier()));
        }

        JPanel panelButtons = new JPanel();
        JButton buttonOk = new JButton("Ok");
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (movie == null) {
                    movie = new Movie(textFieldTitle.getText(),
                            textFieldDescription.getText(),
                            Boolean.parseBoolean(textForOver18.getText()),
                            FilmCategory.valueOf(textCategory.getText()),
                            textStars.getText(),
                            textRelativeMovies.getText(),
                            Integer.parseInt(textPremier.getText()),
                            spinnerModel.getNumber().intValue()
                    );
                    controller.add(movie);
                }
                else {
                    movie.setTitle(textFieldTitle.getText());
                    movie.setDescription(textFieldDescription.getText());
                    movie.setForOver18(Boolean.parseBoolean(textForOver18.getText()));
                    movie.setStars(textStars.getText());
                    movie.setPremier(Integer.parseInt(textPremier.getText()));
                    movie.setDuration(spinnerModel.getNumber().intValue());
                    movie.setCategory(FilmCategory.valueOf(textCategory.getText()));
                    movie.setRelatives(textRelativeMovies.getText());
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
