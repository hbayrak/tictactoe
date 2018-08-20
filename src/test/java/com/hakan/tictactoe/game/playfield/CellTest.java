package com.hakan.tictactoe.game.playfield;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class CellTest {

    @Test
    public void whenConstructorCalledWithValidRowAndColumn_thenShouldAssignToRowAndCol() {
        assertThat(new Cell(0, 0).getRow()).isEqualTo(0);
        assertThat(new Cell(0, 0).getColumn()).isEqualTo(0);
    }

    @Test
    public void whenToStringCalled_thenShouldReturnExpectedStringFormat() {

        Cell cell = new Cell(3, 4);
        assertThat(cell.toString()).isEqualTo("(3,4)");

    }

}
