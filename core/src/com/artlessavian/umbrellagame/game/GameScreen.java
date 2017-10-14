package com.artlessavian.umbrellagame.game;

import com.artlessavian.umbrellagame.Maineroni;
import com.artlessavian.umbrellagame.game.ecs.components.PhysicsComponent;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.artlessavian.umbrellagame.game.ecs.systems.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

import static com.artlessavian.umbrellagame.game.Tile.SOMETHING;

public class GameScreen implements Screen
{
	private Maineroni main;
	private Engine engine;

	private MapInterface map;

	DrawSystem drawSystem;
	GUIDrawSystem guiDrawSystem;
	DebugDrawSystem debugDrawSystem;

	Player p;

	public GameScreen(Maineroni main)
	{
		this.main = main;
		this.engine = new Engine();

		map = new Map("levels/1.txt");
//		map = new AutoMap();

		engine.addSystem(new StateSystem());
		engine.addSystem(new PhysicsSystem());
		engine.addSystem(new CollisionSystem(map));

		p = new Player(main.control, map.getStart());
		engine.addEntity(p);

		drawSystem = new DrawSystem(main.batch, map, p);
		drawSystem.setProcessing(false);
		engine.addSystem(drawSystem);

		guiDrawSystem = new GUIDrawSystem(main.batch, main.font, p);
		guiDrawSystem.setProcessing(false);
		engine.addSystem(guiDrawSystem);

		debugDrawSystem = new DebugDrawSystem(main.batch, main.font, p);
		debugDrawSystem.setProcessing(false);
		engine.addSystem(debugDrawSystem);
	}

	@Override
	public void show()
	{

	}

	private boolean timeStop;
	float updateRate = 1/180f;
	float rollover = 0;

	@Override
	public void render(float delta)
	{
		if (Gdx.input.isKeyJustPressed(Input.Keys.MINUS)) {timeStop = !timeStop;}
		if (!timeStop)
		{
			rollover += delta;
		}
		else
		{
			if (Gdx.input.isKeyJustPressed(Input.Keys.EQUALS))
			{
				rollover += updateRate;
			}
		}

		for (; rollover > updateRate; rollover -= updateRate)
		{
			engine.update(updateRate);
		}

		main.batch.begin();
		drawSystem.update(0);
		guiDrawSystem.update(0);
		debugDrawSystem.update(0);
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
