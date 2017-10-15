package com.artlessavian.umbrellagame.game.ecs.systems;

import com.artlessavian.umbrellagame.game.ecs.components.RemoveMeComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class RemovalSystem extends IteratingSystem
{
	public RemovalSystem()
	{
		super(Family.all(RemoveMeComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime)
	{
		this.getEngine().removeEntity(entity);
	}
}
