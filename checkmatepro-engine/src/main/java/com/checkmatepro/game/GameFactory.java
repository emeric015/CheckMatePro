package com.checkmatepro.game;

import com.checkmatepro.model.BoardFactory;

public class GameFactory
{
    public IGameInterface newClassicGame()
    {
        return new Game(BoardFactory.classicBoard(), EGameRule.CLASSIC_GAME);
    }

    public IGameInterface newFreeMoveGame()
    {
        return new Game(BoardFactory.classicBoard(), EGameRule.FREE_MOVE);
    }

    public IGameInterface newFreeMoveCustomGame(String board)
    {
        return new Game(BoardFactory.customBoard(board), EGameRule.FREE_MOVE);
    }

    public IGameInterface newClassicCustomGame(String board)
    {
        return new Game(BoardFactory.customBoard(board), EGameRule.CLASSIC_GAME);
    }
}
