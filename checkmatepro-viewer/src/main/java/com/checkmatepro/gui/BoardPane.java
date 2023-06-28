package com.checkmatepro.gui;

import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.EColor;
import com.checkmatepro.model.pieces.Piece;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BoardPane extends GridPane
{
    private final Viewer callback;

    private Map<BoardPosition, TilePane> tiles = new HashMap<>();
    private Optional<TilePane> selectedPane = Optional.empty();

    public BoardPane(Viewer callback)
    {
        super();

        this.callback = callback;
    }

    public void updateBoard(GameBoard board)
    {
        EColor color = EColor.BLACK;

        for (int i = 0; i < GameBoard.SIZE; i++)
        {
            for (int j = 0; j < GameBoard.SIZE; j++)
            {
                BoardPosition position = new BoardPosition(j, i);

                Optional<Piece> optionalPiece = board.getPieces().stream()
                        .filter(piece -> piece.position().equals(position))
                        .findAny();

                TilePane tilePane = new TilePane(optionalPiece, color);
                tiles.put(position, tilePane);
                tilePane.setOnMouseClicked(mouseEvent ->
                {
                    if (selectedPane.isPresent() && selectedPane.get() != tilePane)
                    {
                        selectedPane.get().unselect();
                    }

                    tilePane.select();
                    selectedPane = Optional.of(tilePane);

                    if (optionalPiece.isPresent())
                    {
                        tiles.values().forEach(tile -> tile.setPossibleDestination(false));
                        for (BoardPosition destination : callback.getPossibleMoves(board, optionalPiece.get()))
                        {
                            tiles.get(destination).setPossibleDestination(true);
                        }
                        callback.getPossibleMoves(board, optionalPiece.get()).forEach(destinationPosition -> tiles.get(destinationPosition).setPossibleDestination(true));
                    }

                });
                add(tilePane, j, GameBoard.SIZE - i);

                color = (color == EColor.BLACK) ? EColor.WHITE : EColor.BLACK;
            }

            color = (color == EColor.BLACK) ? EColor.WHITE : EColor.BLACK;
        }
    }
}
