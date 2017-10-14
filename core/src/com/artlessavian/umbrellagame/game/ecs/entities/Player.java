package com.artlessavian.umbrellagame.game.ecs.entities;

import com.artlessavian.umbrellagame.game.ControlContainer;
import com.artlessavian.umbrellagame.game.ecs.components.*;
import com.artlessavian.umbrellagame.game.playerstates.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity
{
	public PhysicsComponent physicsC;
	public CollisionComponent collisionC;
	public ControlComponent controlC;
	public PlayerComponent playerC;
	public SpriteComponent spriteC;
	public StateComponent stateC;

	public Player(ControlContainer cc, Vector2 start)
	{
		physicsC = new PhysicsComponent();
		physicsC.pos.set(start);
		physicsC.pos.scl(16);
		physicsC.vel.y = 0;
		physicsC.vel.x = 100;
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
		spriteC.sprite.setTexture(new Texture("Grid.png"));
		spriteC.sprite.setSize(16, 32);
		stateC = new StateComponent();
		stateC.state = new FloatState(stateC, this);

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
			stateC.state = new StandState(stateC.state.sm, (Player)stateC.state.e);
		}

		@Override
		public void onLeft()
		{
<<<<<<< HEAD
=======
			if (stateC.state.getClass() == WallSlideState.class)
			{
				((WallSlideState)(stateC.state)).validSlide = 0;
				return;
			}

>>>>>>> 64e1650beff4a48aa3f8cbcadc562834efb906e2
			if (physicsC.grounded)
			{
				stateC.state = new StandState(stateC.state.sm, (Player)stateC.state.e);
			}
			else if (physicsC.vel.y < 0)
			{
				playerC.facingLeft = false;
				stateC.state = new WallSlideState(stateC.state.sm, (Player)stateC.state.e);
			}
		}

		@Override
		public void onRight()
		{
<<<<<<< HEAD
=======
			if (stateC.state.getClass() == WallSlideState.class)
			{
				((WallSlideState)(stateC.state)).validSlide = 0;
				return;
			}

>>>>>>> 64e1650beff4a48aa3f8cbcadc562834efb906e2
			if (physicsC.grounded)
			{
				stateC.state = new StandState(stateC.state.sm, (Player)stateC.state.e);
			}
			else if (physicsC.vel.y < 0)
			{
				playerC.facingLeft = true;
				stateC.state = new WallSlideState(stateC.state.sm, (Player)stateC.state.e);
			}
		}

		@Override
		public void onFallOff()
		{
			stateC.state = new JumpState(stateC.state.sm, (Player)stateC.state.e, false);
		}

		@Override
		public void onPineapple()
		{

		}
	}
}
