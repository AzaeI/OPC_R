package game;

import java.awt.Color;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import game.objects.impl.Bird.BlueBird;
import game.objects.impl.Decor.Structure;
import game.objects.impl.Pig.HelmetPig;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import game.level.Level;
import game.objects.Bird;
import game.objects.Decor;
import game.objects.GameObject;
import game.objects.Pig;
import game.objects.impl.Bird.ClassicBird;
import game.objects.impl.Pig.ClassicPig;
import game.tools.Constants;

public class Loader {

	private static Loader loader;

	private ArrayList<Level> levels;
	private HashMap<String, Bird> birds;
	private HashMap<String, Pig> pigs;
	private HashMap<String, Decor> decors;

	private Loader() {
		birds = loadBirds();
		pigs = loadPigs();
		decors = loadDecors();
		levels = loadlevels();
	}

	public static Loader getInstance() {
		if (loader == null)
			loader = new Loader();
		return loader;
	}
	
	public ArrayList<Level> getLevels() {
		return levels;
	}

	public HashMap<String, Bird> getBirds() {
		return birds;
	}

	public HashMap<String, Pig> getPigs() {
		return pigs;
	}

	public HashMap<String, Decor> getDecors() {
		return decors;
	}

	// BIRD
	private HashMap<String, Bird> loadBirds() {
		HashMap<String, Bird> lb = new HashMap();

		File f = new File(Constants.BIRD_FILE);
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(f);

			NodeList objects = doc.getElementsByTagName("bird");
			for (int temp = 0; temp < objects.getLength(); temp++) {
				Node nNode = objects.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					String type = eElement.getElementsByTagName("type").item(0).getTextContent();
					Bird b = loadBird(type, eElement);
					lb.put(type, b);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lb;
	}

	private Bird loadBird(String type, Element eElement) {
		try {
			Bird b;
			String colorStr;
			Field field;
			switch (type) {
			case "ClassicBird":
				b = new ClassicBird();
				colorStr = eElement.getElementsByTagName("color").item(0).getTextContent();
				field = Class.forName("java.awt.Color").getField(colorStr.toLowerCase());
				b.setColor((Color) field.get(null));

				b.setWidth(Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent()));
				b.setLength(Integer.parseInt(eElement.getElementsByTagName("length").item(0).getTextContent()));
				b.setMasse(Double.parseDouble(eElement.getElementsByTagName("masse").item(0).getTextContent()));
				b.setSpeed(Double.parseDouble(eElement.getElementsByTagName("speed").item(0).getTextContent()));

				b.setHp(Integer.parseInt(eElement.getElementsByTagName("hp").item(0).getTextContent()));

				b.setSprite(eElement.getElementsByTagName("sprite").item(0).getTextContent());
				return b;
			case "BlueBird":
				b = new BlueBird();
				colorStr = eElement.getElementsByTagName("color").item(0).getTextContent();
				field = Class.forName("java.awt.Color").getField(colorStr.toLowerCase());
				b.setColor((Color) field.get(null));

				b.setWidth(Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent()));
				b.setLength(Integer.parseInt(eElement.getElementsByTagName("length").item(0).getTextContent()));
				b.setMasse(Double.parseDouble(eElement.getElementsByTagName("masse").item(0).getTextContent()));
				b.setSpeed(Double.parseDouble(eElement.getElementsByTagName("speed").item(0).getTextContent()));

				b.setHp(Integer.parseInt(eElement.getElementsByTagName("hp").item(0).getTextContent()));

				b.setSprite(eElement.getElementsByTagName("sprite").item(0).getTextContent());
				return b;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// PIG
	private HashMap<String, Pig> loadPigs() {
		HashMap<String, Pig> lp = new HashMap();

		File f = new File(Constants.PIG_FILE);
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(f);

			NodeList objects = doc.getElementsByTagName("pig");

			for (int temp = 0; temp < objects.getLength(); temp++) {
				Node nNode = objects.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					String type = eElement.getElementsByTagName("type").item(0).getTextContent();
					Pig p = loadPig(type, eElement);
					lp.put(type, p);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lp;
	}

	private Pig loadPig(String type, Element eElement) {
		try {
			Pig p;
			String colorStr;
			Field field;
			switch (type) {
			case "ClassicPig":
				p = new ClassicPig();
				colorStr = eElement.getElementsByTagName("color").item(0).getTextContent();
				field = Class.forName("java.awt.Color").getField(colorStr.toLowerCase());
				p.setColor((Color) field.get(null));

				p.setWidth(Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent()));
				p.setLength(Integer.parseInt(eElement.getElementsByTagName("length").item(0).getTextContent()));
				p.setMasse(Double.parseDouble(eElement.getElementsByTagName("masse").item(0).getTextContent()));

				p.setHp(Integer.parseInt(eElement.getElementsByTagName("hp").item(0).getTextContent()));

				p.setSprite(eElement.getElementsByTagName("sprite").item(0).getTextContent());
				return p;
			case "HelmetPig":
				p = new HelmetPig();
				colorStr = eElement.getElementsByTagName("color").item(0).getTextContent();
				field = Class.forName("java.awt.Color").getField(colorStr.toLowerCase());
				p.setColor((Color) field.get(null));

				p.setWidth(Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent()));
				p.setLength(Integer.parseInt(eElement.getElementsByTagName("length").item(0).getTextContent()));
				p.setMasse(Double.parseDouble(eElement.getElementsByTagName("masse").item(0).getTextContent()));

				p.setHp(Integer.parseInt(eElement.getElementsByTagName("hp").item(0).getTextContent()));

				p.setSprite(eElement.getElementsByTagName("sprite").item(0).getTextContent());
				return p;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// DECOR
	private HashMap<String,Decor> loadDecors(){
		HashMap<String, Decor> ld = new HashMap<>();

		File f = new File(Constants.DECOR_FILE);
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(f);

			NodeList objects = doc.getElementsByTagName("decor");

			for (int temp = 0; temp < objects.getLength(); temp++) {
				Node nNode = objects.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					String type = eElement.getElementsByTagName("type").item(0).getTextContent();
					Decor d = loadDecor(type, eElement);
					ld.put(type, d);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ld;
	}

	private Decor loadDecor(String type, Element eElement){
		Decor d;
		String colorStr;
		Field field;
		try {
			switch (type) {
				case "Structure":
					d = new Structure();
					colorStr = eElement.getElementsByTagName("color").item(0).getTextContent();
					field = Class.forName("java.awt.Color").getField(colorStr.toLowerCase());
					d.setColor((Color) field.get(null));
					d.setMovable(Boolean.parseBoolean(eElement.getElementsByTagName("isMovable").item(0).getTextContent()));
					d.setMasse(Double.parseDouble(eElement.getElementsByTagName("masse").item(0).getTextContent()));
					d.setHp(Integer.parseInt(eElement.getElementsByTagName("hp").item(0).getTextContent()));

					d.setSprite(eElement.getElementsByTagName("sprite").item(0).getTextContent());
					return d;
				case "Ground":
					d = new Structure();
					colorStr = eElement.getElementsByTagName("color").item(0).getTextContent();
					field = Class.forName("java.awt.Color").getField(colorStr.toLowerCase());
					d.setColor((Color) field.get(null));
					d.setMovable(Boolean.parseBoolean(eElement.getElementsByTagName("isMovable").item(0).getTextContent()));
					d.setMasse(Double.parseDouble(eElement.getElementsByTagName("masse").item(0).getTextContent()));
					d.setHp(Integer.parseInt(eElement.getElementsByTagName("hp").item(0).getTextContent()));

					d.setSprite(eElement.getElementsByTagName("sprite").item(0).getTextContent());
					return d;
				case "Grass":
					d = new Structure();
					colorStr = eElement.getElementsByTagName("color").item(0).getTextContent();
					field = Class.forName("java.awt.Color").getField(colorStr.toLowerCase());
					d.setColor((Color) field.get(null));
					d.setMovable(Boolean.parseBoolean(eElement.getElementsByTagName("isMovable").item(0).getTextContent()));
					d.setMasse(Double.parseDouble(eElement.getElementsByTagName("masse").item(0).getTextContent()));
					d.setHp(Integer.parseInt(eElement.getElementsByTagName("hp").item(0).getTextContent()));

					d.setSprite(eElement.getElementsByTagName("sprite").item(0).getTextContent());
					return d;
				default:
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Level
	private ArrayList<Level> loadlevels() {
		ArrayList<Level> lv = new ArrayList<Level>();
		File dir = new File(Constants.LEVEL_DIRECTORY);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				lv.add(loadLevel(child.getName()));
			}
		}
		return lv;
	}

	private Level loadLevel(String levelFile) {
		File f = new File(Constants.LEVEL_DIRECTORY + "/" + levelFile);
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(f);

			ArrayList<GameObject> items = new ArrayList<>();
			double gravity = Double.parseDouble(doc.getElementsByTagName("gravity").item(0).getTextContent());
			String name = doc.getElementsByTagName("name").item(0).getTextContent();

			NodeList objects = doc.getElementsByTagName("object");
			for (int temp = 0; temp < objects.getLength(); temp++) {

				Node nNode = objects.item(temp);
				GameObject g_obj = getGameObject(nNode);
				if (g_obj != null)
					items.add(g_obj);
			}

			Level l = new Level(name, gravity, items);
			return l;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private GameObject getGameObject(Node n) throws NoSuchFieldException, SecurityException, ClassNotFoundException,
			IllegalArgumentException, IllegalAccessException {
		if (n.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) n;
			String type = eElement.getElementsByTagName("type").item(0).getTextContent();
			String classe = null;

			switch (type) {
			case "Bird":
				classe = eElement.getElementsByTagName("class").item(0).getTextContent();
				if (birds.containsKey(classe)) {
					Bird b = (Bird) birds.get(classe).clone();
					b.setOrder(Integer.parseInt(eElement.getElementsByTagName("order").item(0).getTextContent()));
					return b;
				}
				break;
			case "Pig":
				classe = eElement.getElementsByTagName("class").item(0).getTextContent();
				if (pigs.containsKey(classe)) {
					Pig p = (Pig) pigs.get(classe).clone();
					p.setPosX(Integer.parseInt(eElement.getElementsByTagName("posX").item(0).getTextContent()));
					p.setPosY(Integer.parseInt(eElement.getElementsByTagName("posY").item(0).getTextContent()));
					return p;
				}
				break;
			case "Decor":
				classe = eElement.getElementsByTagName("class").item(0).getTextContent();
				if (decors.containsKey(classe)) {
					Decor d = (Decor) decors.get(classe).clone();
					d.setWidth(Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent()));
					d.setLength(Integer.parseInt(eElement.getElementsByTagName("length").item(0).getTextContent()));
					d.setPosX(Integer.parseInt(eElement.getElementsByTagName("posX").item(0).getTextContent()));
					d.setPosY(Integer.parseInt(eElement.getElementsByTagName("posY").item(0).getTextContent()));
					return d;
				}
				break;
			default:
				return null;
			}
		}
		return null;
	}

}
