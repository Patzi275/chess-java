package com.chesspack.pieces;

import com.chesspack.Board;
import com.chesspack.Spot;

public class Rook extends Piece {
    public Rook(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite())
            return false;

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return x * y == 0 && noObstacleBetween(board, start, end);
    }

    private boolean noObstacleBetween(Board board, Spot start, Spot end) {
        int x = start.getX() - end.getX();
        int y = start.getY() - end.getY();

        if (y == 0) {
            int ml = (x > 0) ? -1 : 1;
            for (int i = 1; i <= Math.abs(x); i++) {
                Piece p = null;
                try {
                    System.out.println("Visionnage de " + (start.getX() + ml*i) + " " + start.getY());
                    p = board.getBox(start.getX() + ml*i, start.getY()).getPiece();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (p != null && p.isWhite() == start.getPiece().isWhite()) {
                    return false;
                }
            }
            return true;
        } else if (x == 0) {
            return true;
        }
        return false;
    }
}
