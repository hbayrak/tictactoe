package com.hakan.tictactoe.game.win;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.hakan.tictactoe.game.playfield.Cell;
import com.hakan.tictactoe.game.playfield.Play;
import com.hakan.tictactoe.game.playfield.PlayField;

public class TicTacToeWinCheckerTest {

    private WinChecker winChecker;

    @Before
    public void setup() {

        winChecker = new TicTacToeWinChecker();

    }

    @Test
    public void whenIsWinningPlayCalledWithEmptyPlayFieldAndAPlay_shouldReturnFalse() {

        PlayField playField = new PlayField(3);

        Play play = new Play(new Cell(0, 0), 'X');
        boolean isWinningPlay = winChecker.isWinningPlay(playField, play);

        assertThat(isWinningPlay).isFalse();

    }

    @Test
    public void whenIsWinningPlayCalledWithAWinningColumnPlay_thenShouldReturnTrue() {

        PlayField playField = new PlayField(3);
        playField.submitPlay(new Play(new Cell(0, 0), 'X'));
        playField.submitPlay(new Play(new Cell(0, 1), 'X'));

        Play play = new Play(new Cell(0, 2), 'X');
        playField.submitPlay(play);
        boolean isWinningPlay = winChecker.isWinningPlay(playField, play);

        assertThat(isWinningPlay).isTrue();

    }

    @Test
    public void whenIsWinningPlayCalledWithANotWinningColumnPlay_thenShouldReturnFalse() {

        PlayField playField = new PlayField(3);
        playField.submitPlay(new Play(new Cell(0, 0), 'X'));
        playField.submitPlay(new Play(new Cell(0, 1), 'X'));

        Play play = new Play(new Cell(0, 2), 'Y');
        playField.submitPlay(play);
        boolean isWinningPlay = winChecker.isWinningPlay(playField, play);

        assertThat(isWinningPlay).isFalse();

    }

    @Test
    public void whenIsWinningPlayCalledWithAWinningRowPlay_thenShouldReturnTrue() {

        PlayField playField = new PlayField(3);
        playField.submitPlay(new Play(new Cell(0, 0), 'X'));
        playField.submitPlay(new Play(new Cell(1, 0), 'X'));

        Play play = new Play(new Cell(2, 0), 'X');
        playField.submitPlay(play);
        boolean isWinningPlay = winChecker.isWinningPlay(playField, play);

        assertThat(isWinningPlay).isTrue();

    }

    @Test
    public void whenIsWinningPlayCalledWithANotWinningRowPlay_thenShouldReturnFalse() {

        PlayField playField = new PlayField(3);
        playField.submitPlay(new Play(new Cell(0, 0), 'X'));
        playField.submitPlay(new Play(new Cell(0, 1), 'X'));

        Play play = new Play(new Cell(0, 2), 'Y');
        playField.submitPlay(play);
        boolean isWinningPlay = winChecker.isWinningPlay(playField, play);

        assertThat(isWinningPlay).isFalse();

    }

    @Test
    public void whenIsWinningPlayCalledWithAWinningDiagonalStartingFromLeftPlay_thenShouldReturnTrue() {

        PlayField playField = new PlayField(3);
        playField.submitPlay(new Play(new Cell(0, 0), 'X'));
        playField.submitPlay(new Play(new Cell(1, 1), 'X'));

        Play play = new Play(new Cell(2, 2), 'X');
        playField.submitPlay(play);
        boolean isWinningPlay = winChecker.isWinningPlay(playField, play);

        assertThat(isWinningPlay).isTrue();

    }

    @Test
    public void whenIsWinningPlayCalledWithAWinningDiagonalStartingFromLeftPlay_thenShouldReturnFalse() {

        PlayField playField = new PlayField(3);
        playField.submitPlay(new Play(new Cell(0, 0), 'X'));
        playField.submitPlay(new Play(new Cell(1, 1), 'X'));

        Play play = new Play(new Cell(2, 2), 'Y');
        playField.submitPlay(play);
        boolean isWinningPlay = winChecker.isWinningPlay(playField, play);

        assertThat(isWinningPlay).isFalse();

    }

    @Test
    public void whenIsWinningPlayCalledWithAWinningDiagonalStartingFromRightPlay_thenShouldReturnTrue() {

        PlayField playField = new PlayField(3);
        playField.submitPlay(new Play(new Cell(0, 2), 'X'));
        playField.submitPlay(new Play(new Cell(1, 1), 'X'));

        Play play = new Play(new Cell(2, 0), 'X');
        playField.submitPlay(play);
        boolean isWinningPlay = winChecker.isWinningPlay(playField, play);

        assertThat(isWinningPlay).isTrue();

    }

    @Test
    public void whenIsWinningPlayCalledWithAWinningDiagonalStartingFromRightPlay_thenShouldReturnFalse() {

        PlayField playField = new PlayField(3);
        playField.submitPlay(new Play(new Cell(0, 2), 'X'));
        playField.submitPlay(new Play(new Cell(1, 1), 'X'));

        Play play = new Play(new Cell(2, 0), 'Y');
        playField.submitPlay(play);
        boolean isWinningPlay = winChecker.isWinningPlay(playField, play);

        assertThat(isWinningPlay).isFalse();

    }

}
