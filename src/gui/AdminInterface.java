package gui;

import api.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminInterface extends JFrame {
    AdminInterface adminInterface = this;
    String username;
    Controller controller;
    public AdminInterface(String username) {
        this.username = username;
        setTitle("myTV");
        setSize(500, 300);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controller = new Controller(username,1);
        controller.load();
        buildUI();
        setVisible(true);
    }

    final private void buildUI() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuShow = new JMenu("Show");
        JMenuItem showMovies = new JMenuItem("Movies");
        showMovies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {new MovieShow(adminInterface,controller,0);
            }
        });
        JMenuItem addMovies = new JMenuItem("Add Movie");
        addMovies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MovieDialog( adminInterface, controller,"Add Movie",null);
            }
        });
        JMenuItem showSeries = new JMenuItem("Series");
        showSeries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SeriesShow(adminInterface,controller,0);
            }
        });
        JMenuItem addSeries = new JMenuItem("Add Series");
        addSeries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SerieDialog(adminInterface,controller,"Add Series",null);
            }
        });
        menuShow.add(showMovies);
        menuShow.add(addMovies);
        menuShow.add(showSeries);
        menuShow.add(addSeries);
        JMenu menuSearch = new JMenu("Search");
        JMenuItem search = new JMenuItem("Movies and Series");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {new SearchDialog(controller);
            }
        });
        menuSearch.add(search);
        menuBar.add(menuShow);
        menuBar.add(menuSearch);
        setJMenuBar(menuBar);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.save();
            }
        });


    }
}
