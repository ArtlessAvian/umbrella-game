package com.artlessavian.umbrellagame.game.ecs.systems;

import com.artlessavian.umbrellagame.Maineroni;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GUIDrawSystem extends EntitySystem
{
	private final SpriteBatch batch;
	private final BitmapFont font;
	private Player p;

	public GUIDrawSystem(SpriteBatch batch, BitmapFont font, Player p)
	{
		this.batch = batch;
		this.font = font;
		this.p = p;
	}

	@Override
	public void update(float deltaTime)
	{
		batch.setProjectionMatrix(Maineroni.screenSpace.combined);
		font.draw(batch, "Dryness: " + Math.ceil(p.playerC.wetness * 1000)/10 + "%", 50, 400);
	}
}
