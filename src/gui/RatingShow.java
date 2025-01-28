package gui;

import api.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class RatingShow {
    Controller controller;
    Film film;
    JDialog dialog;
    RatingShow(JFrame parent, Film film){
        dialog = new JDialog(parent, "Ratings", true);
        this.film = film;
        dialog.setLocation(parent.getX()+100, parent.getY()+100);
        buildUI();
        dialog.pack();
        dialog.setVisible(true);
    }

    final private void buildUI() {
        JTable table = new JTable(new AbstractTableModel() {

            String columnNames[] = {"Comment", "Rating"};

            public String getColumnName(int col) {
                return columnNames[col];
            }
            public int getRowCount() { return film.getFilmRating().size(); }
            public int getColumnCount() { return columnNames.length; }
            public Object getValueAt(int row, int col) {
                Rating rating =film.getFilmRating().get(row);
                switch (col) {
                    case 0: return rating.getComments();
                    case 1: return rating.getRating();
                    default: return null;
                }
            }
            public boolean isCellEditable(int row, int col)
            { return true; }
            public void setValueAt(Object value, int row, int col) {
                Rating rating = film.getFilmRating().get(row);
                switch (col) {
                    case 0: rating.setComments((String) value); break;
                    case 1: rating.setRating(Integer.parseInt((String) value)); break;
                }
                fireTableCellUpdated(row, col);
            }
        });
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(table);
        dialog.add(scrollPane, BorderLayout.CENTER);

    }
}