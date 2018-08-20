package com.hakan.tictactoe.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.hakan.tictactoe.game.playfield.Cell;
import com.hakan.tictactoe.game.playfield.Play;
import com.hakan.tictactoe.game.playfield.PlayField;
import com.hakan.tictactoe.player.play.generator.PlayGenerator;

public class AIPlayerTest {

    private static final Character SYMBOL = 'X';

    private Player                 player;

    private PlayGenerator          playGenerator;

    @Before
    public void setup() {

        playGenerator = mock(PlayGenerator.class);

        player = new AIPlayer(SYMBOL, playGenerator);
    }

    @Test
    public void whenGetNextPlayCalled_ThenShouldReturnCellReturnedFromGenerator() {

        Cell cellStub = new Cell(0, 0);

        when(playGenerator.generate(any(PlayField.class))).thenReturn(cellStub);

        Play play = player.getNextPlay(new PlayField(3));

        assertThat(play.getSymbol()).isEqualTo(SYMBOL);
        assertThat(play.getCell().getRow()).isEqualTo(cellStub.getRow());
        assertThat(play.getCell().getRow()).isEqualTo(cellStub.getColumn());

    }

    @Test
    public void whenToStringCalled_thenShoulReturnInExpectedFormat() {

        String expected = "AI Player " + SYMBOL;

        assertThat(player.toString()).isEqualTo(expected);

    }

}
