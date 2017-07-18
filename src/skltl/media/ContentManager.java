package skltl.media;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.InputMismatchException;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import skltl.Game;

/*
 * if (!loadedAssets.containsKey(path)) {

 try {
 loadedAssets.put(path, THING);
 return THING;
 } catch (IOException e) {
 e.printStackTrace();
 return null;
 }

 } else {
 return (THING) loadedAssets.get(path);
 }
 */
/**
 * The ContentManager class is used for:
 * <ol>
 * <li>Loading any resources (Images, Audio, etc.) that your game will use.</li>
 * <li>Managing all of the resources loaded through the ContentManager</li>
 * <li>Unloading your Game's resources when you are done with them</li>
 * </ol>
 * 
 * @author Epichunter
 * 
 */
public final class ContentManager {

	Game game;
	private Hashtable<String, Object> loadedAssets;

	public ContentManager(Game game) {
		this.game = game;
		loadedAssets = new Hashtable<String, Object>();
	}

	/**
	 * Loads an Audio file. </br> </br> Example:
	 * 
	 * <pre>
	 * {@code}
	 * public void loadContent(ContentManager content){
	 * 	Audio song = content.loadAudio("sarudeDanstorm.mp3");
	 *  Audio otherSong = content.loadAudio("/resources/songs/XFilesThemeSong.wav");
	 * }
	 * 
	 * </pre>
	 * 
	 * @param path
	 *            the path of the Audio file
	 * @return the Audio file located at <b>path</b>
	 */
	public Audio loadAudio(String path) {

		if (!loadedAssets.containsKey(path)) {

			try {
				Audio newAudio = new Audio(getFile(path));
				loadedAssets.put(path, newAudio);
				return newAudio;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}

		} else {
			return (Audio) loadedAssets.get(path);
		}
	}

