package com.company;

import java.awt.*;

/**
 * Created by margarita on 3/3/16.
 */
public class Paddle {

    public int paddleNumber;

    public int score;

    int x, y, width = 100, height = 500;

    public Paddle(Pong pong,int paddleNumber) {

        this.paddleNumber = paddleNumber;

        if (paddleNumber == 1) {
            this.x = 0;
        } else if (paddleNumber == 2) {
            this.x = pong.width - width;
        }
        this.y = pong.height / 2 - this.height / 2;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }
}
