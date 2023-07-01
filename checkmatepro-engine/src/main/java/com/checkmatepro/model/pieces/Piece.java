package com.checkmatepro.model.pieces;

import com.checkmatepro.model.BoardPosition;

public class Piece implements IMovable
{
    private final EPieceType type;
    private BoardPosition position;
    private final EColor color;

    private Piece(EPieceType type, BoardPosition position, EColor color)
    {
        this.type = type;
        this.position = position;
        this.color = color;
    }

    public void setPosition(BoardPosition newPosition)
    {
        this.position = newPosition;
    }

    public EPieceType type()
    {
        return type;
    }

    public BoardPosition position()
    {
        return position;
    }

    public EColor color()
    {
        return color;
    }

    public static class Builder
    {
        private EPieceType type;
        private BoardPosition position;
        private EColor color;

        public Piece build()
        {
            return new Piece(type, position, color);
        }

        public Builder type(EPieceType type)
        {
            this.type = type;
            return this;
        }

        public Builder position(BoardPosition position)
        {
            this.position = position;
            return this;
        }

        public Builder color(EColor color)
        {
            this.color = color;
            return this;
        }
    }
}
