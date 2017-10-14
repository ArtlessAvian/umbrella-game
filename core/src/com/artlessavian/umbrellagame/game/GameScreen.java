package com.artlessavian.umbrellagame.game;

import com.artlessavian.umbrellagame.Maineroni;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.artlessavian.umbrellagame.game.ecs.systems.CollisionSystem;
import com.artlessavian.umbrellagame.game.ecs.systems.DrawSystem;
import com.artlessavian.umbrellagame.game.ecs.systems.PhysicsSystem;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen
{
	private Maineroni main;
	private Engine engine;

	private Tile[][] tilemap;

	DrawSystem drawSystem;

	public GameScreen(Maineroni main)
	{
		this.main = main;
		this.engine = new Engine();

		tilemap = new Tile[3][3];

		engine.addSystem(new PhysicsSystem());
		engine.addSystem(new CollisionSystem(tilemap));
//		engine.addSystem(new PhysicsSystem());
		drawSystem = new DrawSystem(main.batch, tilemap);
		drawSystem.setProcessing(false);
		engine.addSystem(drawSystem);

		engine.addEntity(new Player());
	}

	@Override
	public void show()
	{

	}

	float updateRate = 1/60f;
	float rollover = 0;

	@Override
	public void render(float delta)
	{
		rollover += delta;
		for (; rollover > updateRate; rollover -= updateRate)
		{
			engine.update(updateRate);
		}
		drawSystem.update(0);
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
