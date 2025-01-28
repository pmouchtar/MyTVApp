package gui;

import api.Controller;
import api.FilmCategory;
import api.Movie;
import api.Series;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SerieDialog extends JDialog {
    private Controller controller;
    private Series series;

    public SerieDialog(JFrame parent, Controller controller, String title, Series series) {
        super(parent, title, true);
        if (series == null) {
            setLocation(parent.getX() + 100, parent.getY() + 100);
        } else {
            setLocation(parent.getX() + 200, parent.getY() + 200);
        }
        this.controller = controller;
        this.series = series;
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
        String[] fields = {"Title", "Description", "forOver18", "Category", "Relative Series","Stars"};
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

        if (series != null) {
            textFieldTitle.setText(series.getTitle());
            textFieldDescription.setText(series.getDescription());
            textForOver18.setText( series.isForOver18toString());
            textCategory.setText(series.getCategory().toString());
            textRelativeMovies.setText(series.getRelatives());
            textStars.setText(series.getStars());
        }

        JPanel panelButtons = new JPanel();
        JButton buttonOk = new JButton("Ok");
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (series == null) {
                    series = new Series(textFieldTitle.getText(),
                            textFieldDescription.getText(),
                            Boolean.parseBoolean(textForOver18.getText()),
                            FilmCategory.valueOf(textCategory.getText()),
                            textStars.getText(),
                            textRelativeMovies.getText());
                    controller.add(series);
                }
                else {
                    series.setTitle(textFieldTitle.getText());
                    series.setDescription(textFieldDescription.getText());
                    series.setForOver18(Boolean.parseBoolean(textForOver18.getText()));
                    series.setStars(textStars.getText());
                    series.setCategory(FilmCategory.valueOf(textCategory.getText()));
                    series.setRelatives(textRelativeMovies.getText());
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