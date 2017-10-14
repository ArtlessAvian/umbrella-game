package com.artlessavian.umbrellagame.game.playerstates;

import com.artlessavian.umbrellagame.game.State;
import com.artlessavian.umbrellagame.game.StateMachine;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.badlogic.gdx.graphics.Color;

public class JumpState extends State<Player>
{
	final float MAX_AIR_SPEED = 80;
	final float AIR_ACCEL = 4;

	private boolean doJump;
	
	public JumpState(StateMachine sm, Player player, boolean doJump)
	{
		super(sm, player);
		this.doJump = doJump;
		e.physicsC.grounded = false;
		e.physicsC.gravityAcc = 300;
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
		if (e.physicsC.vel.y < 0)
		{
			e.stateC.state = new FloatState(sm, e);
			return true;
		}


		return false;
	}

	@Override
	public void update(float deltaT)
	{
		e.spriteC.sprite.setColor(Color.BLUE);

		if (doJump)
		{
			e.physicsC.vel.y = 200;
			doJump = false;
		}

		if (!e.controlC.control.a)
		{
			e.physicsC.gravityAcc = 400;
		}

		if (e.controlC.control.right != e.controlC.control.left)
		{

			CommonFuncs.accelX(e.physicsC, e.controlC.control.right, AIR_ACCEL);
			e.playerC.facingLeft = !e.controlC.control.right;
			CommonFuncs.limitSpeedX(e.physicsC, MAX_AIR_SPEED);
		}
		else
		{
			CommonFuncs.deccelX(e.physicsC, 2f);
		}
	}
}
