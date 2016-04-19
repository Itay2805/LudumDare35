package com.itay_peleg.engine.gui;

import java.awt.Graphics;

import com.itay_peleg.engine.GameContainer;

public abstract class AbstractGUI {
	
	protected String tag;
	protected boolean interacted;
	
	public abstract void init(GameContainer gc);
	public abstract void update(GameContainer gc);
	public abstract void render(GameContainer gc, Graphics g);
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public boolean isInteracted() {
		return interacted;
	}
	
	public void setInteracted(boolean interacted) {
		this.interacted = interacted;
	}
	
}
