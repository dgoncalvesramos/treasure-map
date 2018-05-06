/**
 * This GameElementCheckers interface provides a definition of the methods that
 * are used to check the values read from the input file. This interface is
 * implemented by {@link GameElementBuilder}
 * 
 * @author dgoncalves
 *
 */

public interface GameElementCheckers {

	/**
	 * Verifies the value provided for the definition of a Mountain.
	 * 
	 * @param aLine
	 *            the current line read from the input file for printing error
	 *            messages purposes
	 * @param atLine
	 *            the number of the current line read from the input file for
	 *            printing error messages purposes
	 * @param aSplitLine
	 *            a split array line with "-" delimiter read from the file
	 * @throws a
	 *             ReadFileException if definition is not valid
	 * @return a Mountain element if definition is valid
	 */
	public Mountain checkAndBuildMountain(String aLine, int atLine, String[] aSplitLine, int mapWidth, int mapHeight)
			throws ReadFileException;

	/**
	 * Verifies the value provided for the definition of a Treasure.
	 * 
	 * @param aLine
	 *            the current line read from the input file for printing error
	 *            messages purposes
	 * @param atLine
	 *            the number of the current line read from the input file for
	 *            printing error messages purposes
	 * @param atLine
	 *            the number of the current line read from the input file for
	 *            printing error messages purposes
	 * @param aSplitLine
	 *            a split array line with "-" delimiter read from the file
	 * @throws a
	 *             ReadFileException if definition is not valid
	 * @return a Treasure element if definition is valid
	 */
	public Treasure checkAndBuildTreasure(String aLine, int atLine, String[] aSplitLine, int mapWidth, int mapHeight)
			throws ReadFileException;

	/**
	 * Verifies the value provided for the definition of an Adventurer.
	 * 
	 * @param aLine
	 *            the current line read from the input file for printing error
	 *            messages purposes
	 * @param atLine
	 *            the number of the current line read from the input file for
	 *            printing error messages purposes
	 * @param aSplitLine
	 *            a split array line with "-" delimiter read from the file
	 * @throws a
	 *             ReadFileException if definition is not valid
	 * @return an Adventurer element is definition is valid
	 */
	public Adventurer checkAndBuildAdventurer(String aLine, int atLine, String[] aSplitLine, int mapWidth,
			int mapHeight) throws ReadFileException;
}