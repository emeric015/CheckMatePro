package com.checkmatepro.gui;

import com.checkmatepro.model.BoardPosition;
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

public class TilePane extends StackPane
{
    private static final int TILE_SIZE = 40;

    private static final int CIRCLE_SIZE = 7;

    private final Rectangle selectionRectangle = new Rectangle(TILE_SIZE, TILE_SIZE, new Color(1, 0, 0, 0.5));

    private final Circle destinationCircle = new Circle(CIRCLE_SIZE, new Color(0.1, 0.1, 0.1, 0.7));

    private final BoardPosition position;
    private Piece piece;

    private final ImageView pieceImageView = new ImageView();

    public TilePane(EColor color, BoardPosition position)
    {
        super();

        this.position = position;

        initTile(color);
    }

    public BoardPosition getPosition()
    {
        return position;
    }

    public void setPiece(Piece piece)
    {
        this.piece = piece;
        updateTile();
    }

    private void initTile(EColor color)
    {
        Image tileImage = new Image(getTileInputStream(color), TILE_SIZE, TILE_SIZE, false, false);
        getChildren().add(new ImageView(tileImage));

        setAlignment(Pos.CENTER);

        getChildren().add(pieceImageView);

        getChildren().add(selectionRectangle);
        selectionRectangle.setVisible(false);

        getChildren().add(destinationCircle);
        destinationCircle.setVisible(false);
    }

    private void updateTile()
    {
        if (piece != null)
        {
            Image pieceImage = EPieceImage.of(piece.type(), piece.color()).getImage();
            pieceImageView.setImage(pieceImage);
        }
        else
        {
            pieceImageView.setImage(null);
        }
    }

    private InputStream getTileInputStream(EColor color)
    {
        String squareColor = color == EColor.WHITE ? "light" : "dark";

        return getClass().getClassLoader().getResourceAsStream("square_brown_" + squareColor + ".png");
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
