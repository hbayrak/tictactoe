package com.hakan.tictactoe.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.hakan.tictactoe.game.playfield.Play;
import com.hakan.tictactoe.game.playfield.PlayField;
import com.hakan.tictactoe.game.playfield.PlayFieldPrinter;
import com.hakan.tictactoe.game.win.WinChecker;
import com.hakan.tictactoe.player.Player;

public class TicTacToeGameTest {

    private PlayField        playFieldMock;

    private PlayFieldPrinter playFieldPrinterMock;

    private WinChecker       winCheckerMock;

    private TicTacToeGame    ticTacToeGame;

    @Before
    public void setup() {
        playFieldMock = mock(PlayField.class);
        playFieldPrinterMock = mock(PlayFieldPrinter.class);
        winCheckerMock = mock(WinChecker.class);

        ticTacToeGame = new TicTacToeGame(playFieldMock, playFieldPrinterMock, winCheckerMock);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGameConstructorCalledWithNullPlayField_thenShouldThrowIllegalArgumentException() {

        new TicTacToeGame(null, playFieldPrinterMock, winCheckerMock);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGameConstructorCalledWithNullWinChecker_thenShouldThrowIllegalArgumentException() {

        new TicTacToeGame(playFieldMock, playFieldPrinterMock, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGameConstructorCalledWithNullPlayFieldPrinter_thenShouldThrowIllegalArgumentException() {

        new TicTacToeGame(playFieldMock, null, winCheckerMock);
    }

    @Test
    public void whenGameConstructorCalled_thenShouldCreateGameWithNotStartedStatus() {

        assertThat(ticTacToeGame.getStatus()).isEqualTo(GameStatus.NOT_STARTED);
    }

    @Test
    public void givenGame_whenRegisterPlayerCalledWithNullPlayer_thenShouldReturnFalse() {

        boolean isRegistered = ticTacToeGame.registerPlayer(null);

        assertThat(isRegistered).isFalse();

    }

    @Test
    public void givenGame_whenRegisterPlayerCalledWithPlayer_thenShouldReturnTrue() {

        Player player = mock(Player.class);
        boolean isRegistered = ticTacToeGame.registerPlayer(player);

        assertThat(isRegistered).isEqualTo(true);
        assertThat(ticTacToeGame.getPlayers().contains(player)).isTrue();

    }

    @Test
    public void givenGameWithOnePlayer_whenRegisterPlayerCalledWithSamePlayer_thenShouldReturnFalse() {

        Player player = mock(Player.class);
        ticTacToeGame.registerPlayer(player);

        boolean isRegistered = ticTacToeGame.registerPlayer(player);

        assertThat(isRegistered).isFalse();

    }

    @Test
    public void givenStartedGame_whenRegisterPlayerCalled_thenShouldReturnFalse() {

        TicTacToeGame game = getGameWithMinPlayerCount();
        game.start();

        Player player = mock(Player.class);
        boolean isRegistered = ticTacToeGame.registerPlayer(player);

        assertThat(isRegistered).isFalse();

    }

    @Test(expected = RuntimeException.class)
    public void whenStartGameCalledForStartedGame_thenShouldThrowRuntimeException() {

        TicTacToeGame game = getGameWithMinPlayerCount();

        game.start();
        game.start();
    }

    private TicTacToeGame getGameWithMinPlayerCount() {

        for (int i = 0; i < TicTacToeGame.MIN_PLAYER_COUNT; i++) {
            Player player = mock(Player.class);
            ticTacToeGame.registerPlayer(player);
        }

        return ticTacToeGame;
    }

    @Test(expected = RuntimeException.class)
    public void whenStartGameCalledWithLessThanMinPlayerCount_thenShouldThrowRuntimeException() {

        for (int i = 0; i < TicTacToeGame.MIN_PLAYER_COUNT - 1; i++) {
            Player player = mock(Player.class);
            ticTacToeGame.registerPlayer(player);
        }

        ticTacToeGame.start();
    }

    @Test
    public void whenGameStarted_thenStatusShouldBeRunning() {

        TicTacToeGame game = getGameWithMinPlayerCount();
        game.start();

        assertThat(game.getStatus()).isEqualTo(GameStatus.RUNNING);
    }

    @Test(expected = RuntimeException.class)
    public void givenNotStartedGame_whenPlayOneTurnCalled_thenShouldThrowRuntimeException() {

        ticTacToeGame.playOneTurn();

    }

    @Test
    public void givenStartedGame_whenPlayOneTurnCalledWithValidNonWinningPlay_thenGameStatusShouldBeRunning() {

        when(playFieldMock.submitPlay(any(Play.class))).thenReturn(true);
        when(playFieldMock.isFull()).thenReturn(false);

        when(winCheckerMock.isWinningPlay(any(PlayField.class), any(Play.class))).thenReturn(false);

        Player player1 = mock(Player.class);
        ticTacToeGame.registerPlayer(player1);

        Player player2 = mock(Player.class);
        ticTacToeGame.registerPlayer(player2);

        ticTacToeGame.start();
        ticTacToeGame.playOneTurn();

        assertThat(ticTacToeGame.getStatus()).isEqualTo(GameStatus.RUNNING);
        assertThat(ticTacToeGame.getWinner()).isNull();

    }

    @Test
    public void givenStartedGame_whenPlayOneTurnCalledWithValidLastNonWinningPlay_thenGameStatusShouldBeFinishedWithNoWinner() {

        when(playFieldMock.submitPlay(any(Play.class))).thenReturn(true);
        when(playFieldMock.isFull()).thenReturn(true);

        when(winCheckerMock.isWinningPlay(any(PlayField.class), any(Play.class))).thenReturn(false);

        Player player1 = mock(Player.class);
        ticTacToeGame.registerPlayer(player1);

        Player player2 = mock(Player.class);
        ticTacToeGame.registerPlayer(player2);

        ticTacToeGame.start();
        ticTacToeGame.playOneTurn();

        assertThat(ticTacToeGame.getWinner()).isNull();
        assertThat(ticTacToeGame.getStatus()).isEqualTo(GameStatus.FINISHED);

    }

    @Test
    public void givenStartedGame_whenPlayOneTurnCalledWithValidWinningPlay_thenGameStatusShouldBeFinishedWithAWinner() {

        when(playFieldMock.submitPlay(any(Play.class))).thenReturn(true);
        when(playFieldMock.isFull()).thenReturn(false);

        when(winCheckerMock.isWinningPlay(any(PlayField.class), any(Play.class))).thenReturn(true);

        Player player1 = mock(Player.class);
        ticTacToeGame.registerPlayer(player1);

        Player player2 = mock(Player.class);
        ticTacToeGame.registerPlayer(player2);

        ticTacToeGame.start();
        ticTacToeGame.playOneTurn();

        assertThat(ticTacToeGame.getWinner()).isNotNull();
        assertThat(ticTacToeGame.getStatus()).isEqualTo(GameStatus.FINISHED);

    }

    //    @Test
    //    public void givenStartedGame_whenPlayOneTurnCalledWithInValidPlay_thenShouldPlayAgain() {
    //
    //        Play invalidPlayStub = new Play(new Cell(5, 5), 'X');
    //
    //        //mock playfield
    //        PlayField playFieldMock = mock(PlayField.class);
    //        when(playFieldMock.submitPlay(invalidPlayStub)).thenReturn(false);
    //        when(playFieldMock.isFull()).thenReturn(false);
    //
    //        //mock winchecker
    //        WinChecker winCheckerMock = mock(WinChecker.class);
    //        when(winCheckerMock.isWinningPlay(any(PlayField.class), any(Play.class))).thenReturn(false);
    //    //
    //        Player player1 = mock(Player.class);
    //        when(player1.getPlay(playFieldMock)).thenReturn(invalidPlayStub);
    //        ticTacToeGame.registerPlayer(player1);
    //
    //        Player player2 = mock(Player.class);
    //        when(player2.getPlay(playFieldMock)).thenReturn(invalidPlayStub);
    //        ticTacToeGame.registerPlayer(player2);
    //
    //        ticTacToeGame.start();
    //        ticTacToeGame.playOneTurn();
    //        ticTacToeGame.playOneTurn();
    //
    //        //TODO how to verify one of the mocks invoked two times and the other one zero.
    //        verify(player1, times(2)).getPlay(playFieldMock);
    //
    //        assertThat(ticTacToeGame.getWinner()).isNull();
    //        assertThat(ticTacToeGame.getStatus()).isEqualTo(GameStatus.RUNNING);
    //
    //    }

}
