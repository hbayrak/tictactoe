package com.hakan.tictactoe.game.playfield;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Helper class to print {@link PlayField} to the console
 * 
 * @author hbayrak
 *
 */
public class PlayFieldPrinter {

    public static final String DELIMETER = "|";

    public void print(PlayField playField) {

        Character[][] cells = playField.getCells();

        int size = cells.length;
        printHeader(size);
        for (int i = 0; i < size; i++) {
            System.out.print(i);
            printRow(cells[i]);
        }

    }

    private void printHeader(int size) {
        StringBuilder header = new StringBuilder(" ").append(DELIMETER);
        IntStream.range(0, size).forEach(i -> header.append(i).append(DELIMETER));
        System.out.println(header);
    }

    private void printRow(Character[] row) {
        StringBuilder rowString = new StringBuilder(DELIMETER);
        Arrays.stream(row).forEachOrdered(c -> rowString.append(c != null ? c : " ").append(DELIMETER));
        System.out.println(rowString);
    }

}
