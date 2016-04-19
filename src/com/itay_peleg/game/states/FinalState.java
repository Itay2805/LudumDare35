package com.itay_peleg.game.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.itay_peleg.engine.GameContainer;
import com.itay_peleg.engine.components.State;
import com.itay_peleg.game.Assets;
import com.itay_peleg.game.util.Map;

public class FinalState extends State {
	
	private State s;
	private Map map;
	private BufferedImage img;
	private double timer = 10;
	
	public FinalState(State s, Map map) {
		this.s = s;
		img = Assets.loadImage("/ui/final.png");
		this.map = map;
	}
	
	public void update(GameContainer gc) {
		if(timer > 0) {
			timer -= gc.getFrameRate();
		}else {
			map.nextLevel(gc);
		}
	}

	public void render(GameContainer gc, Graphics g) {
		s.render(gc, g);
		g.drawImage(img,  gc.getWidth() / 2 - img.getWidth() / 2, gc.getHeight() / 2 - img.getHeight() / 2, null);
	}

	public void dispose() {}

}
