package com.chesspack;

import com.chesspack.pieces.*;

import java.awt.*;
import java.util.ArrayList;

public class Board {
    Spot[][] boxes;

    public Board() {
        boxes = new Spot[8][8];
        this.resetBoard();
    }

    public Spot getBox(int x, int y) throws Exception {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new Exception("Index out of bound");
        }
        return boxes[x][y];
    }

    public void resetBoard() {
        //Initialisation des pieces
            //Pieces blanches
        boxes[0][0] = new Spot(0, 0, new Rook(false));
        boxes[0][1] = new Spot(0, 1, new Knight(false));
        boxes[0][2] = new Spot(0, 2, new Bishop(false));
        boxes[0][3] = new Spot(0, 3, new Queen(false));
        boxes[0][4] = new Spot(0, 4, new King(false));
        boxes[0][5] = new Spot(0, 5, new Bishop(false));
        boxes[0][6] = new Spot(0, 6, new Knight(false));
        boxes[0][7] = new Spot(0, 7, new Rook(false));

        for (int i = 0; i < 8; i++) {
            boxes[1][i] = new Spot(1, i, new Pawn(false, Pawn.Direction.DOWN));
        }

            //Pieces noires
        boxes[7][0] = new Spot(7, 0, new Rook(true));
        boxes[7][1] = new Spot(7, 1, new Knight(true));
        boxes[7][2] = new Spot(7, 2, new Bishop(true));
        boxes[7][3] = new Spot(7, 3, new Queen(true));
        boxes[7][4] = new Spot(7, 4, new King(true));
        boxes[7][5] = new Spot(7, 5, new Bishop(true));
        boxes[7][6] = new Spot(7, 6, new Knight(true));
        boxes[7][7] = new Spot(7, 7, new Rook(true));

        for (int i = 0; i < 8; i++) {
            boxes[6][i] = new Spot(6, i, new Pawn(true, Pawn.Direction.UP));
        }

            //Cases vides
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                boxes[i][j] = new Spot(i, j, null);
            }
        }
    }

    public ArrayList<Point> getPossibleMovement(int x, int y) {
        Piece piece = null;
        Spot start = null;
        Spot end = null;
        ArrayList<Point> liste = new ArrayList<>();

        try {
            start = getBox(x, y);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        piece = start.getPiece();
        if (piece == null)
            return null;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    end = getBox(i, j);
                } catch (Exception e) {
                    System.err.println("Error: bad index getSelectdRect function" + e.getMessage());
                }
                if (piece.canMove(this, start, end)) {
                    liste.add(new Point(i, j));
                }
            }
        }
        return liste;
    }
}
