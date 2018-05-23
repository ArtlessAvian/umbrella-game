package com.artlessavian.umbrellagame;

import com.artlessavian.umbrellagame.game.ControlContainer;
import com.artlessavian.umbrellagame.game.GameScreen;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Maineroni extends Game
{
	public static OrthographicCamera screenSpace;

	public SpriteBatch batch;
	public BitmapFont font;
	public ControlContainer control = new ControlContainer();
	private boolean onComputerDebug;

	private Sprite left;
	private Sprite right;
	private Sprite up;
	private Sprite down;
	private Sprite jump;
	private Sprite swing;

	Vector2 temp;
	Vector3 temp2;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new GameScreen(this));

		screenSpace  = new OrthographicCamera(800, 450);
		screenSpace.position.set(400, 225, 0);
		screenSpace.update();

		onComputerDebug = Gdx.app.getType().equals(Application.ApplicationType.Desktop);

		temp = new Vector2();
		temp2 = new Vector3();

		Texture t = new Texture("Grid.png");
		left = new Sprite(t);
		right = new Sprite(t);
		up = new Sprite(t);
		down = new Sprite(t);
		jump = new Sprite(t);
		swing = new Sprite(t);

		left.setScale(1.5f);
		right.setScale(1.5f);
		up.setScale(1.5f);
		down.setScale(1.5f);
		jump.setScale(1.5f);
		swing.setScale(1.5f);

		left.setCenter(50 * 3 - 100, 75 * 3 - 100);
		right.setCenter(100 * 3 - 100, 75 * 3 - 100);
		up.setCenter(75 * 3 - 100, 100 * 3 - 100);
		down.setCenter(75 * 3 - 100, 50 * 3 - 100);

		jump.setCenter(800 - (50 * 3 - 100), 75 * 3 - 100);
		swing.setCenter(800 - (75 * 3 - 100), 50 * 3 - 100);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.1f, 0.2f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (onComputerDebug)
		{
			control.jump = Gdx.input.isKeyPressed(Input.Keys.J);
			control.swing = Gdx.input.isKeyPressed(Input.Keys.N);
			control.up = Gdx.input.isKeyPressed(Input.Keys.W);
			control.down = Gdx.input.isKeyPressed(Input.Keys.S);
			control.left = Gdx.input.isKeyPressed(Input.Keys.A);
			control.right = Gdx.input.isKeyPressed(Input.Keys.D);
		}
		else
		{
			control.swing = false;
			control.jump = false;
			control.up = false;
			control.down = false;
			control.left = false;
			control.right = false;

			jump.setColor(Color.WHITE);
			swing.setColor(Color.WHITE);
			up.setColor(Color.WHITE);
			down.setColor(Color.WHITE);
			left.setColor(Color.WHITE);
			right.setColor(Color.WHITE);

			for (int i = 0; i < 3; i++)
			{
				if (!Gdx.input.isTouched(i)) {continue;}

				temp2.set(Gdx.input.getX(i), Gdx.input.getY(i), 0);
				screenSpace.unproject(temp2);
				temp.set(temp2.x, temp2.y);
				if (jump.getBoundingRectangle().contains(temp))
				{
					jump.setColor(Color.RED);
					control.jump = true;
				}
				if (swing.getBoundingRectangle().contains(temp))
				{
					swing.setColor(Color.RED);
					control.swing = true;
				}
				if (up.getBoundingRectangle().contains(temp))
				{
					up.setColor(Color.RED);
					control.up = true;
				}
				if (down.getBoundingRectangle().contains(temp))
				{
					down.setColor(Color.RED);
					control.down = true;
				}
				if (left.getBoundingRectangle().contains(temp))
				{
					left.setColor(Color.RED);
					control.left = true;
				}
				if (right.getBoundingRectangle().contains(temp))
				{
					right.setColor(Color.RED);
					control.right = true;
				}
			}
		}

		super.render();

		if (!onComputerDebug)
		{

			batch.setProjectionMatrix(screenSpace.combined);
			batch.begin();

			swing.draw(batch);
			jump.draw(batch);
			up.draw(batch);
			down.draw(batch);
			right.draw(batch);
			left.draw(batch);

			batch.end();

		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
