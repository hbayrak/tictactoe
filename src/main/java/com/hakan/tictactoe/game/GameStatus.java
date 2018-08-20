package com.hakan.tictactoe.game;

/**
 * Game statuses
 * 
 * <li>{@link #NOT_STARTED}</li>
 * <li>{@link #RUNNING}</li>
 * <li>{@link #FINISHED}</li>
 *
 * @author hbayrak
 *
 */
public enum GameStatus {

    /**
     * Game initialized but not started. Player registration is available.
     */
    NOT_STARTED,

    /**
     * Game started, but not ended.
     */
    RUNNING,

    /**
     * Game finished.
     */
    FINISHED;

}
