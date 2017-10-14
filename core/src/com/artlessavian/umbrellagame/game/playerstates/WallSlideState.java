package com.artlessavian.umbrellagame.game.playerstates;

import com.artlessavian.umbrellagame.game.State;
import com.artlessavian.umbrellagame.game.StateMachine;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.badlogic.gdx.graphics.Color;

public class WallSlideState extends State<Player>
{
	public WallSlideState(StateMachine sm, Player player)
	{
		super(sm, player);
		e.physicsC.vel.y = 0;
		e.physicsC.gravityAcc = 30;
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
			e.physicsC.vel.y = 180;
			sm.state = new JumpState(sm, e, false);
			return true;
		}
		return false;
	}

	@Override
	public void update(float deltaT)
	{
		e.spriteC.sprite.setColor(Color.RED);
		CommonFuncs.editWet(e.playerC, -0.2f, deltaT);
	}
}
