package com.artlessavian.umbrellagame.game.playerstates;

import com.artlessavian.umbrellagame.game.State;
import com.artlessavian.umbrellagame.game.StateMachine;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;

public class StandState extends State<Player>
{
	public StandState(StateMachine sm, Player player)
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
		if (e.controlC.control.right != e.controlC.control.left)
		{
			sm.state = new WalkState(sm, e);
			return true;
		}
		return false;
	}

	@Override
	public void update(float deltaT)
	{
		e.physicsC.vel.x -= Math.signum(e.physicsC.vel.x) * 0.3;
		e.physicsC.vel.y = 0;
	}
}
