package com.artlessavian.umbrellagame.game.playerstates;

import com.artlessavian.umbrellagame.game.State;
import com.artlessavian.umbrellagame.game.StateMachine;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;

public class JumpState extends State<Player>
{
	private boolean doJump;

	public JumpState(StateMachine sm, Player player, boolean doJump)
	{
		super(sm, player);
		this.doJump = doJump;
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
			e.physicsC.vel.add(0, 30);
			doJump = false;
		}

		if (e.controlC.control.right != e.controlC.control.left)
		{
			if (e.controlC.control.right)
			{
				e.physicsC.vel.x = 30;
			}
			else
			{
				e.physicsC.vel.x = -30;
			}
		}
		else
		{
			e.physicsC.vel.x -= Math.signum(e.physicsC.vel.x) * 0.3;
		}
	}
}
