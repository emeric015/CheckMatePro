package com.checkmatepro.game;

import com.checkmatepro.model.BoardFactory;
import com.checkmatepro.model.GameBoard;

public class Game
{
    private final GameBoard board;

    protected Game(GameBoard board)
    {
        this.board = board;
    }

    public GameBoard getBoard()
    {
        return board;
    }

    public static void main(String[] args)
    {
        GameBoard board = BoardFactory.classicBoard();

        BoardPrinter.printBoard(board);
    }
}
