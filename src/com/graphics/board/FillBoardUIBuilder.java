package com.graphics.board;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FillBoardUIBuilder extends BoardUIBuilder{
    private BufferedImage image;

    public FillBoardUIBuilder(BufferedImage image) {
        this.image = image;
    }

    public FillBoardUIBuilder setImage(BufferedImage image) {
        this.image = image;
        return this;
    }

    public FillBoardUI createInstance() {
        FillBoardUI t = new FillBoardUI(image, offsetLTX, offsetLTY, offsetRTX, offsetRTY, size);
        return t;
    }
}