	/**
	 * Loads an Image file. </br> </br> Example:
	 * 
	 * <pre>
	 * {@code}
	 * public void loadContent(ContentManager content){
	 * 	 Image memes = content.loadImage("pepe.jpg");
	 * 	 Image car = content.loadImage("/resources/media/car.png");
	 * }
	 * 
	 * </pre>
	 * 
	 * @param path
	 *            the path of the Image file
	 * @return the Image file located at <b>path</b>
	 */
	public Image loadImage(String path) {

		if (!loadedAssets.containsKey(path)) {
			try {
				Image newImage = ImageIO.read(getFile(path));
				loadedAssets.put(path, newImage);
				return newImage;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return (Image) loadedAssets.get(path);
		}

	}

	/**
	 * Loads an Object from a file. It is best if the Object extends
	 * java.io.Serializable </br> </br> Example:
	 * 
	 * <pre>
	 * {@code}
	 * public void loadContent(ContentManager content){
	 * 
	 * 	MyObject obj1 = content.loadAudio("someInstance.ser");
	 *  	MyObject obj2 = content.loadAudio("/resources/songs/anotherInstance.txt");
	 * }
	 * 
	 * </pre>
	 * 
	 * @param path
	 *            the path of the Object file
	 * @return the Object file located at <b>path</b>
	 * @see <a
	 *      href="https://docs.oracle.com/javase/tutorial/jndi/objects/serial.html">
	 *      Oracle Serializable Tutorial </a>
	 */

	public <T> T loadObject(String path) {
		if (!loadedAssets.containsKey(path)) {
			try {
				File file = getFile(path);
				T loadObj;
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object o = ois.readObject();

				loadObj = (T) ois.readObject();

				ois.close();
				fis.close();
				loadedAssets.put(path, loadObj);
				return loadObj;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return (T) loadedAssets.get(path);
		}
	}

	/**
	 * Saves an Object to a file. The object should implement
	 * java.io.Serializable.
	 * 
	 * @param obj
	 *            The Object to be saved
	 * @param path
	 *            the location of the file being used to store <b>obj</b>
	 * @return true if the Object was saved correctly, false otherwise
	 */
	public boolean saveObject(Serializable obj, String path) {
		try {
			File file = getFile(path);
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.close();
			fos.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Loads a Font from a file with a type. The Font has a size of 1, a Plain
	 * style, and is a TrueType Font. </br></br>Example:
	 * 
	 * <pre>
	 * {@code}
	 * public void loadContent(ContentManager content){
	 * 
	 * 	Font timesNewRoman = content.loadFont("timesNewRoman.ttf");
	 *  	Font arial = content.loadFont("/resources/fonts/arial.ttf");
	 * }
	 * 
	 * </pre>
	 * 
	 * @param path
	 *            the path of the Font file
	 * @return the Font file located at <b>path</b>
	 */
	public Font loadFont(String path) {
		if (!loadedAssets.containsKey(path)) {

			try {
				Font newFont = Font.createFont(Font.TRUETYPE_FONT,
						getFile(path));
				loadedAssets.put(path, newFont);
				return newFont;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} catch (FontFormatException e) {
				e.printStackTrace();
				return null;
			}

		} else {
			return (Font) loadedAssets.get(path);
		}
	}

	/**
	 * Loads a Font from a file. </br></br>Example:
	 * 
	 * <pre>
	 * {@code}
	 * public void loadContent(ContentManager content){
	 * 
	 * 	Font timesNewRoman = content.loadFont("timesNewRoman.ttf");
	 *  	Font arial = content.loadFont("/resources/fonts/arial.ttf");
	 * }
	 * 
	 * </pre>
	 * 
	 * @param path
	 *            the path of the Font file
	 * @param fontFormat
	 *            the type of the Font
	 * @param fontSize
	 *            the size of the Font
	 * @return the Font file located at <b>path</b>
	 */
	public Font loadFont(String path, int fontFormat, float fontSize) {
		if (!loadedAssets.containsKey(path)) {

			try {
				Font newFont = Font.createFont(fontFormat, getFile(path))
						.deriveFont(fontSize);
				loadedAssets.put(path, newFont);
				return newFont;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} catch (FontFormatException e) {
				e.printStackTrace();
				return null;
			}

		} else {
			return (Font) loadedAssets.get(path);
		}
	}

	/**
	 * Loads a File. </br> </br> Example:
	 * 
	 * <pre>
	 * {@code}
	 * public void loadContent(ContentManager content){
	 * 	File file = content.loadFile("someFile.txt");
	 *  File file2 = content.loadFile("/resources/files/anotherFile.dll");
	 * }
	 * 
	 * </pre>
	 * 
	 * @param path
	 *            the path of the File
	 * @return the File located at <b>path</b>
	 */
	public File loadFile(String path) {
		if (!loadedAssets.containsKey(path)) {

			try {
				File newFile = getFile(path);
				loadedAssets.put(path, newFile);
				return newFile;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}

		} else {
			return (File) loadedAssets.get(path);
		}
	}
	/**
	 * Loads an XML File.</br></br>Example:
	 * <pre>
	 * {@code}
	 * public void loadContent(ContentManager content){
	 * 	Document xmlFile = content.loadXMLDocument("someFile.xml");
	 *  Document xmlFile2 = content.loadXMLDocument("/resources/data/anotherFile.xml");
	 * }
	 * 
	 * </pre>
	 * @param path the path to the XML file
	 * @return an Xml Document
	 */
	public Document loadXMLDocument(String path) {
		if (!loadedAssets.containsKey(path)) {
			try {
				File xmlFile = getFile(path);
				DocumentBuilder build = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder();
				Document doc = build.parse(xmlFile);
				doc.getDocumentElement().normalize();
				loadedAssets.put(path, doc);
				return doc;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
				return null;
			} catch (SAXException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return (Document) loadedAssets.get(path);
		}
	}
	/**
	 * Releases all of the resources managed by the ContentManager. This method
	 * should be called in the unloadContent() method.
	 */
	public void unload() {
		Collection<Object> resources = loadedAssets.values();
		for (Object r : resources) {
			if (r instanceof skltl.media.Disposable) {
				((skltl.media.Disposable) r).dispose();
			}
		}
		loadedAssets.clear();
	}

	private File getFile(String path) throws IOException {
		try {
			
			File f = new File(game.getClass().getResource(path).toURI());
			if (!f.exists()) {
				throw new IOException();
			} else {
				return f;
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}
}
