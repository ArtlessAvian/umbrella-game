package com.artlessavian.umbrellagame.game;

import com.badlogic.gdx.math.Vector2;

import static com.artlessavian.umbrellagame.game.Tile.AIR;
import static com.artlessavian.umbrellagame.game.Tile.SOMETHING;

public class AutoMap implements MapInterface
{
	private Tile[] tileSet;
	private Vector2 startPos;

	public AutoMap()
	{

	}

	public float square(float x, float duty)
	{
		if ((x - (int)x) < duty)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

	public float magicFunction(float x)
	{
		return (float)(square(x / 10, 0.1f) * Math.sin(x / 13) * 7 + Math.sin(x / 10)) * 2 + 10;
	}

	@Override
	public Vector2 getStart()
	{
		return new Vector2(10, magicFunction(10) + 5);
	}

	public Tile get(int x, int y)
	{
		if (magicFunction(x) > y)
		{
			return SOMETHING;
		}
		else
		{
			return AIR;
		}
	}

	public int getHeight()
	{
		return 100000000;
	}

	public int getWidth()
	{
		return 3000000;
	}
}
