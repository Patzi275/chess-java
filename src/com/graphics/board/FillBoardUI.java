package com.graphics.board;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class FillBoardUI extends BoardUI {
    private BufferedImage boardImage;
    private Dimension imageDimension;

    public FillBoardUI(BufferedImage image, int offsetLTX, int offsetLTY, int offsetRTX, int offsetRTY, int size) {
        super(offsetLTX, offsetLTY, offsetRTX, offsetRTY, size);
        this.boardImage = image;
        this.imageDimension = new Dimension(image.getWidth(null), image.getHeight(null));
    }

    public FillBoardUI(BufferedImage image, int offsetLTX, int offsetLTY, int offsetRTX, int offsetRTY) {
        this(image, offsetLTX, offsetLTY, offsetRTX, offsetRTY, image.getHeight(null));
    }

    public Dimension getImageDimension() {
        return imageDimension;
    }

    @Override
    public void drawBoard(Graphics2D g) {
        g.drawImage(boardImage, 0, 0, null);
    }

    @Override
    public String toString() {
        return "FillBoard{" +
                "\nboardImage=" + boardImage +
                ", \ncasesRects=" + Arrays.toString(casesRects) +
                ", \nboardRect=" + boardRect +
                ", \nselectedRect=" + selectedRect +
                ", \noffsetLTX=" + offsetLTX +
                ", \noffsetLTY=" + offsetLTY +
                ", \nsize=" + size +
                ", \ncaseSize=" + caseSize +
                "\n}";
    }
}
