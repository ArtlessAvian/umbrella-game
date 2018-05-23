package com.artlessavian.umbrellagame.game.playerstates;

import com.artlessavian.umbrellagame.game.State;
import com.artlessavian.umbrellagame.game.StateMachine;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;

public class WallSlideState extends State<Player>
{
	public float validSlide;

	public WallSlideState(StateMachine sm, Player player)
	{
		super(sm, player);
		e.physicsC.vel.y = 0;
		e.physicsC.gravityAcc = 30;


		e.spriteC.fromSheet(3, 1, 4, 4);
	}

	@Override
	public void enter()
	{

	}

	@Override
	public void exit()
	{

	}


	static Sound sound = Gdx.audio.newSound(Gdx.files.internal("sound/Slide.wav"));

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
			if (e.physicsC.facingLeft)
			{
				e.physicsC.vel.x = -85;
			}
			else
			{
				e.physicsC.vel.x = 85;
			}
			e.physicsC.vel.y = 180;
			sound.play();
			sm.state = new JumpState(sm, e, false);
			return true;
		}
		if (e.controlC.control.left && e.physicsC.facingLeft)
		{
			sm.state = new JumpState(sm, e, false);
			return true;
		}
		if (e.controlC.control.right && !e.physicsC.facingLeft)
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

		if (e.physicsC.facingLeft)
		{
			e.physicsC.vel.x = 1;
		}
		else
		{
			e.physicsC.vel.x = -1;
		}
		CommonFuncs.editWet(e.playerC, -0.05f, deltaT);
	}
}
