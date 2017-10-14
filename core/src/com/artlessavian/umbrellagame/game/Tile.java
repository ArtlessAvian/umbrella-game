package com.artlessavian.umbrellagame.game;

import com.badlogic.gdx.graphics.Texture;

public enum Tile
{
	SOMETHING(true, "grid.png");

	static float lmao = 0;
	float checkValid = -1;

	private Texture tex;
	String imagePath;
	boolean solid;

	Tile(boolean solid, String imagePath)
	{
		this.solid = solid;
		this.imagePath = imagePath;
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
}
