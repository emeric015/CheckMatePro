package com.checkmatepro.game.utils;

import com.checkmatepro.logging.LogUtils;
import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.Piece;

import java.util.Optional;

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

    public static String getBoardAsString(GameBoard board)
    {
        StringBuilder builder = new StringBuilder("\n");

        for (int line = GameBoard.SIZE - 1; line >= 0; line--)
        {
            for (int column = 0; column < GameBoard.SIZE; column++)
            {
                BoardPosition position = new BoardPosition(column, line);

                Optional.ofNullable(board.getPiecesByPosition().get(position))
                        .ifPresentOrElse(piece -> builder.append(pieceToString(piece)), () -> builder.append("_"));
            }

            builder.append("\n");
        }

        return builder.toString();
    }

    private static String pieceToString(Piece piece)
    {
        switch (piece.type())
        {
            case PAWN ->
            {
                return "P";
            }
            case KING ->
            {
                return "K";
            }
            case QUEEN ->
            {
                return "Q";
            }
            case KNIGHT ->
            {
                return "N";
            }
            case ROOK ->
            {
                return "R";
            }
            case BISHOP ->
            {
                return "B";
            }
        }

        LogUtils.getLogger().error("Unknown piece type : " + piece.type());
        throw new IllegalArgumentException();
    }
}
