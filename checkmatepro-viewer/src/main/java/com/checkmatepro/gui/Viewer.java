package com.checkmatepro.gui;

import com.checkmatepro.game.Game;
import com.checkmatepro.game.GameFactory;
import com.checkmatepro.game.movement.ClassicMoveLogic;
import com.checkmatepro.model.BoardPosition;
import com.checkmatepro.model.GameBoard;
import com.checkmatepro.model.pieces.EColor;
import com.checkmatepro.model.pieces.Piece;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Set;

public class Viewer extends Application
{
    private ClassicMoveLogic moveLogic;

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("CheckMate Pro");

        moveLogic = new ClassicMoveLogic();

        BoardPane boardPane = new BoardPane(this);

        Scene scene = new Scene(boardPane);

        Game game = new GameFactory().newGame();
        boardPane.updateBoard(game.getBoard());

        stage.setScene(scene);
        stage.show();
    }

    public Set<BoardPosition> getPossibleMoves(GameBoard board, Piece piece)
    {
        return moveLogic.getLegalDestinations(board, piece, EColor.WHITE);
    }
}
