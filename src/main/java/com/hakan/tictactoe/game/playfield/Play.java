package com.hakan.tictactoe.game.playfield;

import com.hakan.tictactoe.player.Player;

/**
 * The object that represents a play made by a {@link Player}
 * 
 * @author hbayrak
 *
 */
public class Play {

    private Cell      cell;
    private Character symbol;

    /**
     * @param cell
     *            to specify the cell on the {@link PlayField}
     * @param symbol
     *            to specify the symbol of the {@link Player} who plays
     * 
     * @throws IllegalArgumentException
     *             if cell or symbol is null
     */
    public Play(Cell cell, Character symbol) {
        if (cell == null || symbol == null)
            throw new IllegalArgumentException("Cell and symbol cannot be null");
        this.cell = cell;
        this.symbol = symbol;
    }

    public Cell getCell() {
        return cell;
    }

    public Character getSymbol() {
        return symbol;
    }

}
