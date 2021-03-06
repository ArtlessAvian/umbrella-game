package com.artlessavian.umbrellagame.game.playerstates;

import com.artlessavian.umbrellagame.game.State;
import com.artlessavian.umbrellagame.game.StateMachine;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.badlogic.gdx.graphics.Color;

public class FastFallState extends State<Player>
{
	final float FAST_FALL_MAX_SPEED = 300;

	public FastFallState(StateMachine sm, Player e)
	{
		super(sm, e);
		e.physicsC.gravityAcc = 1200;
		e.spriteC.fromSheet(3, 0, 4, 4);
	}

	@Override
	public boolean checkTransition()
	{
		if (e.controlC.control.swing)
		{
			sm.state = new ASwingState(sm, e);
			return true;
		}
		if (e.controlC.control.up)
		{
			e.stateC.state = new FloatState(sm, e);
			return true;
		}
		return false;
	}

	@Override
	public void update(float deltaT)
	{
		if (e.physicsC.vel.y < -FAST_FALL_MAX_SPEED) {e.physicsC.vel.y = -FAST_FALL_MAX_SPEED;}

		CommonFuncs.editWet(e.playerC, -0.1f, deltaT);
		if (e.controlC.control.left != e.controlC.control.right)
		{
			CommonFuncs.accelX(e.physicsC, e.controlC.control.right, JumpState.AIR_ACCEL, JumpState.MAX_AIR_SPEED);
		}
	}
}
