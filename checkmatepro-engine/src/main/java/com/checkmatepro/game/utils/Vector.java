package com.checkmatepro.game.utils;

import com.checkmatepro.game.movement.Pair;

import java.util.function.UnaryOperator;

public enum Vector
{
    VERTICAL(new Pair<>(0, 1)),

    HORIZONTAL(new Pair<>(1, 0)),
    TOP_LEFT_BOT_RIGHT(new Pair<>(1, -1)),
    TOP_RIGHT_BOT_LEFT(new Pair<>(1, 1));

    private final Pair<Integer, Integer> axis;
    private final UnaryOperator<Pair<Integer, Integer>> oppositeAxis = (axis) -> new Pair<>(-axis.columnFactor(),
            -axis.lineFactor());

    Vector(final Pair<Integer, Integer> axis)
    {
        this.axis = axis;
    }

    public Pair<Integer, Integer> getAxis()
    {
        return axis;
    }

    public Pair<Integer, Integer> getOpposite()
    {
        return oppositeAxis.apply(this.getAxis());
    }
}
