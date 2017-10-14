package com.artlessavian.umbrellagame.game.playerstates;

import com.artlessavian.umbrellagame.game.ecs.components.PhysicsComponent;
import com.artlessavian.umbrellagame.game.ecs.components.PlayerComponent;

public class CommonFuncs
{

	public static void accelX(PhysicsComponent physicsC, boolean right, float accel, float limit)
	{
		if (right)
		{
			if (physicsC.vel.x >= limit)
			{
				return;
			}
			physicsC.vel.x += accel;
			if (physicsC.vel.x >= limit)
			{
				physicsC.vel.x = limit;
			}
		}
		else
		{
			if (physicsC.vel.x <= -limit)
			{
				return;
			}
			physicsC.vel.x -= accel;
			if (physicsC.vel.x <= -limit)
			{
				physicsC.vel.x = -limit;
			}
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
	public static void editWet(PlayerComponent playerC, float scaling, float time)
	{
		playerC.wetness += scaling * time;
		if (playerC.wetness > 1)
		{
			playerC.wetness = 1;
		}
		if (playerC.wetness < 0)
		{
			playerC.wetness = 0xDEADDEAD;
		}
	}
}
