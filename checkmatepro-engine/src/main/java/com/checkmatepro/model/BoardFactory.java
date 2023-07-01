package com.checkmatepro.model;

import com.checkmatepro.model.pieces.EColor;
import com.checkmatepro.model.pieces.EPieceType;
import com.checkmatepro.model.pieces.Piece;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BoardFactory
{
    private static final Map<String, EPieceType> letterToPieceType = Map.of(
            "K", EPieceType.KING,
            "Q", EPieceType.QUEEN,
            "B", EPieceType.BISHOP,
            "R", EPieceType.ROOK,
            "P", EPieceType.PAWN,
            "N", EPieceType.KNIGHT);

    public static GameBoard parseBoard(String board)
    {
        Set<Piece> pieces = new HashSet<>();

        Arrays.stream(board.split("/"))
                .map(BoardFactory::parsePiece)
                .forEach(pieces::add);

        return new GameBoard(pieces);
    }

    public static Piece parsePiece(String piece)
    {
        if (piece != null)
        {
            String[] elements = piece.split(",");

            if (elements.length == 4)
            {
                return new Piece.Builder()
                        .type(letterToPieceType.get(elements[0]))
                        .position(new BoardPosition(Integer.parseInt(elements[1]), Integer.parseInt(elements[2])))
                        .color("W".equals(elements[3]) ? EColor.WHITE : EColor.BLACK)
                        .build();
            }
            else
            {
                throw new IllegalArgumentException("Invalid element : " + piece);
            }
        }
        else
        {
            throw new IllegalArgumentException("The input element is null");
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
        return new GameBoard(new HashSet<>());
    }
}
