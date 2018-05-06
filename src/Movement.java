public enum Movement {
	WALK('A'), LEFT('G'), RIGHT('D');

	private char value;

	Movement(char value) {
		this.value = value;
	}

	public char getMovement() {
		return value;
	}

}