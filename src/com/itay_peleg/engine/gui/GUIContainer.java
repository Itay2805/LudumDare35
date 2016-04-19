package com.itay_peleg.engine.gui;

import java.awt.Graphics;
import java.util.ArrayList;

import com.itay_peleg.engine.GameContainer;

public class GUIContainer {
	
	private ArrayList<AbstractGUI> guiObjects = new ArrayList<AbstractGUI>();
	
	private String outputTag = null;
	
	public void addGUIObject(AbstractGUI gui) {
		guiObjects.add(gui);
	}
	
	public void removeGUIObject(String name) {
		for(int i = 0; i < guiObjects.size(); i++) {
			if(guiObjects.get(i).equals(name)) guiObjects.remove(i);
		}
	}
	
	public void update(GameContainer gc) {
		boolean interacted = false;
		for(AbstractGUI gui : guiObjects) {
			gui.update(gc);
			if(gui.isInteracted()) {
				outputTag = gui.getTag();
				interacted = true;
			}
		}
		if(!interacted) outputTag = null;
	}
	
	public void render(GameContainer gc, Graphics g) {
		for(AbstractGUI gui : guiObjects) {
			gui.render(gc, g);
		}
	}

	public String getOutputTag() {
		return outputTag;
	}

	public ArrayList<AbstractGUI> getGuiObjects() {
		return guiObjects;
	}
	
}
