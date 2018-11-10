
import java.util.*;


/**
 * @author Sutter Phillips
 * @author Khalil Adams
 */
public class AutoPlayer extends AbstractAutoPlayer {
	List <String> word = new ArrayList<String>();
	List <BoardCell> spots = new ArrayList<BoardCell>();
	
	/**
	 * @param cell 
	 * @param size- size of board
	 * @return cells which are valid options that are next to the given cell
	 */
	public List<BoardCell> neighbors(BoardCell cell, int size){
		int row = cell.getRow();
		int col = cell.getCol();
		List<BoardCell> neighbors = new ArrayList<BoardCell>();
		neighbors.add(new BoardCell(row-1,col-1));
		neighbors.add(new BoardCell(row-1,col));
		neighbors.add(new BoardCell(row-1,col+1));
		neighbors.add(new BoardCell(row,col-1));
		neighbors.add(new BoardCell(row,col+1));
		neighbors.add(new BoardCell(row+1,col-1));
		neighbors.add(new BoardCell(row+1,col));
		neighbors.add(new BoardCell(row+1,col+1));
		for(int i = 0; i < neighbors.size(); i++) {
			BoardCell checkCell = neighbors.get(i);
			int c = checkCell.getCol();
			int r = checkCell.getRow();
			if (c < 0 || r < 0 || c >= size || r >= size || spots.contains(checkCell)) {
				i--;
				neighbors.remove(checkCell);
			}
		}
		return neighbors;
		
	}
	
	/**
	 * adds words formed from given prefix through the position to a word list
	 * 
	 * @param cell cell which contains the last letter of prefix
	 * @param lex valid word lexicon 
	 * @param board board on which to form words
	 * @param prefix proceeding letters
	 */
	public void check(BoardCell cell, ILexicon lex, BoggleBoard board, String prefix) {
		spots.add(cell);
		List<BoardCell> neighbors = neighbors(cell,board.size());
		for(BoardCell nextCell : neighbors) {
			String checkString = prefix + board.getFace(nextCell);
			LexStatus status = lex.wordStatus(checkString);
			if(status.equals(LexStatus.WORD)){
				word.add(checkString);
			}
			if(status.equals(LexStatus.WORD)||status.equals(LexStatus.PREFIX)) {
				
				check(nextCell, lex, board, checkString);
			}
		}spots.remove(cell);
	}
    
    /* (non-Javadoc)
     * @see AbstractAutoPlayer#findAllValidWords(BoggleBoard, ILexicon)
     */
    public List<String> findAllValidWords(BoggleBoard board, ILexicon lex) {
    	int size = board.size();
    	for(int i = 0; i < size; i++) {
    		for(int j = 0; j < size; j++) {
    			check(new BoardCell(i,j),lex,board,board.getFace(i,j));
    		}
    	}
    	return word;
        // IMPLEMENT ME!
    	// Looks in the Boggle board and finds all words that can be formed
    	// (given the lexicon), using a recursive procedure.
        
        // For now, we simply return an empty list. Thus, the program
        // will not actually find any words on the board for now. Replace the 
        // following return statement once you complete your method definition.
    }
    
}
