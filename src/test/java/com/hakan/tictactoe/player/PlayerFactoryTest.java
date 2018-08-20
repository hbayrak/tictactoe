package com.hakan.tictactoe.player;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PlayerFactoryTest {

    @Test
    public void whenGetPlayerCalledWithHumanPlayerType_thenShouldReturnHumanPlayerInstance() {

        Character symbol = 'X';
        Player player = PlayerFactory.getInstance().getPlayer(PlayerType.HUMAN, symbol);

        assertThat(player).isExactlyInstanceOf(HumanPlayer.class);
        assertThat(player.getSymbol()).isEqualTo(symbol);

    }

    @Test
    public void whenGetPlayerCalledWithAIPlayerType_thenShouldReturnHumanPlayerInstance() {

        Character symbol = 'X';
        Player player = PlayerFactory.getInstance().getPlayer(PlayerType.AI, symbol);

        assertThat(player).isExactlyInstanceOf(AIPlayer.class);
        assertThat(player.getSymbol()).isEqualTo(symbol);

    }

    @Test
    public void whenGetPlayerCalledWithNullPlayerType_thenShouldReturnNull() {

        Character symbol = 'X';
        Player player = PlayerFactory.getInstance().getPlayer(null, symbol);

        assertThat(player).isNull();

    }

}
