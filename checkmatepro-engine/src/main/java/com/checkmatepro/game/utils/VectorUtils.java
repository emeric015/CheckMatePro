package com.checkmatepro.game.utils;

import com.checkmatepro.game.movement.Pair;
import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.EColor;
import com.checkmatepro.model.pieces.Piece;

import java.util.HashSet;
import java.util.Set;

public enum VectorUtils
{
    ;

    public static Set<BoardPosition> applyVectorOnBoard(GameBoard board, BoardPosition startingPosition, Pair<Integer, Integer> axis)
    {
        return applyVectorOnBoard(board, startingPosition, axis, GameBoard.SIZE);
    }

    public static Set<BoardPosition> applyVectorOnBoard(GameBoard board, BoardPosition startingPosition, Pair<Integer, Integer> axis, int maxDistance)
    {
        Set<BoardPosition> destinations = new HashSet<>();

        BoardPosition nextPosition = computeNextPosition(startingPosition, axis);
        Piece pieceOnDestination = board.getPieceAtPosition(nextPosition).orElse(null);
        int currentDistance = 1;
        while (BoardUtils.isInBoard(nextPosition) && pieceOnDestination == null && currentDistance <= maxDistance)
        {
            destinations.add(nextPosition);

            nextPosition = computeNextPosition(nextPosition, axis);
            pieceOnDestination = board.getPieceAtPosition(nextPosition).orElse(null);
            currentDistance++;
        }

        EColor colorOfStartingPiece = board.getPieceAtPosition(startingPosition).map(Piece::color).orElse(null);

        if (pieceOnDestination != null && pieceOnDestination.color() != colorOfStartingPiece)
        {
            destinations.add(nextPosition);
        }

        return destinations;
    }

    private static BoardPosition computeNextPosition(BoardPosition startingPosition, Pair<Integer, Integer> axis)
    {
        return new BoardPosition(startingPosition.column() + axis.columnFactor(), startingPosition.line() + axis.lineFactor());
    }
}
