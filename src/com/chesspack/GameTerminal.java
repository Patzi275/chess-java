package com.chesspack;

import com.chesspack.pieces.*;
import com.chesspack.players.Player;

public class GameTerminal extends Game {
    public GameTerminal(Player p1, Player p2) {
        super(p1, p2);
    }

    public boolean playerMove(Player player, int startX, int startY, int endX, int endY) throws Exception {
        Spot startBox = board.getBox(startX, startY);
        Spot endBox = board.getBox(endX, endY);
        Move move = new Move(player, startBox, endBox);
        return this.makeMove(move, player);
    }

    private boolean makeMove(Move move, Player player) throws Exception {
        Piece sourcePiece = move.getStart().getPiece();
        if (sourcePiece == null) {
            throw new Exception("Aucune piece a l'emplacement de départ");
        }

        //valid player
        if (player != currentTurn) {
            throw new Exception("Ce n'est pas le tour de ce joueur");
        }

        if (sourcePiece.isWhite() != player.isWhiteSide()) {
            throw new Exception("Ce n'est pas un pions du joueur actuel");
        }

        //valid move
        if (!sourcePiece.canMove(board, move.getStart(), move.getEnd())) {
            System.out.print("From: " + move.getStart().getX() + " " + move.getStart().getY());
            System.out.println(" To: " + move.getEnd().getX() + " " + move.getEnd().getY());
            throw new Exception("Mouvement invalide");
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

    public void printBoard(boolean indice) {
        Board b = this.board;
        char space = '_',
                bishop = 'B',
                king = 'K',
                knight = 'C',
                pawn = 'P',
                queen = 'Q',
                rook = 'R';
        char toPrint = space;

        System.out.println("  abcdefgh");
        for (int i = 0; i < 8; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < 8; j++) {
                Piece p = null;
                try {
                    p = b.getBox(i, j).getPiece();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (p == null)
                    toPrint = space;
                else if (p instanceof Bishop)
                    toPrint = bishop;
                else if (p instanceof King)
                    toPrint = king;
                else if (p instanceof Knight)
                    toPrint = knight;
                else if (p instanceof Pawn)
                    toPrint = pawn;
                else if (p instanceof Queen)
                    toPrint = queen;
                else if (p instanceof Rook)
                    toPrint = rook;

                System.out.print(toPrint);
            }
            System.out.println(" " + (8 - i));
        }
        System.out.println("  abcdefgh");
    }
}
