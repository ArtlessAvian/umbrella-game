package com.artlessavian.umbrellagame.game.ecs.components;

import com.artlessavian.umbrellagame.game.ControlContainer;
import com.badlogic.ashley.core.Component;

public class ControlComponent implements Component
{
	public ControlContainer control;

	public ControlComponent(ControlContainer cc)
	{
		control = cc;
	}
}
