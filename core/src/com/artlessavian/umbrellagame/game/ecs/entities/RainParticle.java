<<<<<<< HEAD
package com.artlessavian.umbrellagame.game.ecs.entities;

import com.artlessavian.umbrellagame.game.ecs.components.CollisionComponent;
import com.artlessavian.umbrellagame.game.ecs.components.PhysicsComponent;
import com.artlessavian.umbrellagame.game.ecs.components.SpriteComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class RainParticle extends Entity
{
	private static Texture tex;
	private Engine e;

	public RainParticle(Vector2 start, float angle, Engine e)
	{
		this.e = e;
		PhysicsComponent physicsC = new PhysicsComponent();
		physicsC.pos.set(start);
		physicsC.pos.add((float)(Math.random() * 800 - 300), (float)(300 + Math.random() * 300));
		physicsC.vel.y = -300;
		physicsC.gravityAcc = 0;

		CollisionComponent collisionC = new CollisionComponent();
		collisionC.collisionBehavior = new RainParticleCollisionBehavior(this);
		collisionC.sideHeight = 3;
		collisionC.feet = 1.5f;
		collisionC.width = 3;


		SpriteComponent spriteC = new SpriteComponent();
		if (tex == null)
		{
			tex = new Texture("grid.png");
		}
		spriteC.sprite.setTexture(tex);
		spriteC.sprite.setSize(3, 3);

		add(physicsC);
		add(collisionC);
		add(spriteC);
	}

	private class RainParticleCollisionBehavior implements CollisionComponent.CollisionBehavior
	{
		private RainParticle rp;

		RainParticleCollisionBehavior(RainParticle rp)
		{
			this.rp = rp;
		}

		@Override
		public void onFloor()
		{
			e.removeEntity(rp);
		}

		@Override
		public void onLeft()
		{
			onFloor();
		}

		@Override
		public void onRight()
		{
			onFloor();
		}

		@Override
		public void onFallOff()
		{
			onFloor();
		}

		@Override
		public void onPineapple()
		{
			onFloor();
		}
	}
}
=======
//package com.artlessavian.umbrellagame.game.ecs.entities;
//
//import com.artlessavian.umbrellagame.game.ecs.components.CollisionComponent;
//import com.artlessavian.umbrellagame.game.ecs.components.PhysicsComponent;
//import com.artlessavian.umbrellagame.game.ecs.components.SpriteComponent;
//import com.badlogic.ashley.core.Engine;
//import com.badlogic.ashley.core.Entity;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.math.Vector2;
//
//public class RainParticle extends Entity
//{
//	private static Texture tex;
//	private Engine e;
//
//	public RainParticle(Vector2 start, float angle, Engine e)
//	{
//		this.e = e;
//		PhysicsComponent physicsC = new PhysicsComponent();
//		physicsC.pos.set(start);
//		physicsC.pos.add((float)(Math.random() * 800 - 300), (float)(300 + Math.random() * 300));
//		physicsC.vel.y = -300;
//		physicsC.gravityAcc = 0;
//
//		CollisionComponent collisionC = new CollisionComponent();
//		collisionC.collisionBehavior = new RainParticleCollisionBehavior(this);
//		collisionC.sideHeight = 3;
//		collisionC.feet = 1.5f;
//		collisionC.width = 3;
//
//
//		SpriteComponent spriteC = new SpriteComponent();
//		if (tex == null)
//		{
//			tex = new Texture("rain.png");
//		}
//		spriteC.sprite.setTexture(tex);
//		spriteC.sprite.setSize(3, 3);
//
//		add(physicsC);
//		add(collisionC);
//		add(spriteC);
//	}
//
//	private class RainParticleCollisionBehavior implements CollisionComponent.CollisionBehavior
//	{
//		private RainParticle rp;
//
//		RainParticleCollisionBehavior(RainParticle rp)
//		{
//			this.rp = rp;
//		}
//
//		@Override
//		public void onFloor()
//		{
//			e.removeEntity(rp);
//		}
//
//		@Override
//		public void onLeft()
//		{
//			onFloor();
//		}
//
//		@Override
//		public void onRight()
//		{
//			onFloor();
//		}
//
//		@Override
//		public void onFallOff()
//		{
//			onFloor();
//		}
//
//		@Override
//		public void onPineapple()
//		{
//			onFloor();
//		}
//	}
//}
>>>>>>> 64e1650beff4a48aa3f8cbcadc562834efb906e2
