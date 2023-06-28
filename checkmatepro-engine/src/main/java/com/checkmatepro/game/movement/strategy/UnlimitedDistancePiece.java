package com.checkmatepro.game.movement.strategy;

import com.checkmatepro.game.movement.Pair;

import java.util.List;

public sealed interface UnlimitedDistancePiece permits QueenMoveStrategy, RookMoveStrategy, BishopMoveStrategy
{
    List<Pair<Integer, Integer>> getPossibleDirections();
}
