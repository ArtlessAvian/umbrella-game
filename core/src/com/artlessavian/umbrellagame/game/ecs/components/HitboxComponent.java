package com.artlessavian.umbrellagame.game.ecs.components;

import com.artlessavian.umbrellagame.game.OffsetRectangle;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

import java.util.ArrayList;

public class HitboxComponent implements Component
{

	public interface HitBehavior
	{
		void onHit(Entity thisEntity, Entity other);
		void onGetHit(Entity thisEntity, Entity other);
	}

	public HitBehavior behavior;
	public OffsetRectangle hitbox;
	public OffsetRectangle hurtbox;

	public float iframes;
	public ArrayList<Entity> cannotHit;
	public int team = 1;

	public HitboxComponent()
	{
		cannotHit = new ArrayList<Entity>();
	}
}