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

        IGameInterface game = new GameFactory().newFreeMoveCustomGame("R,0,0,W/P,6,6,B/P,0,1,W/N,6,7,B/P,4,6,B/K,4,7,B/P,2,6,B/B,2,7,B/P,0,6,B/R,0,7,B/R,7,0,B/P,7,1,W/B,5,0,W/P,5,1,W/P,3,1,W/N,1,0,W/Q,7,6,W/P,1,1,W/P,5,6,B/B,5,7,B/Q,1,3,B/P,3,6,B/N,1,4,W/P,1,6,B/N,1,7,B/P,6,1,W/K,4,0,W/P,4,1,W/B,2,0,W/P,2,1,W/");
//        IGameInterface game = new GameFactory().newFreeMoveGame();

        BoardGamePane boardGamePane = new BoardGamePane(game);
        boardGamePane.initGameBoardPane();

        Scene scene = new Scene(boardGamePane);

        stage.setScene(scene);
        stage.show();
    }
}
