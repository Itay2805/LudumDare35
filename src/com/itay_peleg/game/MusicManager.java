package com.itay_peleg.game;

import com.itay_peleg.engine.GameContainer;
import com.itay_peleg.engine.sounds.SoundClip;

public class MusicManager {
	
	private static final int MAX = 2;
	
	private int current = 0;
	private SoundClip clip;

	public MusicManager() {}
	
	public void next() {
		current++;
		if(current > MAX) {
			current = 0;
			next();
		}else {
			clip = new SoundClip("/sound/soundtrack" + current + ".wav");
			clip.play();
		}
	}
	
	public void update(GameContainer gc) {
		if(clip == null || !clip.isRunning()) {
			if(clip != null) clip.close();
			next();
		}
	}
	
}
