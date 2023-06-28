package com.checkmatepro.gui;

import com.checkmatepro.model.pieces.EColor;
import com.checkmatepro.model.pieces.Piece;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.InputStream;
import java.util.Optional;

public class TilePane extends StackPane
{
    private static final int TILE_SIZE = 40;
    private static final int PIECE_SIZE = 34;

    private static final int CIRCLE_SIZE = 5;

    private final Rectangle selectionRectangle = new Rectangle(TILE_SIZE, TILE_SIZE, new Color(1, 0, 0, 0.5));

    private final Circle destinationCircle = new Circle(CIRCLE_SIZE, new Color(0.1, 0.1, 0.1, 0.5));

    public TilePane(Optional<Piece> piece, EColor color)
    {
        super();

        buildPane(piece, color);
    }

    private void buildPane(Optional<Piece> piece, EColor color)
    {
        Image tileImage = new Image(getTileInputStream(color), TILE_SIZE, TILE_SIZE, false, false);
        getChildren().add(new ImageView(tileImage));

        if (piece.isPresent())
        {
            Image pieceImage = new Image(getImageInputStream(piece.get()), PIECE_SIZE, PIECE_SIZE, false, false);
            getChildren().add(new ImageView(pieceImage));
        }

        setAlignment(Pos.CENTER);

        getChildren().add(selectionRectangle);
        selectionRectangle.setVisible(false);

        getChildren().add(destinationCircle);
        destinationCircle.setVisible(false);
    }

    private InputStream getTileInputStream(EColor color)
    {

        String squareColor = color == EColor.WHITE ? "light" : "dark";

        return getClass().getClassLoader().getResourceAsStream("square_brown_" + squareColor + ".png");
    }

    private InputStream getImageInputStream(Piece piece)
    {
        String prefix = piece.color() == EColor.WHITE ? "W" : "B";
        String type = piece.type().name().toUpperCase();

        return getClass().getClassLoader().getResourceAsStream(prefix + "_" + type + ".png");
    }

    public void select()
    {
        selectionRectangle.setVisible(true);
    }

    public void unselect()
    {
        selectionRectangle.setVisible(false);
    }

    public void setPossibleDestination(boolean possibleDestination)
    {
        destinationCircle.setVisible(possibleDestination);
    }
}
