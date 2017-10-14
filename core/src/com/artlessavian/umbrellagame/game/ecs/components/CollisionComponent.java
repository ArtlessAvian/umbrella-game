package com.artlessavian.umbrellagame.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class CollisionComponent implements Component
{
	public float sideHeight; // height
	public float width;
	public float feet; // down from center

	public CollisionBehavior collisionBehavior;

	public void getBody(Rectangle r, Vector2 center)
	{
		r.setHeight(sideHeight);
		r.setWidth(width);
		r.setCenter(center);
	}

	public void getFeet(Vector2 v, Vector2 center)
	{
		v.set(center);
		v.y -= feet;
	}

	public static interface CollisionBehavior
	{
		void onFloor();
		void onLeft();
		void onRight();
		void onFallOff();
	}
}
