package com.artlessavian.umbrellagame.game.ecs.components;

import com.badlogic.ashley.core.Component;

public class PlayerComponent implements Component
{
	// something player related
	float hp = 300f; // will be referred to as "dryness"
	// dunno

	public boolean facingLeft;
	public float wetness = 1; // is actually dryness lmao
}
