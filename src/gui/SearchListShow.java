package gui;

import api.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchListShow {
    Controller controller;
    ArrayList<Film> films;
    JDialog dialog;
    SearchListShow(JFrame parent, ArrayList<Film> films,Controller controller){
        dialog = new JDialog(parent, "Search Results", true);
        this.films = films;
        this.controller=controller;
        dialog.setLocation(parent.getX()+100, parent.getY()+100);
        buildUI();
        dialog.pack();
        dialog.setVisible(true);
    }

    final private void buildUI() {
        JTable table = new JTable(new AbstractTableModel() {

            String columnNames[] = {"Title", "Description"};

            public String getColumnName(int col) {
                return columnNames[col];
            }
            public int getRowCount() { return films.size(); }
            public int getColumnCount() { return columnNames.length; }
            public Object getValueAt(int row, int col) {
                Film film =films.get(row);
                switch (col) {
                    case 0: return film.getTitle();
                    case 1: return film.getDescription();
                    default: return null;
                }
            }
            public boolean isCellEditable(int row, int col)
            { return true; }
            public void setValueAt(Object value, int row, int col) {
                Film film = films.get(row);
                switch (col) {
                    case 0: film.setTitle((String) value); break;
                    case 1: film.setDescription((String) value); break;
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