package com.hakan.tictactoe.player;

import com.hakan.tictactoe.player.play.generator.EasyLevelPlayGenerator;
import com.hakan.tictactoe.player.play.generator.PlayGenerator;
import com.hakan.tictactoe.player.play.supplier.ConsolePlaySupplier;
import com.hakan.tictactoe.player.play.supplier.PlaySupplier;

/**
 * A factory class to construct players.
 * 
 * @author hbayrak
 *
 */
public class PlayerFactory {

    /**
     * Human play supplier implementation to use
     */
    private static final PlaySupplier  playSupplier  = new ConsolePlaySupplier();

    /**
     * AI Play generator implementation to use.
     */
    private static final PlayGenerator playGenerator = new EasyLevelPlayGenerator();

    private static final PlayerFactory instance      = new PlayerFactory();

    // Suppresses default constructor, ensuring non-instantiability.
    private PlayerFactory() {
    }

    public static PlayerFactory getInstance() {
        return instance;
    }

    /**
     * @param playerType
     *            {@link PlayerType#HUMAN} or {@link PlayerType#AI}
     * @param symbol
     *            the character that the player will use
     * 
     * @return the {@link Player} or {@code null} if not implemented player type requested
     */
    public Player getPlayer(PlayerType playerType, Character symbol) {

        if (PlayerType.HUMAN == playerType) {
            return new HumanPlayer(symbol, playSupplier);
        }
        if (PlayerType.AI == playerType) {
            return new AIPlayer(symbol, playGenerator);
        }

        return null;

    }

}
