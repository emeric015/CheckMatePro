package com.checkmatepro.model;

import com.checkmatepro.model.pieces.EColor;
import com.checkmatepro.model.pieces.Piece;

import java.util.Optional;
import java.util.Set;

public class GameBoard
{
    public static final int SIZE = 8;
    private final Set<Piece> pieces;

    private EColor colorToPlay = EColor.WHITE;

    protected GameBoard(Set<Piece> pieces)
    {
        this.pieces = pieces;
    }

    public Set<Piece> getPieces()
    {
        return pieces;
    }

    public EColor colorToPlay()
    {
        return colorToPlay;
    }

    public void movePieceTo(BoardPosition origin, BoardPosition destination)
    {
        Optional<Piece> pieceOnOrigin = getPieceAtPosition(origin);

        pieceOnOrigin.ifPresent(piece ->
        {
            Optional<Piece> pieceOnDestination = getPieceAtPosition(destination);
            pieceOnDestination.ifPresent(pieces::remove);

            pieces.remove(piece);
            piece.setPosition(destination);
            pieces.add(piece);
        });
    }

    public Optional<Piece> getPieceAtPosition(BoardPosition position)
    {
        return pieces.stream()
                .filter(piece -> piece.position().equals(position))
                .findAny();
    }
}
