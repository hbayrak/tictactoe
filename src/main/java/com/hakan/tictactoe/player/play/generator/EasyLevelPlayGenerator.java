package com.hakan.tictactoe.player.play.generator;

import com.hakan.tictactoe.game.playfield.Cell;
import com.hakan.tictactoe.game.playfield.PlayField;

/**
 * A not so smart play generator.
 * 
 * @author hbayrak
 *
 */
public class EasyLevelPlayGenerator implements PlayGenerator {

    /**
     * Starts to search from upper left to the lower right, to find the first empty cell on play field.
     * Returns null if the play field is full.
     */
    public Cell generate(PlayField playField) {

        if (playField.isFull())
            return null;

        Character[][] cells = playField.getCells();
        int row = 0;
        int col = 0;
        ROW: while (row < cells.length) {
            col = 0;
            while (col < cells[row].length) {
                if (cells[row][col] == null)
                    break ROW;
                col++;
            }
            row++;
        }
        return new Cell(row, col);
    }

}
