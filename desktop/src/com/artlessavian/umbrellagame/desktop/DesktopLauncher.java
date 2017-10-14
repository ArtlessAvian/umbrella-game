package com.artlessavian.umbrellagame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.artlessavian.umbrellagame.Maineroni;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
<<<<<<< HEAD
		config.width = 800;
		config.height = 450;
=======
		config.width = 1280;
		config.height = 720;
>>>>>>> 64e1650beff4a48aa3f8cbcadc562834efb906e2
		new LwjglApplication(new Maineroni(), config);
	}
}
