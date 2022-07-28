package com.graphics.piece;

import java.awt.image.BufferedImage;

public class PieceUIBuilder {
    private BufferedImage wPawnImg;
    private BufferedImage wBishopImg;
    private BufferedImage wKingImg;
    private BufferedImage wKnightImg;
    private BufferedImage wQueenImg;
    private BufferedImage wRookImg;
    private BufferedImage bPawnImg;
    private BufferedImage bBishopImg;
    private BufferedImage bKingImg;
    private BufferedImage bKnightImg;
    private BufferedImage bQueenImg;
    private BufferedImage bRookImg;

    public PieceUIBuilder setwPawnImg(BufferedImage wPawnImg) {
        this.wPawnImg = wPawnImg;
        return this;
    }

    public PieceUIBuilder setwBishopImg(BufferedImage wBishopImg) {
        this.wBishopImg = wBishopImg;
        return this;
    }

    public PieceUIBuilder setwKingImg(BufferedImage wKingImg) {
        this.wKingImg = wKingImg;
        return this;
    }

    public PieceUIBuilder setwKnightImg(BufferedImage wKnightImg) {
        this.wKnightImg = wKnightImg;
        return this;
    }

    public PieceUIBuilder setwQueenImg(BufferedImage wQueenImg) {
        this.wQueenImg = wQueenImg;
        return this;
    }

    public PieceUIBuilder setwRookImg(BufferedImage wRookImg) {
        this.wRookImg = wRookImg;
        return this;
    }

    public PieceUIBuilder setbPawnImg(BufferedImage bPawnImg) {
        this.bPawnImg = bPawnImg;
        return this;
    }

    public PieceUIBuilder setbBishopImg(BufferedImage bBishopImg) {
        this.bBishopImg = bBishopImg;
        return this;
    }

    public PieceUIBuilder setbKingImg(BufferedImage bKingImg) {
        this.bKingImg = bKingImg;
        return this;
    }

    public PieceUIBuilder setbKnightImg(BufferedImage bKnightImg) {
        this.bKnightImg = bKnightImg;
        return this;
    }

    public PieceUIBuilder setbQueenImg(BufferedImage bQueenImg) {
        this.bQueenImg = bQueenImg;
        return this;
    }

    public PieceUIBuilder setbRookImg(BufferedImage bRookImg) {
        this.bRookImg = bRookImg;
        return this;
    }

    public PieceUI createPieceUI() {
        return new PieceUI(wPawnImg, wBishopImg, wKingImg, wKnightImg, wQueenImg, wRookImg, bPawnImg, bBishopImg, bKingImg, bKnightImg, bQueenImg, bRookImg);
    }
}