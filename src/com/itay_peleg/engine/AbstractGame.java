package com.itay_peleg.engine;

import java.awt.Graphics;
import java.util.Stack;

import com.itay_peleg.engine.components.State;
import com.itay_peleg.engine.util.Log;

public abstract class AbstractGame {
	
	private Stack<State> states = new Stack<State>();
	
	public State peek() {
		return states.peek();
	}
	
	public void push(State s) {
		String name = s.getClass().getName().split("\\.")[s.getClass().getName().split("\\.").length - 1];
		Log.info("AbstractGame", "Pushing game state " + name);
		states.push(s);
	}
	
	public void pop() {
		states.peek().dispose();
		states.pop();
	}
	
	public void setState(State s) {
		states.pop();
		states.push(s);
	}
	
	public abstract void init();
	public abstract void update(GameContainer gc);
	public abstract void render(GameContainer gc, Graphics g);
	
}
