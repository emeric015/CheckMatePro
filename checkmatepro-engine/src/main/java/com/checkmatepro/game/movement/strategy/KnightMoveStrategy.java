package com.checkmatepro.game.movement.strategy;

import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.EColor;
import com.checkmatepro.model.pieces.Piece;

import java.util.HashSet;
import java.util.Set;

public class KnightMoveStrategy implements IMoveStrategy
{
    @Override
    public Set<BoardPosition> getLegalDestinations(GameBoard board, Piece piece, EColor colorToPlay)
    {
        return new HashSet<>();
    }
}
