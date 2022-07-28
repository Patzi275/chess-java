package com.graphics.board;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TiledBoardUI extends BoardUI {
    private Color color00, color01;

    public TiledBoardUI(int offsetLTX, int offsetLTY, int offsetRTX, int offsetRTY, int size, Color color00, Color color01) {
        super(offsetLTX, offsetLTY, offsetRTX, offsetRTY, size);
        this.color00 = color00;
        this.color01 = color01;
    }

    @Override
    public void drawBoard(Graphics2D g) {
        Color initialColor = g.getColor();
        int k = 1;

        g.setColor(this.color01);
        g.fill(boardRect);

        g.setColor(this.color00);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    g.fill(casesRects[i][j]);
                }
            }
        }
        g.setColor(initialColor);
    }
}
