/**
 * Started 2/15/2023
 * Updated 11/21/23 - Added leaderboard/ranking
 */
package minesweeper;
import java.util.*; 
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * @author mahru
 *
 */
public class Minesweeper {

	/**
	 * @param args
	 */
	
	static ArrayList<User> users = new ArrayList<>();
	static File scores;
	
	public static void main(String[] args) throws IOException {
		MineField minefield;
		int l, w;
		int x, y;
		scores = new File("scores.txt");
		getPlayers();
		Collections.sort(users, Collections.reverseOrder()); 
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Hello. Welcome to Minesweeper.");
		System.out.println("Enter the dimensions of your minefield.");
		l = sc.nextInt();
		w = sc.nextInt();
		minefield = new MineField(l, w);
		
		System.out.println("There are " + minefield.getMines() + " mines in the field.");
		minefield.printField();
		long start = System.currentTimeMillis();
		
		while(!minefield.ifLost()) {
			
			System.out.println("Select a spot to open by its location (ex: 1 1 is top left corner)");
			x = sc.nextInt();
			y = sc.nextInt();
			minefield.openASpot(x-1, y-1);
			
			/**
			
			String option;
			System.out.println("Press o to open, press f to flag, press u to unflag");
			option = sc.next();
			
			if(option.equals("o")) {
				System.out.println("Select a spot to open by its location (ex: 1 1 is top left corner)");
				x = sc.nextInt();
				y = sc.nextInt();
				minefield.openASpot(x-1, y-1);
				
			} else if(option.equals("f")) {
				System.out.println("Select a spot to flag by its location (ex: 1 1 is top left corner)");
				x = sc.nextInt();
				y = sc.nextInt();
				minefield.flagASpot(x-1, y-1);
				
			} else if(option.equals("u")){
				
			}
			
			
			**/
			
			
			minefield.printField();	
			
			if(minefield.ifWon()) {
				long elapsed = System.currentTimeMillis() - start;
				int score = (int) (1000000 / elapsed) * l * w;
				String name;
				System.out.println("Congratulations! You won!");
				System.out.println("Enter your name: ");
				name = sc.next();
				User user = searchForUser(name);
				if(user == null) {
					users.add(new User(name, score));
				} else {
					user.setScore(score);
				}
				Collections.sort(users, Collections.reverseOrder()); 
				user = searchForUser(name);
				int rank = users.indexOf(user) + 1;
				System.out.println("Your rank is " + rank + " out of " + users.size() + " players.");
				FileWriter fd = new FileWriter(scores);
				
				for(User player : users) {
					fd.write(player.getName());
					fd.write(" ");
					fd.write(Integer.toString(player.getScore()));
					fd.write("\n");
				}
				
				fd.close();
				break;
			}
		}
		
		sc.close();
		
		
		if(minefield.ifLost()) {
			System.out.println("Game over :( ");
		}
		
		

	}
	
	public static void getPlayers() throws FileNotFoundException {
		Scanner fs = new Scanner(scores);
		while(fs.hasNext()) {
			String username = fs.next();
			int userScore = fs.nextInt();
			users.add(new User(username, userScore));
			
		}
		fs.close();
	}
	
	public static User searchForUser(String username) {
		User myUser = null;
		for(int i=0; i<users.size(); i++) {
			if(users.get(i).getName().equals(username)) {
				myUser = users.get(i);
			}
		}
		
		return myUser;
	}
	
	
}
