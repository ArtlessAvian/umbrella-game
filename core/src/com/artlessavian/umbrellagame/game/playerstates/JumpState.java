package com.artlessavian.umbrellagame.game.playerstates;

import com.artlessavian.umbrellagame.game.State;
import com.artlessavian.umbrellagame.game.StateMachine;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.badlogic.gdx.graphics.Color;

public class JumpState extends State<Player>
{
<<<<<<< HEAD
	final float MAX_AIR_SPEED = 80;
	final float AIR_ACCEL = 4;
=======
	final static float MAX_AIR_SPEED = 80;
	final static float AIR_ACCEL = 4;
>>>>>>> 64e1650beff4a48aa3f8cbcadc562834efb906e2

	private boolean doJump;
	
	public JumpState(StateMachine sm, Player player, boolean doJump)
	{
		super(sm, player);
		this.doJump = doJump;
		e.physicsC.grounded = false;
		e.physicsC.gravityAcc = 300;

		e.spriteC.fromSheet(1, 0, 4, 4);
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
		if (doJump)
		{
			e.physicsC.vel.y = 200;
			doJump = false;
		}

		if (!e.controlC.control.jump)
		{
<<<<<<< HEAD
			e.physicsC.gravityAcc = 600;
=======
			e.physicsC.gravityAcc = 1000;
>>>>>>> 64e1650beff4a48aa3f8cbcadc562834efb906e2
		}

		if (e.controlC.control.right != e.controlC.control.left)
		{

			CommonFuncs.accelX(e.physicsC, e.controlC.control.right, AIR_ACCEL, MAX_AIR_SPEED);
			e.playerC.facingLeft = !e.controlC.control.right;
		}
		else
		{
			CommonFuncs.deccelX(e.physicsC, 2f);
		}

		CommonFuncs.editWet(e.playerC, 0.02f, deltaT);
	}
}
