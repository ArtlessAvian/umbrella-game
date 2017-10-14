//package com.artlessavian.umbrellagame.game.ecs.entities;
//
//import com.artlessavian.umbrellagame.game.State;
//import com.artlessavian.umbrellagame.game.StateMachine;
//import com.artlessavian.umbrellagame.game.ecs.components.PhysicsComponent;
//import com.artlessavian.umbrellagame.game.ecs.components.SpriteComponent;
//import com.artlessavian.umbrellagame.game.ecs.components.StateComponent;
//import com.badlogic.ashley.core.Entity;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.math.Vector2;
//
//public class BugThing extends Entity
//{
//	PhysicsComponent physicsC;
//	SpriteComponent spriteC;
//
//	public BugThing(Vector2 start, Player p)
//	{
//		physicsC = new PhysicsComponent();
//		physicsC.pos.set(start);
//		physicsC.gravityAcc = 0;
//
//		StateComponent stateC = new StateComponent();
//		stateC.state = new BugState(stateC, this, p);
//
//		spriteC = new SpriteComponent();
//		spriteC.sprite = new Sprite(new Texture("Grid.png"));
//		spriteC.sprite.setSize(10, 10);
//
//		add(physicsC);
//		add(stateC);
//		add(spriteC);
//	}
//
//	private class BugState extends State<BugThing>
//	{
//		private Player p;
//
//		public BugState(StateMachine sm, BugThing bugThing, Player p)
//		{
//			super(sm, bugThing);
//			this.p = p;
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
//			e.physicsC.vel.x += (p.physicsC.pos.x - e.physicsC.pos.x) * deltaT;
//			e.physicsC.vel.y += (p.physicsC.pos.y - e.physicsC.pos.y) * deltaT;
//			e.physicsC.vel.clamp(0, 190);
//		}
//	}
//}
