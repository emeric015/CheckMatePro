package com.checkmatepro.game.movement.piece;

import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.Piece;

import java.util.HashSet;
import java.util.Set;

public class PawnPieceMoveStrategy implements IPieceMoveStrategy
{
    @Override
    public Set<BoardPosition> getLegalDestinations(GameBoard board, Piece piece)
    {
        return new HashSet<>();
    }
}
