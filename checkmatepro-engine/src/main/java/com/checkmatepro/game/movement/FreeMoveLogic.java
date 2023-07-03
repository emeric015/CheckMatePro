package com.checkmatepro.game.movement;

import com.checkmatepro.game.movement.piece.IPieceMoveStrategy;
import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.Piece;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FreeMoveLogic implements IMoveLogicStrategy
{

    @Override
    public Set<BoardPosition> getLegalDestinations(GameBoard board, BoardPosition origin)
    {
        return board.getPieceAtPosition(origin).map(piece ->
        {
            IPieceMoveStrategy moveStrategy = IPieceMoveStrategy.of(piece.type());

            //TODO handle check cases
            return moveStrategy.getLegalDestinations(board, origin).stream()
                    .filter(destination -> !wouldBeInCheck(board, origin, destination))
                    .collect(Collectors.toSet());
        }).orElse(Collections.emptySet());
    }

    @Override
    public boolean requestAndDoMove(GameBoard board, BoardPosition origin, BoardPosition requestedDestination)
    {
        board.movePieceTo(origin, requestedDestination);

        return true;
    }

    @Override
    public boolean wouldBeInCheck(GameBoard board, BoardPosition origin, BoardPosition supposedDestination)
    {
        return false;
    }

    @Override
    public boolean inInCheck(GameBoard board)
    {
        return false;
    }
}
