package com.checkmatepro.game.movement;

import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.pieces.Piece;

public record PieceMove(Piece piece, BoardPosition origin, BoardPosition destination)
{

}
