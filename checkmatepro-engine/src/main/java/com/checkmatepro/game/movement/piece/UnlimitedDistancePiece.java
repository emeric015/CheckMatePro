package com.checkmatepro.game.movement.piece;

import com.checkmatepro.game.movement.Pair;

import java.util.List;

public sealed interface UnlimitedDistancePiece permits QueenPieceMoveStrategy, RookPieceMoveStrategy, BishopPieceMoveStrategy
{
    List<Pair<Integer, Integer>> getPossibleDirections();
}
