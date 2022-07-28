package com.chesspack;

import com.chesspack.pieces.Piece;

import java.awt.*;

public class Spot {
    private Piece piece;
    Point point;

    public Spot(int x, int y, Piece piece) {
        point = new Point(x, y);
        this.piece = piece;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getX() {
        return (int) point.x;
    }

    public void setX(int x) {
        this.point.x = x;
    }

    public int getY() {
        return this.point.y;
    }

    public void setY(int y) {
        this.point.y = y;
    }

    public Point getPoint() {
        return (Point) this.point.clone();
    }
}
