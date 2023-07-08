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


        String boardStr = "P,4,4,W/N,6,7,B/P,4,6,B/P,2,4,W/K,4,7,B/P,2,6,W/P,0,6,W/P,7,1,W/P,5,2,W/N,1,0,W/Q,7,6,W/B,3,2,B/P,3,3,B/B,1,1,W/B,5,7,B/Q,1,3,B/P,3,5,W/N,1,4,W/P,1,5,B/N,1,7,B/P,6,0,B/B,2,0,W/K,4,2,W/R,6,4,B/R,2,1,W/";

        IGameInterface game = new GameFactory().newFreeMoveCustomGame(boardStr);
//        IGameInterface game = new GameFactory().newFreeMoveGame();

        BoardGamePane boardGamePane = new BoardGamePane(game);
        boardGamePane.initGameBoardPane();

        Scene scene = new Scene(boardGamePane);

        stage.setScene(scene);
        stage.show();
    }
}
