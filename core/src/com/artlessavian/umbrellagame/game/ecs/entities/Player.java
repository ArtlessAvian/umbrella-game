package com.artlessavian.umbrellagame.game.ecs.entities;

import com.artlessavian.umbrellagame.game.ControlContainer;
import com.artlessavian.umbrellagame.game.ecs.components.*;
import com.artlessavian.umbrellagame.game.playerstates.JumpState;
import com.artlessavian.umbrellagame.game.playerstates.StandState;
import com.artlessavian.umbrellagame.game.playerstates.WalkState;
import com.artlessavian.umbrellagame.game.playerstates.WallSlideState;
import com.badlogic.ashley.core.Entity;

public class Player extends Entity
{
	public PhysicsComponent physicsC;
	public CollisionComponent collisionC;
	public ControlComponent controlC;
	public PlayerComponent playerC;
	public SpriteComponent spriteC;
	public StateComponent stateC;

	public Player(ControlContainer cc)
	{
		physicsC = new PhysicsComponent();
		physicsC.pos.x = 48;
		physicsC.pos.y = 48;
		physicsC.vel.y = 0;
		physicsC.vel.x = 0;
		physicsC.gravityAcc = 60;
		physicsC.grounded = false;

		collisionC = new CollisionComponent();
		collisionC.feet = 16;
		collisionC.width = 16;
		collisionC.sideHeight = 30;
		collisionC.collisionBehavior = new PlayerCollisionBehavior();

		controlC = new ControlComponent(cc);

		playerC = new PlayerComponent();
		spriteC = new SpriteComponent();
		spriteC.sprite.setSize(16, 32);
		stateC = new StateComponent();
		stateC.state = new WalkState(stateC, this);

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
			if (physicsC.grounded)
			{
				stateC.state = new StandState(stateC.state.sm, (Player)stateC.state.e);
			}
			else
			{
				stateC.state = new WallSlideState(stateC.state.sm, (Player)stateC.state.e);
			}
		}

		@Override
		public void onRight()
		{
			if (physicsC.grounded)
			{
				stateC.state = new StandState(stateC.state.sm, (Player)stateC.state.e);
			}
			else
			{
				stateC.state = new WallSlideState(stateC.state.sm, (Player)stateC.state.e);
			}
		}

		@Override
		public void onFallOff()
		{
			stateC.state = new JumpState(stateC.state.sm, (Player)stateC.state.e, false);
		}
	}
}
