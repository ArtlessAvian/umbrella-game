package com.artlessavian.umbrellagame.game.playerstates;

import com.artlessavian.umbrellagame.game.ecs.components.PhysicsComponent;

public class CommonFuncs
{
	public static void limitSpeedX(PhysicsComponent physicsC, float limit)
	{
		if (physicsC.vel.x > limit) {physicsC.vel.x = limit;}
		if (physicsC.vel.x < -limit) {physicsC.vel.x = -limit;}
	}
	public static void accelX(PhysicsComponent physicsC, boolean right, float accel)
	{
		if (right)
		{
			physicsC.vel.x += accel;
		}
		else
		{
			physicsC.vel.x -= accel;
		}
	}
	public static void deccelX(PhysicsComponent physicsC, float deccel)
	{
		if (Math.abs(physicsC.vel.x) < deccel)
		{
			physicsC.vel.x = 0;
		}
		else
		{
			physicsC.vel.x -= Math.signum(physicsC.vel.x) * deccel;
		}
	}
}
