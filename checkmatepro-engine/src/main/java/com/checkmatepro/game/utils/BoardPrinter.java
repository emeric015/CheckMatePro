package com.checkmatepro.game.utils;

import com.checkmatepro.logging.LogUtils;
import com.checkmatepro.model.GameBoard;

public class BoardPrinter
{
    public static void printBoard(GameBoard board)
    {
        LogUtils.getLogger().info(BoardUtils.getBoardAsString(board));
    }
}
