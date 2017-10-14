package com.artlessavian.umbrellagame.game.ecs.entities;

import com.artlessavian.umbrellagame.game.OffsetRectangle;
import com.artlessavian.umbrellagame.game.State;
import com.artlessavian.umbrellagame.game.StateMachine;
import com.artlessavian.umbrellagame.game.ecs.components.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Froge extends Entity
{
	private final HitboxComponent hitboxC;
	PhysicsComponent physicsC;
	SpriteComponent spriteC;

	public Froge(Vector2 start, Player p)
	{
		physicsC = new PhysicsComponent();
		physicsC.pos.set(start);
		physicsC.gravityAcc = 300;

		CollisionComponent collisionC = new CollisionComponent();
		collisionC.collisionBehavior = new FrogeCollisionBehavior();
		collisionC.feet = 5;
		collisionC.width = 5;
		collisionC.sideHeight = 7;

		StateComponent stateC = new StateComponent();
		stateC.state = new FrogeState(stateC, this, p);

		spriteC = new SpriteComponent();
		spriteC.sprite = new Sprite(new Texture("Grid.png"));
		spriteC.sprite.setSize(10, 10);

		hitboxC = new HitboxComponent();
		hitboxC.behavior = new FrogeHitBehavior();
		hitboxC.hurtbox = new OffsetRectangle(-5, -5, 10, 10);
		hitboxC.hitbox = new OffsetRectangle(-5, -5, 10, 10);
		this.add(hitboxC);

		add(physicsC);
		add(stateC);
		add(spriteC);
		add(collisionC);
	}

	private class FrogeState extends State<Froge>
	{
		private Player p;

		public FrogeState(StateMachine sm, Froge froge, Player p)
		{
			super(sm, froge);
			this.p = p;
		}

		@Override
		public boolean checkTransition()
		{
			return false;
		}

		float counter = 0;

		@Override
		public void update(float deltaT)
		{
//			counter += deltaT;
//			if (counter > 1.1f && physicsC.grounded)
//			{
//				counter -= 1.1;
//
//				physicsC.vel.y = (float)(Math.random() * 100 + 50);
//				physicsC.vel.x = 50 / (physicsC.vel.y * 2 / physicsC.gravityAcc);
//				physicsC.grounded = false;
//				if (p.physicsC.pos.x < e.physicsC.pos.x)
//				{
//					physicsC.vel.x *= -1;
//				}
//			}
		}
	}

	private class FrogeCollisionBehavior implements CollisionComponent.CollisionBehavior
	{
		@Override
		public void onFloor()
		{
			physicsC.vel.x = 0;
		}

		@Override
		public void onLeft()
		{

		}

		@Override
		public void onRight()
		{

		}

		@Override
		public void onFallOff()
		{

		}

		@Override
		public void onPineapple()
		{

		}
	}

	private class FrogeHitBehavior implements HitboxComponent.HitBehavior
	{
		int counter = 3;

		@Override
		public void onHit(Entity thisEntity, Entity other)
		{

		}

		@Override
		public void onGetHit(Entity thisEntity, Entity other)
		{
			counter--;
			if (counter == 0)
			{
				thisEntity.add(new RemoveMeComponent());
				return;
			}

			PhysicsComponent component = other.getComponent(PhysicsComponent.class);
			physicsC.vel.x = 70;
			physicsC.grounded = false;
			physicsC.vel.x = physicsC.vel.x * Math.signum(physicsC.pos.x - component.pos.x);
			physicsC.vel.y = 200;
		}
	}
}