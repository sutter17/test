
import java.util.*;

/** ADD ME! */
/**
 * @author Sutter Phillips
 * @author Khalil Adams
 */
public class WordOnBoardFinder implements IWordOnBoardFinder {

	List <BoardCell> spots = new ArrayList<BoardCell>();
	BoggleBoard board = null;
	int size; 

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
	 * @param cell checks  the path down given cell
	 * @param board board on which to check
	 * @param word word to check
	 * @return whether given word can be made down the path of the cell
	 */
	public boolean check(BoardCell cell, BoggleBoard board, String word) {
		spots.add(cell);
		if(word.length()==0) {
			return true;
		}
		List<BoardCell> neighbors = neighbors(cell,board.size());
		for(BoardCell nextCell : neighbors) {
			if(word.substring(0,1).equals(board.getFace(nextCell))){
				if(check(nextCell, board, word.substring(1))) {
					return true;
				}
			}
			if(word.length()>1) {
			if(word.substring(0,2)=="qu"&&board.getFace(nextCell)=="qu") {
				if(check(nextCell, board, word.substring(2))) {
					return true;
				}
			}
			}
		}spots.remove(cell);
		return false;
		}

	/* (non-Javadoc)
	 * @see IWordOnBoardFinder#cellsForWord(BoggleBoard, java.lang.String)
	 */
	public List<BoardCell> cellsForWord(BoggleBoard board, String word) {
		spots.clear();
		int size = board.size();
    	for(int i = 0; i < size; i++) {
    		for(int j = 0; j < size; j++) {
    			if(word.substring(0,2).equals("qu")&&board.getFace(i,j).equals("qu")) {
    				if(check(new BoardCell(i,j),board,word.substring(2))) {
    					return spots;
    				}
    			}
    			if(word.substring(0,1).equals(board.getFace(i, j))) {
    				if(check(new BoardCell(i,j),board,word.substring(1))) {
    					return spots;
    				}else {
    					spots.clear();
    				}
    			}
    		}
    	}
    
    	return spots;
	}


}

