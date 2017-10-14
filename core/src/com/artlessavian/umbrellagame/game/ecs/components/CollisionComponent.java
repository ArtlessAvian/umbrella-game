package com.artlessavian.umbrellagame.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class CollisionComponent implements Component
{
	float sideHeight; // height
	float width;
	float feet; // down from center

	public void getLeft(Rectangle r, Vector2 center)
	{
		r.setHeight(sideHeight);
		r.setWidth(width);
		r.setCenter(center);
		r.x -= width/4f;
	}

	public void getRight(Rectangle r, Vector2 center)
	{
		r.setHeight(sideHeight);
		r.setWidth(width);
		r.setCenter(center);
		r.x += width/4f;
	}

	public void getFeet(Vector2 v, Vector2 center)
	{
		v.set(center);
		v.y -= feet;
	}
}
