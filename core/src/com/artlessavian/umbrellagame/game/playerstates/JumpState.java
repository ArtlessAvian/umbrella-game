package com.artlessavian.umbrellagame.game.playerstates;

import com.artlessavian.umbrellagame.game.State;
import com.artlessavian.umbrellagame.game.StateMachine;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;

public class JumpState extends State<Player>
{
	static Sound sound = Gdx.audio.newSound(Gdx.files.internal("sound/Jump 2.wav"));

	final static float MAX_AIR_SPEED = 80;
	final static float AIR_ACCEL = 4;

	private boolean doJump;

	public JumpState(StateMachine sm, Player player, boolean doJump)
	{
		super(sm, player);
		this.doJump = doJump;
		e.physicsC.grounded = false;
		e.physicsC.gravityAcc = 300;

		e.spriteC.fromSheet(1, 0, 4, 4);

		if (doJump) {sound.play();}
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
			e.physicsC.gravityAcc = 1000;
		}

		if (e.controlC.control.right != e.controlC.control.left)
		{

			CommonFuncs.accelX(e.physicsC, e.controlC.control.right, AIR_ACCEL, MAX_AIR_SPEED);
			e.physicsC.facingLeft = !e.controlC.control.right;
		}
		else
		{
			CommonFuncs.deccelX(e.physicsC, 2f);
		}

		CommonFuncs.editWet(e.playerC, 0.02f, deltaT);
	}
}
