package com.artlessavian.umbrellagame.game;

import com.badlogic.gdx.graphics.Texture;

public enum Tile
{
	AIR(false, "grid.png"),
	SOMETHING(true, "badlogic.jpg");

	static float lmao = 0;
	float checkValid = -1;

	private Texture tex;
	String imagePath;
	public boolean solid;

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
