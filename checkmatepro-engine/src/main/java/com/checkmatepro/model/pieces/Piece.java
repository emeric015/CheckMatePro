package com.checkmatepro.model.pieces;

import com.checkmatepro.model.BoardPosition;

public record Piece(EPieceType type, BoardPosition position, EColor color) implements IMovable
{
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
