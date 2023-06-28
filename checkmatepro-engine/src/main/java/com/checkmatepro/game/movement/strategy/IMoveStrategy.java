package com.checkmatepro.game.movement.strategy;

import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.EColor;
import com.checkmatepro.model.pieces.EPieceType;
import com.checkmatepro.model.pieces.Piece;

import java.util.Set;

public interface IMoveStrategy
{
    static IMoveStrategy of(EPieceType pieceType)
    {
        return switch (pieceType)
                {
                    case KNIGHT -> new KnightMoveStrategy();
                    case BISHOP -> new BishopMoveStrategy();
                    case ROOK -> new RookMoveStrategy();
                    case PAWN -> new PawnMoveStrategy();
                    case QUEEN -> new QueenMoveStrategy();
                    case KING -> new KingMoveStrategy();
                };
    }

    Set<BoardPosition> getLegalDestinations(GameBoard board, Piece piece, EColor colorToPlay);
}
