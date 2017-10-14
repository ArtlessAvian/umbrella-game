//package com.artlessavian.umbrellagame.game.ecs.entities;
//
//import com.artlessavian.umbrellagame.game.State;
//import com.artlessavian.umbrellagame.game.StateMachine;
//import com.artlessavian.umbrellagame.game.ecs.components.CollisionComponent;
//import com.artlessavian.umbrellagame.game.ecs.components.PhysicsComponent;
//import com.artlessavian.umbrellagame.game.ecs.components.SpriteComponent;
//import com.artlessavian.umbrellagame.game.ecs.components.StateComponent;
//import com.badlogic.ashley.core.Engine;
//import com.badlogic.ashley.core.Entity;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.math.Vector2;
//
//public class Poof extends Entity
//{
//	PhysicsComponent physicsC;
//	SpriteComponent spriteC;
//
//	public Poof(Vector2 start, Engine e)
//	{
//		physicsC = new PhysicsComponent();
//		physicsC.pos.set(start);
//		physicsC.gravityAcc = (float)(Math.random() * 40);
//
//		StateComponent stateC = new StateComponent();
//		stateC.state = new PoofState(stateC, this, e);
//
//		spriteC = new SpriteComponent();
//		spriteC.sprite = new Sprite(new Texture("poof.png"));
//		spriteC.sprite.setScale(0.4f);
//
//		add(physicsC);
//		add(stateC);
//		add(spriteC);
//	}
//
//	private class PoofState extends State
//	{
//		private final float rotSpeed;
//
//		public PoofState(StateComponent stateC, Poof poof, Engine e)
//		{
//			super(stateC, poof);
//
//			rotSpeed = (float)(Math.random() * 180);
//		}
//
//		@Override
//		public boolean checkTransition()
//		{
//			return false;
//		}
//
//		@Override
//		public void update(float deltaT)
//		{
//			spriteC.sprite.rotate(rotSpeed * deltaT);
//		}
//	}
//}
