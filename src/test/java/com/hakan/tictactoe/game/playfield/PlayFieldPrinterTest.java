package com.hakan.tictactoe.game.playfield;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlayFieldPrinterTest {

    private final ByteArrayOutputStream outContent  = new ByteArrayOutputStream();
    private final PrintStream           originalOut = System.out;

    private PlayField                   playField;

    private PlayFieldPrinter            playFieldPrinter;

    @Before
    public void setup() {

        //Modifying output stream for testing
        System.setOut(new PrintStream(outContent));

        playField = mock(PlayField.class);

        playFieldPrinter = new PlayFieldPrinter();

    }

    @After
    public void restoreStreams() {

        System.setOut(originalOut);
    }

    @Test
    public void whenPrintCalledWithPlayField_thenShouldPrintPlayFieldAsExpected() {

        // @formatter:off
        when(playField.getCells()).thenReturn(new Character[][] {{null,'X' ,null},
                                                                 {'Y' ,'X' ,null},
                                                                 {'Z' ,null,null}});
        
        String expectedOutput  = " |0|1|2|\n"
                               + "0| |X| |\n"
                               + "1|Y|X| |\n"
                               + "2|Z| | |\n";
        // @formatter:on

        playFieldPrinter.print(playField);

        assertThat(outContent.toString()).isEqualTo(expectedOutput);

    }

}
