package com.checkmatepro.game.utils;

import com.checkmatepro.game.movement.Pair;
import com.checkmatepro.model.BoardFactory;
import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.EColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class VectorUtilsTest
{
    @Nested
    @DisplayName("Empty Board")
    class EmptyBoard
    {
        private final GameBoard board = BoardFactory.emptyBoard();
        private final EColor colorToPlay = EColor.WHITE;

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

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, colorToPlay, boardPosition, axis);

            Assertions.assertEquals(expected, positions);
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

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, colorToPlay, boardPosition, axis);

            Assertions.assertEquals(expected, positions);
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

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, colorToPlay, boardPosition, axis);

            Assertions.assertEquals(expected, positions);
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

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, colorToPlay, boardPosition, axis);

            Assertions.assertEquals(expected, positions);
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

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, colorToPlay, boardPosition, axis);

            Assertions.assertEquals(expected, positions);
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

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, colorToPlay, boardPosition, axis);

            Assertions.assertEquals(expected, positions);
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

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, colorToPlay, boardPosition, axis);

            Assertions.assertEquals(expected, positions);
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

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, colorToPlay, boardPosition, axis);

            Assertions.assertEquals(expected, positions);
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

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, colorToPlay, boardPosition, axis);

            Assertions.assertEquals(expected, positions);
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

            Set<BoardPosition> positions = VectorUtils.applyVectorOnBoard(board, colorToPlay, boardPosition, axis);

            Assertions.assertEquals(expected, positions);
        }
    }
}