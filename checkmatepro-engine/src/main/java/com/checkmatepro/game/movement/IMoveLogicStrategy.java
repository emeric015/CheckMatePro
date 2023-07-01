package com.checkmatepro.game.movement;

import com.checkmatepro.game.EGameRule;
import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;

import java.util.Set;

public interface IMoveLogicStrategy
{
    static IMoveLogicStrategy of(EGameRule gameRule)
    {
        return switch (gameRule)
                {
                    case CLASSIC_GAME -> new ClassicMoveLogic();
                    case FREE_MOVE -> new FreeMoveLogic();
                };
    }

    Set<BoardPosition> getLegalDestinations(GameBoard board, BoardPosition origin);

    boolean requestAndDoMove(GameBoard board, BoardPosition origin, BoardPosition requestedDestination);
}
