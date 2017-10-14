package com.artlessavian.umbrellagame.game.ecs.systems;

import com.artlessavian.umbrellagame.game.ecs.components.StateComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class StateSystem extends IteratingSystem
{
	public StateSystem()
	{
		super(Family.all(StateComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime)
	{
		StateComponent stateC = entity.getComponent(StateComponent.class);

		boolean changed = true;
		for (int i = 0; i < 3 && changed; i++)
		{
			changed = stateC.state.checkTransition();
		}

		stateC.state.update(deltaTime);
	}
}
