package com.hakan.tictactoe.game.win;

import com.hakan.tictactoe.game.playfield.Play;
import com.hakan.tictactoe.game.playfield.PlayField;

/**
 * Win checker interface to use in turn based games.
 * 
 * @author hbayrak
 *
 */
public interface WinChecker {

    /**
     * Checks the play field with the specified play to decide if the play is the winning play
     * 
     * @param playField
     * @param play
     *            the last play to check
     * 
     * @return {@code true} if the last play is the winning play, {@code false} otherwise
     */
    public boolean isWinningPlay(PlayField playField, Play play);

}
