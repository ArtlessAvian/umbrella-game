package com.artlessavian.umbrellagame.game;

import com.badlogic.gdx.math.Vector2;

import static com.artlessavian.umbrellagame.game.Tile.*;

public class AutoMap
{
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
		return (int)(Math.abs(Math.tan(x / 30))) % 2;
	}

//	public float spikeVariation(float x)
//	{
////		return 1;
//		return (float)(square(x / 20, 0.1f) * Math.abs(Math.sin(x / 2)) * 2);
//	}
//
//	public float holeVariation(float x)
//	{
//		return (float)(Math.sin(x/3) * Math.sin(x/8) * 3 - Math.sin(x/7)) * 2 + 18;
//	}

	public float cityVariation(float x)
	{
		return (float)(square(x / 20, 0.6f) * ((Math.sin(x/10) + 3) / 4) * 40);
	}

//	@Override
	public Vector2 getStart()
	{
		return new Vector2(15, mainTerrain(15) + 5);
	}

	public Tile get(int x, int y)
	{
		float magic = mainTerrain(x);
		int biome = getBiome(x);

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
			if (biome == 1)
			{
				if (cityVariation(x) > y)
				{
					return STONE2;
				}
			}

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
