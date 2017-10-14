package com.artlessavian.umbrellagame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;

import static com.artlessavian.umbrellagame.game.Tile.AIR;

public class Map implements MapInterface
{
	private Tile[] tileSet;
	private int[][] tilemap;
	private Vector2 startPos;

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

<<<<<<< HEAD
		Vector2 startPos = new Vector2();
=======
		startPos = new Vector2();
>>>>>>> 64e1650beff4a48aa3f8cbcadc562834efb906e2
		String[] start = tokens[1].split(",");
		startPos.x = Integer.parseInt(start[0]);
		startPos.y = Integer.parseInt(start[1]);

		String[] size = tokens[2].split(",");
		int width = Integer.parseInt(size[0]);
		int height = Integer.parseInt(size[1]);

		tilemap = new int[width][height];

		for (int y = 0; y < height; y++)
		{
			String[] row = tokens[y + 3].split(",");
			for (int x = 0; x < tilemap.length; x++)
			{
				this.tilemap[x][height - y - 1] = Integer.parseInt(row[x]);
			}
		}

	}

	@Override
	public Vector2 getStart()
	{
<<<<<<< HEAD
		return null;
=======
		return startPos;
>>>>>>> 64e1650beff4a48aa3f8cbcadc562834efb906e2
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
