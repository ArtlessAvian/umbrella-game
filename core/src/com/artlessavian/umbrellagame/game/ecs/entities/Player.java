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
		physicsC.vel.y = 3;
		physicsC.vel.x = 3;
		physicsC.gravityAcc = 3;
		collisionC = new CollisionComponent();
		controlC = new ControlComponent();
		playerC = new PlayerComponent();
		spriteC = new SpriteComponent();
		stateC = new StateComponent();
	}
}
