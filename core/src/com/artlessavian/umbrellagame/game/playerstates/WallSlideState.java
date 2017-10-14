package com.artlessavian.umbrellagame.game.playerstates;

import com.artlessavian.umbrellagame.game.State;
import com.artlessavian.umbrellagame.game.StateMachine;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.badlogic.gdx.graphics.Color;

public class WallSlideState extends State<Player>
{
	public float validSlide;

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
		System.out.println(validSlide);
		if (validSlide > 0.03f)
		{
			sm.state = new JumpState(sm, e, false);
			return true;
		}

		if (e.controlC.control.jump)
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
		if (e.controlC.control.left && e.playerC.facingLeft)
		{
			sm.state = new JumpState(sm, e, false);
			return true;
		}
		if (e.controlC.control.right && !e.playerC.facingLeft)
		{
			sm.state = new JumpState(sm, e, false);
			return true;
		}
		return false;
	}

	@Override
	public void update(float deltaT)
	{
		validSlide += deltaT;

		e.spriteC.fromSheet(3, 1, 4, 4);

		if (e.controlC.control.down)
		{
			e.physicsC.vel.y -= 2;
		}

		if (e.playerC.facingLeft)
		{
			e.physicsC.vel.x = 1;
		}
		else
		{
			e.physicsC.vel.x = -1;
		}

		CommonFuncs.editWet(e.playerC, -0.2f, deltaT);
	}
}
