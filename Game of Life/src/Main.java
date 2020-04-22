import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		GameOfLife game = new GameOfLife(6,6);
		System.out.println("Welcome to the Game Of Life\n"
				+ "To create a new generation press N and confirm with ENTER\n"
				+ "To quit the program press any other key and confirm with ENTER\n");
		game.newGeneration();
		String controller = input.nextLine();
		
		while(controller.equals("n")) {
			controller = input.nextLine();
			game.newGeneration();
		}
		
		System.out.println("Program terminated");
		input.close();

	}

}
