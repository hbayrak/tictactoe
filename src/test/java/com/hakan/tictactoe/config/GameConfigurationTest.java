package com.hakan.tictactoe.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

public class GameConfigurationTest {

    private static final String TEST_CONFIG         = "game-configuration-unit-test";
    private static final String INVALID_TEST_CONFIG = "game-configuration-unit-test-invalid";
    private static final String NOT_EXISTS_CONFIG   = "not-exists";

    private GameConfiguration   gameConfiguration;

    @Test
    public void givenNoConfigFile_whenGetPlayFieldSizeCalled_thenShouldReturnDefaultPlayFieldSize() {

        gameConfiguration = new GameConfiguration(null);

        int playFieldSize = gameConfiguration.getPlayFieldSize();

        assertThat(playFieldSize).isEqualTo(5);
    }

    @Test
    public void givenConfigFile_whenGetPlayFieldSizeCalled_thenShouldReturnDefaultPlayFieldSize() {

        gameConfiguration = new GameConfiguration(TEST_CONFIG);

        int playFieldSize = gameConfiguration.getPlayFieldSize();

        assertThat(playFieldSize).isEqualTo(3);
    }

    @Test(expected = RuntimeException.class)
    public void givenInvalidConfigFile_whenGetPlayFieldSizeCalled_thenShouldThrowRuntimeException() {

        gameConfiguration = new GameConfiguration(INVALID_TEST_CONFIG);

        gameConfiguration.getPlayFieldSize();

    }

    @Test(expected = RuntimeException.class)
    public void givenNotExistsConfigFileName_whenGetPlayFieldSizeCalled_thenShouldThrowRuntimeException() {

        gameConfiguration = new GameConfiguration(NOT_EXISTS_CONFIG);

        gameConfiguration.getPlayFieldSize();

    }

    @Test
    public void givenNoConfigFile_whenGetPlayerCharacters_thenShouldReturnPlayerCharacters() {

        gameConfiguration = new GameConfiguration(null);

        List<Character> characters = gameConfiguration.getPlayerCharacters();

        assertThat(characters.size()).isEqualTo(3);
        assertThat(characters.get(0)).isEqualTo('A');
        assertThat(characters.get(1)).isEqualTo('B');
        assertThat(characters.get(2)).isEqualTo('C');

    }

    @Test
    public void givenConfigFile_whenGetPlayerCharacters_thenShouldReturnPlayerCharacters() {

        gameConfiguration = new GameConfiguration(TEST_CONFIG);

        List<Character> characters = gameConfiguration.getPlayerCharacters();

        assertThat(characters.size()).isEqualTo(3);
        assertThat(characters.get(0)).isEqualTo('X');
        assertThat(characters.get(1)).isEqualTo('Y');
        assertThat(characters.get(2)).isEqualTo('Z');

    }

    @Test(expected = RuntimeException.class)
    public void givenInvalidConfigFile_whenGetPlayerCharacters_thenShouldThrowRuntimeException() {

        gameConfiguration = new GameConfiguration(INVALID_TEST_CONFIG);

        gameConfiguration.getPlayerCharacters();

    }

}
