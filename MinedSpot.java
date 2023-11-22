package minesweeper;

class MinedSpot extends MineSpot {

	public MinedSpot(int xLoc, int yLoc) {
		super(xLoc, yLoc);
		hasMine = true;
		
	}
	
	public void openSpot() {
		opened = true;
		icon = 'x';
	}

}
