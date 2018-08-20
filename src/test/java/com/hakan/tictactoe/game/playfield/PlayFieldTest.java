package com.hakan.tictactoe.game.playfield;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.List;

import org.junit.Test;

import com.hakan.tictactoe.game.playfield.Cell;
import com.hakan.tictactoe.game.playfield.Play;
import com.hakan.tictactoe.game.playfield.PlayField;

public class PlayFieldTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenPlayFieldConstructorCalledWithNegativeSize_thenShouldThrowIllegalArgumentException() {
        new PlayField(-1);
    }

    @Test
    public void whenPlayFieldConstructorCalled_thenShouldCreateAndAssign2DCharArray() {
        int size = 3;
        PlayField playField = new PlayField(size);

        assertThat(playField.getCells()).isNotNull();
        assertThat(playField.getCells().length).isEqualTo(size);
        assertThat(playField.getCells()[0].length).isEqualTo(size);

    }

    @Test
    public void givenPlayField_whenIsPlayValidCalledWithInvalidIndices_thenShouldReturnFalse() {

        PlayField playField = new PlayField(5);

        assertThat(playField.isPlayValid(new Play(new Cell(6, 0), 'X'))).isFalse();
        assertThat(playField.isPlayValid(new Play(new Cell(0, 6), 'X'))).isFalse();
        assertThat(playField.isPlayValid(new Play(new Cell(6, 6), 'X'))).isFalse();

    }

    @Test
    public void givenPlayFieldWithOneNonEmptyCell_whenIsPlayValidCalledWithTheIndices_thenShouldReturnFalse() {

        PlayField playField = new PlayField(5);
        playField.submitPlay(new Play(new Cell(0, 0), 'X'));

        Play play = new Play(new Cell(0, 0), 'X');

        assertThat(playField.isPlayValid(play)).isFalse();
    }

    @Test
    public void givenPlayFieldWithAllEmptyCells_whenIsPlayValidCalledWithValidIndices_thenShouldReturnTrue() {

        PlayField playField = new PlayField(5);

        Play play = new Play(new Cell(0, 0), 'X');

        assertThat(playField.isPlayValid(play)).isTrue();
    }

    @Test
    public void givenPlayField_whenSubmitPlayCalledWithInvalidPlay_thenShouldReturnFalse() {

        PlayField playField = spy(new PlayField(5));

        doReturn(false).when(playField).isPlayValid(any(Play.class));

        boolean isSuccessful = playField.submitPlay(new Play(new Cell(0, 0), 'X'));

        assertThat(isSuccessful).isFalse();
        assertThat(playField.getCells()[0][0]).isNull();

    }

    @Test
    public void givenPlayField_whenSubmitPlayCalledWithValidPlay_thenShouldReturnTrueAndFillTheGivenCell() {

        PlayField playField = spy(new PlayField(5));

        doReturn(true).when(playField).isPlayValid(any(Play.class));

        boolean isSuccessful = playField.submitPlay(new Play(new Cell(0, 0), 'X'));

        assertThat(isSuccessful).isTrue();
        assertThat(playField.getCells()[0][0]).isEqualTo('X');

    }

    @Test
    public void givenPlayField_whenGetAvailableCells_thenShouldReturnEmptyCells() {

        PlayField playField = new PlayField(3);
        playField.submitPlay(new Play(new Cell(1, 1), 'X'));

        List<Cell> availableCells = playField.getAvailableCells();

        assertThat(availableCells).isNotNull();
        assertThat(availableCells.size()).isEqualTo(8);

    }

    @Test
    public void givenEmptyPlayField_whenIsFullCalled_thenShouldReturnFalse() {

        PlayField playField = new PlayField(3);

        assertThat(playField.isFull()).isFalse();
    }

    @Test
    public void givenFullPlayField_whenIsFullCalled_thenShouldReturnTrue() {

        PlayField playField = new PlayField(2);
        playField.submitPlay(new Play(new Cell(0, 0), 'X'));
        playField.submitPlay(new Play(new Cell(0, 1), 'X'));
        playField.submitPlay(new Play(new Cell(1, 0), 'X'));
        playField.submitPlay(new Play(new Cell(1, 1), 'X'));

        assertThat(playField.isFull()).isTrue();
    }

}
