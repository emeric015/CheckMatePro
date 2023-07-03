package com.checkmatepro.game.movement.piece;

import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.EPieceType;

import java.util.Set;

public interface IPieceMoveStrategy
{
    static IPieceMoveStrategy of(EPieceType pieceType)
    {
        return switch (pieceType)
                {
                    case KNIGHT -> new KnightPieceMoveStrategy();
                    case BISHOP -> new BishopPieceMoveStrategy();
                    case ROOK -> new RookPieceMoveStrategy();
                    case PAWN -> new PawnPieceMoveStrategy();
                    case QUEEN -> new QueenPieceMoveStrategy();
                    case KING -> new KingPieceMoveStrategy();
                };
    }

    Set<BoardPosition> getLegalDestinations(GameBoard board, BoardPosition origin);
}
