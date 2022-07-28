package com.chesspack.players;

public class ComputerPlayer extends Player {
    public ComputerPlayer(boolean whiteSide) {
        this.whiteSide = whiteSide;
        this.humanPlayer = false;
    }
}
