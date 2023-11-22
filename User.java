package minesweeper;

class User implements Comparable<User> {
	private String name;
	private int score;

	
	public User(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	

	@Override
	public int compareTo(User other) {
		if(this.score > other.score) {
			return 1;
		} else if(this.score < other.score) {
			return -1;
		} else {
			return 0;
		}
	}
}
