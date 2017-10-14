package com.artlessavian.umbrellagame.game.playerstates;

import com.artlessavian.umbrellagame.game.State;
import com.artlessavian.umbrellagame.game.StateMachine;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;

public class JumpState extends State<Player>
{
	final float MAX_AIR_SPEED = 80;
	final float AIR_ACCEL = 9;

	private boolean doJump;
	
	public JumpState(StateMachine sm, Player player, boolean doJump)
	{
		super(sm, player);
		this.doJump = doJump;
		e.physicsC.grounded = false;
		e.physicsC.gravityAcc = 180;
	}

	@Override
	public void enter()
	{

	}

	@Override
	public void exit()
	{

	}

	@Override
	public boolean checkTransition()
	{
		return false;
	}

	@Override
	public void update(float deltaT)
	{
		if (doJump)
		{
			e.physicsC.vel.y = 150;
			doJump = false;
		}

		if (!e.controlC.control.a || e.physicsC.vel.y <= 0)
		{
			// goto float
		}

		if (e.controlC.control.right != e.controlC.control.left)
		{
			if (e.controlC.control.right)
			{
				e.physicsC.vel.x += AIR_ACCEL;
			}
			else
			{
				e.physicsC.vel.x -= AIR_ACCEL;
			}
			if (e.physicsC.vel.x > MAX_AIR_SPEED) {e.physicsC.vel.x = MAX_AIR_SPEED;}
			if (e.physicsC.vel.x < -MAX_AIR_SPEED) {e.physicsC.vel.x = -MAX_AIR_SPEED;}
		}
		else
		{
			e.physicsC.vel.x -= Math.signum(e.physicsC.vel.x) * 0.8;
		}
	}
}
