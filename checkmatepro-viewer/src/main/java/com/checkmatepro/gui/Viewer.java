package com.checkmatepro.gui;

import com.checkmatepro.game.GameFactory;
import com.checkmatepro.game.IGameInterface;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Viewer extends Application
{
    @Override
    public void start(Stage stage)
    {
        stage.setTitle("CheckMate Pro");

//        IGameInterface game = new GameFactory().newFreeMoveCustomGame("R,3,4,W/P,3,6,B/R,2,4,W/P,2,6,W/P,4,2,W/P,7,0,B");
        IGameInterface game = new GameFactory().newFreeMoveGame();

        BoardGamePane boardGamePane = new BoardGamePane(game);
        boardGamePane.initGameBoardPane();

        Scene scene = new Scene(boardGamePane);

        stage.setScene(scene);
        stage.show();
    }
}
