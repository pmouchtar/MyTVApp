package gui;

import api.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieShow extends JFrame{
    MovieShow movieShow = this;
    Controller controller;
    JDialog dialog;
    public int t;
    MovieShow(JFrame parent, Controller data,int t){
        this.t = t;
        dialog = new JDialog(parent, "List of Movies", true);
        this.controller = data;
        dialog.setLocation(parent.getX()+100, parent.getY()+100);
        buildUI();
        dialog.pack();
        dialog.setVisible(true);
    }

    final private void buildUI() {
        JTable table = new JTable(new AbstractTableModel() {

            String columnNames[] = {"Title", "Description", "forOver18", "Category", "Relative Movies","Stars","Duration","Premier"};

            public String getColumnName(int col) {
                return columnNames[col];
            }
            public int getRowCount() { return controller.getMovies().size(); }
            public int getColumnCount() { return columnNames.length; }
            public Object getValueAt(int row, int col) {
                Movie movie = controller.getMovies().get(row);
                switch (col) {
                    case 0: return movie.getTitle();
                    case 1: return movie.getDescription();
                    case 2: return movie.isForOver18toString();
                    case 3: return movie.getCategory();
                    case 4: return movie.getRelatives();
                    case 5: return movie.getStars();
                    case 6: return movie.getDuration();
                    case 7: return movie.getPremier();
                    default: return null;
                }
            }
            public boolean isCellEditable(int row, int col)
            { return true; }
            public void setValueAt(Object value, int row, int col) {
                Movie movie = controller.getMovies().get(row);
                switch (col) {
                    case 0: movie.setTitle((String) value); break;
                    case 1: movie.setDescription((String) value); break;
                    case 2: movie.setForOver18(((Boolean)value)); break;
                    case 3: movie.setCategory(FilmCategory.valueOf(value.toString() )); break;
                    case 4: movie.setRelatives((String) value); break;
                    case 5: movie.setStars((String) value); break;
                    case 6: movie.setDuration((Integer.parseInt((String) value)));break;
                    case 7: movie.setPremier((Integer.parseInt((String) value))); break;
                }
                fireTableCellUpdated(row, col);
            }
        });
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(table);
        dialog.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton buttonEdit = new JButton("Edit");
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int viewRow = table.getSelectedRow();
                if (viewRow != -1) {
                    int modelRow = table.convertRowIndexToModel(viewRow);
                    Movie movie = controller.getMovies().get(modelRow);
                    new MovieDialog((JFrame) dialog.getParent(), controller,"Movie edit",movie);
                    ((AbstractTableModel) table.getModel()).fireTableDataChanged();
                }
            }
        });
        JButton buttonDelete = new JButton("Delete");
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int viewRow = table.getSelectedRow();
                if (viewRow != -1) {
                    int modelRow = table.convertRowIndexToModel(viewRow);
                    controller.getMovies().remove(modelRow);
                    ((AbstractTableModel) table.getModel()).fireTableDataChanged();
                }
            }
        });
        JButton buttonRate = new JButton("Rate");
        buttonRate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int viewRow = table.getSelectedRow();
                if (viewRow != -1) {
                    int modelRow = table.convertRowIndexToModel(viewRow);
                    Movie movie = controller.getMovies().get(modelRow);
                    new RatingDialog((JFrame) dialog.getParent(),movie,"Add Rating",movie.getFilmRating());
                    ((AbstractTableModel) table.getModel()).fireTableDataChanged();
                }
            }
        });
        JButton buttonShowRatings = new JButton("Show Ratings");
        buttonShowRatings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RatingShow(movieShow,controller.getMovies().get(table.getSelectedRow()));
            }
        });
        JButton buttonAddToFavorites = new JButton("Add to Favourites");
        buttonAddToFavorites.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int viewRow = table.getSelectedRow();
                if (viewRow != -1) {
                    int modelRow = table.convertRowIndexToModel(viewRow);
                    Movie movie = controller.getMovies().get(modelRow);
                    controller.setFavorite(movie, controller.getUsername());
                    ((AbstractTableModel) table.getModel()).fireTableDataChanged();
                }
            }
        });
        panel.add(buttonShowRatings);
        if (t==1){
            panel.add(buttonRate);
            //panel.add(buttonShowRatings);
            panel.add(buttonAddToFavorites);
        }
        if (t==0) {
            panel.add(buttonEdit);
            panel.add(buttonDelete);
        }
        dialog.add(panel, BorderLayout.PAGE_END);
    }
}

