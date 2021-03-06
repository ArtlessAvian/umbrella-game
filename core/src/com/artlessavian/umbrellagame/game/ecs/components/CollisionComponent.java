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

	public void getBodyLeft(Rectangle r, Vector2 center)
	{
		r.setHeight(sideHeight);
		r.setWidth(width/2f);
		r.setCenter(center);
		r.x -= width/4f;
	}

	public void getBodyRight(Rectangle r, Vector2 center)
	{
		r.setHeight(sideHeight);
		r.setWidth(width/2f);
		r.setCenter(center);
		r.x += width/4f;
	}

	public void getFeetLeft(Vector2 v, Vector2 center)
	{
		v.set(center);
		v.y -= feet;
		v.x -= width/4f;
	}

	public void getFeetRight(Vector2 v, Vector2 center)
	{
		v.set(center);
		v.y -= feet;
		v.x += width/4f;
	}

	public void getHead(Vector2 v, Vector2 center)
	{
		v.set(center);
		v.y += feet;
	}


	public static interface CollisionBehavior
	{
		void onFloor();
		void onLeft();
		void onRight();
		void onFallOff();
		void onPineapple();
	}
}
