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
	public Vector2 pos = new Vector2();
	public Vector2 vel = new Vector2();
	// no acceleration, thats done by the system.

	// is true if on ground.
	public boolean grounded = false;
	public boolean facingLeft;

	public float gravityAcc = 300; // insert magic number here
}
