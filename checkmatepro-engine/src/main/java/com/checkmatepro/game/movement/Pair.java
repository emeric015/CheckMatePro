package com.checkmatepro.game.movement;

public record Pair<X, Y>(X columnFactor, Y lineFactor)
{
    @Override
    public String toString()
    {
        return "Pair [columnFactor=" + columnFactor + ", lineFactor=" + lineFactor + "]";
    }
}
