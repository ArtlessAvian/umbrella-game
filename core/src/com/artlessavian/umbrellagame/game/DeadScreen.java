package com.artlessavian.umbrellagame.game;

import com.artlessavian.umbrellagame.Maineroni;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import sun.applet.Main;

public class DeadScreen implements Screen
{
	private Maineroni main;
	private GameScreen screen;

	private BitmapFont font2;
	private Player p;

	DeadScreen(Maineroni main, GameScreen screen, Player p)
	{
		this.main = main;
		this.screen = screen;
		this.p = p;

		font2 = new BitmapFont();
	}

	@Override
	public void show()
	{

	}

	float timeIn = 0;

	@Override
	public void render(float delta)
	{
		timeIn += delta;

		main.batch.begin();
		screen.drawSystem.update(0);

		main.batch.setProjectionMatrix(Maineroni.screenSpace.combined);
		font2.getData().setScale(4);
		font2.draw(main.batch, "You Died", 400, 400);
		font2.getData().setScale(2);
		font2.draw(main.batch, "You are:", 200, 350);
		font2.draw(main.batch, "" + (p.playerC.furthestRight / 16f - 15), 200, 325);
		font2.draw(main.batch, "meters closer to home!", 200, 300);

		if (timeIn > 4)
		{
			font2.getData().setScale(1);
			font2.draw(main.batch, "press r or tap to retry", 200, 250);

			if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.R))
			{
				main.setScreen(new GameScreen(main));
			}
		}

		main.batch.end();
	}

	@Override
	public void resize(int width, int height)
	{

	}

	@Override
	public void pause()
	{

	}

	@Override
	public void resume()
	{

	}

	@Override
	public void hide()
	{

	}

	@Override
	public void dispose()
	{

	}
}
