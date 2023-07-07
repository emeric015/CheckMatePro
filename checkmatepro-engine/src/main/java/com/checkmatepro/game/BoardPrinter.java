package com.checkmatepro.game;

import com.checkmatepro.logging.LogUtils;
import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.Piece;

import java.util.Optional;

public class BoardPrinter
{
    public static void printBoard(GameBoard board)
    {
        StringBuilder builder = new StringBuilder();


        for (int line = GameBoard.SIZE - 1; line >= 0; line--)
        {
            for (int column = 0; column < GameBoard.SIZE; column++)
            {
                BoardPosition position = new BoardPosition(column, line);

                Optional.ofNullable(board.getPiecesByPosition().get(position))
                        .ifPresentOrElse(piece -> builder.append(BoardPrinter.pieceToString(piece)), () -> builder.append("_"));
            }

            builder.append("\n");
        }

        LogUtils.getLogger().info(builder);
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
