package com.artlessavian.umbrellagame.game.playerstates;

import com.artlessavian.umbrellagame.game.State;
import com.artlessavian.umbrellagame.game.StateMachine;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.badlogic.gdx.graphics.Color;

public class StandState extends State<Player>
{
	public StandState(StateMachine sm, Player player)
	{
		super(sm, player);
		e.spriteC.fromSheet(2, 0, 4, 4);
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

		if (e.controlC.control.jump)
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

	float timeInState = 0;

	@Override
	public void update(float deltaT)
	{
		timeInState += deltaT;
		if (timeInState > 0.5f)
		{
			e.spriteC.fromSheet(0, 0, 4, 4);
		}

		CommonFuncs.deccelX(e.physicsC, 0.3f);

		e.physicsC.vel.y = 0;

		CommonFuncs.editWet(e.playerC, 0.02f, deltaT);
	}
}
