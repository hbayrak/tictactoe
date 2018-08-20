package com.hakan.tictactoe.player;

import com.hakan.tictactoe.game.playfield.Cell;
import com.hakan.tictactoe.game.playfield.Play;
import com.hakan.tictactoe.game.playfield.PlayField;
import com.hakan.tictactoe.player.play.generator.PlayGenerator;

/**
 * AI Player that uses a {@link PlayGenerator} to generate next play
 * 
 * @author hbayrak
 *
 */
public class AIPlayer extends Player {

    private PlayGenerator playGenerator;

    AIPlayer(Character symbol, PlayGenerator playGenerator) {
        super(symbol);
        this.playGenerator = playGenerator;
    }

    @Override
    public Play getNextPlay(PlayField playField) {
        Cell cell = playGenerator.generate(playField);
        return new Play(cell, symbol);
    }

    @Override
    public String toString() {
        return "AI Player " + symbol;
    }

}
