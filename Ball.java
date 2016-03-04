package com.company;

import java.awt.*;
import java.util.Random;

/**
 * Created by margarita on 3/3/16.
 */
public class Ball {

    public int x, y, width = 25, height = 25;

    public int motionX, motionY;

    public Random random;

    private Pong pong;

    public Ball(Pong pong) {

        this.pong = pong;

        this.random = new Random();
        this.x = pong.width / 2 - this.width / 2;
        this.y = pong.height / 2 - this.height / 2;

        this.motionY = -2 + random.nextInt(4);
        if (motionY == 0) {
            motionY = 1;
        } else {

        }

        if (random.nextBoolean()) {
            motionX = 1;
        } else {
            motionY = -1;
        }
        this.motionX = -1 + random.nextInt(1);
    }

    public void update(Paddle paddle1, Paddle paddle2) {
        int speed = 2;

        this.x += motionX * speed;
        this.y += motionY * speed;


        if (this.y +height> pong.height || this.y < 0) {

            if (this.motionY < 0) {
                this.motionY = random.nextInt(4);
            } else {
                this.motionY = -random.nextInt(4);
            }
        }

        if (checkCollsion(paddle1) == 1) {
            this.motionX = 1;
            this.motionY = -2 + random.nextInt(4);

            if (motionY == 0) {
                motionY = 1;
            }
        }

        if (checkCollsion(paddle2) == 1) {
            this.motionX = -1;
            this.motionY = -2 + random.nextInt(4);

            if (motionY == 0) {
                motionY = 1;
            }
        }

        if (checkCollsion(paddle1) == 2) {
            paddle2.score++;
        }

        if (checkCollsion(paddle2) == 2) {
            paddle1.score++;
        }
    }

    public int checkCollsion(Paddle paddle) {

        if (this.x < paddle.x + paddle.width && this.x + width > paddle.x &&
                this.y < paddle.y + paddle.height && this.y + height > paddle.y) {
            return 1;
        } else if ((paddle.x > x && paddle.paddleNumber == 1) || (paddle.x < x && paddle.paddleNumber == 2)) {
            return 2;
        }

        return 0;//nothing
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }
}
