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
		this.Rows = rows;
		this.Columns = columns;
		this.Generation = 0;
		
		Random rand = new Random();
		OldTable = new int[rows][columns];
		NewTable = new int[rows][columns];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				this.NewTable[i][j] = rand.nextInt(2);
				this.OldTable[i][j] = -1;
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
        for(int i = 0; i < this.Rows; i++) {
        	for(int j = 0; j < this.Columns; j++) {
        		System.out.print(this.OldTable[i][j] + " "); 
        	}
        	System.out.print("\n");
        }   
	}
	
	private void printNewTable() {
        for(int i = 0; i < this.Rows; i++) {
        	for(int j = 0; j < this.Columns; j++) {
        		System.out.print(this.NewTable[i][j] + " "); 
        	}
        	System.out.print("\n");
        }   
	}
	
	//Prints the number of the current generation and the cells for immediate previous and current generation
	public void printGeneration() {
		System.out.print("Generation: " + this.Generation + "\n\nPrevious Table:\n");
		this.printOldTable();
		System.out.print("\nCurrent Table:\n");
		this.printNewTable();
		System.out.print("\n\n");
	}
	
	//checks if a cell is alive
	private boolean isAlive(int i, int j) {
		if(this.OldTable[i][j] == 1)
			return true;
		else
			return false;	
	}
	
	//"kills" a cell; changes its value from 1 to 0
	private void kill(int i, int j) {
		this.NewTable[i][j] = 0;
	}
	
	//"revives" a cell; changes its value from 0 to 1
	private void revive(int i, int j) {
		this.NewTable[i][j] = 1;
	}
	
	//maintains a cell in its current state
	private void live(int i, int j) {
		this.NewTable[i][j] = this.OldTable[i][j];
	}
	
	//returns how many neighbors a cell has
	private int checkNeighbors(int i, int j) {
		int neighbors = 0;
		if(i != 0 && this.OldTable[i - 1][j] == 1)
			neighbors++;
		if(i < (this.Rows - 1) && this.OldTable[i + 1][j] == 1)
			neighbors++;
		if(j != 0 && this.OldTable[i][j - 1] == 1)
			neighbors++;
		if(j < (this.Columns - 1) && this.OldTable[i][j + 1] == 1)
			neighbors++;
		
		return neighbors;
	}
	
	//returns true if a cell is in a solitary state (has less than 2 neighbors)
	private boolean checkSolitude(int i, int j) {
		int neighbors = this.checkNeighbors(i, j);
		if(neighbors < 2)
			return true;
		else 
			return false;
	}
	
	//returns true if a cell suffers from overcrowding (has more than 3 neighbors)
	private boolean checkOverpopulation(int i, int j) {
		int neighbors = this.checkNeighbors(i, j);
		if(neighbors > 3)
			return true;
		else 
			return false;
	}
	
	//returns true if a cell can be "revived" (has 3 neighbors)
	private boolean checkRevive(int i, int j) {
		int neighbors = this.checkNeighbors(i, j);
		if(neighbors == 3)
			return true;
		else 
			return false;
	}
	
	//function that makes the necessary operations to evolve a new generation of cells
	public void newGeneration() {
		this.printGeneration();
		for(int i = 0; i < Rows; i++) {
			for(int j = 0; j < Columns; j++) {
				this.OldTable[i][j] = this.NewTable[i][j]; 
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
		this.Generation++;
	}
		
}
