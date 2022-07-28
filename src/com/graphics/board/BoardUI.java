package com.graphics.board;

import com.chesspack.Board;
import com.graphics.Constante;
import com.myclasses.CRectangle;

import java.awt.*;
import java.util.ArrayList;

public abstract class BoardUI {
    protected CRectangle[][] casesRects;
    protected CRectangle boardRect;
    protected CRectangle selectedRect;
    protected int offsetLTX = 0, offsetLTY = 0,
            offsetRTX = 0, offsetRTY = 0,
            size,
            caseSize;


    public BoardUI(int offsetLTX, int offsetLTY, int offsetRTX, int offsetRTY, int size) {
        this.offsetLTX = offsetLTX;
        this.offsetLTY = offsetLTY;
        this.offsetRTX = offsetRTX;
        this.offsetRTY = offsetRTY;
        this.size = size;
        this.caseSize = size / 8;
        casesRects = new CRectangle[8][8];
        selectedRect = null;
        updateBoardRect();
    }

    public Dimension getBoardRectDimension() {
        return new Dimension(size, size);
    }

    public CRectangle getSelectedRect() {
        return selectedRect;
    }

    public CRectangle getDrawingRect(int lidx, int lidy) {
        return this.casesRects[lidy][lidx];
    }

    public Point getPointedIndex(Point point) {
        return new Point((point.x - offsetLTX) / caseSize,
                (point.y - offsetLTY) / caseSize);
    }

    public CRectangle getPointedRect(Point point) {
        if (!this.contains(point))
            return null;
        int y = (point.x - offsetLTX) / caseSize;
        int x = (point.y - offsetLTY) / caseSize;

        return getRect(x, y);
    }

    public CRectangle getRect(int x, int y) {
        return this.casesRects[x][y];
    }

    public void setSelectedRect(CRectangle selectedRect) {
        this.selectedRect = selectedRect;
    }

    public boolean contains(Point global_point) {
        return this.boardRect.contains(global_point);
    }

    public void updateBoardRect() {
        boardRect = new CRectangle(offsetLTX, offsetLTY, size, size);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                casesRects[i][j] = new CRectangle(offsetLTX + caseSize * i, offsetLTY + caseSize * j, caseSize, caseSize, i, j);
            }
        }
    }

    public void drawPieceMovement(Graphics2D g, int x, int y, Board board) {
        ArrayList<Point> liste = board.getPossibleMovement(x, y);
        Color initialColor = g.getColor();
        if (liste == null)
            return;

        for (Point p : liste) {
            g.setColor(Constante.ORANGETRANS);
            g.fill(getDrawingRect(p.x, p.y));
        }
        g.setColor(initialColor);
    }

    public void drawPieceMovement(Graphics2D g, ArrayList<Point> liste) {
        Color initialColor = g.getColor();
        if (liste == null)
            return;

        for (Point p : liste) {
            g.setColor(Constante.ORANGETRANS);
            g.fill(getDrawingRect(p.x, p.y));
        }
        g.setColor(initialColor);
    }

    public void drawSquareOnSelectedRect(Graphics2D g, Board board) {
        if (selectedRect == null)
            return;
        try {
            if (board.getBox(selectedRect.idx, selectedRect.idy).getPiece() == null)
                return;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Color initialColor = g.getColor();
        g.setColor(Constante.ORANGETRANS);
        g.setStroke(new BasicStroke(4f));
        g.draw(getDrawingRect(selectedRect.idx, selectedRect.idy));
        g.setColor(initialColor);
    }

    public void drawAllRect(Graphics2D g) {
        Color initialColor = g.getColor();
        g.setColor(Color.RED);
        for (CRectangle[] rt : this.casesRects) {
            for (CRectangle rec : rt) {
                g.draw(rec);
            }
        }
        g.setColor(initialColor);
    }

    public abstract void drawBoard(Graphics2D g);


}
