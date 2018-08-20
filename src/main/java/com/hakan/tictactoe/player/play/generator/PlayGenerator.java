package com.hakan.tictactoe.player.play.generator;

import com.hakan.tictactoe.game.playfield.Cell;
import com.hakan.tictactoe.game.playfield.PlayField;

/**
 * Play generator specification.
 * 
 * @author hbayrak
 */
public interface PlayGenerator {

    /**
     * Generates a cell to play.
     * 
     * @param playField
     *            to use while considering cell generation
     * 
     * @return the generated cell.
     */
    public Cell generate(PlayField playField);

}
