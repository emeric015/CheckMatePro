package com.checkmatepro.game.movement.piece;

import com.checkmatepro.game.movement.Pair;
import com.checkmatepro.game.utils.Vector;
import com.checkmatepro.game.utils.VectorUtils;
import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.Piece;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class RookPieceMoveStrategy implements IPieceMoveStrategy, UnlimitedDistancePiece
{
    @Override
    public Set<BoardPosition> getLegalDestinations(GameBoard board, Piece piece)
    {
        Set<BoardPosition> destinations = new HashSet<>();

        getPossibleDirections().forEach(direction -> destinations.addAll(VectorUtils.applyVectorOnBoard(board, piece.position(), direction)));

        return destinations;
    }

    @Override
    public List<Pair<Integer, Integer>> getPossibleDirections()
    {
        return Arrays.asList(Vector.VERTICAL.getAxis(),
                Vector.VERTICAL.getOpposite(),
                Vector.HORIZONTAL.getAxis(),
                Vector.HORIZONTAL.getOpposite());
    }
}
