package com.checkmatepro.model.pieces;

public enum EColor
{
    WHITE,
    BLACK;

    public EColor getOpposite()
    {
        return switch (this)
                {

                    case WHITE -> BLACK;
                    case BLACK -> WHITE;
                };
    }
}
