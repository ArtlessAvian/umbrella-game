package com.artlessavian.umbrellagame.game.ecs.systems;

import com.artlessavian.umbrellagame.game.OffsetRectangle;
import com.artlessavian.umbrellagame.game.ecs.components.HitboxComponent;
import com.artlessavian.umbrellagame.game.ecs.components.PhysicsComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

public class HitboxCollisionSystem extends EntitySystem
{
	private ImmutableArray<Entity> entities;
	private ImmutableArray<Entity> entities2;

	public HitboxCollisionSystem()
	{
	}

	public void addedToEngine(Engine engine)
	{
		// why
		entities = engine.getEntitiesFor(Family.all(HitboxComponent.class).get());
		entities2 = engine.getEntitiesFor(Family.all(HitboxComponent.class, PhysicsComponent.class).get());
	}

	@Override
	public void update(float delta)
	{
		for (Entity e : entities2)
		{
			HitboxComponent hitboxC = e.getComponent(HitboxComponent.class);
			PhysicsComponent physicsC = e.getComponent(PhysicsComponent.class);

//			if (hitboxC.intangible > 0) {hitboxC.intangible--; }

			if (hitboxC.hitbox != null)
			{
				hitboxC.hitbox.setFlip(physicsC.facingLeft);
				hitboxC.hitbox.setPosition(physicsC.pos.x, physicsC.pos.y);
			}

			hitboxC.hurtbox.setFlip(physicsC.facingLeft);
			hitboxC.hurtbox.setPosition(physicsC.pos.x, physicsC.pos.y);
		}


		for (Entity e1 : entities)
		{
			for (Entity e2 : entities2)
			{
				if (e1 == e2) {continue;}
				if (e1.getComponent(HitboxComponent.class).team == e2.getComponent(HitboxComponent.class).team)
				{
					continue;
				}
				if (e2.getComponent(HitboxComponent.class).iframes > 0) {continue;}
				if (e1.getComponent(HitboxComponent.class).hitbox == null) {continue;}
				if (e1.getComponent(HitboxComponent.class).cannotHit.contains(e2))
				{continue;}

				OffsetRectangle hitbox = e1.getComponent(HitboxComponent.class).hitbox;
				OffsetRectangle hurtbox = e2.getComponent(HitboxComponent.class).hurtbox;

				if (hitbox.overlaps(hurtbox))
				{
					e1.getComponent(HitboxComponent.class).behavior.onHit(e1, e2);
					e2.getComponent(HitboxComponent.class).behavior.onGetHit(e2, e1);
				}
			}
		}

		for (Entity e : entities)
		{
			if (e.getComponent(HitboxComponent.class).iframes > 0)
			{
				e.getComponent(HitboxComponent.class).iframes -= delta;
			}
		}
	}
}
