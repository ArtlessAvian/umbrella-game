package com.artlessavian.umbrellagame.game.playerstates;

import com.artlessavian.umbrellagame.game.State;
import com.artlessavian.umbrellagame.game.StateMachine;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.badlogic.gdx.graphics.Color;

public class FloatState extends State<Player>
{

	final float FALL_MAX_SPEED = 30;
	final float FLOAT_MAX_SPEED = 90;
	final float FLOAT_ACCEL = 2;

	public FloatState(StateMachine sm, Player player)
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
		if (e.controlC.control.swing)
		{
			sm.state = new ASwingState(sm, e);
			return true;
		}
		if (e.controlC.control.down)
		{
			e.stateC.state = new FastFallState(sm, e);
			return true;
		}
		return false;
	}

	float timeInState = 0;

	@Override
	public void update(float deltaT)
	{
		timeInState += deltaT;
		e.spriteC.fromSheet(((int)(timeInState / 0.3f)) % 3, 1, 4, 4);

		if (e.physicsC.vel.y < -FALL_MAX_SPEED)
		{
			e.physicsC.vel.y = -FALL_MAX_SPEED;
		}

		if (e.controlC.control.right != e.controlC.control.left)
		{
			CommonFuncs.accelX(e.physicsC, e.controlC.control.right, FLOAT_ACCEL, FLOAT_MAX_SPEED);
			e.physicsC.facingLeft = !e.controlC.control.right;
		}

		CommonFuncs.editWet(e.playerC, 0.05f, deltaT);
	}
}
