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
		this.sm = sm;
		this.e = e;
	}

	public abstract void enter();
	public abstract void exit();
	public abstract void checkTransition();
	public abstract void update(float deltaT);
}
