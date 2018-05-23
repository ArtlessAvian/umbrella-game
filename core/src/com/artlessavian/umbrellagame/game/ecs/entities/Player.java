package com.artlessavian.umbrellagame.game.ecs.entities;

import com.artlessavian.umbrellagame.game.ControlContainer;
import com.artlessavian.umbrellagame.game.GameScreen;
import com.artlessavian.umbrellagame.game.OffsetRectangle;
import com.artlessavian.umbrellagame.game.ecs.components.*;
import com.artlessavian.umbrellagame.game.playerstates.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity
{
	public final HitboxComponent hitboxC;
	public PhysicsComponent physicsC;
	public CollisionComponent collisionC;
	public ControlComponent controlC;
	public PlayerComponent playerC;
	public SpriteComponent spriteC;
	public StateComponent stateC;
	private GameScreen screen;

	public Player(ControlContainer cc, Vector2 start, GameScreen screen)
	{
		this.screen = screen;
		physicsC = new PhysicsComponent();
		physicsC.pos.set(start);
		physicsC.pos.scl(16);
		physicsC.vel.y = 0;
		physicsC.vel.x = 20;
		physicsC.gravityAcc = 180;
		physicsC.grounded = false;

		collisionC = new CollisionComponent();
		collisionC.feet = 16;
		collisionC.width = 16;
		collisionC.sideHeight = 16;
		collisionC.collisionBehavior = new PlayerCollisionBehavior();

		controlC = new ControlComponent(cc);

		playerC = new PlayerComponent();
		spriteC = new SpriteComponent();
		spriteC.sprite = new Sprite(new Texture("character.png"));
		spriteC.sprite.setSize(64, 64);
		stateC = new StateComponent();
		stateC.state = new FloatState(stateC, this);

		hitboxC = new HitboxComponent();
		hitboxC.behavior = new PlayerHitBehavior();
		hitboxC.hurtbox = new OffsetRectangle(-8, -16, 16, 32);
		hitboxC.team = 0;
		this.add(hitboxC);

		this.add(physicsC);
		this.add(collisionC);
		this.add(controlC);
		this.add(playerC);
		this.add(spriteC);
		this.add(stateC);
	}

	class PlayerCollisionBehavior implements CollisionComponent.CollisionBehavior
	{
		@Override
		public void onFloor()
		{
			hitboxC.hitbox = null;
			hitboxC.cannotHit.clear();
			stateC.state = new StandState(stateC.state.sm, (Player)stateC.state.e);
		}

		@Override
		public void onLeft()
		{
			if (stateC.state.getClass() == WallSlideState.class)
			{
				((WallSlideState)(stateC.state)).validSlide = 0;
				return;
			}

			if (physicsC.grounded && stateC.state.getClass() == WalkState.class)
			{
				stateC.state = new StandState(stateC.state.sm, (Player)stateC.state.e);
			}
			else if (physicsC.vel.y < 0)
			{
				physicsC.facingLeft = false;
				stateC.state = new WallSlideState(stateC.state.sm, (Player)stateC.state.e);
			}
		}

		@Override
		public void onRight()
		{
			if (stateC.state.getClass() == WallSlideState.class)
			{
				((WallSlideState)(stateC.state)).validSlide = 0;
				return;
			}

			if (physicsC.grounded && stateC.state.getClass() == WalkState.class)
			{
				stateC.state = new StandState(stateC.state.sm, (Player)stateC.state.e);
			}
			else if (physicsC.vel.y < 0)
			{
				physicsC.facingLeft = true;
				stateC.state = new WallSlideState(stateC.state.sm, (Player)stateC.state.e);
			}
		}

		@Override
		public void onFallOff()
		{
			hitboxC.hitbox = null;
			hitboxC.cannotHit.clear();
			stateC.state = new JumpState(stateC.state.sm, (Player)stateC.state.e, false);
		}

		@Override
		public void onPineapple()
		{

		}
	}

	private class PlayerHitBehavior implements HitboxComponent.HitBehavior
	{
		@Override
		public void onHit(Entity thisEntity, Entity other)
		{
			hitboxC.cannotHit.add(other);
		}

		@Override
		public void onGetHit(Entity thisEntity, Entity other)
		{
			hitboxC.iframes = 2f;
			playerC.wetness -= 0.15f;

			stateC.state = new JumpState(stateC, (Player)thisEntity, false);
			physicsC.vel.x = 70;
			physicsC.grounded = false;
			physicsC.vel.x = physicsC.vel.x * Math.signum(physicsC.pos.x - other.getComponent(PhysicsComponent.class).pos.x);
			physicsC.vel.y = 100;
		}
	}
}
