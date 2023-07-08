package com.checkmatepro.game;

import com.checkmatepro.game.movement.Pair;
import com.checkmatepro.game.utils.BoardUtils;
import com.checkmatepro.game.utils.Vector;
import com.checkmatepro.game.utils.VectorUtils;
import com.checkmatepro.model.BoardFactory;
import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class VectorBoardMoveTest
{
    @Nested
    @DisplayName("Empty Board")
    class EmptyBoard
    {
        private final GameBoard board = BoardFactory.emptyBoard();

        @Test
        void ApplyVector_VerticalUp()
        {
            int column = 0;
            int line = 0;
            BoardPosition boardPosition = new BoardPosition(column, line);
            Pair<Integer, Integer> axis = Vector.VERTICAL.getAxis();

            Set<BoardPosition> expected = IntStream.range(line + 1, GameBoard.SIZE)
                    .mapToObj(value -> new BoardPosition(column, value))
                    .collect(Collectors.toSet());

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, boardPosition, axis);

            Assertions.assertEquals(expected, positions, BoardUtils.getBoardAsString(board));
        }

        @Test
        void ApplyVector_VerticalUpMidBoard()
        {
            int column = 7;
            int line = 3;
            BoardPosition boardPosition = new BoardPosition(column, line);
            Pair<Integer, Integer> axis = Vector.VERTICAL.getAxis();

            Set<BoardPosition> expected = IntStream.range(line + 1, GameBoard.SIZE)
                    .mapToObj(value -> new BoardPosition(column, value))
                    .collect(Collectors.toSet());

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, boardPosition, axis);

            Assertions.assertEquals(expected, positions, BoardUtils.getBoardAsString(board));
        }

        @Test
        void ApplyVector_VerticalDown()
        {
            int column = 0;
            int line = 7;
            BoardPosition boardPosition = new BoardPosition(column, line);
            Pair<Integer, Integer> axis = Vector.VERTICAL.getOpposite();

            Set<BoardPosition> expected = IntStream.range(0, line)
                    .mapToObj(value -> new BoardPosition(column, value))
                    .collect(Collectors.toSet());

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, boardPosition, axis);

            Assertions.assertEquals(expected, positions, BoardUtils.getBoardAsString(board));
        }

        @Test
        void ApplyVector_VerticalDownMidBoard()
        {
            int column = 7;
            int line = 4;
            BoardPosition boardPosition = new BoardPosition(column, line);
            Pair<Integer, Integer> axis = Vector.VERTICAL.getOpposite();

            Set<BoardPosition> expected = IntStream.range(0, line)
                    .mapToObj(value -> new BoardPosition(column, value))
                    .collect(Collectors.toSet());

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, boardPosition, axis);

            Assertions.assertEquals(expected, positions, BoardUtils.getBoardAsString(board));
        }

        @Test
        void ApplyVector_HorizontalRight()
        {
            int line = 0;
            int column = 0;
            BoardPosition boardPosition = new BoardPosition(column, line);
            Pair<Integer, Integer> axis = Vector.HORIZONTAL.getAxis();

            Set<BoardPosition> expected = IntStream.range(column + 1, GameBoard.SIZE)
                    .mapToObj(value -> new BoardPosition(value, line))
                    .collect(Collectors.toSet());

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, boardPosition, axis);

            Assertions.assertEquals(expected, positions, BoardUtils.getBoardAsString(board));
        }

        @Test
        void ApplyVector_HorizontalRightMidBoard()
        {
            int line = 7;
            int column = 4;
            BoardPosition boardPosition = new BoardPosition(column, line);
            Pair<Integer, Integer> axis = Vector.HORIZONTAL.getAxis();

            Set<BoardPosition> expected = IntStream.range(column + 1, GameBoard.SIZE)
                    .mapToObj(value -> new BoardPosition(value, line))
                    .collect(Collectors.toSet());

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, boardPosition, axis);

            Assertions.assertEquals(expected, positions, BoardUtils.getBoardAsString(board));
        }

        @Test
        void ApplyVector_HorizontalLeft()
        {
            int line = 0;
            int column = 7;
            BoardPosition boardPosition = new BoardPosition(column, line);
            Pair<Integer, Integer> axis = Vector.HORIZONTAL.getOpposite();

            Set<BoardPosition> expected = IntStream.range(0, column)
                    .mapToObj(value -> new BoardPosition(value, line))
                    .collect(Collectors.toSet());

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, boardPosition, axis);

            Assertions.assertEquals(expected, positions, BoardUtils.getBoardAsString(board));
        }

        @Test
        void ApplyVector_HorizontalLeftMidBoard()
        {
            int line = 0;
            int column = 4;
            BoardPosition boardPosition = new BoardPosition(column, line);
            Pair<Integer, Integer> axis = Vector.HORIZONTAL.getOpposite();

            Set<BoardPosition> expected = IntStream.range(0, column)
                    .mapToObj(value -> new BoardPosition(value, line))
                    .collect(Collectors.toSet());

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, boardPosition, axis);

            Assertions.assertEquals(expected, positions, BoardUtils.getBoardAsString(board));
        }

        @Test
        void ApplyVector_TopLeftBotRight()
        {
            int column = 0;
            int line = 6;
            BoardPosition boardPosition = new BoardPosition(column, line);
            Pair<Integer, Integer> axis = Vector.TOP_LEFT_BOT_RIGHT.getAxis();

            Set<BoardPosition> expected = IntStream.range(column + 1, GameBoard.SIZE)
                    .mapToObj(value -> new BoardPosition(value, line - value))
                    .filter(BoardUtils::isInBoard)
                    .collect(Collectors.toSet());

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, boardPosition, axis);

            Assertions.assertEquals(expected, positions, BoardUtils.getBoardAsString(board));
        }

        @Test
        void ApplyVector_TopLeftBotRightMidBoard()
        {
            int column = 5;
            int line = 5;
            BoardPosition boardPosition = new BoardPosition(column, line);
            Pair<Integer, Integer> axis = Vector.TOP_LEFT_BOT_RIGHT.getAxis();

            Set<BoardPosition> expected = IntStream.range(column + 1, GameBoard.SIZE)
                    .mapToObj(value -> new BoardPosition(value, line - (value - line)))
                    .filter(BoardUtils::isInBoard)
                    .collect(Collectors.toSet());

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, boardPosition, axis);

            Assertions.assertEquals(expected, positions, BoardUtils.getBoardAsString(board));
        }
    }

    @Nested
    @DisplayName("Non Empty Board")
    class NonEmptyBoard
    {
        private final GameBoard board = BoardFactory.customBoard("R,3,4,W/P,3,6,B/R,2,4,W/P,2,6,W/P,4,2,W/P,7,0,B");

        @Test
        void ApplyVector_VerticalUp_OppositeColor()
        {
            int column = 3;
            int line = 4;

            BoardPosition boardPosition = new BoardPosition(column, line);
            Pair<Integer, Integer> axis = Vector.VERTICAL.getAxis();

            Set<BoardPosition> expected = IntStream.range(line + 1, 7)
                    .mapToObj(value -> new BoardPosition(column, value))
                    .collect(Collectors.toSet());

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, boardPosition, axis);

            Assertions.assertEquals(expected, positions, BoardUtils.getBoardAsString(board));
        }

        @Test
        void ApplyVector_VerticalUp_SameColor()
        {
            int column = 2;
            int line = 4;

            BoardPosition boardPosition = new BoardPosition(column, line);
            Pair<Integer, Integer> axis = Vector.VERTICAL.getAxis();

            Set<BoardPosition> expected = IntStream.range(line + 1, 6)
                    .mapToObj(value -> new BoardPosition(column, value))
                    .collect(Collectors.toSet());

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, boardPosition, axis);

            Assertions.assertEquals(expected, positions, BoardUtils.getBoardAsString(board));
        }

        @Test
        void ApplyVector_TopLeftBotRight_OppositeColor()
        {
            int column = 3;
            int line = 4;

            BoardPosition boardPosition = new BoardPosition(column, line);
            Pair<Integer, Integer> axis = Vector.TOP_LEFT_BOT_RIGHT.getAxis();

            Set<BoardPosition> expected = IntStream.range(column + 1, GameBoard.SIZE)
                    .mapToObj(value -> new BoardPosition(value, line - (value - column)))
                    .filter(BoardUtils::isInBoard)
                    .collect(Collectors.toSet());

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, boardPosition, axis);

            Assertions.assertEquals(expected, positions, BoardUtils.getBoardAsString(board));
        }

        @Test
        void ApplyVector_TopLeftBotRight_SameColor()
        {
            int column = 2;
            int line = 4;

            BoardPosition boardPosition = new BoardPosition(column, line);
            Pair<Integer, Integer> axis = Vector.TOP_LEFT_BOT_RIGHT.getAxis();

            Set<BoardPosition> expected = Collections.singleton(new BoardPosition(3, 3));

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, boardPosition, axis);

            Assertions.assertEquals(expected, positions, BoardUtils.getBoardAsString(board));
        }
    }
}