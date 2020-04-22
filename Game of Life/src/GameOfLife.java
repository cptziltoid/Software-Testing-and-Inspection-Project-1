import java.util.Random;

public class GameOfLife {

	//Attributes
	//holds the cell values of the previous generation
	private int[][] OldTable;
	//holds the cell values of the current generation
	private int[][] NewTable;
	private int Rows;
	private int Columns;
	//number of the current generation (starts at 0)
	private int Generation;
	
	//Constructors
	public GameOfLife(int rows, int columns) {
		Rows = rows;
		Columns = columns;
		Generation = 0;
		
		Random rand = new Random();
		OldTable = new int[rows][columns];
		NewTable = new int[rows][columns];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				NewTable[i][j] = rand.nextInt(2);
				OldTable[i][j] = -1;
			}
		}
		
	}

	//Methods
	public int getCols() {
		return Columns;
	}
	
	public void setCols(int cols) {
		Columns = cols;
	}
	
	public int getRows() {
		return Rows;
	}
	
	public void setRows(int rows) {
		Rows = rows;
	}
	
	private void printOldTable() {
        for(int i = 0; i < Rows; i++) {
        	for(int j = 0; j < Columns; j++) {
        		System.out.print(OldTable[i][j] + " "); 
        	}
        	System.out.print("\n");
        }   
	}
	
	private void printNewTable() {
        for(int i = 0; i < Rows; i++) {
        	for(int j = 0; j < Columns; j++) {
        		System.out.print(NewTable[i][j] + " "); 
        	}
        	System.out.print("\n");
        }   
	}
	
	//Prints the number of the current generation and the cells for immediate previous and current generation
	public void printGeneration() {
		System.out.print("Generation: " + Generation + "\n\nPrevious Table:\n");
		printOldTable();
		System.out.print("\nCurrent Table:\n");
		printNewTable();
		System.out.print("\n\n");
	}
	
	//checks if a cell is alive
	private boolean isAlive(int i, int j) {
		if(OldTable[i][j] == 1)
			return true;
		else
			return false;	
	}
	
	//"kills" a cell; changes its value from 1 to 0
	private void kill(int i, int j) {
		NewTable[i][j] = 0;
	}
	
	//"revives" a cell; changes its value from 0 to 1
	private void revive(int i, int j) {
		NewTable[i][j] = 1;
	}
	
	//maintains a cell in its current state
	private void live(int i, int j) {
		NewTable[i][j] = OldTable[i][j];
	}
	
	//returns how many neighbors a cell has
	private int checkNeighbors(int i, int j) {
		int neighbors = 0;
		//Up
		if(i != 0 && OldTable[i - 1][j] == 1)
			neighbors++;
		//Down
		if(i < (Rows - 1) && OldTable[i + 1][j] == 1)
			neighbors++;
		//Left
		if(j != 0 && OldTable[i][j - 1] == 1)
			neighbors++;
		//Right
		if(j < (Columns - 1) && OldTable[i][j + 1] == 1)
			neighbors++;
		//Up and Left
		if(i != 0 && j != 0 && OldTable[i - 1][j - 1] == 1)
			neighbors++;
		//Down and Right
		if(i < (Rows - 1) && j < (Columns - 1) && OldTable[i + 1][j + 1] == 1)
			neighbors++;
		//Up and Right
		if(i != 0 && j < (Columns - 1) && OldTable[i - 1][j + 1] == 1)
			neighbors++;
		//Down and Left
		if(i < (Rows - 1) && j != 0 && OldTable[i + 1][j - 1] == 1)
			neighbors++;
		
		return neighbors;
	}
	
	//returns true if a cell is in a solitary state (has less than 2 neighbors)
	private boolean checkSolitude(int i, int j) {
		int neighbors = checkNeighbors(i, j);
		if(neighbors < 2)
			return true;
		else 
			return false;
	}
	
	//returns true if a cell suffers from overcrowding (has more than 3 neighbors)
	private boolean checkOverpopulation(int i, int j) {
		int neighbors = checkNeighbors(i, j);
		if(neighbors > 3)
			return true;
		else 
			return false;
	}
	
	//returns true if a cell can be "revived" (has 3 neighbors)
	private boolean checkRevive(int i, int j) {
		int neighbors = checkNeighbors(i, j);
		if(neighbors == 3)
			return true;
		else 
			return false;
	}
	
	//function that makes the necessary operations to evolve a new generation of cells
	public void newGeneration() {
		printGeneration();
		for(int i = 0; i < Rows; i++) {
			for(int j = 0; j < Columns; j++) {
				OldTable[i][j] = NewTable[i][j]; 
			}
		}
		
		for(int i = 0; i < Rows; i++) {
			for(int j = 0; j < Columns; j++) {
				if(isAlive(i, j)) {
					if(checkSolitude(i, j) || checkOverpopulation(i, j))
						kill(i, j);
					else live(i, j);
				}
				else {
					if(checkRevive(i, j))
						revive(i, j);
					else live(i, j);
				}
			}
		}
		Generation++;
	}
		
}
