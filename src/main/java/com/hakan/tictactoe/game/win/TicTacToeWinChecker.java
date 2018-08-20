package com.hakan.tictactoe.game.win;

import com.hakan.tictactoe.game.TicTacToeGame;
import com.hakan.tictactoe.game.playfield.Play;
import com.hakan.tictactoe.game.playfield.PlayField;

/**
 * A {@link WinChecker} to use in {@link TicTacToeGame}
 * 
 * @author hbayrak
 *
 */
public class TicTacToeWinChecker implements WinChecker {

    /**
     * The {@link Play} is the winning play if it completes a row or a column or a diagonal on {@link PlayField} with
     * the same symbol.
     * 
     * 
     * @param playField
     *            the play field
     * @param play
     *            the last play to check
     * 
     * @return {@code true} if the last play is the winning play, {@code false} otherwise
     */
    @Override
    public boolean isWinningPlay(PlayField playField, Play play) {

        int lastMarkedRow = play.getCell().getRow();
        int lastMarkedColumn = play.getCell().getColumn();

        Character symbol = play.getSymbol();

        Character[][] playFieldCells = playField.getCells();
        int playFieldSize = playFieldCells.length;

        //@formatter:off
        boolean isWinner = false;
        if (isWinningColumn(lastMarkedRow, symbol, playFieldCells, playFieldSize)
                || isWinningRow(lastMarkedColumn, symbol, playFieldCells, playFieldSize)
                || (lastMarkedRow == lastMarkedColumn && isWinningDiagonalStartingFromLeft(lastMarkedRow, lastMarkedColumn, symbol, playFieldCells, playFieldSize))
                || (lastMarkedRow + lastMarkedColumn == playFieldSize - 1 && isWinningDiagonalStartingFromRight(lastMarkedRow, lastMarkedColumn, symbol, playFieldCells, playFieldSize))) {
            isWinner = true;
        }
        //@formatter:on

        return isWinner;
    }

    private boolean isWinningColumn(int lastMarkedRow, Character symbol, Character[][] playFieldCells, int playFieldSize) {
        for (int i = 0; i < playFieldSize; i++) {
            if (!symbol.equals(playFieldCells[lastMarkedRow][i])) {
                return false;
            }
        }
        return true;

    }

    private boolean isWinningRow(int lastMarkedColumn, Character symbol, Character[][] playFieldCells, int playFieldSize) {
        for (int i = 0; i < playFieldSize; i++) {
            if (!symbol.equals(playFieldCells[i][lastMarkedColumn])) {
                return false;
            }
        }
        return true;

    }

    private boolean isWinningDiagonalStartingFromLeft(int lastMarkedRow, int lastMarkedColumn, Character symbol, Character[][] playFieldCells,
            int playFieldSize) {
        for (int i = 0; i < playFieldSize; i++) {
            if (!symbol.equals(playFieldCells[i][i])) {
                return false;
            }
        }
        return true;

    }

    private boolean isWinningDiagonalStartingFromRight(int lastMarkedRow, int lastMarkedColumn, Character symbol, Character[][] playFieldCells,
            int playFieldSize) {
        for (int i = 0; i < playFieldSize; i++) {
            if (!symbol.equals(playFieldCells[i][playFieldSize - 1 - i])) {
                return false;
            }
        }
        return true;

    }

}
