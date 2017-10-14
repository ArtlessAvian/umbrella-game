package com.artlessavian.umbrellagame.game.ecs.systems;

import com.artlessavian.umbrellagame.game.Map;
import com.artlessavian.umbrellagame.game.MapInterface;
import com.artlessavian.umbrellagame.game.Tile;
import com.artlessavian.umbrellagame.game.ecs.components.PhysicsComponent;
import com.artlessavian.umbrellagame.game.ecs.components.SpriteComponent;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class DrawSystem extends IteratingSystem
{
	private SpriteBatch batch;
	private MapInterface map;
	private Player p;
	private OrthographicCamera camera;

	private Sprite tileFlyweight;

	public DrawSystem(SpriteBatch batch, MapInterface map, Player p)
	{
		super(Family.all(SpriteComponent.class).get());
		this.batch = batch;
		this.map = map;
		this.p = p;
		this.camera = new OrthographicCamera(400, 225);
		this.camera.position.set(map.getStart(), 0);
//		this.camera.zoom = 5f;

		tileFlyweight = new Sprite();
	}

	Vector3 targetCamera = new Vector3();

	@Override
	public void update(float deltaTime)
	{
		camera.position.x = (camera.position.x * 49 + p.physicsC.vel.x + p.physicsC.pos.x) / 50;
		camera.position.y = p.physicsC.pos.y;

		this.camera.update();
		batch.setProjectionMatrix(this.camera.combined);

		for (int col = (int)Math.max(0, (camera.position.x - 300)/16f); col < Math.min((camera.position.x + 300)/16f, map.getWidth()); col++)
		{
			for (int row = (int)Math.max(0, (camera.position.y - 150)/16f); row < Math.min((camera.position.y + 150)/16f, map.getHeight()); row++)
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
