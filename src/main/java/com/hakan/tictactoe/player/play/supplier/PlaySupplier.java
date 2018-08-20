package com.hakan.tictactoe.player.play.supplier;

import com.hakan.tictactoe.game.playfield.Cell;

/**
 * Play supplier specification.
 * 
 * @author hbayrak
 */
public interface PlaySupplier {

    /**
     * Returns the cell to play.
     * 
     * @return the cell.
     */
    public Cell getMarkedCell();

}
