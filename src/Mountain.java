public class Mountain extends AbstractGameElement {
	public Mountain(Coordinates coord) {
		super.coord = coord;
	}

	@Override
	public String toString() {
		return "M" + super.toString();
	}
}