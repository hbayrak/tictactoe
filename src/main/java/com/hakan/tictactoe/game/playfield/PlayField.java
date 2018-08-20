package com.hakan.tictactoe.game.playfield;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A square play field that is usable for turn based games.
 * 
 * @author hbayrak
 *
 */
public class PlayField {

    /**
     * A 2D array to simulate the square play field.
     */
    private final Character[][] cells;

    /**
     * Constructs the play field
     * 
     * @param size
     *            size of the play field
     * 
     * @throws IllegalArgumentException
     *             if size is negative.
     */
    public PlayField(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("Size must be greater than zero.");

        this.cells = new Character[size][size];
    }

    /**
     * Checks if the play valid
     * 
     * @param play
     *            to check if valid
     * 
     * @return {@code true} if the {@link #cells} has the cell specified by the play an the cell is empty, {@code false}
     *         otherwise
     */
    public boolean isPlayValid(Play play) {

        int row = play.getCell().getRow();
        int column = play.getCell().getColumn();

        if (isCellExists(row, column) && isCellEmpty(row, column)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Submits a play to the play field
     * 
     * @param play
     *            to submit
     * 
     * @return {@code true} if the play is submitted to the {@link PlayField} successfully, {@code false} otherwise
     */
    /**
     * @param play
     * @return
     */
    public boolean submitPlay(Play play) {

        int row = play.getCell().getRow();
        int column = play.getCell().getColumn();

        if (isPlayValid(play)) {
            cells[row][column] = play.getSymbol();
            return true;
        } else {
            return false;
        }

    }

    /**
     * Returns the available cells
     * 
     * @return the available cells on the {@link PlayField}
     */
    public List<Cell> getAvailableCells() {

        List<Cell> availableCells = new ArrayList<>();

        int rowCount = cells.length;
        for (int i = 0; i < rowCount; i++) {
            int columnCount = cells[i].length;
            for (int j = 0; j < columnCount; j++) {
                if (cells[i][j] == null) {
                    availableCells.add(new Cell(i, j));
                }
            }
        }

        return availableCells;
    }

    /**
     * Checks if the play field full
     * 
     * @return {@code true} if the {@link cells} has no available cell, {@code false} otherwise
     */
    public boolean isFull() {
        return !Arrays.stream(cells).flatMap(Arrays::stream).anyMatch(obj -> obj == null);
    }

    public Character[][] getCells() {
        return cells.clone();
    }

    private boolean isCellExists(int row, int column) {

        return row >= 0 && row < cells.length && column >= 0 && column < cells[row].length;
    }

    private boolean isCellEmpty(int row, int column) {

        return cells[row][column] == null;
    }

}
