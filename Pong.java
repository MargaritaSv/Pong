package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by margarita on 3/3/16.
 */
public class Pong implements ActionListener {

    public static Pong pong;

    public int width = 700;
    public int height = 700;

    public Renderer renderer;

    public Paddle player1;
    public Paddle player2;

    public Pong() {

        Timer timer = new Timer(20, this);
        JFrame jframe = new JFrame("Pong");

        renderer = new Renderer();
        jframe.setSize(width, height);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(renderer);
    }

    public void start() {
        player1 = new Paddle(1);
        player2 = new Paddle(2);
    }

    public void update() {

    }

    public void render(Graphics g) {

        player1.render(g);
        player2.render(g);
    }

    public static void main(String[] args) {

        pong = new Pong();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        renderer.repaint();
    }
}
