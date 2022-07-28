package com.graphics;

import com.graphics.board.BoardUI;
import com.graphics.board.FillBoardUIBuilder;
import com.graphics.board.TiledBoardUI;
import com.graphics.board.TiledBoardUIBuilder;
import com.graphics.piece.PieceUI;
import com.graphics.piece.PieceUIBuilder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Constante {
    public static final Color ORANGETRANS = new Color(255, 140, 0, 120);
    //public static final Color

    public static BoardUI getBoardTheme(String theme) {
        BufferedImage pixelBoard = null;
        try {
            pixelBoard = ImageIO.read(new File("src/resources/pixelChess/chess/board_alt.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (theme.equalsIgnoreCase("pixel")) {
            return (new FillBoardUIBuilder(pixelBoard)
                    .setOffsetLT(40, 40)
                    .setOffsetRT(40, 40)
                    .setSize(22 * 8)
                    .createInstance());
        } else if (theme.equalsIgnoreCase("tiled")) {
            return (new TiledBoardUIBuilder()
                    .setColor00(new Color(121, 72, 57))
                    .setColor01(new Color(77, 38, 38))
                    .setSize(40 * 8)
                    .createInstance());
        } else if (theme.equalsIgnoreCase("pixel1_5x")) {
            try {
                return (new FillBoardUIBuilder(ResizeImg.changeSize(pixelBoard, 1.5f))
                        .setOffsetLT((int) (40 * 1.5), (int) (40 * 1.5))
                        .setOffsetRT((int) (40 * 1.5), (int) (40 * 1.5))
                        .setSize((int) (22 * 1.5f * 8))
                        .createInstance());
            } catch (IOException e) {
                System.out.println("Echec de la création du theme pixel1_5x." + e.getMessage());
                return getBoardTheme("pixel");
            }
        }

        return null;
    }

    public static PieceUI getPieceTheme(String name) {
        if (name.equalsIgnoreCase("pixel")) {
            return (new PieceUIBuilder()
                    .setwPawnImg(loadImage("src/resources/pixelChess/chess/white_pawn.png"))
                    .setwBishopImg(loadImage("src/resources/pixelChess/chess/white_bishop.png"))
                    .setwKingImg(loadImage("src/resources/pixelChess/chess/white_king.png"))
                    .setwKnightImg(loadImage("src/resources/pixelChess/chess/white_knight.png"))
                    .setwQueenImg(loadImage("src/resources/pixelChess/chess/white_queen.png"))
                    .setwRookImg(loadImage("src/resources/pixelChess/chess/white_rook.png"))
                    .setbPawnImg(loadImage("src/resources/pixelChess/chess/black_pawn.png"))
                    .setbBishopImg(loadImage("src/resources/pixelChess/chess/black_bishop.png"))
                    .setbKingImg(loadImage("src/resources/pixelChess/chess/black_king.png"))
                    .setbKnightImg(loadImage("src/resources/pixelChess/chess/black_knight.png"))
                    .setbQueenImg(loadImage("src/resources/pixelChess/chess/black_queen.png"))
                    .setbRookImg(loadImage("src/resources/pixelChess/chess/black_rook.png"))
                    .createPieceUI());
        } else if (name.equalsIgnoreCase("original")) {
            return (new PieceUIBuilder()
                    .setwPawnImg(loadImage("src/resources/johnChess/size128/w_pawn_png_128px.png"))
                    .setwBishopImg(loadImage("src/resources/johnChess/size128/w_bishop_png_128px.png"))
                    .setwKingImg(loadImage("src/resources/johnChess/size128/w_king_png_128px.png"))
                    .setwKnightImg(loadImage("src/resources/johnChess/size128/w_knight_png_128px.png"))
                    .setwQueenImg(loadImage("src/resources/johnChess/size128/w_queen_png_128px.png"))
                    .setwRookImg(loadImage("src/resources/johnChess/size128/w_rook_png_128px.png"))
                    .setbPawnImg(loadImage("src/resources/johnChess/size128/b_pawn_png_128px.png"))
                    .setbBishopImg(loadImage("src/resources/johnChess/size128/b_bishop_png_128px.png"))
                    .setbKingImg(loadImage("src/resources/johnChess/size128/b_king_png_128px.png"))
                    .setbKnightImg(loadImage("src/resources/johnChess/size128/b_knight_png_128px.png"))
                    .setbQueenImg(loadImage("src/resources/johnChess/size128/b_queen_png_128px.png"))
                    .setbRookImg(loadImage("src/resources/johnChess/size128/b_rook_png_128px.png"))
                    .createPieceUI());
        }
        return null;
    }

    public static BufferedImage loadImage(String src) {
        try {
            return ImageIO.read(new File(src));
        } catch (IOException e) {
            System.out.println("Renvoi d'une image nulle pour " + src);
            return null;
        }
    }
}
