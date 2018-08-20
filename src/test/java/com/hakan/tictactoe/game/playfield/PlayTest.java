package com.hakan.tictactoe.game.playfield;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.hakan.tictactoe.game.playfield.Cell;
import com.hakan.tictactoe.game.playfield.Play;

public class PlayTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenConstructorCalledWithNullCellAndNonNullSymbol_thenShouldThrowInvalidArgumentException() {

        new Play(null, 'X');
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenConstructorCalledWithNonNullCellAndNullSymbol_thenShouldThrowInvalidArgumentException() {

        new Play(new Cell(0, 0), null);
    }

    @Test
    public void whenConstructorCalledWithNonNullCellAndSymbol_thenShouldAssignToCellAndSymbol() {

        Play play = new Play(new Cell(0, 0), 'X');

        assertThat(play.getCell()).isNotNull();
        assertThat(play.getSymbol()).isNotNull();
    }

    @Test
    public void whenConstructorCalledWithValidRowAndColumn_thenShouldCreateCell() {

        Cell cell = new Cell(0, 0);

        assertThat(cell.getRow()).isEqualTo(0);
        assertThat(cell.getColumn()).isEqualTo(0);
    }

}
