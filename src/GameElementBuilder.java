import java.util.ArrayList;
import java.util.List;

/**
 * This GameElementBuilder class provides the methods that are used to build the
 * different elements of the game. This class implements
 * {@link GameElementCheckers} and build the specific elements if their
 * definition is valid.
 * 
 * @author dgoncalves
 *
 */
public class GameElementBuilder implements GameElementCheckers {
	private GameElementFactory gameElementFactory;

	public GameElementBuilder() {
		gameElementFactory = new GameElementFactory();
	}

	private int isInteger(String s) throws NumberFormatException {
		return Integer.parseInt(s, 10);
	}

	private boolean checkIfCoordIsInMap(int x, int y, int mapWidth, int mapHeight) {
		return (x < mapWidth && y < mapHeight);
	}

	@Override
	public Mountain checkAndBuildMountain(String aLine, int atLine, String[] aSplitLine, int mapWidth, int mapHeight)
			throws ReadFileException {
		int x, y;

		if (aSplitLine.length != 3)
			throw new ReadFileException("Error at " + aLine + " at line " + atLine
					+ " : moutain definition must follow the format M - {x} - {y}");
		try {
			x = isInteger(aSplitLine[1].trim());
			y = isInteger(aSplitLine[2].trim());
		} catch (NumberFormatException e) {
			throw new ReadFileException("Error at " + aLine + " at line " + atLine
					+ " : the coordinates {x} - {y} provided for the moutain must be valid integers", e);
		}
		if (!checkIfCoordIsInMap(x, y, mapWidth, mapHeight))
			throw new ReadFileException("Error at " + aLine + " at line " + atLine
					+ " : the coordinates {x} - {y} provided for the moutain are not in the map");
		return (Mountain) gameElementFactory.getGameElement(GameElementFactory.MOUNTAIN, new Coordinates(x, y), 0, null,
				null, null);
	}

	@Override
	public Treasure checkAndBuildTreasure(String aLine, int atLine, String[] aSplitLine, int mapWidth, int mapHeight)
			throws ReadFileException {
		int x, y, z;

		if (aSplitLine.length != 4)
			throw new ReadFileException("Error at " + aLine + " at line " + atLine
					+ " : treasure definition must follow the format T - {x} - {y} - {nbTreasures}");
		try {
			x = isInteger(aSplitLine[1].trim());
			y = isInteger(aSplitLine[2].trim());
			z = isInteger(aSplitLine[3].trim());
		} catch (NumberFormatException e) {
			throw new ReadFileException("Error at " + aLine + " at line " + atLine
					+ " : the coordinates {x} - {y} and {nbTreasure} provided for the treasure must be valid integers",
					e);
		}
		if (!checkIfCoordIsInMap(x, y, mapWidth, mapHeight))
			throw new ReadFileException("Error at " + aLine + " at line " + atLine
					+ " : the coordinates {x} - {y} provided for the treasure are out of the map");
		if (z == 0)
			throw new ReadFileException("Error at " + aLine + " at line " + atLine
					+ " : the number of treasure {nbTreasures} must be positive");
		return (Treasure) gameElementFactory.getGameElement(GameElementFactory.TREASURE, new Coordinates(x, y), z, null,
				null, null);
	}

	@Override
	public Adventurer checkAndBuildAdventurer(String aLine, int atLine, String[] aSplitLine, int mapWidth,
			int mapHeight) throws ReadFileException {
		int x, y;
		Direction direction = null;
		List<Movement> movements = new ArrayList<>();

		if (aSplitLine.length != 6)
			throw new ReadFileException("Error at " + aLine + " at line " + atLine
					+ " : adventurer definition must follow the format A - {Name} - {x} - {y} - {direction} - {movements}");
		try {
			x = isInteger(aSplitLine[2].trim());
			y = isInteger(aSplitLine[3].trim());
		} catch (NumberFormatException e) {
			throw new ReadFileException("Error at " + aLine + " at line " + atLine
					+ " : the coordinates {x} - {y} provided for the adventurer must be valid integers", e);
		}
		if (!checkIfCoordIsInMap(x, y, mapWidth, mapHeight))
			throw new ReadFileException("Error at " + aLine + " at line " + atLine
					+ " : the coordinates {x} - {y} provided for the adventurer are out of the map");

		/* check if the direction provided is valid (in Direction enum) */
		for (Direction d : Direction.values()) {
			if (d.getDirection() == aSplitLine[4].trim().toUpperCase().charAt(0))
				direction = d;
		}
		if (direction == null)
			throw new ReadFileException("Error at " + aLine + " at line " + atLine
					+ " : the direction provided for the adventurer is not valid");

		/* check if the movement provided is valid (in Movement enum) */
		boolean flagMovementValid;
		int cpt = 0;
		char[] aSplitLineToCharArray = aSplitLine[5].trim().toUpperCase().toCharArray();

		for (char c : aSplitLineToCharArray) {
			cpt = 0;
			flagMovementValid = false;

			for (Movement mv : Movement.values()) {
				cpt += 1;
				if (c == mv.getMovement()) {
					flagMovementValid = true;
					movements.add(mv);
				}
				// one value provided is not correct
				else if (!flagMovementValid && cpt == Movement.values().length) {
					throw new ReadFileException("Error at " + aLine + " at line " + atLine
							+ " : the movements provided for the adventurer are not valid");
				}
			}
		}

		return (Adventurer) gameElementFactory.getGameElement(GameElementFactory.ADVENTURER, new Coordinates(x, y), 0,
				aSplitLine[1], direction, movements);
	}
}