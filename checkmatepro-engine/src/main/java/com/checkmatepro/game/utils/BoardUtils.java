package com.checkmatepro.game.utils;

import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.Piece;

public class BoardUtils
{
    private BoardUtils()
    {
    }

    public static boolean isInBoard(BoardPosition position)
    {
        return position.column() >= 0 && position.column() < GameBoard.SIZE
                && position.line() >= 0 && position.line() < GameBoard.SIZE;
    }

    public static Piece getPieceForPosition(GameBoard board, BoardPosition position)
    {
        return board.getPiecesByPosition().get(position);
    }
}
