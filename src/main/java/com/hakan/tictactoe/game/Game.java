package com.hakan.tictactoe.game;

import java.util.Set;

import com.hakan.tictactoe.player.Player;

/**
 * A turn based game specification.
 * 
 * @author hbayrak
 *
 */
public interface Game {

    /**
     * Minimum player count ({@value}) to start a game.
     */
    int MIN_PLAYER_COUNT = 2;

    /**
     * Registers the player to the game.
     * 
     * @param player
     *            to register
     * @return {@code true} if the {@link Player} registered, {@code false} if not.
     */
    boolean registerPlayer(Player player);

    /**
     * Validates and starts the game.
     */
    void start();

    /**
     * Plays one turn with the current player and takes the turn to the next player.
     */
    void playOneTurn();

    /**
     * Returns a set that contains the players registered to the game.
     * 
     * @return players that are registered to the game.
     */
    Set<Player> getPlayers();

    /**
     * Returns the game status. {@link GameStatus}
     * 
     * @return game status
     */
    GameStatus getStatus();

    /**
     * Returns the winner if exists.
     * 
     * @return the winner player if exists, {@code null} otherwise.
     */
    Player getWinner();

}
