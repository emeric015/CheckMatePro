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

public final class KingPieceMoveStrategy implements IPieceMoveStrategy, DirectionalPiece
{
    @Override
    public Set<BoardPosition> getLegalDestinations(GameBoard board, BoardPosition origin)
    {
        Set<BoardPosition> destinations = new HashSet<>();

        getPossibleDirections().forEach(direction -> destinations.addAll(VectorUtils.applyVectorOnBoard(board, origin, direction, 1)));

        if (board.getPieceAtPosition(origin).orElseThrow().hasMoved())
        {
            destinations.addAll(getCastleDestination(board, origin));
        }

        return destinations;
    }

    private Set<BoardPosition> getCastleDestination(GameBoard board, BoardPosition origin)
    {
        //TODO
        return new HashSet<>();
    }

    @Override
    public List<Pair<Integer, Integer>> getPossibleDirections()
    {
        return Arrays.asList(Vector.VERTICAL.getAxis(),
                Vector.VERTICAL.getOpposite(),
                Vector.HORIZONTAL.getAxis(),
                Vector.HORIZONTAL.getOpposite(),
                Vector.TOP_LEFT_BOT_RIGHT.getAxis(),
                Vector.TOP_LEFT_BOT_RIGHT.getOpposite(),
                Vector.TOP_RIGHT_BOT_LEFT.getAxis(),
                Vector.TOP_RIGHT_BOT_LEFT.getOpposite());
    }
}
