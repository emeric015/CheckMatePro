package com.checkmatepro.model;

import com.checkmatepro.logging.LogUtils;
import com.checkmatepro.model.pieces.EColor;
import com.checkmatepro.model.pieces.EPieceType;
import com.checkmatepro.model.pieces.Piece;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory
{
    private BoardFactory()
    {

    }

    private static final Map<String, EPieceType> letterToPieceType = Map.of(
            "K", EPieceType.KING,
            "Q", EPieceType.QUEEN,
            "B", EPieceType.BISHOP,
            "R", EPieceType.ROOK,
            "P", EPieceType.PAWN,
            "N", EPieceType.KNIGHT);

    public static GameBoard parseBoard(String board)
    {
        Map<BoardPosition, Piece> pieces = new HashMap<>();

        Arrays.stream(board.split("/"))
                .map(BoardFactory::parsePiece)
                .forEach(entry -> pieces.put(entry.getKey(), entry.getValue()));

        return new GameBoard(pieces);
    }

    public static Map.Entry<BoardPosition, Piece> parsePiece(String pieceAsText)
    {
        if (pieceAsText != null)
        {
            String[] elements = pieceAsText.split(",");

            if (elements.length == 4)
            {
                BoardPosition position = new BoardPosition(Integer.parseInt(elements[1]), Integer.parseInt(elements[2]));
                Piece piece = new Piece.Builder()
                        .type(letterToPieceType.get(elements[0]))
                        .color("W".equals(elements[3]) ? EColor.WHITE : EColor.BLACK)
                        .build();

                return Map.entry(position, piece);
            }
            else
            {
                LogUtils.getLogger().error("Invalid element : " + pieceAsText);
                throw new IllegalArgumentException();
            }
        }
        else
        {
            LogUtils.getLogger().error("The input element is null");
            throw new IllegalArgumentException();
        }
    }

    public static GameBoard classicBoard()
    {
        return parseBoard(
                "R,0,0,W/N,1,0,W/B,2,0,W/Q,3,0,W/K,4,0,W/B,5,0,W/N,6,0,W/R,7,0,W/P,0,1,W/P,1,1,W/P,2,1,W/P,3,1,W/"
                        + "P,4,1,W/P,5,1,W/P,6,1,W/P,7,1,W/R,0,7,B/N,1,7,B/B,2,7,B/Q,3,7,B/K,4,7,B/B,5,7,B/"
                        + "N,6,7,B/R,7,7,B/P,0,6,B/P,1,6,B/P,2,6,B/P,3,6,B/P,4,6,B/P,5,6,B/P,6,6,B/P,7,6,B");
    }

    public static GameBoard customBoard(String board)
    {
        return parseBoard(board);
    }

    public static GameBoard emptyBoard()
    {
        return new GameBoard(new HashMap<>());
    }
}
