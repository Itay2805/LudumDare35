package com.itay_peleg.game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.itay_peleg.engine.sounds.SoundClip;
import com.itay_peleg.engine.util.Log;

public class Assets {
	
	public static BufferedImage player_ball, player_tirangle, player_x, player_stick;
	public static BufferedImage npc_ball, npc_tirangle, npc_x, outsider;
	public static BufferedImage basicTile, bendingBlockTile, JumpTile, NextLevelTile, SlowTile, SmallTile, SpeedTile, stick, stopTile;
	public static BufferedImage sign, spike, scroll;
	public static BufferedImage gate_ball, gate_x, gate_triangle;
	public static BufferedImage cant_shift, button, menu, retry;
	
	public static SoundClip jump, click;

	public static void init() {
		player_ball = loadImage("/player/ball.png");
		player_tirangle = loadImage("/player/triangle.png");
		player_x = loadImage("/player/x.png");
		player_stick = loadImage("/player/stick.png");

		npc_ball = loadImage("/npc/ball.png");
		npc_tirangle = loadImage("/npc/triangle.png");
		npc_x = loadImage("/npc/x.png");
		outsider = loadImage("/npc/outsider.png");
		
		basicTile = loadImage("/blocks/BasicTile.png");
		bendingBlockTile = loadImage("/blocks/BendingBlockTile.png");
		JumpTile = loadImage("/blocks/JumpTile.png");
		NextLevelTile = loadImage("/blocks/NextLvl.png");
		SlowTile = loadImage("/blocks/SlowTile.png");
		SmallTile = loadImage("/blocks/SmallTile.png");
		SpeedTile = loadImage("/blocks/SpeedTile.png");
		stick = loadImage("/blocks/stick.png");
		scroll = loadImage("/blocks/scroll.png");
		stopTile = loadImage("/blocks/stopTile.png");

		spike = loadImage("/blocks/spike.png");
		sign = loadImage("/blocks/sign.png");
		
		gate_ball = loadImage("/gates/gate_ball.png");
		gate_x = loadImage("/gates/gate_x.png");
		gate_triangle = loadImage("/gates/gate_triangle.png");
		
		cant_shift = loadImage("/ui/cant_shift.png");
		button = loadImage("/ui/button.png");
		retry = loadImage("/ui/retry.png");
		menu = loadImage("/ui/menu.png");
		
		jump = new SoundClip("/sound/jump.wav");
		click = new SoundClip("/sound/click.wav");
	}
	
	public static BufferedImage loadImage(String path) {
		try {
			Log.info("ImageLoader", "Loading image " + path);
			return ImageIO.read(Assets.class.getResourceAsStream(path));
		} catch (IOException e) {
			Log.error("ImageLoader", "Could not load image " + path + " -> " + e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}
	
}
