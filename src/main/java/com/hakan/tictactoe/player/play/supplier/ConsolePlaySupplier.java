package com.hakan.tictactoe.player.play.supplier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hakan.tictactoe.game.playfield.Cell;

/**
 * An implementation of the {@link PlaySupplier} that gets the play from console.
 * 
 * @author hbayrak
 *
 */
public class ConsolePlaySupplier implements PlaySupplier {

    /**
     * Reader to get input.
     */
    private BufferedReader bufferedReader;

    /**
     * Constructs the {@link ConsolePlaySupplier}
     */
    public ConsolePlaySupplier() {

        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Prints a prompt and gets the {@link Cell} from console
     */
    public Cell getMarkedCell() {

        String rowCol = readLine("Please enter your move: ");
        return createCellFromConsoleInput(rowCol);
    }

    /**
     * @param input
     *            to create cell from
     * 
     * @return {@link Cell} if the input is in N,N format, {@link Cell#INVALID_CELL} otherwise
     */
    private Cell createCellFromConsoleInput(String input) {

        String[] inputArr = input.trim().split(",");

        if (inputArr.length == 2 && isValidIndexString(inputArr[0]) && isValidIndexString(inputArr[1]))
            return new Cell(Integer.parseInt(inputArr[0]), Integer.parseInt(inputArr[1]));
        else
            return Cell.INVALID_CELL;
    }

    /**
     * Prints a prompt to console and reads input from console
     * 
     * @param prompt
     *            to print to the console.
     * 
     * @return the line read from console.
     */
    private String readLine(String prompt) {
        String input = null;

        System.out.print(prompt);
        try {
            input = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Error occured while getting console input.");
        }
        return input;
    }

    /**
     * @param strNum
     *            to check if valid.
     * 
     * @return {@code true} if strNum is a not negative valid integer, {@code false} otherwise
     * 
     */
    private boolean isValidIndexString(String strNum) {
        try {
            int num = Integer.parseInt(strNum);
            return num >= 0;
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
    }

}
