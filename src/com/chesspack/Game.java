package com.chesspack;

import com.chesspack.pieces.King;
import com.chesspack.pieces.Pawn;
import com.chesspack.pieces.Piece;
import com.chesspack.players.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    protected Player[] players;
    protected Board board;
    protected Player currentTurn;
    protected GameStatus status = null;
    protected List<Move> movesPlayed;

    public Game(Player p1, Player p2) {
        players = new Player[2];
        board = new Board();
        movesPlayed = new ArrayList<>();
        this.initialize(p1, p2);
    }

    private void initialize(Player p1, Player p2) {
        players[0] = p1;
        players[1] = p2;

        board.resetBoard();

        if (p1.isWhiteSide()) {
            this.currentTurn = p1;
        } else {
            this.currentTurn = p2;
        }

        movesPlayed.clear();
    }

    public GameStatus getStatus() {
        return this.status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public boolean isEnd() {
        return this.getStatus() != GameStatus.ACTIVE;
    }

    public boolean playerMove(Player player, int startX, int startY, int endX, int endY) throws Exception {
        Spot startBox = board.getBox(startX, startY);
        Spot endBox = board.getBox(endX, endY);
        Move move = new Move(player, startBox, endBox);
        return this.makeMove(move, player);
    }

    private boolean makeMove(Move move, Player player) {
        Piece sourcePiece = move.getStart().getPiece();
        if (sourcePiece == null) {
            return false;
        }

        //valid player
        if (player != currentTurn) {
            return false;
        }

        if (sourcePiece.isWhite() != player.isWhiteSide()) {
            return false;
        }

        //valid move
        if (!sourcePiece.canMove(board, move.getStart(), move.getEnd())) {
            return false;
        }

        //kill
        Piece destPiece = move.getStart().getPiece();
        if (destPiece != null) {
            destPiece.setKilled(true);
            move.setPieceKilled(destPiece);
        }

            //castling
            if (sourcePiece instanceof King && ((King) sourcePiece).isCastelingMove(move)) {
                move.setCastlingMove(true);
                ((King) sourcePiece).setCastlingDone(true);
            }

        //double moving
        if (sourcePiece instanceof Pawn) {
            Pawn p = ((Pawn) sourcePiece);
            if (p.canDoubleMove() && p.isDoubleMoving(move)) {
                move.setDoubleMoving(true);
            }
            p.setCanDoubleMove(false);
        }

        //store the move
        movesPlayed.add(move);

        //move piece from the stat box to end box
        move.getEnd().setPiece(move.getStart().getPiece());
        move.getStart().setPiece(null);

        if (destPiece instanceof King) {
            if (player.isWhiteSide()) {
                this.setStatus(GameStatus.WHITE_WIN);
            } else {
                this.setStatus(GameStatus.BLACK_WIN);
            }
        }

        //set the current turn to the other player
        if (this.currentTurn == players[0]) {
            this.currentTurn = players[1];
        } else {
            this.currentTurn = players[0];
        }
        return true;
    }

    public boolean playerMove(Player player, String start, String end) throws Exception {
        String mStart = start.toLowerCase();
        String mEnd = end.toLowerCase();

        int startY = mStart.charAt(0) - 'a',
        startX = 8 - Integer.parseInt(String.valueOf(mStart.charAt(1))),
        endY = mEnd.charAt(0) - 'a',
        endX = 8 - Integer.parseInt(String.valueOf(mEnd.charAt(1)));

        return this.playerMove(player, startX, startY, endX, endY);
    }

    public Player getCurrentTurn() {
        return this.currentTurn;
    }
    public Board getBoard() {return this.board;}
}
