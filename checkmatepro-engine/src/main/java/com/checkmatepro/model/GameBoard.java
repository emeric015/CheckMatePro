package com.checkmatepro.model;

import com.checkmatepro.model.pieces.Piece;

import java.util.Set;

public class GameBoard
{
    public static final int SIZE = 8;
    private final Set<Piece> pieces;

    protected GameBoard(Set<Piece> pieces)
    {
        this.pieces = pieces;
    }

    public Set<Piece> getPieces()
    {
        return pieces;
    }
}
