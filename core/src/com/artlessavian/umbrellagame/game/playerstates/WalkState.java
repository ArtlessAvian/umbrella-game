package com.artlessavian.umbrellagame.game.playerstates;

import com.artlessavian.umbrellagame.game.State;
import com.artlessavian.umbrellagame.game.StateMachine;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;

public class WalkState extends State<Player>
{

	final float WALK_MAX_SPEED = 90;
	final float WALK_ACCEL = 9;
	
	public WalkState(StateMachine sm, Player player)
	{
		super(sm, player);
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
		if (e.controlC.control.a)
		{
			sm.state = new JumpState(sm, e, true);
			return true;
		}
		if (e.controlC.control.right == e.controlC.control.left)
		{
			e.stateC.state = new StandState(sm, e);
			return true;
		}
		return false;
	}

	@Override
	public void update(float deltaT)
	{
		if (e.controlC.control.right != e.controlC.control.left)
		{
			if (e.controlC.control.right)
			{
				e.playerC.facingLeft = false;
				e.physicsC.vel.x += WALK_ACCEL;
			}
			else
			{
				e.playerC.facingLeft = true;
				e.physicsC.vel.x -= WALK_ACCEL;
			}
			if (e.physicsC.vel.x > WALK_MAX_SPEED) {e.physicsC.vel.x = WALK_MAX_SPEED;}
			if (e.physicsC.vel.x < -WALK_MAX_SPEED) {e.physicsC.vel.x = -WALK_MAX_SPEED;}
		}
	}
}
