package com.hakan.tictactoe.player.play.generator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.hakan.tictactoe.game.playfield.Cell;
import com.hakan.tictactoe.game.playfield.Play;
import com.hakan.tictactoe.game.playfield.PlayField;

public class EasyLevelPlayGeneratorTest {

    private PlayGenerator playGenerator;

    @Before
    public void setup() {

        playGenerator = new EasyLevelPlayGenerator();

    }

    @Test
    public void whenGenerateCalledWithFullPlayField_thenShouldReturnNull() {

        PlayField playField = mock(PlayField.class);
        when(playField.isFull()).thenReturn(true);

        Cell cell = playGenerator.generate(playField);

        assertThat(cell).isNull();

    }

    @Test
    public void whenGenerateCalledWithFreeCellInFirstColumnPlayField_thenShouldReturnNextAvailableCellInThatColumn() {

        PlayField playField = new PlayField(3);
        playField.submitPlay(new Play(new Cell(0, 0), 'X'));

        Cell cell = playGenerator.generate(playField);

        assertThat(cell).isNotNull();
        assertThat(cell.getRow()).isEqualTo(0);
        assertThat(cell.getColumn()).isEqualTo(1);

    }

    @Test
    public void whenGenerateCalledWithFreeCellInLastColumnPlayField_thenShouldReturnNextAvailableCellInThatColumn() {

        PlayField playField = new PlayField(3);
        playField.submitPlay(new Play(new Cell(0, 0), 'X'));
        playField.submitPlay(new Play(new Cell(0, 1), 'X'));
        playField.submitPlay(new Play(new Cell(0, 2), 'X'));
        playField.submitPlay(new Play(new Cell(1, 0), 'X'));
        playField.submitPlay(new Play(new Cell(1, 1), 'X'));
        playField.submitPlay(new Play(new Cell(1, 2), 'X'));
        playField.submitPlay(new Play(new Cell(2, 0), 'X'));

        Cell cell = playGenerator.generate(playField);

        assertThat(cell).isNotNull();
        assertThat(cell.getRow()).isEqualTo(2);
        assertThat(cell.getColumn()).isEqualTo(1);

    }

}
