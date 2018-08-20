package com.hakan.tictactoe.game;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hakan.tictactoe.game.playfield.Play;
import com.hakan.tictactoe.game.playfield.PlayField;
import com.hakan.tictactoe.game.playfield.PlayFieldPrinter;
import com.hakan.tictactoe.game.win.WinChecker;
import com.hakan.tictactoe.player.Player;

/**
 * A tic-tac-toe {@link Game} implementation.
 * 
 * Uses {@link PlayField} store the plays and {@link PlayFieldPrinter} to print play field. Generates player order
 * randomly at start.
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Tic-tac-toe">https://en.wikipedia.org/wiki/Tic-tac-toe</a>
 * 
 * @author hbayrak
 *
 */
public class TicTacToeGame implements Game {

    /**
     * Play field to store the plays
     */
    private PlayField        playField;

    /**
     * Helper to print the play field
     */
    private PlayFieldPrinter playFieldPrinter;

    /**
     * Win checker to decide if there is any winner.
     */
    private WinChecker       winChecker;

    /**
     * Registered player set
     */
    private Set<Player>      players;

    /**
     * Status of the game
     */
    private GameStatus       status;

    /**
     * Player order
     */
    private Deque<Player>    turn;

    /**
     * Winner of the game.
     */
    private Player           winner;

    /**
     * Constructs the {@link TicTacToeGame} with {@link PlayField}, {@link PlayFieldPrinter} and {@link WinChecker}
     * Constructs the player set and sets the game status as {@link GameStatus#NOT_STARTED}.
     * 
     * @param playField
     *            to store the plays
     * @param playFieldPrinter
     *            to print the play field
     * @param winChecker
     *            to decide if there is any winner
     * 
     * @throws IllegalArgumentException
     *             if any of the input is {@code null}
     */
    public TicTacToeGame(PlayField playField, PlayFieldPrinter playFieldPrinter, WinChecker winChecker) {
        if (playField == null)
            throw new IllegalArgumentException("PlayField cannot be null");
        if (winChecker == null)
            throw new IllegalArgumentException("WinChecker cannot be null");
        if (playFieldPrinter == null)
            throw new IllegalArgumentException("PLayFieldPrinter cannot be null");

        this.playField = playField;
        this.playFieldPrinter = playFieldPrinter;
        this.winChecker = winChecker;
        this.players = new HashSet<Player>();
        this.status = GameStatus.NOT_STARTED;
    }

    /**
     * Returns an unmodifiable view of the players set to provide read-only access.
     */
    @Override
    public Set<Player> getPlayers() {
        return players != null ? Collections.unmodifiableSet(players) : null;
    }

    @Override
    public GameStatus getStatus() {
        return status;
    }

    @Override
    public Player getWinner() {
        return winner;
    }

    /**
     * Registers the player and returns {@code true} if player is not null, not registered already and the game status
     * is {@link GameStatus#NOT_STARTED}. Returns {@code false} otherwise.
     */
    @Override
    public boolean registerPlayer(Player player) {

        if (player == null) {
            return false;
        }

        if (status != GameStatus.NOT_STARTED || isPlayerExists(player)) {
            return false;
        } else {
            this.players.add(player);
            return true;
        }

    }

    /**
     * Checks if player exists
     * 
     * @param player
     * 
     * @return {@code true} if exists, {@code false} otherwise
     */
    private boolean isPlayerExists(Player player) {
        return players.contains(player);
    }

    /**
     * Validates the game for start. Generates player order randomly and sets the player queue. Sets game status as
     * {@link GameStatus#RUNNING}.
     * 
     * @throws RuntimeException
     *             if the game status is not {@link GameStatus#NOT_STARTED} or there is not enough players
     */
    @Override
    public void start() {

        if (status != GameStatus.NOT_STARTED) {
            throw new RuntimeException("Game status is not consistent to start the game. Status: " + status);
        }

        if (players.size() < MIN_PLAYER_COUNT) {
            throw new RuntimeException("At least " + MIN_PLAYER_COUNT + " players required to start the game. Current players: " + players.size());
        }

        turn = generatePlayerOrder(players);

        status = GameStatus.RUNNING;

    }

    /**
     * Plays one turn for the game. Takes the current player from the turn queue. If the turn is successful, adds the
     * player at the end of the queue. If the turn is not successful, adds the player at front of the queue.
     * 
     * Checks if there is a winner or the play field is full at the end of the turn to finish the game.
     * 
     * 
     * @throws RuntimeException
     *             if game status is not {@link GameStatus#RUNNING}
     */
    @Override
    public void playOneTurn() {

        if (status != GameStatus.RUNNING) {
            throw new RuntimeException("Game status is not consistent to play. Status: " + status);
        }

        Player currentPlayer = turn.poll();
        System.out.println(currentPlayer.toString() + "'s turn.");
        System.out.println("Available cells : " + playField.getAvailableCells().toString());
        Play currentPlay = currentPlayer.getNextPlay(playField);

        boolean isTurnSuccessful = playField.submitPlay(currentPlay);
        if (isTurnSuccessful) {
            playFieldPrinter.print(playField);
            winner = isWinningPlay(currentPlay) ? currentPlayer : null;
            turn.addLast(currentPlayer);
        } else {
            turn.addFirst(currentPlayer);
            System.out.println("The play is not valid.");
        }
        updateGameStatus();

    }

    private Deque<Player> generatePlayerOrder(Set<Player> players) {
        List<Player> playersToShuffle = new ArrayList<>(players);
        Collections.shuffle(playersToShuffle);
        return new ArrayDeque<>(playersToShuffle);
    }

    private void updateGameStatus() {
        if (winner != null || playField.isFull()) {
            this.status = GameStatus.FINISHED;
        }
    }

    private boolean isWinningPlay(Play lastPlay) {

        return winChecker.isWinningPlay(playField, lastPlay);

    }

}
