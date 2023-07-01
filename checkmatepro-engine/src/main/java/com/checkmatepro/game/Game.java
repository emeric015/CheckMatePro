package com.checkmatepro.game;

import com.checkmatepro.game.movement.IMoveLogicStrategy;
import com.checkmatepro.model.BoardFactory;
import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.Piece;

import java.util.Optional;
import java.util.Set;

public class Game implements IGameInterface
{
    private final GameBoard board;

    private final IMoveLogicStrategy moveStrategy;

    protected Game(GameBoard board, EGameRule gameRule)
    {
        this.board = board;
        this.moveStrategy = IMoveLogicStrategy.of(gameRule);
    }

    @Override
    public GameBoard getBoard()
    {
        return board;
    }

    @Override
    public Optional<Piece> getPieceAtPosition(BoardPosition position)
    {
        return board.getPieceAtPosition(position);
    }

    @Override
    public boolean requestMove(BoardPosition origin, BoardPosition requestedDestination)
    {
        return moveStrategy.requestAndDoMove(board, origin, requestedDestination);
    }

    @Override
    public Set<BoardPosition> getLegalDestinations(BoardPosition position)
    {
        return moveStrategy.getLegalDestinations(board, position);
    }

    public static void main(String[] args)
    {
        GameBoard board = BoardFactory.classicBoard();

        BoardPrinter.printBoard(board);
    }
}
