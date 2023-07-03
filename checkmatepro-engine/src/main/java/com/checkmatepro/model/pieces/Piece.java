package com.checkmatepro.model.pieces;

import com.checkmatepro.model.BoardPosition;

public class Piece implements IMovable
{
    private final EPieceType type;
    private final EColor color;

    private boolean hasMoved = false;

    private Piece(EPieceType type, EColor color)
    {
        this.type = type;
        this.color = color;
    }

    public EPieceType type()
    {
        return type;
    }

    public EColor color()
    {
        return color;
    }

    public boolean hasMoved()
    {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved)
    {
        this.hasMoved = hasMoved;
    }

    public static class Builder
    {
        private EPieceType type;
        private EColor color;

        public Piece build()
        {
            return new Piece(type, color);
        }

        public Builder type(EPieceType type)
        {
            this.type = type;
            return this;
        }

        public Builder color(EColor color)
        {
            this.color = color;
            return this;
        }
    }
}
