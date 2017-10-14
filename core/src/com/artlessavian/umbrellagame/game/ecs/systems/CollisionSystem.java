package com.artlessavian.umbrellagame.game.ecs.systems;

import com.artlessavian.umbrellagame.game.Map;
import com.artlessavian.umbrellagame.game.Tile;
import com.artlessavian.umbrellagame.game.ecs.components.CollisionComponent;
import com.artlessavian.umbrellagame.game.ecs.components.PhysicsComponent;
import com.artlessavian.umbrellagame.game.ecs.components.PlayerComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class CollisionSystem extends IteratingSystem
{
	private Map map;

	public CollisionSystem(Map map)
	{
		super(Family.all(PhysicsComponent.class, CollisionComponent.class).get());
		this.map = map;
	}

	Vector2 temp = new Vector2();
	Rectangle rect = new Rectangle();

	@Override
	protected void processEntity(Entity entity, float deltaTime)
	{
		CollisionComponent collisionC = entity.getComponent(CollisionComponent.class);
		PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);

		PlayerComponent playerC = entity.getComponent(PlayerComponent.class);

		if (physicsC.vel.x < 0)
		{
			checkLeft(collisionC, physicsC);
			checkRight(collisionC, physicsC);
		}
		else
		{
			checkRight(collisionC, physicsC);
			checkLeft(collisionC, physicsC);
		}

		collisionC.getFeet(temp, physicsC.pos);
		if (!physicsC.grounded)
		{
			if (physicsC.vel.y <= 0)
			if (map.get((int)(temp.x / 16), (int)(temp.y / 16)).solid)
			{
				physicsC.vel.y = 0;
				physicsC.pos.y = (int)(temp.y / 16f + 1) * 16 + collisionC.feet;
				physicsC.grounded = true;
				collisionC.collisionBehavior.onFloor();
			}
		}
		else
		{
			if (!map.get((int)(temp.x / 16), (int)(temp.y / 16 - 0.000001)).solid)
			{
				physicsC.grounded = false;
				collisionC.collisionBehavior.onFallOff();
			}
		}
	}

	private void checkLeft(CollisionComponent collisionC, PhysicsComponent physicsC)
	{
//		collisionC.getLeft(rect, physicsC.pos);
//		for (int x = (int)Math.floor(rect.x / 16); x <= Math.ceil((rect.x + rect.width)/16); x++)
//		{
//			for (int y = (int)Math.floor(rect.y / 16); y <= Math.ceil((rect.y + rect.height)/16); y++)
//			{
//				if (map.get(x,y).solid)
//				{
//					physicsC.pos.x = (x + 1) * 16 + collisionC.width/2f;
//					collisionC.getLeft(rect, physicsC.pos);
//					System.out.println("collided left");
//					System.out.println(x + " " + y);
//					return;
//				}
//			}
//		}
	}

	private void checkRight(CollisionComponent collisionC, PhysicsComponent physicsC)
	{
//		collisionC.getRight(rect, physicsC.pos);
//		for (int x = (int)Math.floor(rect.x / 16); x <= Math.ceil((rect.x + rect.width)/16); x++)
//		{
//			for (int y = (int)Math.floor(rect.y / 16); y <= Math.ceil((rect.y + rect.height)/16); y++)
//			{
//				if (map.get(x,y).solid)
//				{
//					physicsC.pos.x = (x) * 16 - collisionC.width/2f;
//					collisionC.getRight(rect, physicsC.pos);
//					System.out.println("collided right");
//					System.out.println(x + " " + y);
//					return;
//				}
//			}
//		}
	}

}
