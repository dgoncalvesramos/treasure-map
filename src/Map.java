import java.util.ArrayList;

public class Map {
	private int width;
	private int height;
	private AbstractGameElement[][] tabGameElements;

	// FIX - we need a list of adventurers to know the priority while walking
	private ArrayList<Adventurer> adventurers;

	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		tabGameElements = new AbstractGameElement[height][width];
		adventurers = new ArrayList<>();
	}

	public void addGameElement(AbstractGameElement gameElement) {
		/*
		 * X pos and Y pos must be reverse in the tabGameElements if we want to keep a
		 * logical order because the axis of the map is reverse
		 */
		tabGameElements[gameElement.getYPosFromCoord()][gameElement.getXPosFromCoord()] = gameElement;
	}

	public void removeGameElement(AbstractGameElement gameElement) {
		// FIX - prevent removing treasure and mountains from the map
		if (!(gameElement instanceof Treasure) && !(gameElement instanceof Mountain))
			tabGameElements[gameElement.getYPosFromCoord()][gameElement.getXPosFromCoord()] = null;
	}

	public void removeTreasure(Treasure treasure) {
		tabGameElements[treasure.getYPosFromCoord()][treasure.getXPosFromCoord()] = null;
	}

	public AbstractGameElement getElement(Coordinates coordGameElement) {
		return tabGameElements[coordGameElement.getY()][coordGameElement.getX()];
	}

	public void addAdventurerToList(Adventurer adventurer) {
		adventurers.add(adventurer);
	}

	public ArrayList<Adventurer> getAdventurers() {
		return adventurers;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		String res = "", m = "", t = "", a = "";
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (tabGameElements[i][j] != null) {
					if (tabGameElements[i][j].toString().startsWith("M"))
						m += tabGameElements[i][j].toString() + "\n";
					else if (tabGameElements[i][j].toString().startsWith("T"))
						t += tabGameElements[i][j].toString() + "\n";
					else
						a += tabGameElements[i][j].toString() + "\n";
				}
			}
		}
		return "C - " + this.getWidth() + " - " + this.getHeight() + "\n" + res.concat(m).concat(t).concat(a);
	}
}