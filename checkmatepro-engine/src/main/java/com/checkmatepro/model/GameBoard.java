package com.checkmatepro.model;

import com.checkmatepro.game.utils.BoardUtils;
import com.checkmatepro.logging.LogUtils;
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
        if (!origin.equals(destination) && piecesByPosition.containsKey(origin))
        {
            Piece pieceToMove = piecesByPosition.get(origin);
            piecesByPosition.put(destination, pieceToMove);
            piecesByPosition.remove(origin);
            pieceToMove.setHasMoved(true);

            colorToPlay = colorToPlay.getOpposite();

            LogUtils.getLogger().info(BoardUtils.toBoardStr(this));
        }
    }

    public Optional<Piece> getPieceAtPosition(BoardPosition position)
    {
        return Optional.ofNullable(piecesByPosition.get(position));
    }
}
