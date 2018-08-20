package com.hakan.tictactoe.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.hakan.tictactoe.game.playfield.Cell;
import com.hakan.tictactoe.game.playfield.Play;
import com.hakan.tictactoe.game.playfield.PlayField;
import com.hakan.tictactoe.player.play.supplier.PlaySupplier;

public class HumanPlayerTest {

    private static final Character SYMBOL = 'X';

    private Player                 player;

    private PlaySupplier           playSupplier;

    @Before
    public void setup() {

        playSupplier = mock(PlaySupplier.class);

        player = new HumanPlayer(SYMBOL, playSupplier);
    }

    @Test
    public void whenGetNextPlayCalled_ThenShouldReturnCellReturnedFromSupplier() {

        Cell cellStub = new Cell(0, 0);

        when(playSupplier.getMarkedCell()).thenReturn(cellStub);

        Play play = player.getNextPlay(new PlayField(3));

        assertThat(play.getSymbol()).isEqualTo(SYMBOL);
        assertThat(play.getCell().getRow()).isEqualTo(cellStub.getRow());
        assertThat(play.getCell().getRow()).isEqualTo(cellStub.getColumn());

    }

    @Test
    public void whenToStringCalled_thenShoulReturnInExpectedFormat() {

        String expected = "Human Player " + SYMBOL;

        assertThat(player.toString()).isEqualTo(expected);

    }

}
