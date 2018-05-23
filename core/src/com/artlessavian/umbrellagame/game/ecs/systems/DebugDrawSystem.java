package com.artlessavian.umbrellagame.game.ecs.systems;

import com.artlessavian.umbrellagame.game.ecs.components.HitboxComponent;
import com.artlessavian.umbrellagame.game.ecs.components.PhysicsComponent;
import com.artlessavian.umbrellagame.game.ecs.components.StateComponent;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DebugDrawSystem extends EntitySystem
{
	private final SpriteBatch batch;
	private final BitmapFont font;
	private final Player p;
	private ImmutableArray<Entity> entities;
	private Sprite sprite;

	public DebugDrawSystem(SpriteBatch batch, BitmapFont font, Player p)
	{
		this.batch = batch;
		this.font = font;
		this.p = p;
		
		this.sprite = new Sprite(new Texture("Grid.png"));
	}

	@Override
	public void addedToEngine(Engine engine)
	{
		entities = engine.getEntitiesFor(Family.all(HitboxComponent.class).get());
	}

	@Override
	public void update(float deltaTime)
	{
		font.draw(batch, p.getComponent(StateComponent.class).state.getClass().getSimpleName(), 0, 48);
		font.draw(batch, p.getComponent(PhysicsComponent.class).grounded + "", 0, 36);
		font.draw(batch, p.getComponent(PhysicsComponent.class).pos + "", 0, 24);
		font.draw(batch, p.getComponent(PhysicsComponent.class).vel + "", 0, 12);

		batch.setProjectionMatrix(DrawSystem.camera.combined);

		for (Entity e : entities)
		{
			HitboxComponent h = e.getComponent(HitboxComponent.class);
			if (h.hitbox != null)
			{
				sprite.setSize(h.hitbox.width, h.hitbox.height);
				sprite.setX(h.hitbox.getX());
				sprite.setY(h.hitbox.getY());
				sprite.draw(batch);
			}
			sprite.setSize(h.hurtbox.width, h.hurtbox.height);
			sprite.setX(h.hurtbox.getX());
			sprite.setY(h.hurtbox.getY());
			sprite.draw(batch);
		}
	}
}
