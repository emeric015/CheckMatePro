package com.checkmatepro.game;

import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.Piece;

import java.util.Optional;
import java.util.Set;

public interface IGameInterface
{
    /**
     * @return the current state of the chess board
     */
    GameBoard getBoard();

    /**
     * @param position the position on the board
     * @return the piece if it's present
     */
    Optional<Piece> getPieceAtPosition(BoardPosition position);

    /**
     * @param origin               orignal position
     * @param requestedDestination the destination to move the piece to
     * @return true if the move is legal and has been done, false if the move is illegal.
     */
    boolean requestMove(BoardPosition origin, BoardPosition requestedDestination);

    /**
     * @param position the board position to get destinations from
     * @return the set of all board positions, which are legal destinations
     */
    Set<BoardPosition> getLegalDestinations(BoardPosition position);
}
