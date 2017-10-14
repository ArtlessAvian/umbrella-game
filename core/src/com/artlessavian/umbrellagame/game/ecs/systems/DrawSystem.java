package com.artlessavian.umbrellagame.game.ecs.systems;

import com.artlessavian.umbrellagame.game.Map;
import com.artlessavian.umbrellagame.game.Tile;
import com.artlessavian.umbrellagame.game.ecs.components.PhysicsComponent;
import com.artlessavian.umbrellagame.game.ecs.components.SpriteComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DrawSystem extends IteratingSystem
{
	private SpriteBatch batch;
	private Map map;
	private OrthographicCamera camera;

	private Sprite tileFlyweight;

	public DrawSystem(SpriteBatch batch, Map map)
	{
		super(Family.all(SpriteComponent.class).get());
		this.batch = batch;
		this.map = map;
		this.camera = new OrthographicCamera(400, 225);
//		this.camera.zoom = 5f;

		tileFlyweight = new Sprite();
	}

	@Override
	public void update(float deltaTime)
	{
		this.camera.update();
		batch.setProjectionMatrix(this.camera.combined);

		for (int col = 0; col < map.getWidth(); col++)
		{
			for (int row = 0; row < map.getHeight(); row++)
			{
				if (map.get(col, row) == Tile.AIR)
				{
					continue;
				}

				tileFlyweight.setTexture(map.get(col, row).getTex());
				tileFlyweight.setSize(16, 16);
				tileFlyweight.setPosition(col * 16, row * 16);
				tileFlyweight.draw(batch);
			}
		}

		super.update(deltaTime);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime)
	{
		SpriteComponent spriteC = entity.getComponent(SpriteComponent.class);
		PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);

		if (physicsC != null)
		{
			spriteC.sprite.setCenter(physicsC.pos.x, physicsC.pos.y);
		}

		spriteC.sprite.draw(batch);
	}
}
