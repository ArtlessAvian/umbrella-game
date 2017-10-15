package com.artlessavian.umbrellagame.game.playerstates;

import com.artlessavian.umbrellagame.game.State;
import com.artlessavian.umbrellagame.game.StateMachine;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.badlogic.gdx.graphics.Color;

public class WalkState extends State<Player>
{

	final float WALK_MAX_SPEED = 200;
	final float WALK_ACCEL = 3;
	
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
		if (e.controlC.control.swing)
		{
			sm.state = new ASwingState(sm, e);
			return true;
		}
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

	float dist = 0;

	@Override
	public void update(float deltaT)
	{
		dist += deltaT * Math.abs(e.physicsC.vel.x) / 30f;
		int pos = ((int)(dist)) % 6;
		if (pos < 4)
		{
			e.spriteC.fromSheet(pos, 2, 4, 4);
		}
		else
		{
			e.spriteC.fromSheet(pos - 4, 3, 4, 4);
		}

		if (e.controlC.control.right != e.controlC.control.left)
		{
			CommonFuncs.accelX(e.physicsC, e.controlC.control.right, WALK_ACCEL, WALK_MAX_SPEED);
			e.physicsC.facingLeft = !e.controlC.control.right;
		}

		CommonFuncs.editWet(e.playerC, 0.02f, deltaT);
	}
}
