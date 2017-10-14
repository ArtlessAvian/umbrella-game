package com.artlessavian.umbrellagame.game.playerstates;

import com.artlessavian.umbrellagame.game.OffsetRectangle;
import com.artlessavian.umbrellagame.game.State;
import com.artlessavian.umbrellagame.game.StateMachine;
import com.artlessavian.umbrellagame.game.ecs.entities.Player;

public class ASwingState extends State<Player>
{
	public ASwingState(StateMachine sm, Player player)
	{
		super(sm, player);
		e.spriteC.fromSheet(2, 3, 4, 4);
		e.physicsC.gravityAcc = 600;
	}

	@Override
	public boolean checkTransition()
	{
		if (time > 0.4)
		{
			e.hitboxC.hitbox = null;
			sm.state = new JumpState(sm, e, false);
			e.hitboxC.cannotHit.clear();
			return true;
		}

		return false;
	}


	float time = 0;

	@Override
	public void update(float deltaT)
	{
		time += deltaT;
		if (time > 0.3)
		{
			e.spriteC.fromSheet(3, 3, 4, 4);
			e.hitboxC.hitbox = new OffsetRectangle(0, -32, 32, 64);
		}
	}
}
