package com.checkmatepro.model;

import com.checkmatepro.game.BoardPrinter;
import com.checkmatepro.model.pieces.EColor;
import com.checkmatepro.model.pieces.Piece;

import java.util.Map;
import java.util.Optional;

public class GameBoard
{
    public static final int SIZE = 8;
    private final Map<BoardPosition, Piece> piecesByPosition;

    private EColor colorToPlay = EColor.WHITE;

    protected GameBoard(Map<BoardPosition, Piece> piecesByPosition)
    {
        this.piecesByPosition = piecesByPosition;
    }

    public Map<BoardPosition, Piece> getPiecesByPosition()
    {
        return piecesByPosition;
    }

    public EColor colorToPlay()
    {
        return colorToPlay;
    }

    public void movePieceTo(BoardPosition origin, BoardPosition destination)
    {
        if (piecesByPosition.containsKey(origin))
        {
            Piece pieceToMove = piecesByPosition.get(origin);
            piecesByPosition.put(destination, pieceToMove);
            piecesByPosition.remove(origin);
            pieceToMove.setHasMoved(true);
        }
    }

    @Override
    protected GameBoard clone()
    {
        return new GameBoard(Map.copyOf(piecesByPosition));
    }

    public Optional<Piece> getPieceAtPosition(BoardPosition position)
    {
        return Optional.ofNullable(piecesByPosition.get(position));
    }
}
