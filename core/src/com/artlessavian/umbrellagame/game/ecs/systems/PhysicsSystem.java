package com.artlessavian.umbrellagame.game.ecs.systems;

import com.artlessavian.umbrellagame.game.ecs.components.PhysicsComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class PhysicsSystem extends IteratingSystem
{
	public PhysicsSystem()
	{
		super(Family.all(PhysicsComponent.class).get());
	}

	/**
	 * Make an entity do projectile motion
	 * @param entity
	 * @param deltaTime
	 */
	@Override
	protected void processEntity(Entity entity, float deltaTime)
	{
		PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);

		physicsC.pos.x += physicsC.vel.x * deltaTime;
		physicsC.pos.y += physicsC.vel.y * deltaTime;
		physicsC.pos.y -= 1/2f * physicsC.gravityAcc * deltaTime * deltaTime;
		physicsC.vel.y -= physicsC.gravityAcc * deltaTime;
	}
}
