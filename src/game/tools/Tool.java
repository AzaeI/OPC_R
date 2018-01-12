package game.tools;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import game.level.Level;
import game.objects.Bird;
import game.objects.GameObject;
import game.objects.impl.ClassicBird;
import game.objects.impl.ClassicPig;

public class Tool {

	private Tool(){}
	
	public static List<String> levelsNames () {
		ArrayList<String> levelsName = new ArrayList<>();
		
		File dir = new File(Constants.LEVEL_DIRECTORY);
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		      levelsName.add(getLevelName(child));
		    }
		  }
		return levelsName;
	}
	
	
	private static String getLevelName(File f) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(f);
			
			NodeList names = doc.getElementsByTagName("name");
			return names.item(0).getTextContent();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Level getLevel(File f) {		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(f);
			
			ArrayList<GameObject> items = new ArrayList<>();
			double gravity = Double.parseDouble(doc.getElementsByTagName("gravity").item(0).getTextContent() );
			
			NodeList objects = doc.getElementsByTagName("object");
			for (int temp = 0; temp < objects.getLength(); temp++) {

				Node nNode = objects.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					String type = eElement.getElementsByTagName("type").item(0).getTextContent(); 
					String classe = null;
					
					switch (type) {
					case "Bird":
						classe = eElement.getElementsByTagName("class").item(0).getTextContent();
						if (classe.equals("ClassicBird")){
							ClassicBird b = new ClassicBird();
							b.setColor(Color.blue);
							b.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
						}
						break;
					case "Pig":
						classe = eElement.getElementsByTagName("class").item(0).getTextContent();
						if (classe.equals("ClassicPig")){
							items.add(new ClassicPig());
						}					
						break;
					case "Decor":
						//b.setWidth(Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent()));
						//b.setLength(Integer.parseInt(eElement.getElementsByTagName("length").item(0).getTextContent()));
						//b.setPosX(Integer.parseInt(eElement.getElementsByTagName("posX").item(0).getTextContent()));
						//b.setPosY(Integer.parseInt(eElement.getElementsByTagName("posY").item(0).getTextContent()));
						//b.setMasse(Integer.parseInt(eElement.getElementsByTagName("posX").item(0).getTextContent()));
						break;

					default:
						break;
					}
					

				}
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
