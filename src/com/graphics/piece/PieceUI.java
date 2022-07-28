package com.graphics.piece;

import com.chesspack.Board;
import com.chesspack.pieces.*;
import com.graphics.ResizeImg;
import com.graphics.board.BoardUI;
import com.myclasses.CRectangle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PieceUI {
    private BufferedImage wPawnImg, wBishopImg, wKingImg, wKnightImg, wQueenImg, wRookImg;
    private BufferedImage bPawnImg, bBishopImg, bKingImg, bKnightImg, bQueenImg, bRookImg;

    public PieceUI(BufferedImage wPawnImg, BufferedImage wBishopImg, BufferedImage wKingImg, BufferedImage wKnightImg, BufferedImage wQueenImg, BufferedImage wRookImg, BufferedImage bPawnImg, BufferedImage bBishopImg, BufferedImage bKingImg, BufferedImage bKnightImg, BufferedImage bQueenImg, BufferedImage bRookImg) {
        this.wPawnImg = wPawnImg;
        this.wBishopImg = wBishopImg;
        this.wKingImg = wKingImg;
        this.wKnightImg = wKnightImg;
        this.wQueenImg = wQueenImg;
        this.wRookImg = wRookImg;
        this.bPawnImg = bPawnImg;
        this.bBishopImg = bBishopImg;
        this.bKingImg = bKingImg;
        this.bKnightImg = bKnightImg;
        this.bQueenImg = bQueenImg;
        this.bRookImg = bRookImg;
    }

    public void drawPieces(Graphics2D g, Board gameBoard, BoardUI boardUI, float scale) {
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                BufferedImage toDraw = null;
                Piece p = null;
                try {
                    p = gameBoard.getBox(i, j).getPiece();
                } catch (Exception e) {
                    System.out.println("Bad spot coordinates specified in drawPiece : " + e.getMessage());
                }
                if (p == null)
                    continue;
                else if (p instanceof Pawn)
                    toDraw = p.isWhite() ? wPawnImg : bPawnImg;
                else if (p instanceof Bishop)
                    toDraw = p.isWhite() ? wBishopImg : bBishopImg;
                else if (p instanceof Knight)
                    toDraw = p.isWhite() ? wKnightImg : bKnightImg;
                else if (p instanceof Rook)
                    toDraw = p.isWhite() ? wRookImg : bRookImg;
                else if (p instanceof Queen)
                    toDraw = p.isWhite() ? wQueenImg : bQueenImg;
                else if (p instanceof King)
                    toDraw = p.isWhite() ? wKingImg : bKingImg;

                try {
                    toDraw = ResizeImg.changeSize(toDraw, scale);
                } catch (IOException e) {
                    System.out.println("Echec du redimensionnement dans la fonction drawPieces");
                }

                CRectangle drawRect = boardUI.getDrawingRect(i, j);
                g.drawImage(toDraw,
                        drawRect.x + (drawRect.width - toDraw.getWidth()) / 2,
                        drawRect.y + (drawRect.height - toDraw.getHeight()) / 2,
                        null);
/*
                CRectangle sr = boardUI.getSelectedRect();
                if (sr == null)
                    return;
                boardUI.drawPieceMovement(g, sr.idx, sr.idy, gameBoard);
 */
            }
        }
    }
}
