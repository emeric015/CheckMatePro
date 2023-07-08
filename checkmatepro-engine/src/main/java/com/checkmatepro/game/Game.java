package com.checkmatepro.game;

import com.checkmatepro.game.movement.IMoveLogicStrategy;
import com.checkmatepro.game.utils.BoardPrinter;
import com.checkmatepro.model.BoardFactory;
import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.Piece;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Game implements IGameInterface
{
    private final GameBoard board;

    private final IMoveLogicStrategy moveStrategy;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    protected Game(GameBoard board, EGameRule gameRule)
    {
        this.board = board;
        this.moveStrategy = IMoveLogicStrategy.of(gameRule);
    }

    @Override
    public GameBoard getBoard()
    {
        try
        {
            readLock.lock();
            return board;
        }
        finally
        {
            readLock.unlock();
        }
    }

    @Override
    public Optional<Piece> getPieceAtPosition(BoardPosition position)
    {
        try
        {
            readLock.lock();
            return board.getPieceAtPosition(position);
        }
        finally
        {
            readLock.unlock();
        }
    }

    @Override
    public boolean requestMove(BoardPosition origin, BoardPosition requestedDestination)
    {
        try
        {
            writeLock.lock();
            return moveStrategy.requestAndDoMove(board, origin, requestedDestination);
        }
        finally
        {
            writeLock.unlock();
        }
    }

    @Override
    public Set<BoardPosition> getLegalDestinations(BoardPosition position)
    {
        try
        {
            readLock.lock();
            return moveStrategy.getLegalDestinations(board, position);
        }
        finally
        {
            readLock.unlock();
        }
    }

    public static void main(String[] args)
    {
        GameBoard board = BoardFactory.classicBoard();

        BoardPrinter.printBoard(board);
    }
}
