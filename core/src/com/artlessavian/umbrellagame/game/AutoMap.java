package com.artlessavian.umbrellagame.game;

import com.badlogic.gdx.math.Vector2;

import static com.artlessavian.umbrellagame.game.Tile.*;

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

	public float mainTerrain(float x)
	{
		return (float)(Math.sin(x / 10) * 4 + 20);
	}

	public int getBiome(float x)
	{
		return (int)(Math.tan(x/100)) % 1;
	}

	public float spikeVariation(float x)
	{
//		return 1;
		return (float)(square(x / 20, 0.1f) * Math.abs(Math.sin(x / 2)) * 2);
	}

	public float holeVariation(float x)
	{
		return (float)(Math.sin(x/3) * Math.sin(x/8) * 3 - Math.sin(x/7)) * 2 + 18;
	}

	public float cityVariation(float x)
	{
		return (float)(square(x / 8, 0.6f));
	}

	@Override
	public Vector2 getStart()
	{
		return new Vector2(30, mainTerrain(30) + 5);
	}

	public Tile get(int x, int y)
	{
		float magic = mainTerrain(x);
		switch (getBiome(x))
		{
			case 0 : {magic += spikeVariation(x); break;}
//			case 1 : {magic += holeVariation(x); break;}
//			case 2 : {magic += 0; break;}
//			case 3 : {magic = Math.max(magic, 35); break;}
		}
		
		if (magic > y)
		{

			if (magic < y + 1)
			{
				return GRASS;
			}
			if (magic > y + 5 + Math.sin(x / 30) * Math.sin(x / 13) * 4)
			{
				return STONE;
			}
			return DIRT;
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
