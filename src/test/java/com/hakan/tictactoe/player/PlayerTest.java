package com.hakan.tictactoe.player;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.hakan.tictactoe.game.playfield.Play;
import com.hakan.tictactoe.game.playfield.PlayField;

public class PlayerTest {

    private static final Character SYMBOL = 'X';

    private Player                 player;

    @Before
    public void setup() {

        player = new Player(SYMBOL) {

            @Override
            public Play getNextPlay(PlayField playField) {
                return null;
            }
        };

    }

    @Test(expected = IllegalArgumentException.class)
    public void whenConstructorCalledWithNullSymbol_thenShoulReturnIllegalArgumentException() {

        new Player(null) {

            @Override
            public Play getNextPlay(PlayField playField) {
                // TODO Auto-generated method stub
                return null;
            }
        };

    }

    @Test(expected = IllegalArgumentException.class)
    public void whenConstructorCalledWithSpaceSymbol_thenShoulReturnIllegalArgumentException() {

        new Player(' ') {

            @Override
            public Play getNextPlay(PlayField playField) {
                // TODO Auto-generated method stub
                return null;
            }
        };

    }

    @Test
    public void whenEqualsCalledWithNull_thenShouldReturnFalse() {

        assertThat(player.equals(null)).isEqualTo(false);

    }

    @Test
    public void whenEqualsCalledWithSamePlayer_thenShouldReturnTrue() {

        assertThat(player.equals(player)).isEqualTo(true);

    }

    @Test
    public void whenEqualsCalledWithSameSymbolPlayer_thenShouldReturnTrue() {

        Player player = new Player(SYMBOL) {

            @Override
            public Play getNextPlay(PlayField playField) {
                // TODO Auto-generated method stub
                return null;
            }
        };

        assertThat(this.player.equals(player)).isEqualTo(true);

    }

    @Test
    public void whenHashCodeCalledForTwoDifferentEqualObjects_thenShoulReturnSameHashCode() {

        Player player = new Player(SYMBOL) {

            @Override
            public Play getNextPlay(PlayField playField) {
                // TODO Auto-generated method stub
                return null;
            }
        };

        assertThat(this.player.hashCode()).isEqualTo(player.hashCode());

    }

    @Test
    public void whenToStringCalled_thenShoulReturnInExpectedFormat() {

        String expected = "Player " + SYMBOL;

        assertThat(player.toString()).isEqualTo(expected);

    }

}
