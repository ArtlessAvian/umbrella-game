package com.artlessavian.umbrellagame.game;

import com.badlogic.gdx.math.Vector2;

public interface MapInterface
{
	Vector2 getStart();
	Tile get(int x, int y);
	int getHeight();
	int getWidth();
}
