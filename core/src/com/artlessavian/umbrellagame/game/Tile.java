package com.artlessavian.umbrellagame.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public enum Tile
{
	AIR(false, "grid.png"),
	SOMETHING(true, "badlogic.jpg"),
	GRASS(true, "tiles.png", 0, 0, 2, 2),
	DIRT(true, "tiles.png", 1, 0, 2, 2),
	STONE(true, "tiles.png", 0, 1, 2, 2),
	STONE2(true, "tiles.png", 1, 1, 2, 2);

	static float lmao = 0;
	float checkValid = -1;

	private Texture tex;
	String imagePath;
	public boolean solid;

	float x = 0;
	float y = 0;
	float xMax = 1;
	float yMax = 1;

	Tile(boolean solid, String imagePath)
	{
		this.solid = solid;
		this.imagePath = imagePath;
	}

	Tile(boolean solid, String imagePath, int x, int y, int xMax, int yMax)
	{
		this(solid, imagePath);
		this.x = x;
		this.y = y;
		this.xMax = xMax;
		this.yMax = yMax;
	}

	public Texture getTex()
	{
		if (lmao != checkValid)
		{
			checkValid = lmao;
			tex = new Texture(imagePath);
		}
		return tex;
	}

	public void setUV(Sprite sprite)
	{
		sprite.setU(x/xMax);
		sprite.setU2((x+1)/xMax);
		sprite.setV(y/yMax);
		sprite.setV2((y+1)/yMax);
	}
}
