package com.checkmatepro.gui;

import com.checkmatepro.model.pieces.EColor;
import com.checkmatepro.model.pieces.EPieceType;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.Arrays;

public enum EPieceImage
{
    WHITE_PAWN_IMAGE(EPieceType.PAWN, EColor.WHITE),
    WHITE_KNIGHT_IMAGE(EPieceType.KNIGHT, EColor.WHITE),
    WHITE_BISHOP_IMAGE(EPieceType.BISHOP, EColor.WHITE),
    WHITE_ROOK_IMAGE(EPieceType.ROOK, EColor.WHITE),
    WHITE_QUEEN_IMAGE(EPieceType.QUEEN, EColor.WHITE),
    WHITE_KING_IMAGE(EPieceType.KING, EColor.WHITE),
    BLACK_PAWN_IMAGE(EPieceType.PAWN, EColor.BLACK),
    BLACK_KNIGHT_IMAGE(EPieceType.KNIGHT, EColor.BLACK),
    BLACK_BISHOP_IMAGE(EPieceType.BISHOP, EColor.BLACK),
    BLACK_ROOK_IMAGE(EPieceType.ROOK, EColor.BLACK),
    BLACK_QUEEN_IMAGE(EPieceType.QUEEN, EColor.BLACK),
    BLACK_KING_IMAGE(EPieceType.KING, EColor.BLACK);
    private static final int PIECE_SIZE = 34;
    private final Image image;

    EPieceImage(EPieceType type, EColor color)
    {
        image = new Image(getImageInputStream(type, color), PIECE_SIZE, PIECE_SIZE, false, false);
    }

    public Image getImage()
    {
        return image;
    }

    private InputStream getImageInputStream(EPieceType type, EColor color)
    {
        String colorPrefix = color == EColor.WHITE ? "W" : "B";

        return getClass().getClassLoader().getResourceAsStream(colorPrefix + "_" + type.name() + ".png");
    }

    public static EPieceImage of(EPieceType type, EColor color)
    {
        return Arrays.stream(values())
                .filter(element -> element.name()
                        .contains(type.name()) && element.name().contains(color.name()))
                .findAny()
                .orElseThrow();
    }
}
