import com.chesspack.GameTerminal;
import com.chesspack.players.HumanPlayer;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        HumanPlayer patrick = new HumanPlayer(false);
        HumanPlayer john = new HumanPlayer(true);
        GameTerminal game = new GameTerminal(patrick, john);
        FileOutputStream fi = null;

        try {
            fi = new FileOutputStream(new File("log.txt"));
            fi.flush();
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier n'a pas été retrouvé.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Scanner sc = new Scanner(System.in);

        /*
        try {
            game.playerMove(john, "f2", "f3");
            game.playerMove(patrick, "b8", "c6");
            game.playerMove(john, "c2", "c4");
            game.playerMove(patrick, "g7", "g5");
            fi.write(("f2 f3\n").getBytes());
            fi.write(("b8 c6\n").getBytes());
            fi.write(("c2 c4\n").getBytes());
            fi.write(("g7 g5\n").getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/

        while (true) {
            try {
                System.out.println(game.getCurrentTurn().whiteSide ? "Blanc" : "Noir");
                game.printBoard(true);
                System.out.print("Mouvement: ");
                String in = sc.nextLine();
                String[] mv = in.split(" ");
                String mvStart = mv[0];
                String mvEnd = mv[1];
                game.playerMove(game.getCurrentTurn(), mvStart, mvEnd);
                fi.write((in + "\n").getBytes());

            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
        //game.printBoard(true);
    }
}