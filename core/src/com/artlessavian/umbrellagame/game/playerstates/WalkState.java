package com.artlessavian.umbrellagame.game.playerstates;

import com.artlessavian.umbrellagame.game.State;
import com.artlessavian.umbrellagame.game.StateMachine;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.badlogic.gdx.graphics.Color;

public class WalkState extends State<Player>
{

<<<<<<< HEAD
	final float WALK_MAX_SPEED = 120;
	final float WALK_ACCEL = 9;
=======
	final float WALK_MAX_SPEED = 200;
	final float WALK_ACCEL = 3;
>>>>>>> 64e1650beff4a48aa3f8cbcadc562834efb906e2
	
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
<<<<<<< HEAD
		if (e.controlC.control.dash)
		{
			sm.state = new DashState(sm, e);
			return true;
		}
=======
//		if (e.controlC.control.dash)
//		{
//			sm.state = new DashState(sm, e);
//			return true;
//		}
>>>>>>> 64e1650beff4a48aa3f8cbcadc562834efb906e2
		if (e.controlC.control.jump)
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
		e.spriteC.sprite.setColor(Color.CHARTREUSE);

		if (e.controlC.control.right != e.controlC.control.left)
		{
			CommonFuncs.accelX(e.physicsC, e.controlC.control.right, WALK_ACCEL, WALK_MAX_SPEED);
			e.playerC.facingLeft = !e.controlC.control.right;
		}

		CommonFuncs.editWet(e.playerC, 0.02f, deltaT);
	}
}
