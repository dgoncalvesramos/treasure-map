public class Treasure extends AbstractGameElement {
	private int nbTreasure;

	public Treasure(Coordinates coord, int nbTreasure) {
		super.coord = coord;
		this.nbTreasure = nbTreasure;
	}

	public int collectTreasure() {
		nbTreasure -= 1;
		return nbTreasure;
	}

	@Override
	public String toString() {
		return "T" + super.toString() + " - " + nbTreasure;
	}
}