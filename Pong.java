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

    public int gameStatus = 0; //0= Stopped (Menu) ; 1=Paused ; 2= Playing 3=over

    public int botMove;
    public int botCooldown = 0;
    public int botDifficulty;
    public int scoreLimet = 7;
    public boolean selectDifficulty;

    public int playerWon;

    public Pong() {

        Timer timer = new Timer(20, this);
        JFrame jframe = new JFrame("Pong");

        renderer = new Renderer();
        jframe.setSize(width + 15, height + 35);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(renderer);
        jframe.addKeyListener(this);

        timer.start();
    }

    public void start() {

        gameStatus = 2;
        player1 = new Paddle(this, 1);
        player2 = new Paddle(this, 2);
        ball = new Ball(this);
    }

    public void update() {

        if (player1.score >= scoreLimet) {
            playerWon = 1;
            gameStatus = 3;
        }

        if (player2.score >= scoreLimet) {
            playerWon = 2;
            gameStatus = 3;
        }

        if (w) {
            player1.move(true); //up
        } else if (s) {
            player1.move(false); //down
        }

        if (!bot) {

            if (up) {
                player2.move(true);
            } else if (down) {
                player2.move(false);
            }
        } else {
            if (botCooldown > 0) {
                botCooldown--;
                if (botCooldown == 0) {
                    botMove = 0;
                }
            }

            if (botMove < 10) {

                if (player2.y + player2.height / 2 < ball.y) {
                    player2.move(false);
                    botMove++;
                }

                if (player2.y + player2.height / 2 < ball.y) {
                    player2.move(true);
                    botMove++;
                }

                if (botDifficulty == 0) {
                    botDifficulty = 20;
                } else if (botDifficulty == 1) {
                    botDifficulty = 15;
                } else if (botDifficulty == 2) {
                    botCooldown = 10;
                }
            }
        }

        ball.update(player1, player2);
    }

    public void render(Graphics2D g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (gameStatus == 0) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Ariel", 1, 27));
            g.drawString("Pong", width / 2 - 75, 20);

            if (!selectDifficulty) {
                g.setFont(new Font("Ariel", 1, 20));
                g.drawString("Press space to Play", width / 2 - 150, height / 2 - 60);
                g.drawString("Press shift to Play with Bot", width / 2 - 200, height / 2 - 40);
                g.drawString("<< Score limet: " + scoreLimet + " >>", width / 2 - 150, height / 2 - 20);
            }
        }

        if (selectDifficulty) {

            String str = botDifficulty == 0 ? "Easy" : (botDifficulty == 1 ? "Medium" : "Hard");

            g.setFont(new Font("Ariel", 1, 20));
            g.drawString("<< Bot difficulty: " + str + " >>", width / 2 - 180, height / 2 - 50);
            g.drawString("Press shift to Play with Bot", width / 2 - 150, height / 2 - 25);
        }

        if (gameStatus == 1) {
            g.setColor(Color.RED);
            g.setFont(new Font("Ariel", 1, 30));
            g.drawString("PAUSED", width / 2 - 20, height / 2 - 25);
        }

        if (gameStatus == 1 || gameStatus == 2) {
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(5f));
            g.drawLine(width / 2, 0, width / 2, height);

            g.setStroke(new BasicStroke(2f));
            g.drawOval(width / 2 - 50, height / 2 - 50, 100, 100);

            g.setFont(new Font("Ariel", 1, 20));
            g.drawString(String.valueOf(player1.score), width / 2 - 75, 50);
            g.drawString(String.valueOf(player2.score), width / 2 + 75, 50);

            player1.render(g);
            player2.render(g);
            ball.render(g);
        }

        if (gameStatus == 3) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Ariel", 1, 27));
            g.drawString("Pong", width / 2 - 75, 20);

            if(bot){
                g.drawString("Bot WIN!", width / 2 - 165, 200);
            }else{
                g.drawString("Player " + playerWon + "WIN!", width / 2 - 165, 100);
            }

            g.setFont(new Font("Ariel", 1, 20));

            g.drawString("Press space to Play Again", width / 2 - 190, height / 2 - 50);
            g.drawString("Press ESC for Menu", width / 2 - 140, height / 2 - 25);
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
    public void keyPressed(KeyEvent e) {
        int id = e.getKeyCode();

        if (id == KeyEvent.VK_W) {
            w = true;

        } else if (id == KeyEvent.VK_S) {
            s = true;

        } else if (id == KeyEvent.VK_UP) {
            up = true;

        } else if (id == KeyEvent.VK_DOWN) {
            down = true;

        } else if (id == KeyEvent.VK_RIGHT) {

            if (selectDifficulty) {

                if (botDifficulty < 2) {
                    botDifficulty++;
                } else {
                    botDifficulty = 0;
                }

            } else if (gameStatus == 0) {
                scoreLimet++;
            }

        } else if (id == KeyEvent.VK_LEFT) {

            if (selectDifficulty) {
                if (botDifficulty > 0) {
                    botDifficulty--;
                } else {
                    botDifficulty = 2;
                }

            } else if (gameStatus == 0 && scoreLimet > 1) {
                scoreLimet--;
            }

        } else if (id == KeyEvent.VK_ESCAPE && (gameStatus == 2 || gameStatus == 3)) {
            gameStatus = 0;

        } else if (id == KeyEvent.VK_SHIFT && gameStatus == 0) {
            bot = true;
            selectDifficulty = true;

        } else if (id == KeyEvent.VK_SPACE) {

            if (gameStatus == 0 || gameStatus == 3) {

                if (!selectDifficulty) {
                    bot = false;
                } else {
                    selectDifficulty = false;
                }
                start();

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
            up = false;
        } else if (id == KeyEvent.VK_DOWN) {
            down = false;
        } else if (id == KeyEvent.VK_SPACE) {

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
