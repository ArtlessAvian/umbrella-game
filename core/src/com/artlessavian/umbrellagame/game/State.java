package com.artlessavian.umbrellagame.game;

/**
 * Does or edits behavior over time
 * @author ArtlessAvian
 * @param <E> Thing to pass around, like a player
 */
public abstract class State<E>
{
	private final StateMachine sm;
	private final E e;

	State(StateMachine sm, E e)
	{
		this.sm = sm;
		this.e = e;
	}

	abstract void enter();
	abstract void exit();
	abstract void update(float deltaT);
}
