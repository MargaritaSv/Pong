package com.company;

import java.awt.*;

/**
 * Created by margarita on 3/3/16.
 */
public class Paddle {

    public int paddleNumber;

    public int score;

    int x, y, width = 100, height = 500;

    public Paddle(int paddleNumber) {

        this.paddleNumber = paddleNumber;

        if (paddleNumber == 1) {
            this.x = width;
        } else if (paddleNumber == 2) {
            this.x = Pong.pong.width - width;
        }
        this.y = Pong.pong.height / 2 - this.height / 2;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }
}
