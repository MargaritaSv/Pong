package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by margarita on 3/3/16.
 */
public class Renderer extends JPanel {

    private static final long serialVersionUID = 1L;

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Pong.pong.render(g);
    }
}
