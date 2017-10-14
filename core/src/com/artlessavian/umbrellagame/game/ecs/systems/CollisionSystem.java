package com.artlessavian.umbrellagame.game.ecs.systems;

import com.artlessavian.umbrellagame.game.Tile;
import com.artlessavian.umbrellagame.game.ecs.components.CollisionComponent;
import com.artlessavian.umbrellagame.game.ecs.components.PhysicsComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class CollisionSystem extends IteratingSystem
{
	public CollisionSystem(Tile[][] tilemap)
	{
		super(Family.all(PhysicsComponent.class, CollisionComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime)
	{

	}
}
