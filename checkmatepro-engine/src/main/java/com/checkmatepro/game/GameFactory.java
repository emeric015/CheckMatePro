package com.checkmatepro.game;

import com.checkmatepro.model.BoardFactory;

public class GameFactory
{
    public Game newGame()
    {
        return new Game(BoardFactory.classicBoard());
    }
}
