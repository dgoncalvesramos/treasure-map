public class Treasure extends AbstractGameElement {
	private int nbTreasure;
	private boolean isAdventurerOnPos;

	public Treasure(Coordinates coord, int nbTreasure) {
		super.coord = coord;
		this.nbTreasure = nbTreasure;
		isAdventurerOnPos = false;
	}

	public int collectTreasure() {
		nbTreasure -= 1;
		return nbTreasure;
	}

	public void setAdventurerOnPos() {
		isAdventurerOnPos = true;
	}
	
	public void unsetAdventurerOnPos() {
		isAdventurerOnPos = false;
	}

	public boolean isAdventurerOnPos() {
		return isAdventurerOnPos;
	}
	
	@Override
	public String toString() {
		return "T" + super.toString() + " - " + nbTreasure;
	}
}