package gui;

import api.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FavoritesShow extends JFrame{
    Controller controller;
    JDialog dialog;
    String username;
    FavoritesShow(JFrame parent, Controller data,String username){
        dialog = new JDialog(parent, "List of Favourites", true);
        this.controller = data;
        this.username=username;
        controller.loadFavorites(username);
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
            public int getRowCount() {
                if (controller.getFavoriteFilms(username)==null){
                    return 0;
                }
                else return controller.getFavoriteFilms(username).size();
            }
            public int getColumnCount() { return columnNames.length; }
            public Object getValueAt(int row, int col) {
                Film film = controller.getFavoriteFilms(username).get(row);
                switch (col) {
                    case 0: return film.getTitle();
                    case 1: return film.getDescription();
                    default: return null;
                }
            }
            public boolean isCellEditable(int row, int col)
            { return true; }
            public void setValueAt(Object value, int row, int col) {
                Film film = controller.getFavoriteFilms(username).get(row);
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

        JPanel panel = new JPanel();

        JButton buttonDelete = new JButton("Delete");
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int viewRow = table.getSelectedRow();
                if (viewRow != -1) {
                    int modelRow = table.convertRowIndexToModel(viewRow);
                    Film film = controller.getFavoriteFilms(username).remove(modelRow);
                    controller.removeFavorite(film,username);
                    ((AbstractTableModel) table.getModel()).fireTableDataChanged();
                }
            }
        });
        panel.add(buttonDelete);
        dialog.add(panel, BorderLayout.PAGE_END);
    }
}