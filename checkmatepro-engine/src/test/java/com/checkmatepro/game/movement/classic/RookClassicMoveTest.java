package com.checkmatepro.game.movement.classic;

import com.checkmatepro.game.GameFactory;
import com.checkmatepro.game.IGameInterface;
import com.checkmatepro.game.utils.BoardUtils;
import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.pieces.EColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RookClassicMoveTest
{
    @Nested
    @DisplayName("Legal Destinations")
    class LegalDestinations
    {
        @Test
        public void Rook_No_Moves()
        {
            String boardStr = "R,0,0,W/P,6,6,B/R,4,4,B/P,0,1,W/N,6,7,B/P,4,5,B/K,4,7,B/P,2,6,B/B,2,7,B/P,0,6,B/R,0,7,B/P,7,1,W/B,5,0,W/P,5,1,W/P,3,1,W/N,1,0,W/Q,7,6,W/P,5,4,B/P,1,1,W/P,3,4,B/Q,1,3,B/N,1,4,W/P,1,6,B/N,1,7,B/P,6,1,W/K,4,0,W/P,4,1,W/B,2,0,W/P,2,1,W/B,4,3,B/";
            BoardPosition rookPosition = new BoardPosition(4, 4);
            IGameInterface gameInterface = new GameFactory().newClassicCustomGame(boardStr);

            Set<BoardPosition> destinations = gameInterface.getLegalDestinations(rookPosition);

            Assertions.assertEquals(0, destinations.size(), BoardUtils.getBoardAsString(gameInterface.getBoard()));
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

            Assertions.assertEquals(expected, destinations, BoardUtils.getBoardAsString(gameInterface.getBoard()));
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

            Assertions.assertEquals(expected, destinations, BoardUtils.getBoardAsString(gameInterface.getBoard()));
        }
    }

    @Nested
    @DisplayName("Move")
    class Move
    {
        @Test
        public void Rook_Legal_Move()
        {
            String boardStr = "P,4,4,W/N,6,7,B/P,4,6,B/K,4,7,B/P,2,5,B/P,2,6,W/B,2,7,B/P,0,6,W/R,0,7,B/Q,3,0,W/P,3,1,W/B,3,2,W/P,1,0,W/B,1,1,W/N,1,2,W/B,5,7,B/Q,1,3,B/P,3,5,W/N,1,4,W/P,1,5,B/N,1,7,B/P,6,0,B/K,4,2,W/P,2,0,W/R,2,1,W/";
            IGameInterface gameInterface = new GameFactory().newClassicCustomGame(boardStr);
            BoardPosition origin = new BoardPosition(2, 1);
            BoardPosition destination = new BoardPosition(2, 5);

            boolean moveResult = gameInterface.requestMove(origin, destination);

            Assertions.assertTrue(moveResult, BoardUtils.getBoardAsString(gameInterface.getBoard()));
            Assertions.assertEquals(EColor.BLACK, gameInterface.getBoard().colorToPlay());
        }

        @Test
        public void Rook_Illegal_Move()
        {
            String boardStr = "P,4,4,W/N,6,7,B/P,4,6,B/P,2,4,W/K,4,7,B/P,2,6,W/B,2,7,B/P,0,6,W/R,0,7,B/P,7,1,W/P,5,2,W/N,1,0,W/Q,7,6,W/P,3,3,B/B,1,1,W/B,5,7,B/Q,1,3,B/P,3,5,W/N,1,4,W/P,1,5,B/N,1,7,B/P,6,0,B/B,2,0,W/K,4,2,W/R,2,1,W/";
            IGameInterface gameInterface = new GameFactory().newClassicCustomGame(boardStr);
            BoardPosition origin = new BoardPosition(2, 1);
            BoardPosition destination = new BoardPosition(6, 6);

            boolean moveResult = gameInterface.requestMove(origin, destination);

            Assertions.assertFalse(moveResult, BoardUtils.getBoardAsString(gameInterface.getBoard()));
            Assertions.assertEquals(EColor.WHITE, gameInterface.getBoard().colorToPlay());
        }

        @Test
        public void Rook_Out_Of_Board_Move()
        {
            String boardStr = "P,4,4,W/N,6,7,B/P,4,6,B/P,2,4,W/K,4,7,B/P,2,6,W/B,2,7,B/P,0,6,W/R,0,7,B/P,7,1,W/P,5,2,W/N,1,0,W/Q,7,6,W/P,3,3,B/B,1,1,W/B,5,7,B/Q,1,3,B/P,3,5,W/N,1,4,W/P,1,5,B/N,1,7,B/P,6,0,B/B,2,0,W/K,4,2,W/R,2,1,W/";
            IGameInterface gameInterface = new GameFactory().newClassicCustomGame(boardStr);
            BoardPosition origin = new BoardPosition(2, 1);
            BoardPosition destination = new BoardPosition(10, 10);

            boolean moveResult = gameInterface.requestMove(origin, destination);

            Assertions.assertFalse(moveResult, BoardUtils.getBoardAsString(gameInterface.getBoard()));
            Assertions.assertEquals(EColor.WHITE, gameInterface.getBoard().colorToPlay());
        }

        @Test
        public void Rook_Take_Piece()
        {
            String boardStr = "P,4,4,W/N,6,7,B/P,4,6,B/K,4,7,B/P,2,6,W/B,2,7,B/P,0,6,W/R,0,7,B/P,7,1,W/P,5,2,W/N,1,0,W/Q,7,6,W/P,3,3,B/B,1,1,W/P,5,5,W/B,5,7,B/Q,1,3,B/P,3,5,W/N,1,4,W/P,1,5,B/N,1,7,B/P,6,0,B/R,2,0,W/K,4,2,W/";
            IGameInterface gameInterface = new GameFactory().newClassicCustomGame(boardStr);
            BoardPosition origin = new BoardPosition(2, 0);
            BoardPosition destination = new BoardPosition(6, 0);

            boolean moveResult = gameInterface.requestMove(origin, destination);

            Assertions.assertTrue(moveResult, BoardUtils.getBoardAsString(gameInterface.getBoard()));
            Assertions.assertEquals(EColor.BLACK, gameInterface.getBoard().colorToPlay());
        }

        @Test
        public void Rook_Take_Wrong_Piece()
        {
            String boardStr = "P,4,4,W/N,6,7,B/P,4,6,B/K,4,7,B/P,2,6,W/B,2,7,B/P,0,6,W/R,0,7,B/P,7,1,W/P,5,2,W/N,1,0,W/Q,7,6,W/P,3,3,B/B,1,1,W/P,5,5,W/B,5,7,B/Q,1,3,B/P,3,5,W/N,1,4,W/P,1,5,B/N,1,7,B/P,6,0,B/R,2,0,W/K,4,2,W/";
            IGameInterface gameInterface = new GameFactory().newClassicCustomGame(boardStr);
            BoardPosition origin = new BoardPosition(2, 0);
            BoardPosition destination = new BoardPosition(2, 6);

            boolean moveResult = gameInterface.requestMove(origin, destination);

            Assertions.assertFalse(moveResult, BoardUtils.getBoardAsString(gameInterface.getBoard()));
            Assertions.assertEquals(EColor.WHITE, gameInterface.getBoard().colorToPlay());
        }

        @Test
        public void Rook_Wrong_Color_To_Play()
        {
            String boardStr = "P,4,4,W/N,6,7,B/P,4,6,B/K,4,7,B/P,2,5,B/P,2,6,W/B,2,7,B/P,0,6,W/R,0,7,B/Q,3,0,W/P,3,1,W/B,3,2,W/P,1,0,W/B,1,1,W/N,1,2,W/B,5,7,B/Q,1,3,B/P,3,5,W/N,1,4,W/P,1,5,B/N,1,7,B/P,6,0,B/K,4,2,W/P,2,0,W/R,2,1,B/";
            IGameInterface gameInterface = new GameFactory().newClassicCustomGame(boardStr);
            BoardPosition origin = new BoardPosition(2, 1);
            BoardPosition destination = new BoardPosition(2, 5);

            boolean moveResult = gameInterface.requestMove(origin, destination);

            Assertions.assertFalse(moveResult, BoardUtils.getBoardAsString(gameInterface.getBoard()));
            Assertions.assertEquals(EColor.WHITE, gameInterface.getBoard().colorToPlay());
        }
    }
}
