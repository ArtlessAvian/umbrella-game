package com.artlessavian.umbrellagame.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * SAMPLE
 * ONLY HOLDS INFORMATION
 * No methods, but only a bunch of variables
 */
public class PhysicsComponent implements Component
{
	// Library Vector in two dimensions;
	public Vector2 pos;
	public Vector2 vel;
	// no acceleration, thats done by the system.

	// is true if on ground.
	boolean grounded;

	float accelGravity = 3; // insert magic number here
}
