package com.artlessavian.umbrellagame.game.ecs.systems;

import com.artlessavian.umbrellagame.game.ecs.components.PhysicsComponent;
import com.artlessavian.umbrellagame.game.ecs.components.StateComponent;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DebugDrawSystem extends EntitySystem
{
	private final SpriteBatch batch;
	private final BitmapFont font;
	private final Player p;

	public DebugDrawSystem(SpriteBatch batch, BitmapFont font, Player p)
	{
		this.batch = batch;
		this.font = font;
		this.p = p;
	}

	@Override
	public void update(float deltaTime)
	{
		font.draw(batch, p.getComponent(StateComponent.class).state.getClass().getSimpleName(), 0, 48);
		font.draw(batch, p.getComponent(PhysicsComponent.class).grounded + "", 0, 36);
		font.draw(batch, p.getComponent(PhysicsComponent.class).pos + "", 0, 24);
		font.draw(batch, p.getComponent(PhysicsComponent.class).vel + "", 0, 12);
	}
}
