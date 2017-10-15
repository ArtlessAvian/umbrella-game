package com.artlessavian.umbrellagame.game.ecs.systems;

import com.artlessavian.umbrellagame.game.AutoMap;
import com.artlessavian.umbrellagame.game.ecs.entities.Froge;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.Vector2;

public class PVESystem extends IntervalSystem
{
	private AutoMap map;
	private Player p;

	public PVESystem(AutoMap map, Player p)
	{
		super(2);
		this.map = map;
		this.p = p;
	}

	Vector2 temp = new Vector2();

	@Override
	protected void updateInterval()
	{
//		temp.x = (int)p.physicsC.pos.x;
//		temp.y = map.mainTerrain(temp.x);
		temp.x = p.physicsC.pos.x + 450;
		temp.y = 16 * map.mainTerrain(temp.x / 16) + 16;
//		temp.y = p.physicsC.pos.y;
//		getEngine().addEntity(new BugThing(temp, p));
		getEngine().addEntity(new Froge(temp, p));
	}
}
