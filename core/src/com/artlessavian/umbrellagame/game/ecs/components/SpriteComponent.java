package com.artlessavian.umbrellagame.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent implements Component
{
	public Sprite sprite;

	public void fromSheet(float x, float y, int xMax, int yMax)
	{
		sprite.setU(x/xMax);
		sprite.setU2((x+1)/xMax);
		sprite.setV(y/yMax);
		sprite.setV2((y+1)/yMax);
	}
}
