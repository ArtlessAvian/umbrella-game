package com.artlessavian.umbrellagame.game.ecs.entities;

import com.artlessavian.umbrellagame.game.ecs.components.*;
import com.badlogic.ashley.core.Entity;

public class Player extends Entity
{
	public PhysicsComponent physicsC;
	public CollisionComponent collisionC;
	public ControlComponent controlC;
	public PlayerComponent playerC;
	public SpriteComponent spriteC;
	public StateComponent stateC;

	public Player()
	{
		physicsC = new PhysicsComponent();
		physicsC.pos.x = 48;
		physicsC.pos.y = 48;
		physicsC.vel.y = 0;
		physicsC.vel.x = 0;
		physicsC.gravityAcc = 6;
		collisionC = new CollisionComponent();
		collisionC.feet = 16;
		collisionC.width = 16;
		collisionC.sideHeight = 30;

		controlC = new ControlComponent();
		playerC = new PlayerComponent();
		spriteC = new SpriteComponent();
		spriteC.sprite.setSize(16, 32);
		stateC = new StateComponent();

		this.add(physicsC);
		this.add(collisionC);
		this.add(controlC);
		this.add(playerC);
		this.add(spriteC);
		this.add(stateC);
	}
}
