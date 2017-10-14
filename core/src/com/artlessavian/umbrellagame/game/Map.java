package com.artlessavian.umbrellagame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import static com.artlessavian.umbrellagame.game.Tile.AIR;

public class Map
{
	private Tile[] tileSet;
	private int[][] tilemap;

	public Map(String filePath)
	{
		FileHandle file = Gdx.files.internal(filePath);
		String mapString = file.readString();

		mapString = mapString.replaceAll("\\s++", "");
		String[] tokens = mapString.split(";");

		String[] tileSetString = tokens[0].split(",");
		tileSet = new Tile[tileSetString.length];
		for (int i = 0; i < tileSetString.length; i++)
		{
			tileSet[i] = Tile.valueOf(tileSetString[i]);
		}

		String[] size = tokens[1].split(",");
		int width = Integer.parseInt(size[0]);
		int height = Integer.parseInt(size[1]);

		tilemap = new int[width][height];

		for (int y = 0; y < height; y++)
		{
			String[] row = tokens[y + 2].split(",");
			for (int x = 0; x < tilemap.length; x++)
			{
				this.tilemap[x][height - y - 1] = Integer.parseInt(row[x]);
			}
		}

	}

	public Tile get(int x, int y)
	{
		try
		{
			return tileSet[tilemap[x][y]];
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			return AIR;
		}
	}

	public int getHeight()
	{
		return tilemap[0].length;
	}

	public int getWidth()
	{
		return tilemap.length;
	}
}
