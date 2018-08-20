package com.hakan.tictactoe.player.play.supplier;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.Test;

import com.hakan.tictactoe.game.playfield.Cell;

public class ConsolePlaySupplierTest {

    private final InputStream systemIn = System.in;

    private PlaySupplier      playSupplier;

    @After
    public void restoreSystemIn() {

        System.setIn(systemIn);
    }

    @Test
    public void givenInvalidLengthInput_whenGetMarkedCellCalled_thenShouldReturnInvalidCell() throws IOException {

        System.setIn(new ByteArrayInputStream("1,2,3".getBytes()));

        playSupplier = new ConsolePlaySupplier();

        Cell cell = playSupplier.getMarkedCell();

        assertThat(cell).isEqualTo(Cell.INVALID_CELL);

    }

    @Test
    public void givenInvalidRowInput_whenGetMarkedCellCalled_thenShouldReturnInvalidCell() throws IOException {

        System.setIn(new ByteArrayInputStream("-1,0".getBytes()));

        playSupplier = new ConsolePlaySupplier();

        Cell cell = playSupplier.getMarkedCell();

        assertThat(cell).isEqualTo(Cell.INVALID_CELL);

    }

    @Test
    public void givenInvalidColumnInput_whenGetMarkedCellCalled_thenShouldReturnInvalidCell() throws IOException {

        System.setIn(new ByteArrayInputStream("0,-1".getBytes()));

        playSupplier = new ConsolePlaySupplier();

        Cell cell = playSupplier.getMarkedCell();

        assertThat(cell).isEqualTo(Cell.INVALID_CELL);

    }

    @Test
    public void givenNotNumberInput_whenGetMarkedCellCalled_thenShouldReturnInvalidCell() throws IOException {

        System.setIn(new ByteArrayInputStream("A,B".getBytes()));

        playSupplier = new ConsolePlaySupplier();

        Cell cell = playSupplier.getMarkedCell();

        assertThat(cell).isEqualTo(Cell.INVALID_CELL);

    }

    @Test
    public void givenValidInput_whenGetMarkedCellCalled_thenShouldReturnCell() throws IOException {

        System.setIn(new ByteArrayInputStream("0,0".getBytes()));

        playSupplier = new ConsolePlaySupplier();

        Cell cell = playSupplier.getMarkedCell();

        assertThat(cell.getRow()).isEqualTo(0);
        assertThat(cell.getColumn()).isEqualTo(0);

    }

}
