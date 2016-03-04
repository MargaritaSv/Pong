package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by margarita on 3/3/16.
 */
public class Pong implements ActionListener, KeyListener {

    public static Pong pong;

    public int width = 700;
    public int height = 700;

    public Renderer renderer;

    public Paddle player1;
    public Paddle player2;

    public boolean bot = false;
    public boolean w, s, up, down;

    public Ball ball;

    public int gameStatus = 0; //0= Stopped ; 1=Paused ; 2= Playing

    public Pong() {

        Timer timer = new Timer(20, this);
        JFrame jframe = new JFrame("Pong");

        renderer = new Renderer();
        jframe.setSize(width + 15, height + 35);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(renderer);
        jframe.addKeyListener(this);
     //   start();

        timer.start();
    }

    public void start() {
        gameStatus =2;
        player1 = new Paddle(this, 1);
        player2 = new Paddle(this, 2);

        ball = new Ball(this);

    }

    public void update() {
        if (w) {
            player1.move(true); //up
        } else if (s) {
            player1.move(false); //down
        }

        if (up) {
            player2.move(true);
        } else if (down) {
            player2.move(false);
        }

        ball.update(player1,player2);
    }

    public void render(Graphics2D g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (gameStatus == 0) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Ariel", 1, 27));
            g.drawString("Pong", width / 2 - 75, 10);

            g.setFont(new Font("Ariel", 1, 50));
            g.drawString("Press space to Play", width / 2 - 150, height / 2 - 50);
            g.drawString("Press shift to Play with Bot", width / 2 - 200, height / 2 - 25);

        }
        if (gameStatus == 2 || gameStatus == 1) {
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(5f));
            g.drawLine(width / 2, 0, width / 2, height);

            player1.render(g);
            player2.render(g);
            ball.render(g);
        }
        ball.render(g);

        if (gameStatus == 1) {
            g.setColor(Color.RED);
            g.setFont(new Font("Ariel", 1, 10));
            g.drawString("PAUSED", width / 2 - 20, height / 2 - 20);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (gameStatus == 2) {
            update();
        }
        renderer.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int id = e.getKeyCode();

        if (id == KeyEvent.VK_W) {
            w = true;
        } else if (id == KeyEvent.VK_S) {
            s = true;
        } else if (id == KeyEvent.VK_UP) {
            System.out.println("up");
            up = true;
        } else if (id == KeyEvent.VK_DOWN) {

            down = true;
            System.out.println("down");

        } else if (id == KeyEvent.VK_SHIFT && gameStatus == 0) {
            bot = true;
            start();
        } else if (id == KeyEvent.VK_SPACE) {
            if (gameStatus == 0) {
                start();
                bot = false;
            } else if (gameStatus == 1) {
                gameStatus = 2;
            } else if (gameStatus == 2) {
                gameStatus = 1;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int id = e.getKeyCode();
        if (id == KeyEvent.VK_W) {
            w = false;
        } else if (id == KeyEvent.VK_S) {
            s = false;
        } else if (id == KeyEvent.VK_UP) {
            System.out.println("p");
            up = false;
        } else if (id == KeyEvent.VK_DOWN) {
            down = false;
        } else if (id == KeyEvent.VK_SPACE) {

        }
    }
}
