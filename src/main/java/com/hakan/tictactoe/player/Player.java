package com.hakan.tictactoe.player;

import java.util.Objects;

import com.hakan.tictactoe.game.playfield.Play;
import com.hakan.tictactoe.game.playfield.PlayField;

/**
 * Abstract Player class
 * 
 * @see AIPlayer
 * @see HumanPlayer
 * 
 * @author hbayrak
 *
 */
public abstract class Player {

    /**
     * Player's symbol
     */
    protected final Character symbol;

    /**
     * Constructs {@link Player}
     * 
     * @param symbol
     *            the players symbol
     * 
     * @throws IllegalArgumentException
     *             if symbol is null or empty
     */
    Player(Character symbol) {
        if (symbol == null || symbol == ' ')
            throw new IllegalArgumentException("Symbol cannot be null or empty");
        this.symbol = symbol;
    }

    public Character getSymbol() {
        return symbol;
    }

    /**
     * Returns the next play of the player
     * 
     * @param playField
     *            to consider while generating next play
     * 
     * @return the next play of the player
     */
    public abstract Play getNextPlay(PlayField playField);

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Player)) {
            return false;
        }

        Player player = (Player) obj;
        return Objects.equals(this.symbol, player.getSymbol());

    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }

    @Override
    public String toString() {
        return "Player " + symbol;
    }

}
