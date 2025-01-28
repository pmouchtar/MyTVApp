package gui;

import api.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class SeazonShow {
    Controller controller;
    Series series;
    JDialog dialog;
    int t;
    SeazonShow(JFrame parent, Series series,int t){
        this.t=t;
        dialog = new JDialog(parent, "List of Seazons", true);
        this.series = series;
        dialog.setLocation(parent.getX()+100, parent.getY()+100);
        buildUI();
        dialog.pack();
        dialog.setVisible(true);
    }

    final private void buildUI() {
        JTable table = new JTable(new AbstractTableModel() {

            String columnNames[] = {"Seazon Number", "Premier", "List of episodes"};

            public String getColumnName(int col) {
                return columnNames[col];
            }
            public int getRowCount() { return series.getSeazons().size(); }
            public int getColumnCount() { return columnNames.length; }
            public Object getValueAt(int row, int col) {
                Seazon seazon =series.getSeazons().get(row);
                    switch (col) {
                        case 0: return seazon.getNumber();
                        case 1: return seazon.getPremier();
                        case 2: return seazon.getEpisodes();
                        default: return null;
                    }
            }
            public boolean isCellEditable(int row, int col)
            { return true; }
            public void setValueAt(Object value, int row, int col) {
                Seazon seazon = series.getSeazons().get(row);
                switch (col) {
                    case 0: seazon.setNumber((String) value); break;
                    case 1: seazon.setPremier((String) value); break;
                    case 2: seazon.setEpisodes((HashMap<Integer, Integer>) value); break;
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
                    series.getSeazons().remove(modelRow);
                    ((AbstractTableModel) table.getModel()).fireTableDataChanged();
                }
            }
        });
        JButton buttonEdit = new JButton("Edit");
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int viewRow = table.getSelectedRow();
                if (viewRow != -1) {
                    int modelRow = table.convertRowIndexToModel(viewRow);
                    Seazon seazon = series.getSeazons().get(modelRow);
                    new SeazonDialog((JFrame) dialog.getParent(), series,"Seazon edit",seazon);
                    ((AbstractTableModel) table.getModel()).fireTableDataChanged();
                }
            }
        });

        if (t==0){
            panel.add(buttonDelete);
            panel.add(buttonEdit);
        }
        dialog.add(panel, BorderLayout.PAGE_END);

    }
}