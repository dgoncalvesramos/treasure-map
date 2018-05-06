import java.util.List;

public class GameElementFactory {
	public static final int ADVENTURER = 1;
	public static final int MOUNTAIN = 2;
	public static final int PLAIN = 3;
	public static final int TREASURE = 4;

	public AbstractGameElement getGameElement(int typeGameElement, Coordinates coord, int nbTreasure, String name,
			Direction direction, List<Movement> movements) throws ReadFileException {

		AbstractGameElement gameElement = null;

		switch (typeGameElement) {
		case ADVENTURER:
			gameElement = new Adventurer(coord, name, direction, movements, nbTreasure);
			break;
		case MOUNTAIN:
			gameElement = new Mountain(coord);
			break;
		case TREASURE:
			gameElement = new Treasure(coord, nbTreasure);
			break;
		default:
			throw new ReadFileException("Error, this element is not defined !");
		}

		return gameElement;
	}
}