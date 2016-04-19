package com.itay_peleg.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.itay_peleg.engine.GameContainer;
import com.itay_peleg.engine.components.State;
import com.itay_peleg.engine.gui.GUIContainer;
import com.itay_peleg.engine.gui.builtin.Button;
import com.itay_peleg.game.Assets;
import com.itay_peleg.game.util.Map;

public class ScoreState extends State {
	
	private Map map;
	private GUIContainer gui = new GUIContainer();
	private Button menu = new Button("", 10, 480 * 2 - 180 - 10, 180, 100);
	private Button retry = new Button("", 640 * 2 - 180 - 10, 480 * 2 - 180 - 10, 180, 100);
	private BufferedImage score;
	
	public ScoreState(Map map) {
		score = Assets.loadImage("/ui/score.png");
		this.map = map;
		gui.addGUIObject(menu.setButtonImage(Assets.menu));
		gui.addGUIObject(retry.setButtonImage(Assets.retry));
	}
	
	public void update(GameContainer gc) {
		gui.update(gc);
		if(menu.isInteracted()) {
			gc.getGame().setState(new MenuState());
			Assets.click.play();
		}else if(retry.isInteracted()) {
			gc.getGame().setState(new PlayState(map.getLevel()));
			Assets.click.play();
		}
	}
	
	public void render(GameContainer gc, Graphics g) {
		g.drawImage(score, 0, 0, gc.getWidth(), gc.getHeight(), null);
		g.setColor(Color.BLACK);
		g.setFont(new Font("arial", Font.BOLD, 30));
		if(map.getLevel() == 0) {
			int strLen = (int) g.getFontMetrics().getStringBounds("You died in the first level...", g).getWidth();
			g.drawString("You died in the first level...", gc.getWidth() / 2 - strLen / 2, 100);			
		}else if(map.getLevel() == 1) {
			int strLen = (int) g.getFontMetrics().getStringBounds("It will be much harder...", g).getWidth();
			g.drawString("It will be much harder...", gc.getWidth() / 2 - strLen / 2, 100);			
		}else if(map.getLevel() == 18) {
			int strLen = (int) g.getFontMetrics().getStringBounds("You won! You actually did it!", g).getWidth();
			g.drawString("It will be much harder...", gc.getWidth() / 2 - strLen / 2, 100);						
		}else {
			int strLen = (int) g.getFontMetrics().getStringBounds("What a shame...", g).getWidth();
			g.drawString("What a shame...", gc.getWidth() / 2 - strLen / 2, 100);			
		}
		int strLen = (int) g.getFontMetrics().getStringBounds("Level: " + map.getLevel(), g).getWidth();
		g.drawString("Level: " + map.getLevel(), gc.getWidth() / 2 - strLen / 2, 200);
		gui.render(gc, g);
	}
	
	public void dispose() {
		
	}

}