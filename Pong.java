package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by margarita on 3/3/16.
 */
public class Pong {

    public static Pong pong;

    public int width = 700;
    public int height = 700;

    public Renderer randerer;

    public Pong() {
        JFrame jframe = new JFrame("Pong");

        jframe.setSize(width, height);
        jframe.setVisible(true);
        jframe.add(randerer);
    }


    public void render(Graphics g) {

    }

    public static void main(String[] args) {

        pong = new Pong();
    }
}
