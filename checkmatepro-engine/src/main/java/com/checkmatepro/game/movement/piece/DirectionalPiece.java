package com.checkmatepro.game.movement.piece;

import com.checkmatepro.game.movement.Pair;

import java.util.List;

public sealed interface DirectionalPiece permits QueenPieceMoveStrategy, RookPieceMoveStrategy, BishopPieceMoveStrategy, KingPieceMoveStrategy
{
    List<Pair<Integer, Integer>> getPossibleDirections();
}
