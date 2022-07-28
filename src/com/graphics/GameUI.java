package com.graphics;

import com.chesspack.Game;
import com.chesspack.pieces.Piece;
import com.chesspack.players.Player;
import com.graphics.board.BoardUI;
import com.graphics.board.FillBoardUI;
import com.graphics.piece.PieceUI;
import com.myclasses.CRectangle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameUI {
    private Game game;
    private BoardUI boardUI;
    private PieceUI pieceUI;
    private BufferedImage wPawnImg, wBishopImg, wKingImg, wKnightImg, wQueenImg, wRookImg;
    private BufferedImage bPawnImg, bBishopImg, bKingImg, bKnightImg, bQueenImg, bRookImg;

    public GameUI(Player p1, Player p2) {
        game = new Game(p1, p2);
        initBoard();
    }

    private void initBoard() {
        //taille constante
        boardUI = Constante.getBoardTheme("tiled"/*"Pixel1_5x"*/);
        pieceUI = Constante.getPieceTheme("original");
    }

    public Dimension getBoardDimension() {
        if (boardUI instanceof FillBoardUI)
            return ((FillBoardUI) boardUI).getImageDimension();
        return boardUI.getBoardRectDimension();
    }

    public void drawBoard(Graphics2D g) {
        boardUI.drawBoard(g);
    }

    public void drawPieces(Graphics2D g) {
        pieceUI.drawPieces(g, game.getBoard(), boardUI, .25f);
    }

    public void onMouseClicked(MouseEvent mouseEvent) {

        CRectangle newSelectedRect = boardUI.getPointedRect(mouseEvent.getPoint());
        CRectangle selectedRect = boardUI.getSelectedRect();

        if (newSelectedRect != null) {
            System.out.println("Un rect a bien été sélectionner");
            if (selectedRect == null) {
                System.out.println("Ancien rectangle sélectionner null");
                boardUI.setSelectedRect(newSelectedRect);
            } else {
                try {
                    if (game.playerMove(game.getCurrentTurn(), selectedRect.idx, selectedRect.idy, newSelectedRect.idx, newSelectedRect.idy)) {
                        System.out.println("Mouvement réussit");

                        if (false) {
                            boolean isWhite = game.getCurrentTurn().isWhiteSide();
                            System.out.println(isWhite ? "is white turn" : "is black turn");
                            Random random = new Random();
                            Piece randomPiece = null;
                            Point point = null;
                            int moveidx = 0;

                            do {
                                point = new Point(random.nextInt(8), random.nextInt(8));
                                randomPiece = game.getBoard().getBox(point.x, point.y).getPiece();
                            } while (randomPiece == null || randomPiece.isWhite() != isWhite);

                            System.out.println("Choosed Point : " + point.x + " " + point.y);
                            ArrayList<Point> pm = game.getBoard().getPossibleMovement(point.x, point.y);
                            do {
                                moveidx = random.nextInt(pm.size());
                                System.out.println("Ai movement trying");
                            } while (!game.playerMove(game.getCurrentTurn(), point.x, point.y, pm.get(moveidx).x, pm.get(moveidx).y));
                        }

                        boardUI.setSelectedRect(null);
                    } else {
                        boardUI.setSelectedRect(newSelectedRect);
                    }
                } catch (Exception e) {
                    System.out.println("Erreur lors du déplacement de la piece.");
                }
            }
        }
        selectedRect = boardUI.getSelectedRect();
        System.out.println(selectedRect == null ? "Clicked null" : "Clicked Rect (ligne, colonne): (" + selectedRect.idx + ", " + selectedRect.idy + ")");
    }

    public void drawSelectedSquare(Graphics2D g2d) {
        boardUI.drawSquareOnSelectedRect(g2d, game.getBoard());
    }
    public void drawSelectedMove(Graphics2D g2d) {
        CRectangle sr = boardUI.getSelectedRect();
        Piece selectedPiece = null;
        try {
            selectedPiece = game.getBoard().getBox(sr.idx, sr.idy).getPiece();
        } catch (Exception e) {}

        if (sr != null &&  selectedPiece != null && selectedPiece.isWhite() == game.getCurrentTurn().isWhiteSide()) {
            boardUI.drawPieceMovement(g2d, sr.idx, sr.idy, game.getBoard());
            this.drawSelectedSquare(g2d);
        }
    }

}
