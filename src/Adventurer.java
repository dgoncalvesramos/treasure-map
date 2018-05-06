import java.util.List;

public class Adventurer extends AbstractGameElement {
	private String name;
	private Direction direction;
	private List<Movement> movements;
	private int nbTreasuresCollected;

	public Adventurer(Coordinates coord, String name, Direction direction, List<Movement> movements,
			int nbTreasuresCollected) {
		super.coord = coord;
		this.name = name;
		this.direction = direction;
		this.movements = movements;
		this.nbTreasuresCollected = nbTreasuresCollected;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public String getName() {
		return name;
	}

	public int getNbTreasureCollected() {
		return nbTreasuresCollected;
	}

	public void collectTreasure() {
		nbTreasuresCollected += 1;
	}

	public Movement walk() {
		if (movements.isEmpty())
			return null;
		else
			return movements.remove(0);
	}
	
	public boolean StillMovements(){
		return (!movements.isEmpty());
	}

	@Override
	public String toString() {
		return "A - " + name.trim() + super.toString() + " - " + direction.getDirectionValue() + " - "
				+ this.nbTreasuresCollected;
	}
}