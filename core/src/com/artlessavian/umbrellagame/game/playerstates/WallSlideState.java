package com.artlessavian.umbrellagame.game.playerstates;

import com.artlessavian.umbrellagame.game.State;
import com.artlessavian.umbrellagame.game.StateMachine;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;

public class WallSlideState extends State<Player>
{
	public WallSlideState(StateMachine sm, Player player)
	{
		super(sm, player);
		e.physicsC.vel.y = 0;
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
			if (e.playerC.facingLeft)
			{
				e.physicsC.vel.x = -85;
			}
			else
			{
				e.physicsC.vel.x = 85;
			}
			sm.state = new JumpState(sm, e, true);
			return true;
		}
		return false;
	}

	@Override
	public void update(float deltaT)
	{

	}
}
