import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Game {
	private Map map;

	public Game(String inFile) {
		map = new MapBuilder(inFile).buildMapFromFile();
	}

	public void play() {
		ArrayList<Adventurer> adventurers = map.getAdventurers();

		if (!adventurers.isEmpty()) {
			int len = adventurers.size();
			int i = 0;

			while (i < len) {
				Adventurer adventurer = adventurers.get(i);
				makeAdventurerWalk(adventurer);
				i++;
				if (i == len) {
					for (int j = 0; j < adventurers.size(); j++) {
						if (adventurers.get(j).StillMovements()) {
							i = j;
							break;
						}
					}
				}
			}
			writeResult();
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

	private void moveAventurerToNextPosition(Adventurer adventurer, int prevX, int prevY, int nextX, int nextY) {
		AbstractGameElement gameElemAtNextPos, gameElementAtPrevPos;

		// Get the game element at the position where we want to move the adventurer
		gameElemAtNextPos = map.getElement(new Coordinates(nextX, nextY));

		// Get the game element at the current position that we want to remove if it is
		// the adventurer and not a treasure
		gameElementAtPrevPos = map.getElement(new Coordinates(prevX, prevY));

		// Move the adventurer to position if it is a plain element ->
		// map.getElement==null
		if (gameElemAtNextPos == null) {

			// check if adventurer was on a treasure at last position and unset flag
			if (gameElementAtPrevPos instanceof Treasure)
				((Treasure) gameElementAtPrevPos).unsetAdventurerOnPos();

			// remove the element at the previous position
			map.removeGameElement(gameElementAtPrevPos);

			// update the coordinates of the adventurer
			adventurer.setXCoord(nextX);
			adventurer.setYCoord(nextY);

			// add the adventurer at new position
			map.addGameElement(adventurer);
		}
		// Move the adventurer to position if it is a treasure and if there is no other
		// adventurers at this position
		else if (gameElemAtNextPos instanceof Treasure && !((Treasure) gameElemAtNextPos).isAdventurerOnPos()) {
			// set a flag to tell to the other adventurers an adventurer is already at the
			// position of this treasure
			((Treasure) gameElemAtNextPos).setAdventurerOnPos();
			map.removeGameElement(gameElementAtPrevPos);

			// update the coordinates of the adventurer
			adventurer.setXCoord(nextX);
			adventurer.setYCoord(nextY);

			// collect treasure
			adventurer.collectTreasure();

			// remove treasure from tab if there is no more to collect and add adventurer at
			// this position
			if (((Treasure) gameElemAtNextPos).collectTreasure() == 0) {
				map.removeTreasure((Treasure) gameElemAtNextPos);
				map.addGameElement(adventurer);
			}
		}
	}

	private void switchDirectionOfAventurer(Adventurer adventurer, Movement curMov) {
		if (curMov == Movement.RIGHT) {
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
		} else {
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

	private void makeAdventurerWalk(Adventurer adventurer) {
		Movement curMov;
		int posX, posY;

		if ((curMov = adventurer.walk()) == null)
			return;

		// if adventurer walk
		if (curMov == Movement.WALK) {
			posX = adventurer.getXPosFromCoord();
			posY = adventurer.getYPosFromCoord();

			switch (adventurer.getDirection()) {
			case NORTH:
				// check if next move is in the map
				if (posY - 1 >= 0)
					moveAventurerToNextPosition(adventurer, posX, posY, posX, posY - 1);
				break;

			case SOUTH:
				if (posY + 1 < map.getHeight())
					moveAventurerToNextPosition(adventurer, posX, posY, posX, posY + 1);
				break;

			case EAST:
				if (posX - 1 >= 0)
					moveAventurerToNextPosition(adventurer, posX, posY, posX - 1, posY);
				break;

			case WEST:
				if (posX + 1 < map.getWidth())
					moveAventurerToNextPosition(adventurer, posX, posY, posX + 1, posY);
				break;
			}
		} else {
			switchDirectionOfAventurer(adventurer, curMov);
		}
	}
}
