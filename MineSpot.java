package minesweeper;


class MineSpot {

	protected int x;
	protected int y;
	protected boolean hasMine;
	protected boolean opened;
	protected boolean flagged;
	protected char icon;
	protected MineSpot[] mineNeighbors;
	protected int numMineNeighbors;
	
	public MineSpot(int xLoc, int yLoc) {
		hasMine = false;
		opened = false;
		flagged = false;
		icon = '@';
		x = xLoc;
		y = yLoc;
		mineNeighbors = new MineSpot[8];
		numMineNeighbors = 0;
		
	}
	
	public void printSpot() {
		System.out.print(icon);
	}
	
	public void openSpot() {
		opened = true;
		if(flagged) {
			System.out.println("Spot flagged. Cannot open. ");
		} else {
			if(numMineNeighbors == 0) {
				icon = ' ';	
				for (int i=0; i<8; i++) {
					if(mineNeighbors[i] != null && !mineNeighbors[i].isOpen()) {
						mineNeighbors[i].openSpot();
					}
					
				}
			} else {
				icon = (char) (numMineNeighbors + '0');
			}
		}
		
		
	}
	
	public void unflagSpot() {
		if(flagged) {
			flagged = false;
			icon = '@';
		}
	}
	
	public void flagSpot() {
		if(!opened) {
			flagged = true;
			icon = '*';
		}
		
	}
	
	public boolean isMine() {
		return hasMine;
	}
	
	public boolean isOpen() {
		return opened;
	}
	
	public boolean isFlagged() {
		return flagged;
	}
	
	public void setNeighbors(int i, MineSpot s) {
		mineNeighbors[i] = s;
		
		if(s != null) {
			if(s.isMine()) {
				numMineNeighbors++;
			}
		}
	}
	
	
}
