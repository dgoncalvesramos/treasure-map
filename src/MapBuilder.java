import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This MapBuilder class provides the methods that are used to build a instance
 * of a {@link Map} from an input file. This class implements
 * {@link MapCheckers} and build the Map with all the game elements if the
 * definition is valid.
 * 
 * @author dgoncalves
 *
 */

public class MapBuilder implements MapCheckers {
	private static final int EXIT_READ_FILE_FAILURE = 1;
	private BufferedReader bf;
	private GameElementBuilder gameElementBuilder;

	public MapBuilder(String fileName) {
		try {
			bf = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(MapBuilder.EXIT_READ_FILE_FAILURE);
		}

		gameElementBuilder = new GameElementBuilder();
	}

	public Map buildMapFromFile() {
		Map gameMap = null;
		int cptLine = 1;
		String text;

		try {
			while ((text = bf.readLine()) != null) {
				/*
				 * minus signs in front of numbers are automatically removed with this split
				 * thus negative number become positive by default
				 */
				String[] fileArray = text.split("-");

				if (fileArray[0].startsWith("#")) {
					// do nothing, ignore line
				}
				/* Instanciate Map */
				else if (cptLine == 1 && fileArray[0].trim().equalsIgnoreCase("c")) {
					gameMap = checkAndInstantiateMap(fileArray);
				}

				else if (cptLine > 1 && fileArray[0].trim().equalsIgnoreCase("c")) {
					throw new ReadFileException(
							"Error at " + text + " at line " + cptLine + " : map can not be defined twice !");
				} else if (cptLine > 1 && fileArray[0].trim().equalsIgnoreCase("m")) {
					Mountain moutain = gameElementBuilder.checkAndBuildMountain(text, cptLine, fileArray,
							gameMap.getWidth(), gameMap.getHeight());
					checkIfElementCanBeInsertedAndUpdateMap(gameMap, moutain, cptLine, text);
				} else if (cptLine > 1 && fileArray[0].trim().equalsIgnoreCase("a")) {
					Adventurer adventurer = gameElementBuilder.checkAndBuildAdventurer(text, cptLine, fileArray,
							gameMap.getWidth(), gameMap.getHeight());
					checkIfElementCanBeInsertedAndUpdateMap(gameMap, adventurer, cptLine, text);
					gameMap.addAdventurerToList(adventurer);
				} else if (cptLine > 1 && fileArray[0].trim().equalsIgnoreCase("t")) {
					Treasure treasure = gameElementBuilder.checkAndBuildTreasure(text, cptLine, fileArray,
							gameMap.getWidth(), gameMap.getHeight());
					checkIfElementCanBeInsertedAndUpdateMap(gameMap, treasure, cptLine, text);
				} else {
					if (cptLine > 1)
						throw new ReadFileException("Content :" + text + " at line " + cptLine + " is not valid !");
					throw new ReadFileException("Error at " + text + " at line " + cptLine
							+ " : map needs to be defined at the first line of the input file !");
				}

				cptLine += 1;
			}
		} catch (IOException | ReadFileException e) {
			e.printStackTrace();
			System.exit(MapBuilder.EXIT_READ_FILE_FAILURE);
		} finally {
			try {
				bf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return gameMap;
	}

	private int isInteger(String s) throws NumberFormatException {
		return Integer.parseInt(s, 10);
	}

	@Override
	public Map checkAndInstantiateMap(String[] aSplitLine) throws ReadFileException {
		int width, height;
		if (aSplitLine.length != 3)
			throw new ReadFileException("Map definition must follow the format C - {width} - {height} !");
		try {
			width = isInteger(aSplitLine[1].trim());
			height = isInteger(aSplitLine[2].trim());
		} catch (NumberFormatException e) {
			throw new ReadFileException("Map definition size numbers {width} - {height} must be valid integers !", e);
		}
		return new Map(width, height);
	}

	@Override
	public Map checkIfElementCanBeInsertedAndUpdateMap(Map gameMap, AbstractGameElement gameElement, int atLine,
			String aLine) throws ReadFileException {
		if (gameMap.getElement(gameElement.getCoord()) == null)
			gameMap.addGameElement(gameElement);
		else
			throw new ReadFileException(
					"Error at " + aLine + " at line " + atLine + " a game element is already inserted at {"
							+ gameElement.getXPosFromCoord() + "}" + " {" + gameElement.getYPosFromCoord() + "}");
		return gameMap;
	}
}