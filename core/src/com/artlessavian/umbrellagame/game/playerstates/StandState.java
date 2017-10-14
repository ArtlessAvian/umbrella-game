package com.artlessavian.umbrellagame.game.playerstates;

import com.artlessavian.umbrellagame.game.State;
import com.artlessavian.umbrellagame.game.StateMachine;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;

public class StandState extends State<Player>
{
	StandState(StateMachine sm, Player player)
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
	public void checkTransition()
	{
		if (e.controlC.control.right != e.controlC.control.left)
		{
			sm.state = new WalkState(sm, e);
		}
		if (e.controlC.control.a)
		{
			sm.state = new JumpState(sm, e);
		}
	}

	@Override
	public void update(float deltaT)
	{

	}
}
