package com.checkmatepro.game.movement.piece;

import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;

import java.util.HashSet;
import java.util.Set;

public class PawnPieceMoveStrategy implements IPieceMoveStrategy
{
    @Override
    public Set<BoardPosition> getLegalDestinations(GameBoard board, BoardPosition origin)
    {
        return new HashSet<>();
    }
}
