package com.chesspack.pieces;

import com.chesspack.Board;
import com.chesspack.Move;
import com.chesspack.Spot;

public class Pawn extends Piece {
    public enum Direction {UP, DOWN}
    Direction direction = null;
    boolean canDoubleMove = true;
    public Pawn(boolean white, Direction direction) {
        super(white);
        this.direction = direction;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite())
            return false;

        int x = start.getX() - end.getX();
        int y = Math.abs(start.getY() - end.getY());

        //Verifie la bonne direction
        if ((x < 0 && this.direction == Direction.UP) || //x > 0 : se dirige vers le haut
                (x > 0 && this.direction == Direction.DOWN))
            return false;
        x = Math.abs(x);

        //x ligne     y colonne
        if (x == 1) {
            if (y == 0) {
                if (end.getPiece() == null)
                    return true;
                else
                    return false;
            } else if (y == 1 && end.getPiece() != null) {
                return true;
            }
        } else if (canDoubleMove()){
            boolean posCond = x == 2 && y == 0;
            boolean notkillCond = end.getPiece() == null;
            boolean noPawnBetweenCond = false;
            try {
                noPawnBetweenCond = board.getBox(end.getX() - (this.direction == Direction.UP ? -1 : 1), end.getY()).getPiece() == null;
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (posCond && notkillCond && noPawnBetweenCond)
                return true;
        }
        return false;
    }

    private boolean isValidDoubleMove(Board board, Spot start, Spot end) {
        //Check movement validity suivant le contexte
        if (canDoubleMove()) {
            return true;
        }
        return false;
    }

    public boolean canDoubleMove() {
        return canDoubleMove;
    }

    public void setCanDoubleMove(boolean canDoubleMove) {
        this.canDoubleMove = canDoubleMove;
    }

    public boolean isDoubleMoving(Move move) {
        int x = Math.abs(move.getStart().getX() - move.getEnd().getX());
        if (move.getEnd().getY() != move.getStart().getY())
            return false;
        return x == 2;
    }
}
