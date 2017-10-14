//package com.artlessavian.umbrellagame.game.playerstates;
//
//import com.artlessavian.umbrellagame.game.State;
//import com.artlessavian.umbrellagame.game.StateMachine;
//import com.artlessavian.umbrellagame.game.ecs.entities.Player;
//
//public class DashState extends State<Player>
//{
//	public DashState(StateMachine sm, Player player)
//	{
//		super(sm, player);
//
//		e.physicsC.vel.x = 200;
//		if (e.controlC.control.left)
//		{
//			e.physicsC.vel.x *= -1;
//		}
//	}
//
//	@Override
//	public boolean checkTransition()
//	{
//		if (!e.controlC.control.dash)
//		{
//			e.stateC.state = new StandState(sm, e);
//		}
//		if (e.controlC.control.jump)
//		{
//			e.stateC.state = new JumpState(sm, e, true);
//		}
//		return false;
//	}
//
//	@Override
//	public void update(float deltaT)
//	{
//
//	}
//}
