package com.artlessavian.umbrellagame.game.ecs.systems;

import com.artlessavian.umbrellagame.game.ecs.components.PhysicsComponent;
import com.artlessavian.umbrellagame.game.ecs.components.SpriteComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DrawSystem extends IteratingSystem
{
	private SpriteBatch batch;

	public DrawSystem(SpriteBatch batch)
	{
		super(Family.all(SpriteComponent.class).get());
		this.batch = batch;
	}

	@Override
	public void update(float deltaTime)
	{
		batch.begin();
		super.update(deltaTime);
		batch.end();
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime)
	{
		SpriteComponent spriteC = entity.getComponent(SpriteComponent.class);
		PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);

		if (physicsC != null)
		{
			spriteC.sprite.setCenter(physicsC.pos.x, physicsC.pos.y);
		}

		spriteC.sprite.draw(batch);
	}
}
