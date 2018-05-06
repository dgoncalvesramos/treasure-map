public abstract class AbstractGameElement {
	protected Coordinates coord;

	public Coordinates getCoord() {
		return coord;
	}

	public int getXPosFromCoord() {
		return coord.getX();
	}

	public int getYPosFromCoord() {
		return coord.getY();
	}

	public void setCoord(Coordinates coord) {
		this.coord = coord;
	}

	public void setXCoord(int x) {
		coord.setX(x);
	}

	public void setYCoord(int y) {
		coord.setY(y);
	}

	@Override
	public String toString() {
		return " - " + getXPosFromCoord() + " - " + getYPosFromCoord();
	}
}