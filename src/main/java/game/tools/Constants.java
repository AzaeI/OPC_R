package game.tools;

public class Constants {

	private Constants(){}
	
	public final static String GAME_NAME = "OPC - Oiseaux pas Content !";

	public final static String LEVEL_DIRECTORY = "levels";
	public final static String CHARACTERS_DIRECTORY = "characters";
    public final static String IMAGE_DIRECTORY = "img";

    public final static String BIRD_IMG = IMAGE_DIRECTORY + "/sprites/birds";
	public final static String PIGS_IMG = IMAGE_DIRECTORY + "/sprites/pigs";
	public final static String DECOR_IMG = IMAGE_DIRECTORY + "/sprites/decors";

	public final static String BIRD_FILE = CHARACTERS_DIRECTORY + "/Bird.xml";
	public final static String PIG_FILE = CHARACTERS_DIRECTORY + "/Pig.xml";
	public final static String DECOR_FILE = CHARACTERS_DIRECTORY + "/Decors.xml";


    public final static String BG_FILE = IMAGE_DIRECTORY+ "/bg.jpg";

    public final static boolean DEBUG = false;

    public final static double FORCE_MAX = 2000;
	
}
