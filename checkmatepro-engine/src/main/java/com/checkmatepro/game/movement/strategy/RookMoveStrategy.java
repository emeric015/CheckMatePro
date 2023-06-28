package com.checkmatepro.game.movement.strategy;

import com.checkmatepro.game.movement.Pair;
import com.checkmatepro.game.utils.Vector;
import com.checkmatepro.game.utils.VectorUtils;
import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.EColor;
import com.checkmatepro.model.pieces.Piece;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class RookMoveStrategy implements IMoveStrategy, UnlimitedDistancePiece
{
    @Override
    public Set<BoardPosition> getLegalDestinations(GameBoard board, Piece piece, EColor colorToPlay)
    {
        Set<BoardPosition> destinations = new HashSet<>();

        getPossibleDirections().forEach(direction -> destinations.addAll(VectorUtils.applyVectorOnBoard(board, colorToPlay, piece.position(), direction)));

        return destinations;
    }

    @Override
    public List<Pair<Integer, Integer>> getPossibleDirections()
    {
        return Arrays.asList(Vector.VERTICAL.getAxis(),
                Vector.VERTICAL.getAxis(),
                Vector.HORIZONTAL.getAxis(),
                Vector.HORIZONTAL.getAxis());
    }
}
