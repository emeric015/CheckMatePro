package com.checkmatepro.gui;

import com.checkmatepro.game.IGameInterface;
import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.EColor;
import com.checkmatepro.model.pieces.Piece;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BoardGamePane extends GridPane
{
    private final IGameInterface gameInterface;

    private final Map<BoardPosition, TilePane> tiles = new HashMap<>();
    private Optional<TilePane> selectedTile = Optional.empty();

    public BoardGamePane(IGameInterface gameInterface)
    {
        super();

        this.gameInterface = gameInterface;
    }

    public void initGameBoardPane()
    {
        EColor color = EColor.BLACK;

        for (int i = 0; i < GameBoard.SIZE; i++)
        {
            for (int j = 0; j < GameBoard.SIZE; j++)
            {
                BoardPosition position = new BoardPosition(j, i);

                TilePane tilePane = new TilePane(color, position);
                tiles.put(position, tilePane);
                tilePane.setOnMouseClicked(mouseEvent -> selectTile(tilePane));
                add(tilePane, j, GameBoard.SIZE - i);

                color = (color == EColor.BLACK) ? EColor.WHITE : EColor.BLACK;
            }

            color = (color == EColor.BLACK) ? EColor.WHITE : EColor.BLACK;
        }

        updateGameBoardPane();
    }

    private void updateGameBoardPane()
    {
        tiles.values().forEach(tile -> tile.setPiece(null));

        for (Map.Entry<BoardPosition, Piece> pieceByPosition : gameInterface.getBoard().getPiecesByPosition().entrySet())
        {
            TilePane tile = tiles.get(pieceByPosition.getKey());
            tile.setPiece(pieceByPosition.getValue());
        }

        tiles.values().forEach(tile -> tile.setPossibleDestination(false));
        if (selectedTile.isPresent())
        {
            for (BoardPosition destination : gameInterface.getLegalDestinations(selectedTile.get().getPosition()))
            {
                tiles.get(destination).setPossibleDestination(true);
            }
        }
    }

    private void selectTile(TilePane tilePane)
    {
        if (selectedTile.isPresent())
        {
            BoardPosition position = selectedTile.get().getPosition();
            Optional<Piece> pieceOnPreviousSelectedTile = gameInterface.getPieceAtPosition(position);
            pieceOnPreviousSelectedTile.ifPresent(piece ->
            {
                gameInterface.requestMove(position, tilePane.getPosition());
            });
            selectedTile.get().unselect();
            selectedTile = Optional.empty();
        }
        else
        {
            tilePane.select();
            selectedTile = Optional.of(tilePane);
        }

        updateGameBoardPane();
    }
}
