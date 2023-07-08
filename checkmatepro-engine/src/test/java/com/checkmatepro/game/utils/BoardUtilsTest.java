package com.checkmatepro.game.utils;

import com.checkmatepro.game.GameFactory;
import com.checkmatepro.game.IGameInterface;
import com.checkmatepro.model.BoardPosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BoardUtilsTest
{
    @Nested
    @DisplayName("Is In Board")
    class IsInBoard
    {
        @Test
        public void Position_In_Board()
        {
            BoardPosition position = new BoardPosition(7, 7);

            Assertions.assertTrue(BoardUtils.isInBoard(position));
        }

        @Test
        public void Position_Not_In_Board()
        {
            BoardPosition position = new BoardPosition(10, 7);

            Assertions.assertFalse(BoardUtils.isInBoard(position));
        }
    }

    @Nested
    @DisplayName("Get Board As String")
    class GetBoardAsString
    {
        @Test
        public void BoardAsString()
        {
            String boardStr = "B,0,0,W/K,0,2,W/P,4,7,B/P,2,6,W/P,0,6,W/Q,0,7,B/Q,7,0,W/N,7,2,W/P,5,2,W/B,3,0,B/P,7,7,B/P,3,5,W/P,1,5,B/N,1,7,B/R,6,4,B/";
            IGameInterface gameInterface = new GameFactory().newClassicCustomGame(boardStr);

            String expectedBoard =
                    """

                            QN__P__P
                            P_P_____
                            _P_P____
                            ______R_
                            ________
                            K____P_N
                            ________
                            B__B___Q
                            """;

            String actualBoard = BoardUtils.getBoardAsString(gameInterface.getBoard());

            Assertions.assertEquals(expectedBoard, actualBoard);
        }

        @Test
        public void Position_Not_In_Board()
        {
            BoardPosition position = new BoardPosition(10, 7);

            Assertions.assertFalse(BoardUtils.isInBoard(position));
        }
    }
}
