public interface MapCheckers {

	/**
	 * Verifies the value provided for the definition of the Map.
	 * 
	 * @param aSplitLine
	 *            a split array line with "-" delimiter read from the file
	 * @throws a
	 *             ReadFileException if definition is not valid
	 * @return Map object that represents the Map of the game if definition is valid
	 */
	public Map checkAndInstantiateMap(String[] aSplitLine) throws ReadFileException;

	/**
	 * Verifies if an Element can be inserted in the map at the position provided by
	 * his coordinates
	 * 
	 * @param gameMap
	 *            a instance of the current map of the game
	 * @param gameElement
	 *            that we want to insert in the map
	 * @param atLine
	 *            the number of the current line read from the input file (for
	 *            printing clear error messages purposes)
	 * @param aLine
	 *            the current line read from the input file (for printing clear
	 *            error messages purposes)
	 * @return a Map updated with the new element inserted
	 * @throws a
	 *             ReadFileException if the element can not be inserted at this
	 *             position because an other element is already define
	 */
	public Map checkIfElementCanBeInsertedAndUpdateMap(Map gameMap, AbstractGameElement gameElement, int atLine,
			String aLine) throws ReadFileException;
}