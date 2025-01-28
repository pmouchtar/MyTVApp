package gui;

import api.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SubscriberInterface extends JFrame {
    Controller controller;
    SubscriberInterface subscriberInterface = this;
    String username;

    public SubscriberInterface(String username){
        this.username=username;
        setTitle("myTV");
        setSize(500, 300);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controller = new Controller(username,0);
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
            public void actionPerformed(ActionEvent e) {new MovieShow(subscriberInterface,controller,1);
            }
        });
        JMenuItem showSeries = new JMenuItem("Series");
        showSeries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SeriesShow(subscriberInterface,controller,1);
            }
        });
        menuShow.add(showMovies);
        menuShow.add(showSeries);
        JMenu menuSearch = new JMenu("Search");
        JMenuItem search = new JMenuItem("Movies and Series");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {new SearchDialog(controller);
            }
        });
        JMenu menuFavor = new JMenu("Show Favourites");
        JMenuItem menuFavorites = new JMenuItem("Favourites");
        menuFavorites.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FavoritesShow(subscriberInterface,controller,username);
            }
        });
        menuFavor.add(menuFavorites);
        menuSearch.add(search);
        menuBar.add(menuShow);
        menuBar.add(menuSearch);
        menuBar.add(menuFavor);
        setJMenuBar(menuBar);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.save();
            }
        });


    }
}
