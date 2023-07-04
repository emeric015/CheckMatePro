package com.checkmatepro.game.movement.classic;

import com.checkmatepro.game.GameFactory;
import com.checkmatepro.game.IGameInterface;
import com.checkmatepro.game.utils.BoardUtils;
import com.checkmatepro.model.BoardPosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RookClassicMoveTest
{
    @Test
    public void Rook_NoMoves()
    {
        String boardStr = "R,0,0,W/P,6,6,B/R,4,4,B/P,0,1,W/N,6,7,B/P,4,5,B/K,4,7,B/P,2,6,B/B,2,7,B/P,0,6,B/R,0,7,B/P,7,1,W/B,5,0,W/P,5,1,W/P,3,1,W/N,1,0,W/Q,7,6,W/P,5,4,B/P,1,1,W/P,3,4,B/Q,1,3,B/N,1,4,W/P,1,6,B/N,1,7,B/P,6,1,W/K,4,0,W/P,4,1,W/B,2,0,W/P,2,1,W/B,4,3,B/";
        BoardPosition rookPosition = new BoardPosition(4, 4);
        IGameInterface gameInterface = new GameFactory().newClassicCustomGame(boardStr);

        Set<BoardPosition> destinations = gameInterface.getLegalDestinations(rookPosition);

        Assertions.assertEquals(0, destinations.size());
    }

    @Test
    public void Rook_1_Move_Each_Direction()
    {
        String boardStr = "R,0,0,W/P,6,6,B/R,4,4,B/P,0,1,W/N,6,7,B/B,4,5,W/K,4,7,B/P,2,6,B/B,2,7,B/P,0,6,B/R,0,7,B/P,7,1,W/P,3,1,W/N,1,0,W/P,5,4,W/P,1,1,W/Q,3,4,W/Q,1,3,B/N,1,4,W/P,1,6,B/N,1,7,B/P,6,1,W/K,4,0,W/B,2,0,W/P,2,1,W/P,4,3,W/";
        BoardPosition rookPosition = new BoardPosition(4, 4);
        IGameInterface gameInterface = new GameFactory().newClassicCustomGame(boardStr);

        Set<BoardPosition> destinations = gameInterface.getLegalDestinations(rookPosition);

        Set<BoardPosition> expected = new HashSet<>(Arrays.asList(new BoardPosition(rookPosition.column() + 1, rookPosition.line()),
                new BoardPosition(rookPosition.column() - 1, rookPosition.line()),
                new BoardPosition(rookPosition.column(), rookPosition.line() - 1),
                new BoardPosition(rookPosition.column(), rookPosition.line() + 1)));

        Assertions.assertEquals(expected, destinations);
    }

    @Test
    public void Rook_Unlimited_Move_Each_Direction()
    {
        String boardStr = "P,6,6,B/R,4,4,B/P,2,2,W/P,0,1,W/N,6,7,B/B,2,3,W/Q,2,5,W/N,2,6,W/P,0,6,B/P,7,1,W/K,5,2,W/P,5,3,W/R,3,2,W/B,7,6,B/P,1,1,W/N,5,5,W/B,3,3,W/Q,1,3,B/P,3,5,W/P,3,6,B/K,3,7,B/N,1,7,B/P,6,1,W/P,6,3,W/R,6,5,B/";
        BoardPosition rookPosition = new BoardPosition(4, 4);
        IGameInterface gameInterface = new GameFactory().newClassicCustomGame(boardStr);

        Set<BoardPosition> destinations = gameInterface.getLegalDestinations(rookPosition);

        Set<BoardPosition> expected = IntStream.range(1, 5)
                .mapToObj(value -> Arrays.asList(new BoardPosition(rookPosition.column() + value, rookPosition.line()),
                        new BoardPosition(rookPosition.column() - value, rookPosition.line()),
                        new BoardPosition(rookPosition.column(), rookPosition.line() - value),
                        new BoardPosition(rookPosition.column(), rookPosition.line() + value)))
                .flatMap(Collection::stream)
                .filter(BoardUtils::isInBoard)
                .collect(Collectors.toSet());

        Assertions.assertEquals(expected, destinations);
    }
}
