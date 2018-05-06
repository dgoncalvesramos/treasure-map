public enum Direction {
	NORTH('N'), SOUTH('S'), EAST('E'), WEST('O');

	private char value;

	Direction(char value) {
		this.value = value;
	}

	public char getDirection() {
		return value;
	}
}