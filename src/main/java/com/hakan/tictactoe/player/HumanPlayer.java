package com.hakan.tictactoe.player;

import com.hakan.tictactoe.game.playfield.Cell;
import com.hakan.tictactoe.game.playfield.Play;
import com.hakan.tictactoe.game.playfield.PlayField;
import com.hakan.tictactoe.player.play.supplier.PlaySupplier;

/**
 * Human Player that uses a {@link PlaySupplier} to get next play
 * 
 * @author hbayrak
 *
 */
public class HumanPlayer extends Player {

    private PlaySupplier playSupplier;

    HumanPlayer(Character symbol, PlaySupplier playSupplier) {
        super(symbol);
        this.playSupplier = playSupplier;
    }

    @Override
    public Play getNextPlay(PlayField playField) {
        Cell markedCell = playSupplier.getMarkedCell();
        return new Play(markedCell, symbol);
    }

    @Override
    public String toString() {
        return "Human Player " + symbol;
    }

}
