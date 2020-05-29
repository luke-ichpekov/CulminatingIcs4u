package ichpekov.luke;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.sun.tools.javac.launcher.Main;

public class GameApp extends GameApplication {

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(700);
		settings.setHeight(500);
		settings.setIntroEnabled(true);
		settings.setTitle("Jimmy Vs the World");

	}

	public static void main(String[] args) {
		launch(args);
		

	}

}
 