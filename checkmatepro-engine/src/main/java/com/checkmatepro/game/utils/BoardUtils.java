package com.checkmatepro.game.utils;

import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.Piece;

import java.util.Map;

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

    public static String toBoardStr(GameBoard board)
    {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<BoardPosition, Piece> pieceEntry : board.getPiecesByPosition().entrySet())
        {
            builder.append(toPieceStr(pieceEntry.getKey(), pieceEntry.getValue())).append('/');
        }

        return builder.toString();
    }

    private static String toPieceStr(BoardPosition boardPosition, Piece piece)
    {
        char charPiece = switch (piece.type())
                {

                    case PAWN -> 'P';
                    case KNIGHT -> 'N';
                    case BISHOP -> 'B';
                    case ROOK -> 'R';
                    case QUEEN -> 'Q';
                    case KING -> 'K';
                };

        char charColor = switch (piece.color())
                {

                    case WHITE -> 'W';
                    case BLACK -> 'B';
                };

        return String.valueOf(charPiece) + ',' + boardPosition.column() + ',' + boardPosition.line() + ',' + charColor;
    }
}
