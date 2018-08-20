package com.hakan.tictactoe;

import java.util.List;

import com.hakan.tictactoe.config.GameConfiguration;
import com.hakan.tictactoe.game.Game;
import com.hakan.tictactoe.game.GameStatus;
import com.hakan.tictactoe.game.TicTacToeGame;
import com.hakan.tictactoe.game.playfield.PlayField;
import com.hakan.tictactoe.game.playfield.PlayFieldPrinter;
import com.hakan.tictactoe.game.win.TicTacToeWinChecker;
import com.hakan.tictactoe.game.win.WinChecker;
import com.hakan.tictactoe.player.Player;
import com.hakan.tictactoe.player.PlayerFactory;
import com.hakan.tictactoe.player.PlayerType;

public class TicTacToe {

    public static void main(String... args) {

        GameConfiguration config = getConfiguration(args);

        start(config.getPlayFieldSize(), config.getPlayerCharacters());

    }

    /**
     * Returns game configuration
     * 
     * @param args
     *            arguments array
     * 
     * @return {@link GameConfiguration} with the specified {@code arg[0]}, if args null or empty returns default
     *         configuration
     */
    private static GameConfiguration getConfiguration(String[] args) {
        String configFileName = null;
        if (args != null && args.length > 0 && args[0] != null) {
            configFileName = args[0];
        }

        return new GameConfiguration(configFileName);

    }

    /**
     * Starts a game with the specified play field size and players.
     * Plays till game ends.
     * 
     * @param playFieldSize
     * @param playerCharacters
     */
    private static void start(int playFieldSize, List<Character> playerCharacters) {

        Game game = createGame(playFieldSize);

        registerPlayers(game, playerCharacters);

        game.start();

        boolean isEnded = false;
        while (!isEnded) {
            game.playOneTurn();
            if (GameStatus.FINISHED == game.getStatus()) {
                isEnded = true;
            }
        }

        Player winner = game.getWinner();
        if (winner == null) {
            System.out.println("Game ended with a tie.");
        } else {
            System.out.println("Game ended with the victory of " + winner.toString());
        }

    }

    private static Game createGame(int playFieldSize) {

        PlayField playField = new PlayField(playFieldSize);
        WinChecker winChecker = new TicTacToeWinChecker();
        PlayFieldPrinter playFieldPrinter = new PlayFieldPrinter();

        Game game = new TicTacToeGame(playField, playFieldPrinter, winChecker);
        return game;
    }

    private static void registerPlayers(Game game, List<Character> playerCharacters) {

        for (int i = 0; i < playerCharacters.size(); i++) {
            Character symbol = playerCharacters.get(i);

            PlayerFactory playerFactory = PlayerFactory.getInstance();
            //Last character of the list will be used by AI.
            if (i == playerCharacters.size() - 1) {
                game.registerPlayer(playerFactory.getPlayer(PlayerType.AI, symbol));
            } else {
                game.registerPlayer(playerFactory.getPlayer(PlayerType.HUMAN, symbol));
            }
        }
    }

}
