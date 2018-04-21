package com.flappymansour.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.flappymansour.game.flappyMansour;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = flappyMansour.WIDTH;
		config.height = flappyMansour.HEIGHT;
		config.title = flappyMansour.TITLE;
		new LwjglApplication(new flappyMansour(), config);
	}
}
