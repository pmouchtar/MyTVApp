package gui;

import api.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeriesShow extends JFrame{
    public int t;
    SeriesShow seriesShow = this;
    Controller controller;
    JDialog dialog;
    SeriesShow(JFrame parent, Controller data,int t){
        this.t = t;
        dialog = new JDialog(parent, "List of Series", true);
        this.controller = data;
        dialog.setLocation(parent.getX()+100, parent.getY()+100);
        buildUI();
        dialog.pack();
        dialog.setVisible(true);
    }

    final private void buildUI() {
        JTable table = new JTable(new AbstractTableModel() {

            String columnNames[] = {"Title", "Description", "forOver18", "Category", "Relative Series","Stars"};

            public String getColumnName(int col) {
                return columnNames[col];
            }
            public int getRowCount() { return controller.getSeries().size(); }
            public int getColumnCount() { return columnNames.length; }
            public Object getValueAt(int row, int col) {
                Series series = controller.getSeries().get(row);
                switch (col) {
                    case 0: return series.getTitle();
                    case 1: return series.getDescription();
                    case 2: return series.isForOver18();
                    case 3: return series.getCategory();
                    case 4: return series.getRelatives();
                    case 5: return series.getStars();
                    default: return null;
                }
            }
            public boolean isCellEditable(int row, int col)
            { return true; }
            public void setValueAt(Object value, int row, int col) {
                Series series = controller.getSeries().get(row);
                switch (col) {
                    case 0: series.setTitle((String) value); break;
                    case 1: series.setDescription((String) value); break;
                    case 2: series.setForOver18((Boolean) value); break;
                    case 3: series.setCategory(FilmCategory.valueOf(value.toString() )); break;
                    case 4: series.setRelatives((String) value); break;
                    case 5: series.setStars((String) value); break;

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
                    Series series = controller.getSeries().get(modelRow);
                    new SerieDialog((JFrame) dialog.getParent(), controller,"Series edit",series);
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
                    controller.getSeries().remove(modelRow);
                    ((AbstractTableModel) table.getModel()).fireTableDataChanged();
                }
            }
        });
        JButton buttonAddSeazon = new JButton("Add Seazon");
        buttonAddSeazon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int viewRow = table.getSelectedRow();
                if (viewRow != -1) {
                    int modelRow = table.convertRowIndexToModel(viewRow);
                    Series series = controller.getSeries().get(modelRow);
                    new SeazonDialog((JFrame) dialog.getParent(), series,"Add Seazon",null);
                    ((AbstractTableModel) table.getModel()).fireTableDataChanged();
                }
            }
        });
        JButton buttonShowSeazons = new JButton("Show Seazons");
        buttonShowSeazons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SeazonShow(seriesShow,controller.getSeries().get(table.getSelectedRow()),t);
            }
        });
        JButton buttonRate = new JButton("Rate");
        buttonRate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int viewRow = table.getSelectedRow();
                if (viewRow != -1) {
                    int modelRow = table.convertRowIndexToModel(viewRow);
                    Series series = controller.getSeries().get(modelRow);
                    new RatingDialog((JFrame) dialog.getParent(), series,"Add Rating",series.getFilmRating());
                    ((AbstractTableModel) table.getModel()).fireTableDataChanged();
                }
            }
        });
        JButton buttonShowRatings = new JButton("Show Ratings");
        buttonShowRatings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RatingShow(seriesShow,controller.getSeries().get(table.getSelectedRow()));
            }
        });
        JButton buttonAddToFavorites = new JButton("Add to Favourites");
        buttonAddToFavorites.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int viewRow = table.getSelectedRow();
                if (viewRow != -1) {
                    int modelRow = table.convertRowIndexToModel(viewRow);
                    Series series = controller.getSeries().get(modelRow);
                    controller.setFavorite(series, controller.getUsername());
                    ((AbstractTableModel) table.getModel()).fireTableDataChanged();
                }
            }
        });
        if (t==1){
            panel.add(buttonRate);
            panel.add(buttonShowRatings);
            panel.add(buttonAddToFavorites);
        }
        if (t==0){
            panel.add(buttonEdit);
            panel.add(buttonDelete);
            panel.add(buttonAddSeazon);
        }
        panel.add(buttonShowSeazons);
        dialog.add(panel, BorderLayout.PAGE_END);
    }
}
