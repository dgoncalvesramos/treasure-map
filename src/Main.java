public class Main {
	public static void main(String args[]) {
		if (args.length != 1) {
			System.out.println("Usage : java Main input_file");
			System.exit(1);
		}

		Game game = new Game(args[0]);
		game.play();
	}
}
