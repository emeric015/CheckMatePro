package com.checkmatepro.game.movement;

import com.checkmatepro.game.movement.piece.IPieceMoveStrategy;
import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.Piece;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class FreeMoveLogic implements IMoveLogicStrategy
{

    @Override
    public Set<BoardPosition> getLegalDestinations(GameBoard board, BoardPosition origin)
    {
        Optional<Piece> pieceOnOrigin = board.getPieceAtPosition(origin);

        if (pieceOnOrigin.isPresent())
        {
            IPieceMoveStrategy moveStrategy = IPieceMoveStrategy.of(pieceOnOrigin.get().type());

            return moveStrategy.getLegalDestinations(board, pieceOnOrigin.get());
        }
        else
        {
            return Collections.emptySet();
        }

        //TODO handle check cases
    }

    @Override
    public boolean requestAndDoMove(GameBoard board, BoardPosition origin, BoardPosition requestedDestination)
    {
        board.movePieceTo(origin, requestedDestination);

        return true;
    }
}
