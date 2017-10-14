package com.artlessavian.umbrellagame;

import com.artlessavian.umbrellagame.game.ControlContainer;
import com.artlessavian.umbrellagame.game.GameScreen;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Maineroni extends Game
{
	public SpriteBatch batch;
	BitmapFont font;
	ControlContainer control;
	private boolean onComputerDebug;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new GameScreen(this));

		onComputerDebug = Gdx.app.getType().equals(Application.ApplicationType.Desktop);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.3f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (onComputerDebug)
		{
			control.a = Gdx.input.isButtonPressed(Input.Keys.J);
			control.b = Gdx.input.isButtonPressed(Input.Keys.N);
			control.up = Gdx.input.isButtonPressed(Input.Keys.W);
			control.down = Gdx.input.isButtonPressed(Input.Keys.S);
			control.left = Gdx.input.isButtonPressed(Input.Keys.A);
			control.right = Gdx.input.isButtonPressed(Input.Keys.D);
		}

		super.render();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
