package com.chesspack.pieces;

import com.chesspack.Board;
import com.chesspack.Move;
import com.chesspack.Spot;

public class King extends Piece {
    private boolean castlingDone = false;

    public King(boolean white) {
        super(white);
    }

    public boolean isCastlingDone() {
        return this.castlingDone;
    }

    public void setCastlingDone(boolean castlingDone) {
        this.castlingDone = castlingDone;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite())
            return false;

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        if (x + y == 1 || x * y == 1) {
            //Check if this move will not result the king
            //being attacked else return true
            return true;
        }
        return this.isValidCastling(board, start, end);
    }

    private boolean isValidCastling(Board board, Spot start, Spot end) {
        if (this.isCastlingDone())
            return false;
        //Logique pour retourner v ou f
        //king is not checked
        //Verifier si ce n'est pas le premier mouvement
        return false;
    }

    public boolean isCastelingMove(Spot start, Spot end) {
        if (start.getX() != 7 && start.getX() != 0)
            return false;
        return Math.abs(end.getY() - start.getY()) == 2;
    }

    public boolean isCastelingMove(Move move) {
        return this.isCastelingMove(move.getStart(), move.getEnd());
    }
}
