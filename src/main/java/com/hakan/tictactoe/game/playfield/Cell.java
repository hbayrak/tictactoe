package com.hakan.tictactoe.game.playfield;

/**
 * The cell object that points the specific cell on the {@link PlayField}
 * 
 * @author hbayrak
 *
 */
public class Cell {

    /**
     * Used to point an invalid cell
     */
    public static final Cell INVALID_CELL = new Cell(-1, -1);

    private int              row;
    private int              column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "(" + row + "," + column + ")";
    }

}
