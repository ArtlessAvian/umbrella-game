package com.artlessavian.umbrellagame.game.ecs.systems;

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
	private OrthographicCamera camera;
	private Tile[][] tilemap;

	private Sprite tileFlyweight;

	public DrawSystem(SpriteBatch batch, Tile[][] tilemap)
	{
		super(Family.all(SpriteComponent.class).get());
		this.batch = batch;
		this.camera = new OrthographicCamera(400, 225);
		this.tilemap = tilemap;

		tileFlyweight = new Sprite();
	}

	@Override
	public void update(float deltaTime)
	{
		this.camera.update();
		batch.setProjectionMatrix(this.camera.combined);

		batch.begin();

		for (int row = 0; row < tilemap.length; row++)
		{
			for (int col = 0; col < tilemap[0].length; col++)
			{
//				tileFlyweight.setTexture(Tile.SOMETHING.getTex());
//				tileFlyweight.setSize(16, 16);
//				tileFlyweight.setPosition(row * 16, col * 16);
//				tileFlyweight.draw(batch);
			}
		}

		super.update(deltaTime);
		batch.end();
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
