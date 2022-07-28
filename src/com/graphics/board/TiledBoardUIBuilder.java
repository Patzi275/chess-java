package com.graphics.board;

import com.chesspack.Board;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TiledBoardUIBuilder extends BoardUIBuilder {
    private Color color00 = Color.WHITE,
            color01 = Color.BLACK;

    public TiledBoardUIBuilder setColor00(Color color) {
        this.color00 = color;
        return this;
    }

    public TiledBoardUIBuilder setColor01(Color color) {
        this.color01 = color;
        return this;
    }

    public TiledBoardUI createInstance() {
        TiledBoardUI t = new TiledBoardUI(offsetLTX, offsetLTY, offsetRTX, offsetRTY, size, color00, color01);
        return t;
    }
}
