package com.checkmatepro.game;

import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.Piece;

public class BoardPrinter
{
    public static void printBoard(GameBoard board)
    {
        StringBuilder builder = new StringBuilder();


        for (int i = 0; i < GameBoard.SIZE; i++)
        {
            for (int j = 0; j < GameBoard.SIZE; j++)
            {
                BoardPosition position = new BoardPosition(j, i);

                board.getPieces().stream()
                        .filter(piece -> piece.position().equals(position))
                        .findAny()
                        .ifPresentOrElse(piece -> builder.append(BoardPrinter.pieceToString(piece)), () -> builder.append("_"));
            }

            builder.append("\n");
        }

        System.out.println(builder);
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

        throw new IllegalArgumentException("Unknown piece");
    }
}
