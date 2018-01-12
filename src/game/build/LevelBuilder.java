package game.build;

import java.util.HashMap;
import java.util.Map;

import game.level.Level;

public class LevelBuilder {
	
	private Map<String, Level> loadLevels;
	
	public LevelBuilder() {
		loadLevels = new HashMap<>();
	}
	
	/**
	 * 
	 * @param levelFile
	 * @return
	 */
	public Level create_level(String levelFile) {
		if (loadLevels.containsKey(levelFile)) {
			return loadLevels.get(levelFile);
		}
		return loadLevel(levelFile);
	}
	
	private Level loadLevel(String levelFile) {
		
		
		return null;
	}
}
