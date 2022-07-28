package com.graphics;

import com.chesspack.players.HumanPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PrincipalUI extends JPanel {


    GameUI gameGraphic;
    private Dimension boardDimension;

    public PrincipalUI() {
        gameGraphic = new GameUI(new HumanPlayer(true), new HumanPlayer(false));
        setPreferredSize(gameGraphic.getBoardDimension());

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                gameGraphic.onMouseClicked(mouseEvent);
                repaint();
                PrincipalUI.super.repaint();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                //System.out.println(mouseEvent.getPoint());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        gameGraphic.drawBoard(g2d);
        gameGraphic.drawPieces(g2d);

        gameGraphic.drawSelectedMove(g2d); //Contient drawSelectSquare(g2d)
    }

}
