package com.chesspack;

import com.chesspack.pieces.Piece;
import com.chesspack.players.Player;

public class Move {
    private Player player;
    private Spot start, end;
    private Piece pieceMoved, pieceKilled;
    private boolean castlingMove = false;
    private boolean doubleMoving = false;

    public Move(Player player, Spot start, Spot end) {
        this.player = player;
        this.start = start;
        this.end = end;
        this.pieceMoved = start.getPiece();
    }

    public boolean isCastlingMove() {
        return this.castlingMove;
    }

    public void setCastlingMove(boolean castlingMove) {
        this.castlingMove = castlingMove;
    }

    public boolean isDoubleMoving() {
        return this.doubleMoving;
    }

    public void setDoubleMoving(boolean doubleMoving) {
        this.doubleMoving = doubleMoving;
    }

    public Spot getStart() {
        return this.start;
    }

    public Spot getEnd() {
        return this.end;
    }

    public void setPieceKilled(Piece destPiece) {
        this.pieceKilled = destPiece;
    }
}
