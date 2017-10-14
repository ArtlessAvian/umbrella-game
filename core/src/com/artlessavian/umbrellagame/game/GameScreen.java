package com.artlessavian.umbrellagame.game;

import com.artlessavian.umbrellagame.Maineroni;
import com.artlessavian.umbrellagame.game.ecs.components.PhysicsComponent;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.artlessavian.umbrellagame.game.ecs.systems.CollisionSystem;
import com.artlessavian.umbrellagame.game.ecs.systems.DrawSystem;
import com.artlessavian.umbrellagame.game.ecs.systems.PhysicsSystem;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Screen;

import static com.artlessavian.umbrellagame.game.Tile.SOMETHING;

public class GameScreen implements Screen
{
	private Maineroni main;
	private Engine engine;

	private Map map;
	DrawSystem drawSystem;

	Player p;

	public GameScreen(Maineroni main)
	{
		this.main = main;
		this.engine = new Engine();

		map = new Map("levels/1.txt");

		engine.addSystem(new PhysicsSystem());
		engine.addSystem(new CollisionSystem(map));
//		engine.addSystem(new PhysicsSystem());
		drawSystem = new DrawSystem(main.batch, map);
		drawSystem.setProcessing(false);
		engine.addSystem(drawSystem);

		p = new Player();
		engine.addEntity(p);
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
