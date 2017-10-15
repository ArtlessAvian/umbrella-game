package com.artlessavian.umbrellagame.game.ecs.systems;

import com.artlessavian.umbrellagame.Maineroni;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GUIDrawSystem extends EntitySystem
{
	private final SpriteBatch batch;
	private final BitmapFont font;
	private Player p;
	Sprite sprite;

	public GUIDrawSystem(SpriteBatch batch, BitmapFont font, Player p)
	{
		this.batch = batch;
		this.font = font;
		this.p = p;
		this.sprite = new Sprite(new Texture("Grid.png"));
		sprite.setCenter(10,10);
	}

	@Override
	public void update(float deltaTime)
	{
		batch.setProjectionMatrix(Maineroni.screenSpace.combined);
		font.draw(batch, Gdx.graphics.getFramesPerSecond() + "", 0, 440);
		font.draw(batch, "Dryness: " + Math.ceil(p.playerC.wetness * 1000)/10 + "%", 50, 400);
		sprite.setScale(p.playerC.wetness * 2, 0.3f);
		sprite.setX(70);
		sprite.setY(340);
		sprite.draw(batch);

		if (p.playerC.wetness <= 0)
		{
			font.draw(batch, "you are dead", 400, 200);
		}
	}
}
