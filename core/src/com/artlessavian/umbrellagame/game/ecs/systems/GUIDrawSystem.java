package com.artlessavian.umbrellagame.game.ecs.systems;

import com.artlessavian.umbrellagame.game.ecs.components.StateComponent;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;

public class GUIDrawSystem extends EntitySystem
{
	private final SpriteBatch batch;
	private final BitmapFont font;
	private Player p;

	private OrthographicCamera screenSpace = new OrthographicCamera(800, 450);

	public GUIDrawSystem(SpriteBatch batch, BitmapFont font, Player p)
	{
		this.batch = batch;
		this.font = font;
		this.p = p;

		screenSpace.position.set(400, 225, 0);
		screenSpace.update();
	}

	@Override
	public void update(float deltaTime)
	{
		batch.setProjectionMatrix(screenSpace.combined);
		font.draw(batch, "Wetness: " + Math.ceil(p.playerC.wetness * 1000)/10 + "%", 50, 400);
	}
}
