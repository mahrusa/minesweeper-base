/**
 * 
 */
package minesweeper;
import java.util.ArrayList;

/**
 * @author mahru
 *
 */
class MineField {
	
	private MineSpot[][] field;
	private int l;
	private int w;
	private int numOfMines;
	private boolean lost;
	private boolean won;
	private ArrayList<MineSpot> mines;
	
	public MineField(int length, int width) {
		
		//create minefield of specified size
		field = new MineSpot[length][width];
		l = length;
		w = width;
		numOfMines = 0;
		lost = false;
		won = false;
		mines = new ArrayList<MineSpot>();
		
		
		for(int i=0; i<l; i++) {
			for(int j=0; j<w; j++) {
				
				//initialize mines randomly (15% probability)
				double num = Math.random();
				if(num<0.15) {
					field[i][j] = new MinedSpot(i, j);
					mines.add(field[i][j]);
					numOfMines++;
				} else {
					field[i][j] = new MineSpot(i, j);
				}
				
				
				
			}
		}
		
		for(int i=0; i<l; i++) {
			for(int j=0; j<w; j++) {
//set neighbors of each spot (8 neighbors around)
				
				if(i>0) {
					if(j>0) {
						field[i][j].setNeighbors(0, field[i-1][j-1]);
					}
					field[i][j].setNeighbors(1, field[i-1][j]);
					if(j<w-1) {
						field[i][j].setNeighbors(2, field[i-1][j+1]);
					}
					
				}
				
				if(j>0) {
					field[i][j].setNeighbors(3, field[i][j-1]);
				}
				if(j<w-1) {
					field[i][j].setNeighbors(4, field[i][j+1]);
				}
				
				
				if(i<l-1) {
					if(j>0) {
						field[i][j].setNeighbors(5, field[i+1][j-1]);
					}
					field[i][j].setNeighbors(6, field[i+1][j]);
					if(j<w-1) {
						field[i][j].setNeighbors(7, field[i+1][j+1]);
					}
					
				}
				
				
			}
		}
		
		
	}
	
	public int getMines() {
		return numOfMines;
	}
	
	public void printField() {
		if(lost) {
			for(int i=0; i<numOfMines; i++) {
				mines.get(i).openSpot();
			}
		}
		
		for(int i=0; i<l; i++) {
			for(int j=0; j<w; j++) {
				field[i][j].printSpot();
			}
			System.out.print("\n");
		}
	}
	
	public void openASpot(int x, int y) {
		field[x][y].openSpot();
		
		if(field[x][y].isMine()) {
			lost = true;
		}
		
		int count = 0;
		for(int i=0; i<l; i++) {
			for(int j=0; j<w; j++) {
				if(field[i][j].isOpen() && !field[i][j].hasMine) {
					count++;
				}
			}
		}
		
		if(count == (l*w)-numOfMines) {
			won = true;
		}
	}
	
	public void flagASpot(int x, int y) {
		field[x][y].flagSpot();
	}
	
	public void unflagASpot(int x, int y) {
		field[x][y].unflagSpot();
	}
	
	public boolean ifLost() {
		return lost;
	}
	
	public boolean ifWon() {
		return won;
	}
	
	

}
