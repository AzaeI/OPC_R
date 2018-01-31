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
    public final static String GRAVITY_IMG = IMAGE_DIRECTORY + "/sprites/gravity";

	public final static String BIRD_FILE = CHARACTERS_DIRECTORY + "/Bird.xml";
	public final static String PIG_FILE = CHARACTERS_DIRECTORY + "/Pig.xml";
	public final static String DECOR_FILE = CHARACTERS_DIRECTORY + "/Decors.xml";
    public final static String GRAVITY_FILE = CHARACTERS_DIRECTORY + "/Gravity.xml";


	public final static String BG_FILE = IMAGE_DIRECTORY+ "/bg.jpg";
	public final static String TOMB_FILE = IMAGE_DIRECTORY+ "/sprites/tomb.png";
	public final static String TOMB_BIRD_FILE = IMAGE_DIRECTORY+ "/sprites/tomb_bird.png";


	public final static boolean DEBUG = false;

    public final static double FORCE_MAX = 2000;
	public final static double FORCE_MIN = 80;
	public final static double COLLISION_REDUCTOR = 3;
	public final static double FRICTION_POURCENTAGE = 0.2;

	public final static int DEACREASE_TIMER_DESAPPEAR_GCHARACTER = 10; //0.1s

	public final static int DEACREASE_TIMER_DESAPPEAR_BIRD = 10; //0.1s
	public final static int TIME_TO_LIVE_BIRD = 7000;

}
