package com.hakan.tictactoe;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TicTacToeIntegrationTest {

    private static final String         TEST_CONFIG = "game-configuration-integration-test";

    private final ByteArrayOutputStream outContent  = new ByteArrayOutputStream();

    private final PrintStream           systemOut   = System.out;
    private final InputStream           systemIn    = System.in;

    @Before
    public void setup() {

        //Modifying output stream for testing
        System.setOut(new PrintStream(outContent));

    }

    @After
    public void restoreSystem() {

        System.setIn(systemIn);
        System.setOut(systemOut);

    }

    @Test
    public void givenPlayerInputs_whenMainCalled_thenGameShouldEnd() {

        // @formatter:off
        String input = new StringBuilder().append("0,0\n")
                .append("1,0\n")
                .append("1,0\n")          //not empty cell
                .append("123123123123\n") //invalid input
                .append("A,B\n")          //invalid input
                .append("1,\n")           //invalid input
                .append("1,2.,3\n")       //invalid input
                .append("1,1\n")
                .append("1,2\n")
                .append("1,3\n")
                .append("2,0\n")
                .append("2,1\n")
                .append("2,2\n")
                .append("2,3\n")
                .append("3,0\n")
                .append("3,1\n")
                .append("3,2\n")
                .append("3,3\n").toString();
        // @formatter:on

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        TicTacToe.main(TEST_CONFIG);

        String consoleOutput = outContent.toString();

        assertThat(consoleOutput).contains("Game ended with");

    }

}
