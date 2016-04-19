package com.itay_peleg.game.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.itay_peleg.engine.GameContainer;
import com.itay_peleg.engine.components.State;
import com.itay_peleg.game.gameobjects.Player;
import com.itay_peleg.game.util.Map;

public class PlayState extends State {
	
	private Map map;
	
	public PlayState(int level) {
		map = new Map();
		map.setLevel(level);
		map.load();
		manager.addObject(new Player(map));
	}

	public void update(GameContainer gc) {
		manager.updateObjects(gc);
		if(gc.getInput().isKeyPressed(KeyEvent.VK_ESCAPE)) {
			gc.getGame().setState(new MenuState());
		}
	}

	public void render(GameContainer gc, Graphics g) {
		map.render(gc, g);
		manager.renderObjects(gc, g);
	}

	public void dispose() {
		manager.disposeObjects();
	}
	
}
