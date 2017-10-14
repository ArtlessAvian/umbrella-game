package com.artlessavian.umbrellagame.game;

import com.artlessavian.umbrellagame.game.ecs.entities.Player;

/**
 * Does or edits behavior over time
 * @author ArtlessAvian
 * @param <E> Thing to pass around, like a player
 */
public abstract class State<E>
{
	public final StateMachine sm;
	public final E e;

	public State(StateMachine sm, E e)
	{
		System.out.println(this.getClass());
		this.sm = sm;
		this.e = e;
	}

	@Deprecated
	public void enter() {}
	@Deprecated
	public void exit() {}

	public abstract boolean checkTransition();
	public abstract void update(float deltaT);
}
