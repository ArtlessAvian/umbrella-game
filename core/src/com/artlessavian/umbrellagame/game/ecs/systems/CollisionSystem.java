package com.artlessavian.umbrellagame.game.ecs.systems;

import com.artlessavian.umbrellagame.game.Map;
import com.artlessavian.umbrellagame.game.MapInterface;
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
	private MapInterface map;

	public CollisionSystem(MapInterface map)
	{
		super(Family.all(PhysicsComponent.class, CollisionComponent.class).get());
		this.map = map;
	}

	Vector2 temp = new Vector2();
	Vector2 temp2 = new Vector2();
	Rectangle rect = new Rectangle();

	@Override
	protected void processEntity(Entity entity, float deltaTime)
	{
		CollisionComponent collisionC = entity.getComponent(CollisionComponent.class);
		PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);

		PlayerComponent playerC = entity.getComponent(PlayerComponent.class);

		if (physicsC.vel.y > 0)
		{
			collisionC.getHead(temp, physicsC.pos);
			if (map.get((int)(temp.x / 16), (int)(temp.y / 16)).solid)
			{
				physicsC.vel.y = 0;
				physicsC.pos.y = (int)(temp.y / 16f) * 16 - collisionC.feet;
				physicsC.grounded = false;
				collisionC.collisionBehavior.onPineapple();
			}
		}

		if (physicsC.vel.x < 0)
		{
			checkLeft(collisionC, physicsC);
		}
		else if (physicsC.vel.x > 0)
		{
			checkRight(collisionC, physicsC);
		}

		if (!physicsC.grounded)
		{
			if (physicsC.vel.y <= 0)
			{
				collisionC.getFeetLeft(temp, physicsC.pos);
				collisionC.getFeetRight(temp2, physicsC.pos);
				if (map.get((int)(temp.x / 16), (int)(temp.y / 16)).solid)
				{
					physicsC.vel.y = 0;
					physicsC.pos.y = (int)(temp.y / 16f + 1) * 16 + collisionC.feet;
					physicsC.grounded = true;
					collisionC.collisionBehavior.onFloor();
				}
				else if (map.get((int)(temp2.x / 16), (int)(temp2.y / 16)).solid)
				{
					physicsC.vel.y = 0;
					physicsC.pos.y = (int)(temp2.y / 16f + 1) * 16 + collisionC.feet;
					physicsC.grounded = true;
					collisionC.collisionBehavior.onFloor();
				}
			}
		}
		else
		{
			collisionC.getFeetLeft(temp, physicsC.pos);
			collisionC.getFeetRight(temp2, physicsC.pos);
			if (!map.get((int)(temp.x / 16), (int)(temp.y / 16 - 0.0001)).solid)
			{
				if (!map.get((int)(temp2.x / 16), (int)(temp2.y / 16 - 0.0001)).solid)
				{

					physicsC.grounded = false;
					collisionC.collisionBehavior.onFallOff();
				}
			}
		}
	}

	private void checkLeft(CollisionComponent collisionC, PhysicsComponent physicsC)
	{
		collisionC.getBody(rect, physicsC.pos);
		for (int x = (int)(rect.x / 16); x < Math.ceil((rect.x + rect.width)/16); x++)
		{
			for (int y = (int)Math.floor(rect.y / 16); y < Math.ceil((rect.y + rect.height)/16); y++)
			{
				if (map.get(x,y).solid)
				{
					physicsC.pos.x = (x + 1) * 16 + collisionC.width/2f;
					collisionC.collisionBehavior.onLeft();
					physicsC.vel.x = 0;
					return;
				}
			}
		}
	}

	private void checkRight(CollisionComponent collisionC, PhysicsComponent physicsC)
	{
		collisionC.getBody(rect, physicsC.pos);
		for (int x = (int)(rect.x / 16); x < Math.ceil((rect.x + rect.width)/16); x++)
		{
			for (int y = (int)(rect.y / 16); y < Math.ceil((rect.y + rect.height)/16); y++)
			{
				if (map.get(x,y).solid)
				{
					physicsC.pos.x = x * 16 - collisionC.width/2f;
					collisionC.collisionBehavior.onRight();
					physicsC.vel.x = 0;
					return;
				}
			}
		}
	}

}
