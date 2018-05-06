import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Game {
	private Map map;

	public Game(String inFile) {
		map = new MapBuilder(inFile).buildMapFromFile();
	}

	public void play() {
		boolean finished = false;
		ArrayList<Adventurer> adventurers = map.getAdventurers();

		if (!adventurers.isEmpty()) {
			ExecutorService es = Executors.newCachedThreadPool();

			for (Adventurer adventurer : adventurers) {
				es.execute(() -> makeAdventurersWalk(adventurer));
			}

			es.shutdown();
			try {
				// must complete in less than one minute
				finished = es.awaitTermination(1, TimeUnit.MINUTES);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (finished)
				writeResult();
			else
				System.out.println("Error not all tasks have been finished, result file might be corrupted !");
		}

		// no adventurers in the game
		else {
			writeResult();
		}
	}

	private void writeResult() {
		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new FileWriter(new File("result.txt")));
			out.write(map.toString());
			System.out.println("File result.txt has been generated !");
		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private synchronized void makeAdventurersWalk(Adventurer adventurer) {
		Movement curMov;
		AbstractGameElement gameElemAtNextPos, gameElementAtPrevPos;
		int nextX, nextY;

		while ((curMov = adventurer.walk()) != null) {

			// if adventurer walk
			if (curMov == Movement.WALK) {
				Coordinates coordAdventurer = adventurer.getCoord();

				switch (adventurer.getDirection()) {
				case NORTH:
					// check if next move is in the map
					if ((nextY = coordAdventurer.getY() - 1) >= 0) {

						// Get the game element at the position where we want to move the adventurer
						gameElemAtNextPos = map.getElement(new Coordinates(coordAdventurer.getX(), nextY));

						// Get the game element at the current position that we want to remove if it is
						// the adventurer
						gameElementAtPrevPos = map
								.getElement(new Coordinates(coordAdventurer.getX(), coordAdventurer.getY()));

						// Move adventurer if plain element -> map.getElement==null
						if (gameElemAtNextPos == null) {
							map.removeGameElement(gameElementAtPrevPos);
							adventurer.setYCoord(nextY);
							map.addGameElement(adventurer);
						}
						// Move adventurer and collect treasure
						else if (gameElemAtNextPos instanceof Treasure) {
							map.removeGameElement(gameElementAtPrevPos);
							adventurer.setYCoord(nextY);
							adventurer.collectTreasure();

							// remove treasure from tab if there is no more to collect
							if (((Treasure) gameElemAtNextPos).collectTreasure() == 0) {
								map.removeTreasure((Treasure) gameElemAtNextPos);
								map.addGameElement(adventurer);
							}
						}
					}
					break;

				case SOUTH:
					if ((nextY = coordAdventurer.getY() + 1) < map.getHeight()) {
						gameElemAtNextPos = map.getElement(new Coordinates(coordAdventurer.getX(), nextY));
						gameElementAtPrevPos = map
								.getElement(new Coordinates(coordAdventurer.getX(), coordAdventurer.getY()));

						if (gameElemAtNextPos == null) {
							map.removeGameElement(gameElementAtPrevPos);
							adventurer.setYCoord(nextY);
							map.addGameElement(adventurer);
						} else if (gameElemAtNextPos instanceof Treasure) {
							map.removeGameElement(gameElementAtPrevPos);
							adventurer.setYCoord(nextY);
							adventurer.collectTreasure();

							if (((Treasure) gameElemAtNextPos).collectTreasure() == 0) {
								map.removeTreasure((Treasure) gameElemAtNextPos);
								map.addGameElement(adventurer);
							}
						}
					}
					break;

				case EAST:
					if ((nextX = coordAdventurer.getX() - 1) >= 0) {
						gameElemAtNextPos = map.getElement(new Coordinates(nextX, coordAdventurer.getY()));
						gameElementAtPrevPos = map
								.getElement(new Coordinates(coordAdventurer.getX(), coordAdventurer.getY()));

						if (gameElemAtNextPos == null) {
							map.removeGameElement(gameElementAtPrevPos);
							adventurer.setXCoord(nextX);
							map.addGameElement(adventurer);
						} else if (gameElemAtNextPos instanceof Treasure) {
							map.removeGameElement(gameElementAtPrevPos);
							adventurer.setXCoord(nextX);
							adventurer.collectTreasure();

							if (((Treasure) gameElemAtNextPos).collectTreasure() == 0) {
								map.removeTreasure((Treasure) gameElemAtNextPos);
								map.addGameElement(adventurer);
							}
						}
						break;
					}

				case WEST:
					if ((nextX = coordAdventurer.getX() + 1) < map.getWidth()) {
						gameElemAtNextPos = map.getElement(new Coordinates(nextX, coordAdventurer.getY()));
						gameElementAtPrevPos = map
								.getElement(new Coordinates(coordAdventurer.getX(), coordAdventurer.getY()));

						if (gameElemAtNextPos == null) {
							map.removeGameElement(gameElementAtPrevPos);
							adventurer.setXCoord(nextX);
							map.addGameElement(adventurer);
						} else if (gameElemAtNextPos instanceof Treasure) {
							map.removeGameElement(gameElementAtPrevPos);
							adventurer.setXCoord(coordAdventurer.getX() + 1);
							adventurer.collectTreasure();

							if (((Treasure) gameElemAtNextPos).collectTreasure() == 0) {
								map.removeTreasure((Treasure) gameElemAtNextPos);
								map.addGameElement(adventurer);
							}
						}
						break;
					}
				}
			} else if (curMov == Movement.RIGHT) {
				switch (adventurer.getDirection()) {
				case NORTH:
					adventurer.setDirection(Direction.WEST);
					break;
				case SOUTH:
					adventurer.setDirection(Direction.EAST);
					break;
				case EAST:
					adventurer.setDirection(Direction.NORTH);
					break;
				case WEST:
					adventurer.setDirection(Direction.SOUTH);
					break;
				}
			} else if (curMov == Movement.LEFT) {
				switch (adventurer.getDirection()) {
				case NORTH:
					adventurer.setDirection(Direction.EAST);
					break;
				case SOUTH:
					adventurer.setDirection(Direction.WEST);
					break;
				case EAST:
					adventurer.setDirection(Direction.SOUTH);
					break;
				case WEST:
					adventurer.setDirection(Direction.NORTH);
					break;
				}
			}
		}
	}
}