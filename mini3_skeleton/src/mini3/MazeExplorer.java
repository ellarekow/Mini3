
package mini3;

import api.CellStatus;
import api.GridCell;
import api.TwoDGrid;

/**
 * Utility class for searching a maze described by a collection
 * of GridCell objects.
 */
public class MazeExplorer
{
  /**
   * Recursively searches a given GridCell and all of its unexplored 
   * neighbors.  Immediately returns true if the current cell is the goal;
   * otherwise,  immediately returns false if the current cell is a wall 
   * or if the current cell's status is anything other than NOT_STARTED.
   * Otherwise, initiates a recursive search of the neighbors of the current cell.
   * If any search of a neighbor results in the goal being found, the current cell's status
   * is set to CellStatus.FOUND_IT and the method immediately returns true.  If 
   * no search of a neighbor results in the goal being found, the current cell's 
   * status is set to DEAD_END and the method returns false.
   * <p>
   * <strong>Neighbors are always searched in the order up, down, left, then right,</strong>
   * and during a recursive search of a neighbor, the current cell's status
   * is set to SEARCHING_UP, SEARCHING_DOWN, SEARCHING_LEFT, or SEARCHING_RIGHT, 
   * respectively.
   * 
   * @param maze
   *   the 2d grid to be searched
   * @param row
   *   the row for the current cell being searched
   * @param col
   *   the column for the current cell being searched
   * @return
   *   true if a search from the current cell has reached the goal,
   *   false if the goal can't be reached from the current cell
   */
  public static boolean search(TwoDGrid maze, int row, int col)
  {
	if(maze.getCell(row, col).isGoal())
		return true;
	
	else if(maze.getCell(row, col).getStatus() != CellStatus.NOT_STARTED || maze.getCell(row, col).isWall())
		return false; 
	
	else {
		maze.setCellVal(row, col, CellStatus.SEARCHING_UP); //searching up 
		if(search(maze, row - 1, col)) {
			maze.setCellVal(row, col, CellStatus.FOUND_IT);
			return true;
		}
		
		maze.setCellVal(row, col, CellStatus.SEARCHING_DOWN); //searching down 
		if(search(maze, row + 1, col)) {
			maze.setCellVal(row, col, CellStatus.FOUND_IT);
			return true;
		}
		
		maze.setCellVal(row, col, CellStatus.SEARCHING_LEFT); //searching left 
		if(search(maze, row, col - 1)) {
			maze.setCellVal(row, col, CellStatus.FOUND_IT);
			return true;
		}
		
		maze.setCellVal(row, col, CellStatus.SEARCHING_RIGHT); //searching right 
		if(search(maze, row, col + 1)) {
			maze.setCellVal(row, col, CellStatus.FOUND_IT);
			return true;
		}
	}
	maze.setCellVal(row, col, CellStatus.DEAD_END);
    return false;
  }
}
