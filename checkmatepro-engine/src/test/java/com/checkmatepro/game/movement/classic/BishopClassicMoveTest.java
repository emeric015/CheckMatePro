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

public class BishopClassicMoveTest
{
    @Test
    public void Bishop_NoMoves()
    {
        String boardStr = "R,5,2,B/P,5,4,B/N,3,2,B/P,3,4,B/B,4,3,B/";
        BoardPosition bishopPosition = new BoardPosition(3, 4);
        IGameInterface gameInterface = new GameFactory().newClassicCustomGame(boardStr);

        Set<BoardPosition> destinations = gameInterface.getLegalDestinations(bishopPosition);

        Assertions.assertEquals(0, destinations.size());
    }

    @Test
    public void Bishop_1_Move_Each_Direction()
    {
        String boardStr = "B,6,6,W/B,7,5,B/P,7,7,B/P,5,5,B/P,5,7,B/";
        BoardPosition bishopPosition = new BoardPosition(6, 6);
        IGameInterface gameInterface = new GameFactory().newClassicCustomGame(boardStr);

        Set<BoardPosition> destinations = gameInterface.getLegalDestinations(bishopPosition);

        Set<BoardPosition> expected = new HashSet<>(Arrays.asList(new BoardPosition(bishopPosition.column() + 1, bishopPosition.line() + 1),
                new BoardPosition(bishopPosition.column() + 1, bishopPosition.line() - 1),
                new BoardPosition(bishopPosition.column() - 1, bishopPosition.line() - 1),
                new BoardPosition(bishopPosition.column() - 1, bishopPosition.line() + 1)));

        Assertions.assertEquals(expected, destinations);
    }

    @Test
    public void Bishop_Unlimited_Move_Each_Direction()
    {
        String boardStr = "R,0,0,W/P,6,6,B/P,4,4,B/P,0,1,W/N,6,7,B/P,4,5,B/K,4,7,B/P,2,6,B/B,2,7,B/R,0,5,B/P,0,6,B/P,7,1,W/P,5,1,W/N,3,0,W/R,5,3,B/Q,5,5,W/P,3,3,W/P,5,6,B/B,5,7,B/P,3,6,B/N,1,4,W/N,1,7,B/P,6,0,W/K,4,0,W/P,6,2,W/P,4,1,W/B,2,0,W/P,4,2,W/B,4,3,W/";
        BoardPosition bishopPosition = new BoardPosition(4, 3);
        IGameInterface gameInterface = new GameFactory().newClassicCustomGame(boardStr);

        Set<BoardPosition> destinations = gameInterface.getLegalDestinations(bishopPosition);

        Set<BoardPosition> expected = IntStream.range(1, 5)
                .mapToObj(value -> Arrays.asList(new BoardPosition(bishopPosition.column() + value, bishopPosition.line() + value),
                        new BoardPosition(bishopPosition.column() + value, bishopPosition.line() - value),
                        new BoardPosition(bishopPosition.column() - value, bishopPosition.line() - value),
                        new BoardPosition(bishopPosition.column() - value, bishopPosition.line() + value)))
                .flatMap(Collection::stream)
                .filter(BoardUtils::isInBoard)
                .collect(Collectors.toSet());

        Assertions.assertEquals(expected, destinations);
    }
}
