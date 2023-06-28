package com.checkmatepro.game.movement;

import com.checkmatepro.game.movement.strategy.IMoveStrategy;
import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.EColor;
import com.checkmatepro.model.pieces.Piece;

import java.util.Set;

public class ClassicMoveLogic
{

    public Set<BoardPosition> getLegalDestinations(GameBoard board, Piece piece, EColor colorToPlay)
    {
        if (colorToPlay != piece.color())
        {
            //TODO error message
            throw new IllegalArgumentException("Piece to move not consistent with color to play");
        }

        IMoveStrategy moveStrategy = IMoveStrategy.of(piece.type());

        return moveStrategy.getLegalDestinations(board, piece, colorToPlay);
        //TODO handle check cases
    }
}
