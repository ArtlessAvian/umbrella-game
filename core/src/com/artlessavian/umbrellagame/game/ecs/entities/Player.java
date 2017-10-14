package com.artlessavian.umbrellagame.game.ecs.entities;

import com.artlessavian.umbrellagame.game.ecs.components.*;
import com.badlogic.ashley.core.Entity;

public class Player extends Entity
{
	PhysicsComponent physicsC;
	CollisionComponent collisionC;
	ControlComponent controlC;
	PlayerComponent playerC;
	SpriteComponent spriteC;
	StateComponent stateC;

	public Player()
	{
		physicsC = new PhysicsComponent();
		physicsC.vel.y = 30;
		physicsC.vel.x = 30;
		physicsC.gravityAcc = 3;
		collisionC = new CollisionComponent();
		controlC = new ControlComponent();
		playerC = new PlayerComponent();
		spriteC = new SpriteComponent();
		stateC = new StateComponent();

		this.add(physicsC);
		this.add(collisionC);
		this.add(controlC);
		this.add(playerC);
		this.add(spriteC);
		this.add(stateC);
	}
}
