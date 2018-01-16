package game;

import java.awt.Color;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import game.level.Level;
import game.objects.Bird;
import game.objects.Decor;
import game.objects.GameObject;
import game.objects.Pig;
import game.objects.impl.ClassicBird;
import game.objects.impl.ClassicPig;
import game.tools.Constants;

public class Loader {

	private static Loader loader;

	private ArrayList<Level> levels;
	private HashMap<String, Bird> birds;
	private HashMap<String, Pig> pigs;

	private Loader() {
		birds = loadBirds();
		pigs = loadPigs();
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

	// BIRD
	private HashMap<String, Bird> loadBirds() {
		HashMap<String, Bird> lb = new HashMap<>();

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
			switch (type) {
			case "ClassicBird":
				ClassicBird b = new ClassicBird();
				String colorStr = eElement.getElementsByTagName("color").item(0).getTextContent();
				Field field = Class.forName("java.awt.Color").getField(colorStr.toLowerCase());
				b.setColor((Color) field.get(null));

				b.setWidth(Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent()));
				b.setLength(Integer.parseInt(eElement.getElementsByTagName("length").item(0).getTextContent()));
				b.setMasse(Double.parseDouble(eElement.getElementsByTagName("masse").item(0).getTextContent()));
				b.setSpeed(Double.parseDouble(eElement.getElementsByTagName("speed").item(0).getTextContent()));
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
		HashMap<String, Pig> lp = new HashMap<>();

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
					Pig b = loadPig(type, eElement);
					lp.put(type, b);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lp;
	}

	private Pig loadPig(String type, Element eElement) {
		try {
			switch (type) {
			case "ClassicPig":
				ClassicPig p = new ClassicPig();
				String colorStr = eElement.getElementsByTagName("color").item(0).getTextContent();
				Field field = Class.forName("java.awt.Color").getField(colorStr.toLowerCase());
				p.setColor((Color) field.get(null));

				p.setWidth(Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent()));
				p.setLength(Integer.parseInt(eElement.getElementsByTagName("length").item(0).getTextContent()));
				p.setMasse(Double.parseDouble(eElement.getElementsByTagName("masse").item(0).getTextContent()));
				return p;
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
				Decor d = new Decor();
				d.setMovable(Boolean.parseBoolean(eElement.getElementsByTagName("isMovable").item(0).getTextContent()));
				d.setWidth(Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent()));
				d.setLength(Integer.parseInt(eElement.getElementsByTagName("length").item(0).getTextContent()));
				d.setPosX(Integer.parseInt(eElement.getElementsByTagName("posX").item(0).getTextContent()));
				d.setPosY(Integer.parseInt(eElement.getElementsByTagName("posY").item(0).getTextContent()));
				d.setMasse(Integer.parseInt(eElement.getElementsByTagName("masse").item(0).getTextContent()));

				String colorStr = eElement.getElementsByTagName("color").item(0).getTextContent();
				Field field = Class.forName("java.awt.Color").getField(colorStr.toLowerCase());
				d.setColor((Color) field.get(null));
				return d;
			default:
				return null;
			}
		}
		return null;
	}

}
